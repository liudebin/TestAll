package qian.ling.yi.work;

import org.junit.Test;
import org.springframework.util.StopWatch;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;


/**
 * TODO
 *
 * @date: 2018/11/7.
 * @author: guobin.liu@holaverse.com
 */

public class ExtFacadeMockUtil {
    static StopWatch stopWatch = new StopWatch();

    @Test
    public void extFacadeToMock() {
        String path = "D:/ideaProjects/rfs/rfs-app/src/main/java/com/qihoo/finance/rfs/modules/external/impl";
        List<File> facadeList = getFacadeOrImplList(path, "FacadeImpl");

//        facadeList.forEach(System.out::println);
//        File fin = new File("D:/ideaProjects/rds/rds-api/src/main/java/com/qihoo/finance/rds/modules/credible/facade/CredibleFacade.java");//读取的文件
//        file2Rest(fin);
        facadeList.forEach(ExtFacadeMockUtil::file2Mock);

        System.out.println(stopWatch.prettyPrint());
    }


    public static List<File> getFacadeOrImplList(String path, String keyWord) {
        List<File> facadeOrImpl = new ArrayList<>();

        File file = new File(path);
        File[] files = file.listFiles();
        if (file.exists() && null != files) {
            Arrays.stream(files)
                    .filter(File::isDirectory)
                    .forEach(file2 -> {
                                collectFacadeOrImplFromDir(file2, keyWord).ifPresent(facadeOrImpl::addAll);
                                facadeOrImpl.addAll(getFacadeOrImplList(file2.getAbsolutePath(), keyWord));
                            }
                    );
        }
        return facadeOrImpl;
    }

    private static Optional<List<File>> collectFacadeOrImplFromDir(File file, String key) {
        if (file.exists()
                && file.isDirectory()) {

            File[] files = file.listFiles();

            if (null != files) {
                List<File> facades = Arrays.stream(files)
                        .filter(f -> f.getName().endsWith(key + ".java")
                                && !f.getName().contains("Ext")
                        )
                        .collect(Collectors.toList());
                return Optional.of(facades);
            }
        }
        return Optional.empty();
    }


    static Optional<String> addRequestAnnotation(String str) {
        if (str.contains("(")) {
            String[] split = str.substring(0, str.indexOf("("))
                    .trim()
                    .split(" ");
            StringBuilder sb = new StringBuilder("\t@RequestMapping(\"/");
            if (split.length > 1) {
                sb.append(split[split.length - 1]).append("\")");
                return Optional.of(sb.toString());
            }
        }
        return Optional.empty();
    }

    static Optional<String> addRestAnnotation(String str) {
        return Optional.of("@RestController");
    }

    static void file2Mock(File file) {
        stopWatch.start(file.getName());
        int bufSize = 1000000;//一次读取的字节长度
        ByteBuffer rBuffer = ByteBuffer.allocate(bufSize);
        try (FileChannel fcin = new RandomAccessFile(file, "r").getChannel()) {
            readFileByLine(fcin, rBuffer,(enter, line) -> {

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        stopWatch.stop();
    }


    public static void readFileByLine(FileChannel fcin,
                                      ByteBuffer rBuffer,
                                      BiConsumer<String, String> addMock) {
        String enter = "\n";
        byte[] lineByte;

        String encode = "UTF-8";
        try {
            byte[] temp = new byte[0];
            while (fcin.read(rBuffer) != -1) {
                int rSize = rBuffer.position();
                byte[] bs = new byte[rSize];
                rBuffer.rewind();
                rBuffer.get(bs);
                rBuffer.clear();

                int startNum = 0;
                int LF = 10;//换行符
                int CR = 13;//回车符
                boolean hasLF = false;//是否有换行符
                for (int i = 0; i < rSize; i++) {
                    if (bs[i] == LF) {
                        hasLF = true;
                        int tempNum = temp.length;
                        int lineNum = i - startNum;
                        lineByte = new byte[tempNum + lineNum];

                        System.arraycopy(temp, 0, lineByte, 0, tempNum);//填充了lineByte[0]~lineByte[tempNum-1]
                        temp = new byte[0];
                        System.arraycopy(bs, startNum, lineByte, tempNum, lineNum);//填充lineByte[tempNum]~lineByte[tempNum+lineNum-1]

                        String line = new String(lineByte, 0, lineByte.length, encode);//一行完整的字符串(过滤了换行和回车)

                        addMock.accept(enter, line);

                        //过滤回车符和换行符
                        if (i + 1 < rSize && bs[i + 1] == CR) {
                            startNum = i + 2;
                        } else {
                            startNum = i + 1;
                        }

                    }
                }
                if (hasLF) {
                    temp = new byte[bs.length - startNum];
                    System.arraycopy(bs, startNum, temp, 0, temp.length);
                } else {//兼容单次读取的内容不足一行的情况
                    byte[] toTemp = new byte[temp.length + bs.length];
                    System.arraycopy(temp, 0, toTemp, 0, temp.length);
                    System.arraycopy(bs, 0, toTemp, temp.length, bs.length);
                    temp = toTemp;
                }
            }
            if (temp.length > 0) {//兼容文件最后一行没有换行的情况
                String line = new String(temp, 0, temp.length, encode);
                addMock.accept(enter, line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写到文件上
     *
     * @param fcout
     * @param wBuffer
     * @param line
     */
    public static void writeFileByLine(FileChannel fcout, ByteBuffer wBuffer,
                                       String line) {
        try {
            fcout.write(wBuffer.wrap(line.getBytes("UTF-8")), fcout.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


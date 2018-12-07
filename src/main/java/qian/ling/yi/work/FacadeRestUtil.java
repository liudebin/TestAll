package qian.ling.yi.work;

import org.junit.Test;
import org.springframework.util.StopWatch;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;


/**
 * FacadeRestUtil
 *
 * @date: 2018/11/7.
 * @author: guobin.liu@holaverse.com
 */

public class FacadeRestUtil {
    static StopWatch stopWatch = new StopWatch();

    @Test
    public void facadeToRest() {
//        String path = "D:/ideaProjects/rds/rds-api";
        String path = "D:/ideaProjects/rds";
        List<File> facadeList = getFacadeOrImplList(path, "Facade");

//        facadeList.forEach(System.out::println);
//        File fin = new File("D:/ideaProjects/rds/rds-api/src/main/java/com/qihoo/finance/rds/modules/credible/facade/CredibleFacade.java");//读取的文件
//        file2Rest(fin);
        facadeList.forEach(FacadeRestUtil::file2Rest);

        System.out.println(stopWatch.prettyPrint());
    }

    @Test
    public void facadeImplToRest() {
//        String path = "D:/ideaProjects/rds/rds-app";
        String path = "D:/ideaProjects/rds";
        List<File> facadeList = getFacadeOrImplList(path, "FacadeImpl");
//
//        facadeList.forEach(System.out::println);
//
        facadeList.forEach(FacadeRestUtil::fileImpl2Rest);
//
//        System.out.println(stopWatch.prettyPrint());

//        fileImpl2Rest(new File("D:\\ideaProjects\\rds\\rds-app\\src\\main\\java\\com\\qihoo\\finance\\rds\\modules\\blacklist\\facade\\BlackListFacadeImpl.java"));
    }



    public static List<File> getFacadeOrImplList(String path, String keyWord) {
        List<File> facadeOrImpl = new ArrayList<>();

        File file = new File(path);
        File[] files = file.listFiles();
        if (file.exists() && null != files ) {
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
                        .filter(f -> f.getName().endsWith(key+".java")
                                &&!f.getName().contains("Ext")
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

    static  void  file2Rest (File file){
        stopWatch.start(file.getName());
        int bufSize = 1000000;//一次读取的字节长度
        File fout = new File(file.getAbsolutePath() + ".tmp");//写入的文件
        ByteBuffer rBuffer = ByteBuffer.allocate(bufSize);
        ByteBuffer wBuffer = ByteBuffer.allocateDirect(bufSize);
        try(FileChannel fcin = new RandomAccessFile(file, "r").getChannel();
            FileChannel fcout =  new FileOutputStream(fout,false).getChannel()) {
            readWriteFileByLine(fcin, rBuffer, fcout, wBuffer, (enter, line) -> {
                if (line.contains("(")) {
                    addRequestAnnotation(line)
                            .ifPresent(s -> writeFileByLine(fcout, wBuffer, s + enter));
                }
            }, (enter, line) -> {
                if (line.contains("package")) {
                    writeFileByLine(fcout, wBuffer, "import org.springframework.web.bind.annotation.RequestMapping;" + enter);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        file.delete();
        fout.renameTo(file);
        stopWatch.stop();
    }

    static  void  fileImpl2Rest (File file){
        stopWatch.start(file.getName());
        int bufSize = 1000000;//一次读取的字节长度
//        File fin = new File("D:/ideaProjects/rds/rds-api/src/main/java/com/qihoo/finance/rds/modules/credible/facade/CredibleFacade.java");//读取的文件
        File fout = new File(file.getAbsolutePath() + ".tmp");//写入的文件
        ByteBuffer rBuffer = ByteBuffer.allocate(bufSize);
        ByteBuffer wBuffer = ByteBuffer.allocateDirect(bufSize);
        try(FileChannel fcin = new RandomAccessFile(file, "r").getChannel();
            FileChannel fcout =  new FileOutputStream(fout,false).getChannel()) {
            readWriteFileByLine(fcin, rBuffer, fcout, wBuffer, (enter, line) -> {
                if (line.contains("public class")) {
                    addRestAnnotation(line)
                            .ifPresent(s -> writeFileByLine(fcout, wBuffer, s + enter));
                }
                printHasServiceAnnotation(line, file.getName());
            }, (enter, line) -> {
                if (line.contains("package")) {
                    writeFileByLine(fcout, wBuffer, "import org.springframework.web.bind.annotation.RestController;" + enter);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        file.delete();
        fout.renameTo(file);
        stopWatch.stop();
    }


    static void printHasServiceAnnotation(String line, String name) {
        line = line.trim();
        if (line.contains("@Service")) {
                System.out.println(name);
        }
    }

    /**
     * NIO读取百万级别文件
     * @author Chillax
     *
     */
        public static void main(String args[]) throws Exception {

            int bufSize = 1000000;//一次读取的字节长度
            File fin = new File("D:/ideaProjects/rds/rds-api/src/main/java/com/qihoo/finance/rds/modules/credible/facade/CredibleFacade.java");//读取的文件
            File fout = new File("D:/ideaProjects/rds/rds-api/src/main/java/com/qihoo/finance/rds/modules/credible/facade/CredibleFacade1.java");//写入的文件
            Date startDate = new Date();
            FileChannel fcin = new RandomAccessFile(fin, "r").getChannel();
            ByteBuffer rBuffer = ByteBuffer.allocate(bufSize);

            FileChannel fcout = new RandomAccessFile(fout, "rws").getChannel();
            ByteBuffer wBuffer = ByteBuffer.allocateDirect(bufSize);

            readWriteFileByLine(fcin, rBuffer, fcout, wBuffer,  (enter, line) -> {
                if (line.contains("package")) {
                    writeFileByLine(fcout, wBuffer, "import org.springframework.web.bind.annotation.RequestMapping;" + "\n");
                }
            }, (enter, line) -> {
                if (line.contains("package")) {
                    writeFileByLine(fcout, wBuffer, "import org.springframework.web.bind.annotation.RequestMapping;" + enter);
                }
            });
            Date endDate = new Date();

            System.out.print(startDate+"|"+endDate);//测试执行时间
            if(fcin.isOpen()){
                fcin.close();
            }
            if(fcout.isOpen()){
                fcout.close();
            }
            fin.delete();
            fout.renameTo(new File("D:/ideaProjects/rds/rds-api/src/main/java/com/qihoo/finance/rds/modules/credible/facade/CredibleFacade.java"));

        }

        public static void readWriteFileByLine(FileChannel fcin,
                                               ByteBuffer rBuffer, FileChannel fcout, ByteBuffer wBuffer, BiConsumer<String, String> addAnnotation, BiConsumer<String, String> addImport) {
            String enter = "\n";
            byte[] lineByte;

		    String encode = "UTF-8";
            try {
                //temp：由于是按固定字节读取，在一次读取中，第一行和最后一行经常是不完整的行，因此定义此变量来存储上次的最后一行和这次的第一行的内容，
                //并将之连接成完成的一行，否则会出现汉字被拆分成2个字节，并被提前转换成字符串而乱码的问题
                byte[] temp = new byte[0];
                while (fcin.read(rBuffer) != -1) {//fcin.read(rBuffer)：从文件管道读取内容到缓冲区(rBuffer)
                    int rSize = rBuffer.position();//读取结束后的位置，相当于读取的长度
                    byte[] bs = new byte[rSize];//用来存放读取的内容的数组
                    rBuffer.rewind();//将position设回0,所以你可以重读Buffer中的所有数据,此处如果不设置,无法使用下面的get方法
                    rBuffer.get(bs);//相当于rBuffer.get(bs,0,bs.length())：从position初始位置开始相对读,读bs.length个byte,并写入bs[0]到bs[bs.length-1]的区域
                    rBuffer.clear();

                    int startNum = 0;
                    int LF = 10;//换行符
                    int CR = 13;//回车符
                    boolean hasLF = false;//是否有换行符
                    for(int i = 0; i < rSize; i++){
                        if(bs[i] == LF) {
                            hasLF = true;
                            int tempNum = temp.length;
                            int lineNum = i - startNum;
                            lineByte = new byte[tempNum + lineNum];//数组大小已经去掉换行符

                            System.arraycopy(temp, 0, lineByte, 0, tempNum);//填充了lineByte[0]~lineByte[tempNum-1]
                            temp = new byte[0];
                            System.arraycopy(bs, startNum, lineByte, tempNum, lineNum);//填充lineByte[tempNum]~lineByte[tempNum+lineNum-1]

                            String line = new String(lineByte, 0, lineByte.length, encode);//一行完整的字符串(过滤了换行和回车)

                            addAnnotation.accept(enter, line);
                            writeFileByLine(fcout, wBuffer, line + enter);
                            addImport.accept(enter, line);

                            //过滤回车符和换行符
                            if (i + 1 < rSize && bs[i + 1] == CR) {
                                startNum = i + 2;
                            } else {
                                startNum = i + 1;
                            }

                        }
                    }
                    if(hasLF){
                        temp = new byte[bs.length - startNum];
                        System.arraycopy(bs, startNum, temp, 0, temp.length);
                    }else{//兼容单次读取的内容不足一行的情况
                        byte[] toTemp = new byte[temp.length + bs.length];
                        System.arraycopy(temp, 0, toTemp, 0, temp.length);
                        System.arraycopy(bs, 0, toTemp, temp.length, bs.length);
                        temp = toTemp;
                    }
                }
                if(temp.length > 0){//兼容文件最后一行没有换行的情况
                    String line = new String(temp, 0, temp.length, encode);
                    addAnnotation.accept(enter, line);
                    writeFileByLine(fcout, wBuffer, line + enter);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * 写到文件上
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


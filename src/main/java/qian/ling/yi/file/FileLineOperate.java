package qian.ling.yi.file;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>文件读取类</b><br />
 * 1、按字节读取文件内容<br />
 * 2、按字符读取文件内容<br />
 * 3、按行读取文件内容<br />
 * Created by liuguobin on 2016/12/23.
 */
public class FileLineOperate extends AbstractTest {
        private static final String FILE_PATH = "d:/work/jipinwodi.txt";

        /**
         * 以字节为单位读写文件内容
         *
         * @param filePath
         *            ：需要读取的文件路径
         */
        public static void readFileByByte(String filePath) {
            File file = new File(filePath);
            // InputStream:此抽象类是表示字节输入流的所有类的超类。
            InputStream ins = null;
            OutputStream outs = null;
            try {
                // FileInputStream:从文件系统中的某个文件中获得输入字节。
                ins = new FileInputStream(file);
                outs = new FileOutputStream("/Users/liuguobin/IdeaProjects/pay/src/main/resources/interface/kq/file/test");
                int temp;
                // read():从输入流中读取数据的下一个字节。
                while ((temp = ins.read()) != -1) {
                    outs.write(temp);
                }
            } catch (Exception e) {
                e.getStackTrace();
            } finally {
                if (ins != null && outs != null) {
                    try {
                        outs.close();
                        ins.close();
                    } catch (IOException e) {
                        e.getStackTrace();
                    }
                }
            }
        }

        /**
         * 以字符为单位读写文件内容
         *
         * @param filePath
         */
        public static void readFileByCharacter(String filePath) {
            File file = new File(filePath);
            // FileReader:用来读取字符文件的便捷类。
            FileReader reader = null;
            FileWriter writer = null;
            try {
                reader = new FileReader(file);
                writer = new FileWriter("/Users/liuguobin/IdeaProjects/pay/src/main/resources/interface/kq/file/test");
                int temp;
                while ((temp = reader.read()) != -1) {
                    writer.write((char)temp);
                }
            } catch (IOException e) {
                e.getStackTrace();
            } finally {
                if (reader != null && writer != null) {
                    try {
                        reader.close();
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        /**
         * 以行为单位读写文件内容
         *
         * @param filePath
         */
        public static void readFileByLine(String filePath) {
            File file = new File(filePath);
            // BufferedReader:从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。
            BufferedReader bufReader = null;
            BufferedWriter bufWriter = null;
            try {
                // FileReader:用来读取字符文件的便捷类。
                bufReader = new BufferedReader(new FileReader(file));
                bufWriter = new BufferedWriter(new FileWriter("/Users/liuguobin/IdeaProjects/pay/src/main/resources/interface/kq/file/test"));
                // buf = new BufferedReader(new InputStreamReader(new
                // FileInputStream(file)));
                String temp = null;
                while ((temp = bufReader.readLine()) != null) {
                    bufWriter.write(temp+"\n");
                }
            } catch (Exception e) {
                e.getStackTrace();
            } finally {
                if (bufReader != null && bufWriter != null) {
                    try {
                        bufReader.close();
                        bufWriter.close();
                    } catch (IOException e) {
                        e.getStackTrace();
                    }
                }
            }
        }

        /**
         * 使用Java.nio ByteBuffer字节将一个文件输出至另一文件
         *
         * @param filePath
         */
        public static void readFileByBybeBuffer(String filePath) {
            FileInputStream in = null;
            FileOutputStream out = null;
            try {
                // 获取源文件和目标文件的输入输出流
                in = new FileInputStream(filePath);
                out = new FileOutputStream("d:/work/readFileByBybeBuffer.txt");
                // 获取输入输出通道
                FileChannel fcIn = in.getChannel();
                FileChannel fcOut = out.getChannel();
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                while (true) {
                    // clear方法重设缓冲区，使它可以接受读入的数据
                    buffer.clear();
                    // 从输入通道中将数据读到缓冲区
                    int r = fcIn.read(buffer);
                    if (r == -1) {
                        break;
                    }
                    // flip方法让缓冲区可以将新读入的数据写入另一个通道
                    buffer.flip();
                    fcOut.write(buffer);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (in != null && out != null) {
                    try {
                        in.close();
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public static long getTime(){
            return System.currentTimeMillis();
        }

//        public static void main(String args[]) {
//            long time1 = getTime() ;
//            // readFileByByte(FILE_PATH);// 8734,8281,8000,7781,8047
//            // readFileByCharacter(FILE_PATH);// 734, 437, 437, 438, 422
//            // readFileByLine(FILE_PATH);// 110, 94,  94,  110, 93
//            readFileByBybeBuffer(FILE_PATH);// 125, 78,  62,  78, 62
//            long time2 = getTime() ;
//            System.out.println(time2-time1);
//        }
    public static void main(String args[]) {
        long time1 = getTime() ;
//         readFileByByte(FILE_PATH);     //2338,2286
//         readFileByCharacter(FILE_PATH);//160,162,158
//         readFileByLine(FILE_PATH);     //46,51,57
//        readFileByBybeBuffer(FILE_PATH);//19,18,17
//        readFileByBybeBuffer(FILE_PATH);//2048: 11,13
//        readFileByBybeBuffer(FILE_PATH);//1024*100 100k,711k: 6,6
//        readFileByBybeBuffer(FILE_PATH);//1024*100 100k,1422k: 7
//        readFileByBybeBuffer(FILE_PATH);//1024*100 100k,9951k: 49,48
//        readFileByBybeBuffer(FILE_PATH);//1024*1000 1M,711k: 7,7
//        readFileByBybeBuffer(FILE_PATH);//1024*1000 1M,1422k: 7,8
//        readFileByBybeBuffer(FILE_PATH);//1024*1000 1M,9951k: 48,49
//        readFileByBybeBuffer(FILE_PATH);//1024*10000 10M,711k: 21,13,17
//        readFileByBybeBuffer(FILE_PATH);//1024*10000 10M,1422k: 16,17,14,15
//        readFileByBybeBuffer(FILE_PATH);//1024*10000 10M,9951k:64,60

        long time2 = getTime() ;
        System.out.println(time2-time1);
    }

    @Test
    public void writeFile() throws Exception {
        List<String> lineValue = new ArrayList<>(3);
        lineValue.add("1\n");
        lineValue.add("1\n");
        lineValue.add("1\n");
        String filePath = "/Users/liuguobin/IdeaProjects/pay/src/main/resources/interface/kq/file/test";
        try (BufferedWriter bufWriter = new BufferedWriter(new FileWriter(filePath,true))) {
            for(int i = 0; i < lineValue.size(); i ++) {
                bufWriter.write(lineValue.get(i));
            }
//            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Thread.sleep(10000000);

//        return true;
    }

    @Test
    public void readFileByLineTest() {
        File file = new File("/Users/liuguobin/IdeaProjects/pay/src/main/resources/interface/kq/file/test");
        // BufferedReader:从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。
        BufferedReader bufReader = null;
        BufferedWriter bufWriter = null;
        try {
            // FileReader:用来读取字符文件的便捷类。
            bufReader = new BufferedReader(new FileReader(file));
            String temp = null;
            while ((temp = bufReader.readLine()) != null) {
                logger.info(temp);
            }
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (bufReader != null && bufWriter != null) {
                try {
                    bufReader.close();
                    bufWriter.close();
                } catch (IOException e) {
                    e.getStackTrace();
                }
            }
        }
    }



}

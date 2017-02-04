package qian.ling.yi.file;

import qian.ling.yi.AbstractTest;

/**
 * 因为FileWriter在写文件的时候，其编码方式似乎是System.encoding或者System.file.encoding(已经证明FileWriter和FileReader确实使用系统当前默认的编码方式
 *
 * 为什么文件读写会嵌套那么多层次，为了效率
 * utputStreamWriter： 每次调用 write() 方法都会导致在给定字符（或字符集）上调用编码转换器。在写入底层输出流之前，得到的这些字节将在缓冲区中累积。
 * 可以指定此缓冲区的大小，不过，默认的缓冲区对多数用途来说已足够大。为了获得最高效率，可考虑将 OutputStreamWriter 包装到 BufferedWriter 中，以避免频繁调用转换器。
 * Created by liuguobin on 2016/12/27.
 */
public class FileWriterTest extends AbstractTest {

}

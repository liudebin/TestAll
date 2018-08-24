package qian.ling.yi.ext.doc;

/**
 * WordDocumentUtils
 *
 * @author liuguobin
 * @date 2018/5/11
 */

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;


public class WordDocumentUtils
{
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        //  parse2007();
        parse97();
    }

    public static void parse2007()  throws Exception {
        XWPFDocument docx = new XWPFDocument(new FileInputStream("/Users/liuguobin/tmp/2.docx"));

        int pages = docx.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();//总页数
        int wordCount = docx.getProperties().getExtendedProperties().getUnderlyingProperties().getCharactersWithSpaces();// 忽略空格的总字符数 另外还有getCharactersWithSpaces()方法获取带空格的总字数。
        System.out.println ("pages=" + pages + " wordCount=" + wordCount);

    }

    public static void parse97()  throws Exception {
//        HWPFDocument doc = new HWPFDocument(new FileInputStream("/Users/liuguobin/tmp/1.doc"));
        HWPFDocument doc = new HWPFDocument(new FileInputStream("/Users/liuguobin/tmp/3.doc"));

        System.out.println(doc.getRange().text());
        System.out.println(doc.getRange().text().length());
        System.out.println(doc.getRange().text().replace("\r\n","").trim().length());
        System.out.println(doc.getOverallRange().text());
        System.out.println(doc.getOverallRange().text().length());
//        System.out.println(doc.getText().toString());
        int pages = doc.getSummaryInformation().getPageCount();//总页数
        int wordCount = doc.getSummaryInformation().getWordCount();//总字符数
//        wordCount = doc.getText().length();//总字符数
        System.out.println ("pages=" + pages + " wordCount=" + wordCount);
        System.out.println(doc.getDocProperties().getCChWS());
        System.out.println(doc.getDocProperties().getCChWSFtnEdn());
        System.out.println(doc.getDocProperties().getCDBC());
        System.out.println(doc.getDocProperties().getCDBCFtnEdn());
        System.out.println(doc.getDocProperties().getCParasFtnEdn());
        System.out.println(doc.getDocProperties().getCWordsFtnEnd());
        System.out.println(doc.getDocProperties().getCWords());

    }
}
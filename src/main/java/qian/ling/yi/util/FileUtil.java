package qian.ling.yi.util;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.io.File;

/**
 * Created by liuguobin on 2016/12/23.
 */
public class FileUtil extends AbstractTest{

    /**
     * 并没有像其他人说的只能写两层
     */
    @Test
    public void testMkdir() {
        File file = new File("/JAVA_Files/ftp/localFile/a/b/c/d");
        if (!file.exists()) {
            logger.info("{}", file.mkdirs());
        }
    }

}

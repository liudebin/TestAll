package qian.ling.yi.pattern;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liuguobin on 2016/11/17.
 */
public class PartternTest extends AbstractTest{

    @Test
    public void testMethod() {
        Pattern p = Pattern.compile("\\s*:\\s*\\{");
        String s = "a:{b:{a:b}}";
        Matcher matcher = p.matcher(s);
        if(matcher.find()){
            logger.info(s.substring(0,matcher.start()).trim());
            logger.info(s.substring(matcher.end(),s.length()-1).trim());
        }else{
            logger.info(s);
        }
    }
}

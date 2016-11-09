package qian.ling.yi.ext.dom4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLog {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Test
	public void test() {
		logger.info("123");
		logger.info("[12312]", new Object());
		logger.info("12312{}", new Object().toString());
	}
}

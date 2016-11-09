package qian.ling.yi.ext.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDom4j {
	private Logger logger = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) {
		new TestDom4j().testCreate();
	}

	@Test
	public void testCreate(){
		Element element = DocumentHelper.createElement("ni");
		element.addElement("mei").addText("1");
		element.addElement("ya").addText("2");
		logger.info(element.asXML());
//		<ni><mei>1</mei><ya>2</ya></ni>
		
		Document tmp = DocumentHelper.createDocument();
		element = tmp.addElement("ni");
		element.addElement("mei").addText("1");
		element.addElement("ya").addText("2");
		logger.info(element.asXML());
//		<ni><mei>1</mei><ya>2</ya></ni>

		Element doc = DocumentHelper.createElement("ni");
		doc.addElement("mei", "1");
		doc.addElement("ya", "2");
		logger.info(doc.asXML());
	}

	@Test
	public void testNONO() {
		Element msgBean = DocumentHelper.createElement("MSGBEAN");
		msgBean.addElement("VERSION").addText("");
		msgBean.addElement("MSG_TYPE").addText("");
		msgBean.addElement("BATCH_NO").addText("");
		msgBean.addElement("USER_NAME").addText("");
		msgBean.addElement("TRANS_STATE").addText("");
		msgBean.addElement("MSG_SIGN").addText("");

		Element transDetails = msgBean.addElement("TRANS_DETAILS");
		Element transDetail = transDetails.addElement("TRANS_DETAIL");
		transDetail.addElement("SN").addText("");
		transDetail.addElement("BANK_CODE").addText("");
		transDetail.addElement("ACC_NO").addText("");
		transDetail.addElement("ACC_NAME").addText("");
		transDetail.addElement("ACC_PROVINCE").addText("");
		transDetail.addElement("ACC_CITY").addText("");
		transDetail.addElement("AMOUNT").addText("");

		transDetail.addElement("MOBILE_NO").addText("");
		transDetail.addElement("PAY_STATE").addText("");
		transDetail.addElement("BANK_NO").addText("");
		transDetail.addElement("BANK_NAME").addText("");
		transDetail.addElement("ACC_TYPE").addText("");
		transDetail.addElement("ACC_PROP").addText("");
		transDetail.addElement("ID_TYPE").addText("");
		transDetail.addElement("ID_NO").addText("");
		transDetail.addElement("CNY").addText("");
		transDetail.addElement("EXCHANGE_RATE").addText("");
		transDetail.addElement("SETT_AMOUNT").addText("");
		transDetail.addElement("USER_LEVEL").addText("");
		transDetail.addElement("SETT_DATE").addText("");
		transDetail.addElement("REMARK").addText("");
		transDetail.addElement("RESERVE").addText("");

		transDetail.addElement("RETURN_URL").addText("");
		transDetail.addElement("MER_ORDER_NO").addText("");
		transDetail.addElement("MER_SEQ_NO").addText("");
		transDetail.addElement("QUERY_NO_FLAG").addText("");
		transDetail.addElement("TRANS_DESC").addText("");

		logger.info(msgBean.asXML());
	}
}

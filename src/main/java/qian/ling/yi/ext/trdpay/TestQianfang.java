package qian.ling.yi.ext.trdpay;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.slf4j.Logger;
import qian.ling.yi.ext.httpClient.HttpClientUtil;
import qian.ling.yi.util.MD5Util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuguobin on 2016/9/28.
 */
public class TestQianfang {
    @Test
    public void TestPay() {

        Map<String, String> param = new HashMap<>();
        param.put("goods_name","145399626053599943");
        param.put("limit_pay", "no_credit");
//        param.put("mchid", "");
//        param.put("udid", "");
        param.put("out_trade_no", "145399626053599943");
        param.put("pay_type", "800201");

        BigDecimal amount = new BigDecimal(10);
//                transData.getAmount().multiply(new BigDecimal(100)); //使用分进行提交
        String orderMoney=String.valueOf(amount.setScale(0,BigDecimal.ROUND_HALF_UP));//四舍五入，舍掉小数点后面的位数，不应该是进位处理ROUND_UP
        param.put("txamt", orderMoney);
        param.put("txcurrcd", "CNY");
        param.put("txdtm", "2016-09-27 17:51:00");


        StringBuilder builder = new StringBuilder();
        param.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(entry -> builder.append(entry.getKey()).append("=").append(entry.getValue()).append("&"));
        System.out.println(builder);
        builder.deleteCharAt(builder.length()-1).append("BD6B5A03769940B2BC40D542DFB5CCC3");
        System.out.println(builder);
        String sign = MD5Util.md5Hex(builder.toString());

        Map<String, String> headParam = new HashMap<>();
        headParam.put("X-QF-SIGN", sign);
        headParam.put("X-QF-APPCODE", "E650A9B2C05D43DA8C12672FDE93605B");
        Map<String, String> result = HttpClientUtil.postMapWithHead("https://openapi.qfpay.com/trade/v1/payment", headParam, param);

        System.out.println(JSON.toJSONString(result));

    }


    /**
     * 沙盒测试不成功
     */
    @Test
    public void TestSandBoxPay() {

        Map<String, String> param = new HashMap<>();
        param.put("goods_name","145399626053599943");
        param.put("limit_pay", "no_credit");
//        param.put("mchid", "");
//        param.put("udid", "");
        param.put("out_trade_no", "145399626053599943");
        param.put("pay_type", "800201");

        BigDecimal amount = new BigDecimal(10);
//                transData.getAmount().multiply(new BigDecimal(100)); //使用分进行提交
        String orderMoney=String.valueOf(amount.setScale(0,BigDecimal.ROUND_HALF_UP));//四舍五入，舍掉小数点后面的位数，不应该是进位处理ROUND_UP
        param.put("txamt", orderMoney);
        param.put("txcurrcd", "CNY");
        param.put("txdtm", "2016-09-27 17:51:00");


        StringBuilder builder = new StringBuilder();
        param.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(entry -> builder.append(entry.getKey()).append("=").append(entry.getValue()).append("&"));
        System.out.println(builder);
        builder.deleteCharAt(builder.length()-1).append("C5AA8711481AEAF347C28AEE12FB345B");
        System.out.println(builder);
        String sign = MD5Util.md5Hex(builder.toString());

        Map<String, String> headParam = new HashMap<>();
        headParam.put("X-QF-SIGN", sign);
        headParam.put("X-QF-APPCODE", "F2D3F9DC90C54FAB4CCBA55A459F8E4B");
        Map<String, String> result = HttpClientUtil.postMapWithHead("https://qtsandbox.qfpay.com/trade/v1/payment", headParam, param);


    }
}


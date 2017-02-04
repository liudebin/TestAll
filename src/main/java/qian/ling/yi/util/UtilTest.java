package qian.ling.yi.util;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.util.Random;

/**
 * Created by liuguobin on 2016/12/30.
 */
public class UtilTest extends AbstractTest {
    private static String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
    private static String getTel() {
        int index=getNum(0,telFirst.length-1);
        String first=telFirst[index];
        String second=String.valueOf(getNum(1,888)+10000).substring(1);
        String third=String.valueOf(getNum(1,9100)+10000).substring(1);
        return first+second+third;
    }
    public static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }

    @Test
    public void test() {
        logger.info(getTel());
    }

    /**
     * 生成银行卡号
     */
    @Test
    public void testCreateBankCard() {
        String bankType = "ICBC";
        String cardNo = "";
        String bankCode ;
        for(int i = 0; i < 200; i++){
            StringBuilder sb = null;
            if(bankType.equals("ICBC") ||bankType.equals("CCB") ||bankType.equals("ABC") || bankType.equals("PSBC") || bankType.equals("BCOM") || bankType.equals("GDB")|| bankType.equals("BOC")){
                 sb = new StringBuilder(13);

                for(int j = 0; j < 13; j++){
                    sb.append(new Random().nextInt(10));
                }
                logger.info("{}", "***"+sb+"***");

            }else{
                sb = new StringBuilder(10);

                for(int j= 0; j < 10; j++){
//                    sb.append(parseInt(Math.random() * 10));
                    sb.append(new Random().nextInt(10));
                }
            }

            switch(bankType){
                case "CCB":
                    cardNo = "621700" + sb;
                    bankCode = "105";
                    break;
                case "CMBC":
                    cardNo = "621691" + sb;
                    bankCode = "305";
                    break;
                case "ABC":
                    cardNo = "622827" + sb;
                    bankCode = "103";
                    break;
                case "BCOM":
                    cardNo = "622262" + sb;
                    bankCode = "301";
                    break;
                case "CMB":
                    cardNo = "621486" + sb;
                    bankCode = "308";
                    break;
                case "SPDB":
                    cardNo = "622521" + sb;
                    bankCode = "310";
                    break;
                case "GDB":
                    cardNo = "622568" + sb;
                    bankCode = "306";
                    break;
                case "HXB":
                    cardNo = "622632" + sb;
                    bankCode = "304";
                    break;
                case "PAB":
                    cardNo = "622298" + sb;
                    bankCode = "783";
                    break;
                case "CITIC":
                    cardNo = "622696" + sb;
                    bankCode = "302";
                    break;
                case "ICBC":
                    cardNo = "620058" + sb;
                    bankCode = "102";
                    break;
                case "BOC":
                    cardNo = "620061" + sb;
                    bankCode = "104";
                    break;
                case "CIB":
                    cardNo = "622908" + sb;
                    bankCode = "309";
                    break;
                case "CEB":
                    cardNo = "622660" + sb;
                    bankCode = "303";
                    break;
                case "PSBC":
                    cardNo = "621799" + sb;
                    bankCode = "403";
                    break;
                default:
                    cardNo = "621700" + sb;
                    bankCode = "105";
            }

            if(luhmCheck(cardNo)){
                logger.info(cardNo);
            }
            logger.info("失败");

//	return 0;
        }

    }



    private static  boolean luhmCheck(String cardNo) {
        return (luhnCheck(convertStrToInArr(cardNo)));
    }
    private static int[] convertStrToInArr(String cardNo) {
        if(null==cardNo)throw new IllegalArgumentException();
        int index = cardNo.length();
        int[] cardNoArr = new int[cardNo.length()];
        for(char c : cardNo.toCharArray())
        {
            cardNoArr[--index] = c - '0';
        }
        return cardNoArr;
    }

    /**
     * 校验的具体算法实现
     * @param cardNoArr
     * @return
     */
    private static boolean luhnCheck(int[] cardNoArr) {
        for(int i=1;i<cardNoArr.length;i+=2)
        {
            cardNoArr[i] <<= 1;
            cardNoArr[i] = cardNoArr[i]/10 + cardNoArr[i]%10;
        }
        int sum = 0;
        for(int i=0;i<cardNoArr.length;i++)
        {
            sum += cardNoArr[i];
            //System.out.print(cardNoArr[i]);
        }

        return sum % 10 == 0;
    }
}

package qian.ling.yi.ext.json.fastJson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import qian.ling.yi.AbstractTest;

/**
 * Created by liuguobin on 2016/10/9.
 */
public class FastJsonTest extends AbstractTest {
    Pojo pojo = new Pojo();

    {
        pojo.setAge(1);
        pojo.setName("test");
    }

    @Test
    public void testO2M() {

        JSONObject jsonObject = (JSONObject) JSON.toJSON(pojo);
        for (String key : jsonObject.keySet()) {
            logger.info("{} = {}",key, jsonObject.get(key));
        }
    }


    @Test
    public void testContainKey() {
        JSONObject jsonObject = (JSONObject) JSON.toJSON(pojo);
        logger.info("{}", jsonObject.containsKey("remark"));
        logger.info("{}", jsonObject.containsKey("no exist"));
    }
}

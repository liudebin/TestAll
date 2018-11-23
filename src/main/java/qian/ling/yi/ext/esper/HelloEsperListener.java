package qian.ling.yi.ext.esper;

import com.alibaba.fastjson.JSON;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

/**
 * 实现UpdateListener接口，来定义事件的后置处理过程
 *
 * @date: 2018/10/25.
 * @author: guobin.liu@holaverse.com
 */

public class HelloEsperListener implements UpdateListener {

    @Override
    public void update(EventBean[] arg0, EventBean[] arg1) {
        try {
//            System.out.println(JSON.toJSONString(arg0) + "\n" + JSON.toJSONString(arg1));
            System.out.println("coder: name-" + arg0[0].get("name") + " ss-" + arg0[0].get("ss"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package qian.ling.yi.ext.esper;

import com.espertech.esper.client.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @date: 2018/10/25.
 * @author: guobin.liu@holaverse.com
 */

public class HelloEsperApp {
    public static void main(String[] args) throws InterruptedException {
        EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();
        EPAdministrator admin = epService.getEPAdministrator();

        //指定事件模型
        String coderModel = Coder.class.getName();

        // 注册coder到Esper
        admin.getConfiguration().addEventType("Coder", Coder.class);
        //描述复杂事件
//        String epl = "select name,salary,age from " + coderModel;
        String epl = "select name, avg(salary) ss from Coder.win:length_batch(5)";
        EPStatement state = admin.createEPL(epl);

        //添加事后处理
        state.addListener(new HelloEsperListener());
        EPRuntime runtime = epService.getEPRuntime();

        //模拟事件发生
        for (int i = 0; i < 10; i++) {
            Coder coder = new Coder();
            coder.setName("coder"+i);
            coder.setAge(20+i);
            coder.setSalary(20+i);
            runtime.sendEvent(coder);
        }


        Map<String, Object> blog = new HashMap<>();
        blog.put("url", String.class);
        blog.put("title", String.class);

        Map<String, Object> coder = new HashMap<>();
        coder.put("name", String.class);
        coder.put("salary", int.class);
        coder.put("friends", List.class);
        coder.put("blogKey", "Blog");

        // 注册blog模型到esper
        admin.getConfiguration().addEventType("Blog", blog);
        // 注册coder到Esper
        admin.getConfiguration().addEventType("Coder", coder);


//        //在前文Coder的基础上，给Coder加上团队标志，再定义一个Team事件模型
//        Map<String, Object> coder = new HashMap<String, Object>();
//        coder.put("name", String.class);
//        coder.put("salary", int.class);
//        coder.put("teamName", String.class);
//
//        Map<String, Object> team = new HashMap<String, Object>();
//        team.put("teamName", String.class);
//        team.put("totalSalary", int.class);
//        admin.getConfiguration().addEventType("Coder", coder);
//        admin.getConfiguration().addEventType("Team", team);
//
//        String epl4CoderIn = "insert Team(teamName, totalSalary) select teanName,sum(salary) as totalSalary from Coder groupby teamName";
//        String epl4TeamIn = "select teamName, totalSalary from Team";
//
//        EPStatement state = admin.createEPL(epl4CoderIn);
//        EPStatement state = admin.createEPL(epl4TeamIn);
//        state.addListener(new TeamEventListener());
    }




}

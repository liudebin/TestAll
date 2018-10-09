package qian.ling.yi.ext.dubbo;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 * date: 2018/10/8.
 * author: guobin.liu@holaverse.com
 */

public class DemoServiceImpl implements DemoService {
    @Override
    public List<String> getPermissions(Long id) {
        List<String> demo = new ArrayList<String>();
        demo.add(String.format("Permission_%d", id - 1));
        demo.add(String.format("Permission_%d", id));
        demo.add(String.format("Permission_%d", id + 1));
        return demo;
    }

}
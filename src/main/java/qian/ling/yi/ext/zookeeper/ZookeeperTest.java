package qian.ling.yi.ext.zookeeper;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.lf5.viewer.LogFactor5ErrorDialog;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.util.List;

/**
 * 测试ZK的一些简单的api
 * Created by liuguobin on 2016/10/12.
 */
public class ZookeeperTest extends AbstractTest {
    private ZooKeeper zk ;
    private String rootPath = "/qian";
    @Before
    public void initZK() throws Exception{
        zk = new ZooKeeper("localhost:2181",300000, new MyWatcher());
        while (!zk.getState().equals(ZooKeeper.States.CONNECTED)) {
            Thread.sleep(1000);
        }
        logger.info("zk 连接上了");
    }

    @After
    public void cleanZK() throws Exception {
        deleteTree(rootPath);
    }

    @Test
    public void testCreate() {
    }

    @Test
    public void testGetChildren() throws Exception{
        String tmp = zk.create(rootPath, "我的rootpath,等下要删掉".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        logger.info(tmp);
        tmp = zk.create(rootPath + "/first_children", "第一个子节点,等下要删掉".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        logger.info(tmp);
        tmp = zk.create(rootPath + "/second_children", "第二个子节点,等下要删掉".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        logger.info(tmp);
        tmp = zk.create(rootPath + "/third_children", "第三个子节点,等下要删掉".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        logger.info(tmp);

        List<String> strs = zk.getChildren(rootPath, false);
        for (String name : strs) {
            logger.info(name);
        }
    }



    @Test
    public void testExists() throws Exception{
        Stat stat = zk.exists("/tbSchedule/haha/baseTaskType/myTbScheduleJob1/myTbScheduleJob1/taskItem", false);
        logger.info(JSON.toJSONString(stat));
    }

    public void deleteTree(String nodePath) throws Exception{
        if (zk.exists(nodePath, false) != null) {
            List<String> childNodeNames = zk.getChildren(nodePath, false);
            for (String name : childNodeNames) {
                logger.info("在删除节点{}", name);
                deleteTree(nodePath + "/" + name);
            }
            zk.delete(nodePath, -1);
        }
    }


}


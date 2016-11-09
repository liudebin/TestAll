package qian.ling.yi.ext.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.ZooDefs.Ids;
import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.io.IOException;

/**
 * Zookeeper 所有的读操作
 * 	getData()
 *  getChildren()
 *  exists() 都 可以设置监视(watch).
 *  监视事件可以理解为一次性的触发器， 官方定义如下： a watch event is one-time trigger,
 *  sent to the client that set the watch, which occurs when the data for which the watch was set changes。
 *  对此需要作出如下理解：
 *  （一次性触发）One-time trigger
 *  	当设置监视的数据发生改变时，该监视事件会被发送到客户端，
 *  	例如，如果客户端调用了 getData("/znode1", true)
 *  	并且稍后 /znode1 节点上的数据发生了改变或者被删除了，客户端将会获取到 /znode1 发生变化的监视事件，
 *  	而如果 /znode1 再一次发生了变化，除非客户端再次对 /znode1 设置监视，否则客户端不会收到事件通知。
 *
 *  （发送至客户端）Sent to the client
 *  	Zookeeper 客户端和服务端是通过 socket 进行通信的，
 *  	由于网络存在故障，所以监视事件很有可能不会成功地到达客户端，监视事件是异步发送至监视者的，
 *  	Zookeeper 本身提供了保序性(ordering guarantee)：
 *  		即客户端只有首先看到了监视事件后，才会感知到它所设置监视的 znode 发生了变化
 *  		(a client will never see a change for which it has set a watch until it first sees the watch event).
 *  		网络延迟或者其他因素可能导致不同的客户端在不同的时刻感知某一监视事件，但是不同的客户端所看到的一切具有一致的顺序。
 *
 *  （设置 watch 的数据）The data for which the watch was set
 *  	这意味着 znode 节点本身具有不同的改变方式。
 *  	你也可以想象 Zookeeper 维护了两条监视链表：
 *  	数据监视和子节点监视(data watches and child watches)
 *  		getData() and exists() 设置数据监视，getChildren() 设置子节点监视。
 *  	或者，你也可以想象 Zookeeper 设置的不同监视返回不同的数据，
 *  		getData() 和 exists() 返回 znode 节点的相关信息，
 *  		而 getChildren() 返回子节点列表。
 *  	因此， setData() 会触发设置在某一节点上所设置的数据监视(假定数据设置成功)，
 *  	而一次成功的 create() 操作则会出发当前节点上所设置的数据监视以及父节点的子节点监视。
 *  	一次成功的 delete() 操作将会触发当前节点的数据监视和子节点监视事件，同时也会触发该节点父节点的child watch。
 *
 *  Zookeeper 中的监视是轻量级的，因此容易设置、维护和分发。
 *  当客户端与 Zookeeper 服务器端失去联系时，客户端并不会收到监视事件的通知，
 *  只有当客户端重新连接后，若在必要的情况下，以前注册的监视会重新被注册并触发，对于开发人员来说 这通常是透明的。
 *  只有一种情况会导致监视事件的丢失，即：通过 exists() 设置了某个 znode 节点的监视，
 *  但是如果某个客户端在此 znode 节点被创建和删除的时间间隔内与 zookeeper 服务器失去了联系，
 *  该客户端即使稍后重新连接 zookeeper服务器后也得不到事件通知。
 * Zookeeper 监视器测试
 * Created by liuguobin on 2016/10/11.
 */
public class ZookeeperWatcherTest extends AbstractTest {

    /**
     * 测试事件触发，只会在创建时链接和 getData设定为true，之后有事件时才会触发。
     */
    @Test
    public void testWatch() {

        ZooKeeper zk = null;
        try {

            logger.info("...");
            logger.info("...");
            logger.info("...");
            logger.info("...");

            logger.info("开始连接ZooKeeper...");

            // 创建与ZooKeeper服务器的连接zk
            String address = "localhost:2181";
            int sessionTimeout = 30000000;
            zk = new ZooKeeper(address, sessionTimeout, new MyWatcher());
            logger.info("zk state {}", zk.getState());
            logger.info("ZooKeeper连接创建成功！");

            Thread.currentThread().sleep(1000l);
            logger.info("zk state {}", zk.getState());

            logger.info("...");
            logger.info("...");
            logger.info("...");
            logger.info("...");

            // 创建根目录节点
            // 路径为/tmp_root_path
            // 节点内容为字符串"我是根目录/tmp_root_path"
            // 创建模式为CreateMode.PERSISTENT
            logger.info("开始创建根目录节点/tmp_root_path...");
            String root = zk.create("/tmp_root_path", "我是根目录/tmp_root_path".getBytes(),
                    Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            logger.info("根目录节点/tmp_root_path创建成功！{}", root);

            Thread.currentThread().sleep(1000l);

            logger.info("...");
            logger.info("...");
            logger.info("...");
            logger.info("...");

            // 创建第一个子目录节点
            // 路径为/tmp_root_path/childPath1
            // 节点内容为字符串"我是第一个子目录/tmp_root_path/childPath1"
            // 创建模式为CreateMode.PERSISTENT
            logger.info("开始创建第一个子目录节点/tmp_root_path/childPath1...");
            String path1 = zk.create("/tmp_root_path/childPath1",
                    "我是第一个子目录/tmp_root_path/childPath1".getBytes(),
                    Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            logger.info("第一个子目录节点/tmp_root_path/childPath1创建成功！{}", path1);

            // 获取第二个子目录节点/tmp_root_path/childPath2节点数据
            logger.info("开始获取第一个子目录节点/tmp_root_path/childPath节点数据...");
            logger.info(new String(zk.getData(
                    "/tmp_root_path/childPath1", false, null)));
            logger.info("第一个子目录节点/tmp_root_path/childPath1节点数据获取成功！");

//            Thread.currentThread().sleep(1000l);

            logger.info("...");
            logger.info("...");
            logger.info("...");
            logger.info("...");

//            Thread.currentThread().sleep(1000l);

            logger.info("...");
            logger.info("...");
            logger.info("...");
            logger.info("...");

            // 创建第二个子目录节点
            // 路径为/tmp_root_path/childPath2
            // 节点内容为字符串"我是第二个子目录/tmp_root_path/childPath2"
            // 创建模式为CreateMode.PERSISTENT
            logger.info("开始创建第二个子目录节点/tmp_root_path/childPath2...");
            String path2 = zk.create("/tmp_root_path/childPath2",
                    "我是第二个子目录/tmp_root_path/childPath2".getBytes(),
                    Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            logger.info("第二个子目录节点/tmp_root_path/childPath2创建成功！{}", path2);

//            Thread.currentThread().sleep(1000l);

            logger.info("...");
            logger.info("...");
            logger.info("...");
            logger.info("...");

            // 获取第二个子目录节点/tmp_root_path/childPath2节点数据
            logger.info("开始获取第二个子目录节点/tmp_root_path/childPath2节点数据...");
            logger.info(new String(zk.getData(
                    "/tmp_root_path/childPath2", true, null)));
            logger.info("第二个子目录节点/tmp_root_path/childPath2节点数据获取成功！");

//            Thread.currentThread().sleep(1000l);

            logger.info("...");
            logger.info("...");
            logger.info("...");
            logger.info("...");

            // 修改第一个子目录节点/tmp_root_path/childPath1数据
            logger.info("开始修改第一个子目录节点/tmp_root_path/childPath1数据...");
            zk.setData("/tmp_root_path/childPath1",
                    "我是修改数据后的第一个子目录/tmp_root_path/childPath1".getBytes(), -1);
            logger.info("修改第一个子目录节点/tmp_root_path/childPath1数据成功！");

            Thread.currentThread().sleep(1000l);

            logger.info("...");
            logger.info("...");
            logger.info("...");
            logger.info("...");

            // 修改第二个子目录节点/tmp_root_path/childPath2数据
            logger.info("开始修改第二个子目录节点/tmp_root_path/childPath2数据...");
            zk.setData("/tmp_root_path/childPath2",
                    "我是修改数据后的第二个子目录/tmp_root_path/childPath2".getBytes(), -1);
            logger.info("修改第二个子目录节点/tmp_root_path/childPath2数据成功！");

            Thread.currentThread().sleep(1000l);

            logger.info("...");
            logger.info("...");
            logger.info("...");
            logger.info("...");

            // 删除第一个子目录节点
            logger.info("开始删除第一个子目录节点/tmp_root_path/childPath1...");
            zk.delete("/tmp_root_path/childPath1", -1);
            logger.info("第一个子目录节点/tmp_root_path/childPath1删除成功！");

            Thread.currentThread().sleep(1000l);

            logger.info("...");
            logger.info("...");
            logger.info("...");
            logger.info("...");

            // 删除第二个子目录节点
            logger.info("开始删除第二个子目录节点/tmp_root_path/childPath2...");
            zk.delete("/tmp_root_path/childPath2", -1);
            logger.info("第二个子目录节点/tmp_root_path/childPath2删除成功！");

            Thread.currentThread().sleep(1000l);

            logger.info("...");
            logger.info("...");
            logger.info("...");
            logger.info("...");

            // 删除根目录节点
            logger.info("开始删除根目录节点/tmp_root_path...");
            zk.delete("/tmp_root_path", -1);
            logger.info("根目录节点/tmp_root_path删除成功！");

            Thread.currentThread().sleep(1000l);

            logger.info("...");
            logger.info("...");
            logger.info("...");
            logger.info("...");

        } catch (IOException | KeeperException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            if (zk != null) {
                try {
                    zk.close();
                    logger.info("释放ZooKeeper连接成功！");

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 若删除节点时，有子节点，则报directory not empty
     * @throws Exception
     */
    @Test
    public void testDeleteNode() throws Exception{
        // 创建与ZooKeeper服务器的连接zk
        String address = "localhost:2181";
        int sessionTimeout = 30000000;
        ZooKeeper zk = new ZooKeeper(address, sessionTimeout, new MyWatcher());
        zk.delete("/tmp_root_path/childPath1", -1);
        zk.delete("/tmp_root_path", -1);
    }

}

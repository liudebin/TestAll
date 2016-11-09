package qian.ling.yi.ext.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import qian.ling.yi.AbstractTest;

/**
 * Created by liuguobin on 2016/10/12.
 */
public class MyWatcher extends AbstractTest implements Watcher{
        // 监控所有被触发的事件
        @Override
        public void process(WatchedEvent event) {
            logger.info("event type {} - event state {}", event.getType(), event.getState());
            logger.info("event path {}", event.getPath());
            if (event.getType() == null || "".equals(event.getType())) {
                return;
            }
            logger.info("已经触发了" + event.getType() + "事件！");
        }
}

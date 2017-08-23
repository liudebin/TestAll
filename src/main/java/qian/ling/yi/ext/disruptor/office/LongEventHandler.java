package qian.ling.yi.ext.disruptor.office;

import com.lmax.disruptor.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LongEventHandler
 *
 * @author liuguobin
 * @date 2017/8/10
 */

public class LongEventHandler implements EventHandler<LongEvent>
{
    Logger logger = LoggerFactory.getLogger(LongEventHandler.class);
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
//        System.out.println("Event: " + event);
        logger.info("Event: {}, sequence : {}, endOfBatch: {}", event, sequence, endOfBatch);
    }
}

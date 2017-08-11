package qian.ling.yi.ext.disruptor.office;

import com.lmax.disruptor.EventHandler;

/**
 * LongEventHandler
 *
 * @author liuguobin
 * @date 2017/8/10
 */

public class LongEventHandler implements EventHandler<LongEvent>
{
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
        System.out.println("Event: " + event);
    }
}

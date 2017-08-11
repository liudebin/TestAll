package qian.ling.yi.ext.disruptor.office;

import com.lmax.disruptor.EventFactory;

/**
 * LongEventFactory
 *
 * @author liuguobin
 * @date 2017/8/10
 */

public class LongEventFactory implements EventFactory<LongEvent>
{
    @Override
    public LongEvent newInstance()
    {
        return new LongEvent();
    }
}
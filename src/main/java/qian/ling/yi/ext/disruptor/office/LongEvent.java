package qian.ling.yi.ext.disruptor.office;

/**
 * LongEvent
 *
 * @author liuguobin
 * @date 2017/8/10
 */

public class LongEvent {
    private long value;

    public void set(long value)
    {
        this.value = value;
    }
    public long get() {
        return value;
    }

    LongEvent() {
        System.out.println("init");
    }
    @Override
    public String toString() {
        return "LongEvent{" +
                "value=" + value +
                '}';
    }
}

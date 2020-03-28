package qian.ling.yi.arithmetic;


import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class FunnelBucketRateLimiter {
    static class FunnelBucket {
        int capacity;//容量
        float secondRate;//每秒漏斗流水速度
        int leftQuota;//漏斗剩余空间
        long lastLeakTime;//上一次漏水时间

        public FunnelBucket(int capacity, float secondRate) {
            this.capacity = capacity;
            this.secondRate = secondRate;
            this.leftQuota = capacity;
            this.lastLeakTime = System.nanoTime();
        }

        //计算剩余空间
        void makeSpace() {
            long curTime = System.nanoTime();
            //距离上次开始漏水所经过的时间
            long timeInterval = (curTime - lastLeakTime);
            //距离上次开始漏水到现在总共漏水量
            int deltaQuota = (int) (timeInterval * secondRate)/1000/1000/1000;
            if (deltaQuota < 0) { // 间隔时间太长，整数数字过大溢出
                this.leftQuota = capacity;
                this.lastLeakTime = curTime;
                return;
            }
            //漏的水太少 不计算腾出的空间
            if (deltaQuota < 1) { // 腾出空间太小，最小单位是1
                return;
            }
            //剩余的空间增加
            this.leftQuota += deltaQuota;
            //当前时间标记为上次漏水时间
            this.lastLeakTime = curTime;
            //如果水全部漏完，剩余空间为漏斗总容量
            if (this.leftQuota > this.capacity) {
                this.leftQuota = this.capacity;
            }
        }

        //开始漏水，传入需要的空间大小quota，返回是否能分配quota个空间
        boolean watering(int quota) {
            if (this.leftQuota >= quota) {
                this.leftQuota -= quota;
                return true;
            }
            //计算剩余的空间
            makeSpace();
            //如果剩余空间充足，则分配quota个空间，返回true。不足则返回false
            if (this.leftQuota >= quota) {
                this.leftQuota -= quota;
                return true;
            }
            return false;
        }
    }

    private Map<String, FunnelBucket> funnels = new HashMap<>();

    public boolean isActionAllowed(String userId, String actionKey, int capacity, float leakingRate) {
        String key = String.format("%s:%s", userId, actionKey);
        //获取当前某个漏斗
        FunnelBucket funnel = funnels.get(key);
        //如果没有则新建漏斗
        if (funnel == null) {
            funnel = new FunnelBucket(capacity, leakingRate);
            funnels.put(key, funnel);
        }


        //判断当前漏斗还能否分配空间
        return funnel.watering(1); // 需要1个quota
    }

    public static void main(String[] args) throws IOException {
        ArrayBlockingQueue<Integer> integers = new ArrayBlockingQueue<>(10);
        ScheduledExecutorService scheduledExecutorServiceIn = Executors.newScheduledThreadPool(1);
        AtomicInteger atomicInteger  = new AtomicInteger(0);
        scheduledExecutorServiceIn.scheduleWithFixedDelay(() -> {
            integers.offer(atomicInteger.getAndIncrement());
        }, 0, 10, TimeUnit.MILLISECONDS);

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            System.out.println(integers.poll());
        }, 0, 100, TimeUnit.MILLISECONDS);

        System.in.read();

    }
}

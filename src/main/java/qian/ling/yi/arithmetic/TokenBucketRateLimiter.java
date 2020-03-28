package qian.ling.yi.arithmetic;


import java.util.HashMap;
import java.util.Map;

public class TokenBucketRateLimiter {
    static class TokenBucket {
        int capacity;//容量
        float secondRate;//每秒令牌桶流水速度
        int leftQuota;//令牌桶剩余空间
        long lastLeakTime;//上一次漏水时间

        public TokenBucket(int capacity, float secondRate) {
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
            //如果水全部漏完，剩余空间为令牌桶总容量
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

    private Map<String, TokenBucket> funnels = new HashMap<>();

    public boolean isActionAllowed(String userId, String actionKey, int capacity, float leakingRate) {
        String key = String.format("%s:%s", userId, actionKey);
        //获取当前某个令牌桶
        TokenBucket funnel = funnels.get(key);
        //如果没有则新建令牌桶
        if (funnel == null) {
            funnel = new TokenBucket(capacity, leakingRate);
            funnels.put(key, funnel);
        }
        //判断当前令牌桶还能否分配空间
        return funnel.watering(1); // 需要1个quota
    }

    public static void main(String[] args) {
        int num = 0;
        TokenBucketRateLimiter funnelRateLimiter = new TokenBucketRateLimiter();
        for (int i = 0; i < 200; i++) {
            num += funnelRateLimiter.isActionAllowed("a", "b", 100, 10f) ? 1 : 0;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(num);

    }
}

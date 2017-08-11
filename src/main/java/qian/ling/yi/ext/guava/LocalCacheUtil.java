package qian.ling.yi.ext.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 本地缓存
 *
 * @author liuguobin
 * @date 2017/8/7
 */
public class LocalCacheUtil {
    private static Logger logger = LoggerFactory.getLogger(LocalCacheUtil.class);
    private static Cache<Long, String> accountCache = CacheBuilder.newBuilder()
            .expireAfterAccess(2,TimeUnit.HOURS)
            .build();


    public static String getAccountNo(Long userId, Callable<String> getAccount) {
        try {
            return accountCache.get(userId, getAccount);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

}

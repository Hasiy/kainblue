package top.hasiy.kainblue;

import java.util.HashMap;
import java.util.Map;

public abstract class DoubleCheckCache {

    // DCL和缓存雪崩
    private Map<String, Object> localCache = new HashMap<>();

    public Object get(String key) {
        Object res = null;
        if (localCache.get(key) == null) { // 第一次检查
            synchronized (this) {
                if (localCache.get(key) == null) { // 第二次检查，其他排队请求获取锁的线程走到这里时已经能够看到缓存中的值了，也就不用再发起远程调用了
                    res = loadExternal(key); //防止多个线程多次调用loadExternal
                    localCache.put(key, res);
                }
            }
        }
        return res;
    }

    // 从外部加载key对应的value，通常是从数据库加载或者是发起RPC调用来加载，此操作是耗时的
    protected abstract Object loadExternal(String key);
}



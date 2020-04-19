package com.mimu.simple.comn.redis;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author: mimu
 * date: 2020/4/17
 */
@Slf4j
public class JedisMultiThreadTest {
    private ExecutorService service = Executors.newFixedThreadPool(1000);

    public void info() {
        Jedis jedis = new Jedis("127.0.0.1", 6379, 1000);
        for (int i = 0; i < 100; i++) {
            service.execute(new JedisSetEvent(jedis));
        }
        service.shutdown();
    }

    public void info1() {
        for (int i = 0; i < 3; i++) {
            service.execute(new JedisPoolDemo.PoolSetEvent("s" + i, "v" + 1));
        }
        service.shutdown();
    }

    public static void main(String[] args) {
        JedisMultiThreadTest threadTest = new JedisMultiThreadTest();
        threadTest.info1();
    }

    /**
     * jedis 非线程安全， 每个 jedis 中 存在 socket 链接
     * <p>
     * 1.jedis 进行每个 command 操作时会进行 connect 操作
     * (1.1 new Socket(),1.2.socket.connect(),
     * 1.3.new RedisInputStream(socket.getInputStream()), new RedisOutputStream(socket))
     * 2. 进行 Protocol.command() 操作, 该操作会 向 1.3 中进行 数据的 读写
     * <p>
     * first:
     * 当 thread1 进行了 1.1,1.2 操作，当要进行 1.3 操作时，被thread2 抢占了 该 jedis
     * 则 thread2 会进行 1.1,1.2,1.3 操作，当 thread2 进行到 1.1 操作后，被 thread1 抢占 thread1 进行 1.3 操作此时就会出现异常。
     * <p>
     * <p>
     * second:
     * 当 thread1 进行 Protocol.command() 操作 并发送了部分数据，后 被 thread2 抢占，thread2 也发送了部分数据，
     * 当 再次 执行 thread1 操作 向 server 端发送数据后，server 端 接受到的 数据格式 就会 不符合 redis 通信协议，
     * 则 server 端会关闭该 socket 而此时 thread2 抢占 cpu 执行 后续 发送操作，由于 socket 已关闭，则此时会出现异常
     */
    public static class JedisSetEvent implements Runnable {
        private Jedis jedis;

        public JedisSetEvent(Jedis jedis) {
            this.jedis = jedis;
        }

        @Override
        public void run() {
            while (true) {
                jedis.set("aaa", "aaa");
                jedis.close();
            }
        }
    }


    /**
     * 在实际使用过程中一般都通过 threadPool 的形式使用 jedis
     * jedis 提供了 1. JedisPool 2. ShardedJedisPool 3. JedisSentinelPool 三种 pool 形式
     * 而 使用 jedispool 的流程为
     * 1. pool.getResource()
     * 2. jedis.command()  execute command jedis or shardJedis
     * 3. jedis.close()
     * <p>
     * <p>
     * 1. getResource()
     * 1.1 从 GenericObjectPool.idleObject 中获取空闲 DefaultPoolObject<Jedis or ShardedJedis>
     * 1.2 若 1.1 中没有空闲的 DefaultPoolObject 进行如下操作
     * 1.2.1 若 newCreateCount < jedispoolconfig 中指定的 maxTotal(JedisPoolDemo 中 maxTotal =2 )
     * 则 新建 DefaultPoolObject factory.makeObject() factory(JedisFactory or ShardedJedisFactroy)
     * 1.2.2 若 newCreateCount > jedispoolconfig 中指定的 maxTotal 则 不创建 并 返回空
     * 1.3 拿到 DefaultPoolObject 后 返回 poolObject.getObject() 该 object 为 Jedis or ShardJedis
     * 2. execute command
     * 3 jedis.close()
     * 3.1 关闭 transaction,pipeline,重置 connection 状态
     * 3.2 把 该 DefaultPoolObject 放入到 idleObject 中
     */

    @Slf4j
    public static class JedisPoolDemo {
        private static JedisPool jedisPool;

        static {
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(2);
            jedisPoolConfig.setMaxWaitMillis(1);
            jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379, 1000);
        }

        public static Jedis getJedis() {
            return jedisPool.getResource();
        }

        public static void set(String key, String value) {
            Jedis jedis = getJedis();
            try {
                String set = jedis.set(key, value);
                System.out.println(set);
            } catch (Exception e) {
                log.error("set error", e);
            } finally {
                jedis.close();
            }
        }

        public static class PoolSetEvent implements Runnable {
            private String key;
            private String value;

            public PoolSetEvent(String key, String value) {
                this.key = key;
                this.value = value;
            }

            @Override
            public void run() {
                set(key, value);
            }
        }
    }

}

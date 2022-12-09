package com.jason;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author Jason
 * @Date 2022/12/05
 */
@Slf4j
public class ConcurrentTest {

    @Test
    public void createThreadFactory() throws InterruptedException {
        ThreadFactory threadFactory = r -> {
            Thread thread = new Thread(r);
            thread.setName("thread-test");
            // 记录线程异常
            thread.setUncaughtExceptionHandler((t, e) -> {
                log.error(e.getMessage(), e);
            });
            return thread;
        };

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 60,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(100000),
                threadFactory);
        threadPool.execute(() -> {
            log.info("---------------------");
            int i = 1 / 0;
        });

        threadPool.shutdown();
        System.out.println("pool shutdown: " + threadPool.isShutdown());
        while (!threadPool.isTerminated()) {
            threadPool.awaitTermination(1, TimeUnit.SECONDS);
        }
        System.out.println("all task complete");
    }

    @Test
    public void logTest() {
        log.info("hello: {}", "jason");
    }
}

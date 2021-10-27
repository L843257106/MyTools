package pers.liuhan.toolkit.concurrent.factory;


import java.util.concurrent.*;

/**
 * @author liuhan19691
 */
public class ThreadPoolFactory {

    private static final int CORE_SIZE = 16;

    public static ExecutorService getFixedThreadPool() {
        ThreadFactory nameFactory = Thread::new;
        return new ThreadPoolExecutor(CORE_SIZE, CORE_SIZE, 0,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(CORE_SIZE), nameFactory);
    }

}

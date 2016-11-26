package com.gizwits.affinity;

import net.openhft.affinity.Affinity;
import net.openhft.affinity.AffinityLock;
import net.openhft.affinity.AffinityStrategies;

/**
 * Created by feel on 2016/11/5.
 */
public class App {

    public static void main(String[] args) {

        // 获取当前多少个线程可用

        int cpuId = Affinity.getThreadId();
        System.out.println("可用线程数:" + cpuId);

        int cpu = Affinity.getCpu();
        System.out.println(cpu);


        try (final AffinityLock al = AffinityLock.acquireLock()) {
            System.out.println("Main locked");
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try (AffinityLock al2 = al.acquireLock(AffinityStrategies.SAME_SOCKET,
                            AffinityStrategies.ANY)) {
                        System.out.println("Thread-0 locked");

                    }
                }
            });
            t.start();
        }


    }
}

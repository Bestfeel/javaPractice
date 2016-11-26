package com.gizwits.concurrent;

/**
 * Peterson 互斥算法
 */
public class Peterson {

    /**
     * 用于表示进程进入临界区的意愿，下标对应进程号.主观地表示某一个进程是否希望使用资源
     */
    private static boolean[] in = {false, false};

    /**
     * 用于表示轮到哪个进程,客观地表示哪一个进程有权使用进程
     */
    private static volatile int turn = 0;


    /**
     * 进入临界区
     *
     * @param process 进程号
     */
    public void enterRegion(int process) {
        int cnt = 5;
        while (cnt-- > 0) {
            // 进程id想进入临界区,表示本进程想使用资源
            in[process] = true;

            // 另一个进程的进程号
            int other = 1 - process;
            // 设置轮到自己进入临界区了, 谦让，把使用进程的权限让给对方进程
            turn = other;

            // 如果对方进程想使用资源，且对方进程有使用资源的权限时，本进程等待
            while (in[other] && turn == other) {
                System.out.println("[" + process + "] - wait...");
            }
            System.out.println("-------------------[" + process + "] Working");
            leaveRegion(process);    // 本进程用完资源后，必须表示不再想用资源
        }

    }

    private void leaveRegion(int process) {

        in[process] = false;     // 本进程用完资源后，必须表示不再想用资源
    }

    public static void main(String[] args) {

        final Peterson peterson = new Peterson();
        int threadNum = 2;


        for (int i = 0; i < threadNum; i++) {

            final int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    peterson.enterRegion(finalI);
                }
            }).start();
        }
    }


}
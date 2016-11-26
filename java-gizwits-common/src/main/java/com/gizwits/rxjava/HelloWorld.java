package com.gizwits.rxjava;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.util.Arrays;
import java.util.List;

/**
 * Created by feel on 2016/10/20.
 * 响应式函数编程   简单入门
 */
public class HelloWorld {

    private final static Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    public static void main(String[] args) {


        Observable.just("Hello RxJava")
                .subscribe(s -> logger.info(s));


        Observable.just("Hello RxJava")
                .map(s -> s.toLowerCase())
                .subscribe(s -> logger.info(s));

        Observable.just("Hello RxJava")
                .map(s -> s.toLowerCase())
                .subscribe(s -> logger.info(s))
                .unsubscribe();


        Observable.create(subscriber -> {
            subscriber.onNext("Hello  world");
            subscriber.onCompleted();
        }).map(s -> ((String) s).toLowerCase()).subscribe(s -> logger.info(s));


        Observable.from(Arrays.asList(1, 2, 3, 4, 5))
                // .debounce(4000, TimeUnit.MINUTES)
                // .subscribeOn(Schedulers.io()) //  指定 subscribe（） 发生在IO 线程
                .map(s -> s * 2)
                .take(2)
                .doOnNext(s -> {
                    //  int i = 1 / 0;  // 这里出现异常,之后的操作都将出错
                    logger.info("{}", "这里允许我们在每次输出一个元素之前做一些额外的事情");
                })
                .subscribe(s -> logger.info("{}", s));


        Observable.from(Arrays.asList(1, 2, 3, 4, 5))
                .repeat(3)
                .distinct()
                .elementAt(4)// 选择第5个元素
                .subscribe(s -> logger.info("{}", s));


        List<Integer> list = Arrays.asList(6, 7, 8, 9, 10);


        Observable.from(list).window(4).subscribe(subs -> {
            logger.info("....windows ...");
            subs.subscribe(s -> logger.info("{}", s));
        });

        // end main
    }

}

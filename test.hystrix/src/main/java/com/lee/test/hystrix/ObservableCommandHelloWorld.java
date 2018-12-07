package com.lee.test.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by LIPAN on 2018/3/30.
 */
public class ObservableCommandHelloWorld extends HystrixObservableCommand<String> {

    private final String name;

    public ObservableCommandHelloWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    protected Observable<String> construct() {
        return Observable.create(new Observable.OnSubscribe<String>(){

            public void call(Subscriber<? super String> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext("Hello");
                        subscriber.onNext(name + "!");
                        subscriber.onCompleted();
                    }
                } catch (Exception e) {
                    subscriber.onError(e);
                }

            }
        }).subscribeOn(Schedulers.io());
    }

    public static void main(String[] args) {
        System.out.println(new ObservableCommandHelloWorld("World").construct());
    }

}

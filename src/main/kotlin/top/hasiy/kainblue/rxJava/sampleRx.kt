package top.hasiy.kainblue.rxJava

import io.reactivex.*
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import org.reactivestreams.Subscription
import java.lang.reflect.Array
import java.util.*
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit
import kotlin.Array as Array1


/**
 * @Author: hasiy
 * @Date: 2019/12/17 - 09 : 46
 * @LastEditors: zhuxiaoyao
 * @LastEditTime: 2019/12/17 - 09 : 46
 * @Description: kainblue
 * @Email: hasiy.jj@gmail.com
 */

fun main() {

    val subject = PublishSubject.create<Int>()

    subject.subscribe {
        fun onError(e: Throwable) {}
        fun onNext(integer: Int) {}
        fun onComplete() {}
        fun onSubscribe(s: Subscription?) {}
    }

    Observable.create { emitter: ObservableEmitter<Int?> ->
        emitter.onNext(1)
        emitter.onNext(2)
        emitter.onNext(3)
    }.subscribe { x: Int? -> println(x) }

    println()

    Observable.range(0, 10)
        .map { obj: Int -> obj.toString() }
        .forEach { x: String? -> println(x) }

    println()

    Observable.range(0, 10)
        .map { it.toString() }
        .forEach { println(it) }

    println()

    Observable.range(5, 10)
        .subscribe { i: Int -> println("1: $i") }

    println()

    Observable.create(ObservableOnSubscribe { observableEmitter: ObservableEmitter<Int?> ->
        observableEmitter.onNext(1)
        observableEmitter.onNext(2)
        observableEmitter.onComplete()
    }).subscribe { println(it) }

    println()

    val observable = Observable.defer(Callable<ObservableSource<Long>> { Observable.just(System.currentTimeMillis()) })
    observable.subscribe { obj: Long? -> print(obj) }
    println()
    observable.subscribe { obj: Long? -> print(obj) }
    println()


    Flowable.just(arrayListOf(1, 2, 3, 4, 5, 6, 7).filter { it < 5 }).map { obj -> print(obj.javaClass.name) }
        .subscribe()
    println()

    Flowable.fromArray(intArrayOf(1, 2, 3, 4, 5, 6, 7)).map { obj: IntArray -> println(obj.javaClass.name) }
        .subscribe()
    println()


    val taskArgs = Random().run { (1..10).map { nextLong() % 5000 + 5000 } }
    Observable
        .merge(taskArgs.mapIndexed { i, arg ->
            Observable.create<Long> {
                val result = SomeTask(i, arg).call()
                it.onNext(result)
                it.onComplete()
            }.subscribeOn(Schedulers.newThread())
        })
        .toList()
        .flatMap { args ->
            Single.create<Long> {
                val result = AnotherTask(args).call()
                it.onSuccess(result)
            }.subscribeOn(Schedulers.newThread())
        }
//        .observeOn(mainThread())
        .subscribe()

}

class SomeTask(private val no: Int, private val time: Long) : Callable<Long> {
    override fun call(): Long {
        println("Task #$no is started on Thread #${Thread.currentThread().id}.")
        TimeUnit.MILLISECONDS.sleep(time)
        println("Task #$no is done after $time ms.")
        return time
    }
}

class AnotherTask(private val args: List<Long>) : Callable<Long> {
    override fun call(): Long {
        println("Sum task is started on Thread #${Thread.currentThread().id} with args: ${args.joinToString()}.")
        TimeUnit.SECONDS.sleep(3)
        println("Sum task is done.")
        return args.sum()
    }
}




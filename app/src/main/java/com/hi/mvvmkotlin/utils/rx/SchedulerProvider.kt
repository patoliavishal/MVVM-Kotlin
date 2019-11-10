package com.hi.mvvmkotlin.utils.rx

import io.reactivex.Scheduler

/**
 * Created by Vishal Patel on 11/10/19.
 */
interface SchedulerProvider {

    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler
}
package com.example.countriesapp;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Before;
import org.junit.Rule;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

public class ListViewModuleTest {

    /**
     * @rule : is a rule witch indicate that every task execution is instant, that means it's not asynchronous.
     * That rule is necessary to test the task we make in the background thread through RxJava.
     * Make sure you implement this library before using that rule : 'androidx.arch.core:core-testing:2.1.0'
     */
    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Before
    public void setupRxSchedulers(){
        Scheduler immediate = new Scheduler() {
            @NonNull
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run, true);
            }
        };

        RxJavaPlugins.setInitNewThreadSchedulerHandler(schedulerCallable -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> immediate);
    }
}

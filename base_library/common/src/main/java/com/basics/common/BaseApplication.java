package com.basics.common;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.multidex.MultiDex;

import com.basics.common.dagger.component.AppComponent;

/**
 * @Author: xueshijie
 * @CreateDate: 2020-03-21 17:15
 * @Version: 1.0
 * @Description: java类作用描述
 */
public class BaseApplication extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * 程序终止的时候执行
     */
    @Override
    public void onTerminate() {
        Log.d("Application", "onTerminate");
        super.onTerminate();
    }


    public static AppComponent getAppComponent() {
        return appComponent;
    }

}

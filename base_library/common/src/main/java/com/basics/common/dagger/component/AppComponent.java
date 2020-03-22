package com.basics.common.dagger.component;

import android.content.SharedPreferences;

import com.basics.common.dagger.module.GlobalConfigModule;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * @Author: xueshijie
 * @CreateDate: 2020-03-22 15:57
 * @Version: 1.0
 * @Description: java类作用描述
 */
@Singleton
@Component(modules = GlobalConfigModule.class)
public interface AppComponent {


    Gson getGson();

    OkHttpClient.Builder getOkHttpClientBuilder();

    Retrofit getRetrofit();

    SharedPreferences getSharedPreferences();

    Retrofit.Builder getRetrofitBuilder();
}

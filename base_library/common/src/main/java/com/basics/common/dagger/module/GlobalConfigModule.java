package com.basics.common.dagger.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

import com.basics.common.http.OkHttpGlobalHandler;
import com.basics.common.http.TrustAllCerts;
import com.basics.common.http.interceptor.LogInterceptor;
import com.basics.common.http.interceptor.TokenInterceptor;
import com.basics.common.utils.Constant;
import com.basics.common.utils.FileUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author: xueshijie
 * @CreateDate: 2020-03-22 15:57
 * @Version: 1.0
 * @Description: java类作用描述
 */
@Module
public class GlobalConfigModule {

    private Application application;


    public GlobalConfigModule(Application application) {
        this.application = application;
    }


    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient(OkHttpClient.Builder okHttpBuilder) {
        return okHttpBuilder.build();
    }


    @Singleton
    @Provides
    public Interceptor provideLogInterceptor(OkHttpGlobalHandler okHttpGlobalHandler) {
        return new LogInterceptor(okHttpGlobalHandler, LogInterceptor.Level.BODY);
    }

    @Singleton
    @Provides
    public OkHttpGlobalHandler provideGlobalNetHandler() {
        return new OkHttpGlobalHandler() {
            @Override
            public Response onResultResponse(String printResult, Interceptor.Chain chain, Response response) {
                return null;
            }

            @Override
            public Request onRequestBefore(Interceptor.Chain chain, Request request) {
                return request;
            }
        };
    }

    @Singleton
    @Provides
    public OkHttpClient.Builder provideOkHttpBuilder(@Nullable final OkHttpGlobalHandler handler, @Nullable Interceptor logInterceptor
            , TokenInterceptor tokenInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS);
        //        自定义签名证书
        //builder.sslSocketFactory(SSLConfig.getSSLSocketFactory(BaseApplication.getInstance()), (X509TrustManager) Objects.requireNonNull(SSLConfig.getTrustManager(BaseApplication.getInstance())));
        builder.sslSocketFactory(TrustAllCerts.createSSLSocketFactory());
        builder.hostnameVerifier(new TrustAllCerts.TrustAllHostnameVerifier());
        if (tokenInterceptor != null) {
            builder.addNetworkInterceptor(tokenInterceptor);
        }
        if (logInterceptor != null) {
            builder.addNetworkInterceptor(logInterceptor);
        }
        if (handler != null) {
            builder.addInterceptor(chain -> chain.proceed(handler.onRequestBefore(chain, chain.request())));
        }
        return builder;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Retrofit.Builder builder) {
        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit.Builder provideRetrofitBuilder(OkHttpClient okHttpClient, @Nullable Gson gson) {
        Retrofit.Builder builder = new Retrofit.Builder();
        return builder.baseUrl(Constant.BASE_URL).client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson));
    }


    @Singleton
    @Provides
    public Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls().enableComplexMapKeySerialization();
        return gsonBuilder.create();
    }

    @Singleton
    @Provides
    public SharedPreferences provideSharedPreferences() {
        return application.getSharedPreferences("common", Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    public File provideCacheFile() {
        return FileUtil.getDefaultCacheFile(application);
    }




}

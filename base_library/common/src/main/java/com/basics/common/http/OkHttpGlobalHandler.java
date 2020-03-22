package com.basics.common.http;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by COOTEK on 2017/7/28.
 */

public interface OkHttpGlobalHandler {

     Response onResultResponse(String printResult, Interceptor.Chain chain, Response response);

     Request onRequestBefore(Interceptor.Chain chain, Request request);


}

package com.kxky.demo.http;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * Created by kxky on 2017/12/15.
 */

public class RetrofitService {
    public static String BASE_IM_URL = "";
    public static String SERVICE_URL = "";
    public Retrofit.Builder builder;
    private static volatile RetrofitService instance;
    private Map<String, String> headers;
    private OkHttpClient mHttpclient;

    private RetrofitService() {

    }

    public static RetrofitService getInstance() {
        if (instance == null) {
            synchronized (RetrofitService.class) {
                instance = new RetrofitService();
            }
        }
        return instance;
    }

    public static void setBaseImUrl(String url) {
        BASE_IM_URL = url;
    }

    public static void setServiceUrl(String url) {
        SERVICE_URL = url;
    }

    public void init(Context context) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        okhttp3.Cache cache = new okhttp3.Cache(new File(context.getApplicationContext().getCacheDir(), "HttpCache"), 1024 * 1024 * 100);
        mHttpclient = new OkHttpClient.Builder()
                .cache(cache)  //缓存大小
                .addNetworkInterceptor(mHead) //添加head 拦截器
                .retryOnConnectionFailure(true) //是否自动重连
                .connectTimeout(10, TimeUnit.SECONDS) // 超出响应时间10s重新发送
                .addInterceptor(logging) //okhttp  Log拦截
                .build();

        builder = new Retrofit.Builder();
        builder.addConverterFactory(FastJsonConverterFactory.create())  //因为Retrofit  Retrofit 默认只能将响应体转换为 OkHttp 中的 ResponseBody
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());  //返回类型适配 我们回调的是Observable　所以加入相对应的RX库
    }

    public <T> T createHttpService(String baseUrl, Map<String, String> headers, Class<T> serviceInterface) {
        this.headers = headers;
        Retrofit retrofit = builder.baseUrl(baseUrl)
                .client(mHttpclient)
                .build();
        T t = retrofit.create(serviceInterface);
        return t;
    }

    public static MultipartBody imgFileBody(String name, File file) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
        builder.addFormDataPart(name, file.getName(), requestBody);
        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();
        return multipartBody;
    }

    /**
     * 利用拦截器请求header
     */
    private Interceptor mHead = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Request.Builder builder = original.newBuilder();
            if (headers != null) {
                Iterator<Map.Entry<String, String>> entries = headers.entrySet().iterator();
                while (entries.hasNext()) {
                    Map.Entry<String, String> entry = entries.next();
                    builder.addHeader(entry.getKey(), entry.getValue());
                    Log.d("headers", "key:" + entry.getKey() + " value:" + entry.getValue());
                }
            }
            Request request = builder.build();
            return chain.proceed(request);
        }
    };
}

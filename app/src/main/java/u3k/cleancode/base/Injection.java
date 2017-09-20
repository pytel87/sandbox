package u3k.cleancode.base;


import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import u3k.cleancode.BuildConfig;
import u3k.cleancode.dao.AppDatabase;
import u3k.cleancode.dao.QuestionDao;
import u3k.cleancode.manager.QuestionsManager;

/**
 * Created by Vladimir Skoupy.
 */

public class Injection {


    public static QuestionDao provideQuestionDao() {
        return AppDatabase.getInstance(BaseApp.getInstance()).questionDao();
    }

    public static Retrofit provideRetrofit(String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(provideGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUGGING ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        builder.addInterceptor(loggingInterceptor);
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(10, TimeUnit.SECONDS);

        return builder.build();
    }

    public static Gson provideGson() {
        return new Gson();
    }

    public static QuestionsManager provideQuestionsManager() {
        return new QuestionsManager();
    }
}

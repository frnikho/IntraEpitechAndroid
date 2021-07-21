package fr.nikho.epitech.intra.utils;

import android.content.Context;

import java.io.IOException;
import java.util.HashSet;

import fr.nikho.epitech.intra.services.LanguageService;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {

    private Context context;

    public AddCookiesInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("Cookie", String.format("language=%s; Path=/;", LanguageService.getCurrentLanguage(context).getCode()));
        return chain.proceed(builder.build());
    }
}

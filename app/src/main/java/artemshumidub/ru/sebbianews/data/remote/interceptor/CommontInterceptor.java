package artemshumidub.ru.sebbianews.data.remote.interceptor;

import android.content.Context;
import android.support.annotation.NonNull;
import java.io.IOException;
import java.net.UnknownHostException;
import artemshumidub.ru.sebbianews.NewsApp;
import artemshumidub.ru.sebbianews.data.exception.NoInternetException;
import artemshumidub.ru.sebbianews.data.exception.ServerErrorException;
import artemshumidub.ru.sebbianews.data.exception.UnknownException;
import artemshumidub.ru.sebbianews.data.util.ConnectionUtil;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CommontInterceptor implements Interceptor {

    private final ConnectionUtil connectionUtil;

    public CommontInterceptor(Context appContext) {
        connectionUtil = ((NewsApp) appContext).getComponent().connectionUtil();
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        try {
            Request originalRequest = chain.request();
            Response originalResponse = chain.proceed(originalRequest);
            if (originalResponse != null && originalResponse.isSuccessful()) return originalResponse;
            else {
                if (!connectionUtil.checkInternetConnection()) {
                    throw new NoInternetException();
                } else if (originalResponse != null
                        && originalResponse.code() >= 500
                        && originalResponse.code() <= 599) {
                    throw new ServerErrorException();
                } else {
                    throw new UnknownException();
                }
            }
        }catch (UnknownHostException e){
            throw new UnknownException();
        }
    }
}
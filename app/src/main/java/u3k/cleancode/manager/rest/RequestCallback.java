package u3k.cleancode.manager.rest;

import android.util.Log;

import com.google.gson.Gson;

import io.reactivex.subscribers.DisposableSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;
import u3k.cleancode.model.json.ErrorResponse;

public abstract class RequestCallback<T> extends DisposableSubscriber<T> {

    protected abstract void onSuccess(T t);

    protected abstract void onError(ErrorResponse error);

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        ErrorResponse error = null;
        if (e instanceof HttpException) {
            error = getErrorResponse((HttpException) e);
        }

        if (error == null||error.isEmpty()) {
            error = ErrorResponse.create(e.getMessage(), Log.getStackTraceString(e));
        }

        onError(error);
    }

    @Override
    public void onComplete() {

    }

    private ErrorResponse getErrorResponse(HttpException exception) {
        ErrorResponse error = null;
        try {
            Response<?> response = ((HttpException) exception).response();
            if (response != null) {
                ResponseBody responseBody = response.errorBody();
                if (responseBody != null) {
                    error = new Gson().fromJson(responseBody.string(), ErrorResponse.class);
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }

        return error;
    }
}
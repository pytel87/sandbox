package u3k.cleancode.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import u3k.cleancode.model.json.ErrorResponse;

/**
 * Created by Vladimir Skoupy.
 */

public class BaseViewModel extends ViewModel {

    private MutableLiveData<ErrorResponse> liveErrors = new MutableLiveData<>();

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void cleanDisposables() {
        compositeDisposable.clear();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        cleanDisposables();
    }
}

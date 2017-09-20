package u3k.cleancode.view.activity;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import u3k.cleancode.R;
import u3k.cleancode.view.custom.LoaderView;

/**
 * Created by Vladimir Skoupy.
 */

public class BaseActivity extends AppCompatActivity {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    @BindView(R.id.loading)
    public LoaderView loaderView;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        setupActivity();
    }

    public void setupActivity() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mapThingsToUI();
    }

    private void mapThingsToUI() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    public void showProgress(boolean progress) {
        loaderView.setVisibility(progress ? View.VISIBLE : View.GONE);
    }
}

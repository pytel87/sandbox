package u3k.cleancode.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import u3k.cleancode.R;

public class LoaderView extends FrameLayout {


    @BindView(R.id.progress)
    ProgressBar progressView;


    public LoaderView(Context context) {
        super(context);
        init();
    }

    public LoaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_loader, this, true);
        ButterKnife.bind(this);
    }


}
package u3k.cleancode.view.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;
import u3k.cleancode.R;
import u3k.cleancode.model.Question;
import u3k.cleancode.model.Resource;
import u3k.cleancode.utils.Logg;
import u3k.cleancode.view.adapter.QuestionsAdapter;
import u3k.cleancode.viewmodel.QuestionsViewModel;

/**
 * Created by Vladimir Skoupy.
 */

public class QuestionsActivity extends BaseActivity {

    public static final String TAG = "QuestionsActivity";

    @BindView(R.id.items)
    RecyclerView itemsView;

    @BindView(R.id.refresh)
    SwipeRefreshLayout swipeRefreshLayout;


    private QuestionsViewModel viewModel;

    private QuestionsAdapter questionsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        if (savedInstanceState == null) {
            viewModel.loadQuestions();
        }
    }

    @Override
    public void setupActivity() {
        super.setupActivity();
        viewModel = ViewModelProviders.of(this).get(QuestionsViewModel.class);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        itemsView.setLayoutManager(layoutManager);
        itemsView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        questionsAdapter = new QuestionsAdapter(this);
        compositeDisposable.add(questionsAdapter.getClickSubjectAsObservable().subscribe(new Consumer<Question>() {
            @Override
            public void accept(Question question) throws Exception {
                Logg.i(TAG, question.getTitle());
            }

        }));

        itemsView.setAdapter(questionsAdapter);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                viewModel.loadQuestions();

            }
        });

        setupObservers();

    }

    private void setupObservers() {
        viewModel.getLiveQuestions().observeForever(new Observer<Resource<List<Question>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<Question>> resource) {
                if (resource != null) {
                    switch (resource.status) {
                        case LOADING:
                            showProgress(true);
                            questionsAdapter.setItems(resource.data);
                            break;
                        case SUCCESS:
                            showProgress(false);
                            questionsAdapter.setItems(resource.data);
                            break;
                        case ERROR:
                            showProgress(false);
                            Logg.e(TAG, resource.message);
                            break;
                    }
                }

            }
        });
    }
}

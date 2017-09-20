package u3k.cleancode.viewmodel;

import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import u3k.cleancode.base.Injection;
import u3k.cleancode.manager.QuestionsManager;
import u3k.cleancode.manager.rest.RequestCallback;
import u3k.cleancode.model.Question;
import u3k.cleancode.model.Resource;
import u3k.cleancode.model.json.ErrorResponse;

/**
 * Created by Vladimir Skoupy.
 */

public class QuestionsViewModel extends BaseViewModel {

    private MutableLiveData<Resource<List<Question>>> liveQuestions = new MutableLiveData<>();

    QuestionsManager questionsManager;

    public QuestionsViewModel() {
        this.questionsManager = Injection.provideQuestionsManager();
    }

    public void loadQuestions() {

        compositeDisposable.add(questionsManager.loadQuestions(1).subscribeWith(new RequestCallback<Resource<List<Question>>>() {

            @Override
            protected void onSuccess(Resource<List<Question>> resource) {
                liveQuestions.setValue(resource);
            }

            @Override
            protected void onError(ErrorResponse error) {
                liveQuestions.setValue(Resource.error(error.getError(), (List<Question>) null));
            }
        }));
    }

    public MutableLiveData<Resource<List<Question>>> getLiveQuestions() {
        return liveQuestions;
    }
}

package u3k.cleancode.manager;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import u3k.cleancode.base.Injection;
import u3k.cleancode.dao.QuestionDao;
import u3k.cleancode.manager.rest.NetworkBoundSource;
import u3k.cleancode.manager.rest.QuestionsApi;
import u3k.cleancode.model.Question;
import u3k.cleancode.model.Resource;
import u3k.cleancode.model.json.QuestionsResponse;

/**
 * Created by Vladimir Skoupy.
 */

public class QuestionsManager extends BaseManager {

    public static final String QUESTIONS_API_URL = "https://api.stackexchange.com";
    private final QuestionDao questionDao;
    private final QuestionsApi questionsApi;

    public QuestionsManager() {
        this.questionsApi = Injection.provideRetrofit(QUESTIONS_API_URL).create(QuestionsApi.class);
        questionDao = Injection.provideQuestionDao();
    }

    public Flowable<Resource<List<Question>>> loadQuestions(final int page) {

        return Flowable.create(new FlowableOnSubscribe<Resource<List<Question>>>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<Resource<List<Question>>> e) throws Exception {

                new NetworkBoundSource<List<Question>, QuestionsResponse>(e) {

                    @Override
                    public Flowable<QuestionsResponse> getRemote() {
                        return questionsApi.loadQuestions(page);
                    }

                    @Override
                    public Flowable<List<Question>> getLocal() {
                        return questionDao.getAll();
                    }

                    @Override
                    public void saveRequestResult(List<Question> data) {
                        saveQuestionsToRoom(data);
                    }

                    @Override
                    public Function<QuestionsResponse, List<Question>> mapper() {
                        return new Function<QuestionsResponse, List<Question>>() {
                            @Override
                            public List<Question> apply(@NonNull QuestionsResponse questionsResponse) throws Exception {
                                return questionsResponse.getItems();
                            }
                        };
                    }
                };

            }
        }, BackpressureStrategy.BUFFER).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());



        /* return questionsApi.loadQuestions(page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).flatMap(new Function<QuestionsResponse, Publisher<List<Question>>>() {
                    @Override
                    public Publisher<List<Question>> apply(@NonNull QuestionsResponse questionsResponse) throws Exception {
                        return Flowable.just(questionsResponse.getItems());
                    }
                });*/

    }

    private void saveQuestionsToRoom(List<Question> data) {
        questionDao.deleteAll();
        questionDao.insertAll(data);
    }

}

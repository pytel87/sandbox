package u3k.cleancode.manager.rest;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import u3k.cleancode.model.json.QuestionsResponse;

/**
 * Created by Vladimir Skoupy.
 */

public interface QuestionsApi {
    @GET("/2.2/questions?filter=withbody&order=desc&sort=creation&site=cooking&pagesize=20")
    Flowable<QuestionsResponse> loadQuestions(@Query("page") int page);
}

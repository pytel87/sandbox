package u3k.cleancode.model.json;

import java.util.List;

import u3k.cleancode.model.Question;

/**
 * Created by Vladimir Skoupy.
 */

public class QuestionsResponse extends BaseResponse {

    private List<Question> items;

    public List<Question> getItems() {
        return items;
    }
}

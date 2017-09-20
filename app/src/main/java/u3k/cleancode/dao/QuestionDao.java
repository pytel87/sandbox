package u3k.cleancode.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import u3k.cleancode.model.Question;

@Dao
public interface QuestionDao {

    @Query("SELECT * FROM question")
    Flowable<List<Question>> getAll();

    @Insert
    void insertAll(List<Question> questions);

    @Query("DELETE FROM question")
    public void deleteAll();
}
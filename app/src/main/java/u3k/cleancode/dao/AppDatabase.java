package u3k.cleancode.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import u3k.cleancode.model.Question;

@Database(entities = {Question.class}, version = AppDatabase.DATABASE_VERSION)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "app.db";
    public static final int DATABASE_VERSION = 2;

    private static AppDatabase instance;

    public abstract QuestionDao questionDao();

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, DATABASE_NAME)
                    .build();
        }
        return instance;
    }
}
package u3k.cleancode.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Vladimir Skoupy.
 */

@Entity(tableName = "question")
public class Question {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "body")
    private String body;

    @Embedded
    private User owner;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getOwnerImage() {
        return owner != null ? owner.getImage() : null;
    }

}

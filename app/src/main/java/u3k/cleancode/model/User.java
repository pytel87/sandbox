package u3k.cleancode.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vladimir Skoupy.
 */

public class User {

    @SerializedName("profile_image")
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

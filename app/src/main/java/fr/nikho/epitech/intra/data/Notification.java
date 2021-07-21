package fr.nikho.epitech.intra.data;

import com.google.gson.annotations.SerializedName;

public class Notification {

    public class User {

        @SerializedName("picture")
        public String picture;

        @SerializedName("title")
        public String title;

        @SerializedName("url")
        public String url;

        public String getPicture() {
            return picture;
        }

        public String getTitle() {
            return title;
        }

        public String getUrl() {
            return url;
        }
    }

    @SerializedName("id")
    public String id;

    @SerializedName("title")
    public String title;

    @SerializedName("class")
    public String type;

    @SerializedName("user")
    public User user;

    @SerializedName("content")
    public String content;

    @SerializedName("date")
    public String date;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public User getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }
}

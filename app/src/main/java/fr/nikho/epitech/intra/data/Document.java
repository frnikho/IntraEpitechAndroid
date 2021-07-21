package fr.nikho.epitech.intra.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Document {

    public class Rights implements Serializable {
        @SerializedName("ged_read")
        public int gedRead;

        public int getGedRead() {
            return gedRead;
        }
    }

    public class Modifier implements Serializable {
        @SerializedName("login")
        public String login;

        @SerializedName("title")
        public String title;

        @SerializedName("picture")
        public String picture;

        public String getLogin() {
            return login;
        }

        public String getTitle() {
            return title;
        }

        public String getPicture() {
            return picture;
        }
    }

    @SerializedName("type")
    public String type;

    @SerializedName("slug")
    public String slug;

    @SerializedName("title")
    public String title;

    @SerializedName("secure")
    public boolean secure;

    @SerializedName("synchro")
    public boolean synchro;

    @SerializedName("archive")
    public boolean archive;

    @SerializedName("language")
    public String language;

    @SerializedName("size")
    public int size;

    @SerializedName("ctime")
    public String ctime;

    @SerializedName("mtime")
    public String mtime;

    @SerializedName("mime")
    public String mime;

    @SerializedName("isLeaf")
    public boolean isLeaf;

    @SerializedName("noFolder")
    public boolean noFolder;

    @SerializedName("rights")
    public Rights rights;

    @SerializedName("modifier")
    public Modifier modifier;

    @SerializedName("fullpath")
    public String fullpath;

    public String getType() {
        return type;
    }

    public String getSlug() {
        return slug;
    }

    public String getTitle() {
        return title;
    }

    public boolean isSecure() {
        return secure;
    }

    public boolean isSynchro() {
        return synchro;
    }

    public boolean isArchive() {
        return archive;
    }

    public String getLanguage() {
        return language;
    }

    public int getSize() {
        return size;
    }

    public String getCtime() {
        return ctime;
    }

    public String getMtime() {
        return mtime;
    }

    public String getMime() {
        return mime;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public boolean isNoFolder() {
        return noFolder;
    }

    public Rights getRights() {
        return rights;
    }

    public Modifier getModifier() {
        return modifier;
    }

    public String getFullpath() {
        return fullpath;
    }
}

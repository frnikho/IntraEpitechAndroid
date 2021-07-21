package fr.nikho.epitech.intra.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MissedCourses {

    public class Missed implements Serializable {

        @SerializedName("module_title")
        public String moduleTitle;

        @SerializedName("acti_title")
        public String actiTitle;

        @SerializedName("link_module")
        public String linkModule;

        @SerializedName("link_event")
        public String linkEvent;

        @SerializedName("recent")
        public String recent;

        @SerializedName("begin")
        public String begin;

        @SerializedName("end")
        public String end;

        @SerializedName("categ_title")
        public String categTitle;

        public String getModuleTitle() {
            return moduleTitle;
        }

        public String getActiTitle() {
            return actiTitle;
        }

        public String getLinkModule() {
            return linkModule;
        }

        public String getLinkEvent() {
            return linkEvent;
        }

        public String getRecent() {
            return recent;
        }

        public String getBegin() {
            return begin;
        }

        public String getEnd() {
            return end;
        }

        public String getCategTitle() {
            return categTitle;
        }
    }

    @SerializedName("recents")
    public Missed[] recents;

    @SerializedName("others")
    public Missed[] others;

    public Missed[] getRecents() {
        return recents;
    }

    public Missed[] getOthers() {
        return others;
    }
}

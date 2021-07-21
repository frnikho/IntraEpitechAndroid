package fr.nikho.epitech.intra.data;

import com.google.gson.annotations.SerializedName;

public class Binomes {

    @SerializedName("login")
    private String login;

    @SerializedName("picture")
    private String pictureUrl;

    @SerializedName("activities")
    private String activities;

    @SerializedName("id_activities")
    private String activitiesId;

    @SerializedName("nb_activities")
    private String activitiesCount;

    @SerializedName("weight")
    private String weight;

    public String getLogin() {
        return login;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public String getActivities() {
        return activities;
    }

    public String getActivitiesId() {
        return activitiesId;
    }

    public String getActivitiesCount() {
        return activitiesCount;
    }

    public String getWeight() {
        return weight;
    }
}

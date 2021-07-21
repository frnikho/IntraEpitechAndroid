package fr.nikho.epitech.intra.flags;

import com.google.gson.annotations.SerializedName;

public abstract class AFlags {

    @SerializedName("value")
    private int value;

    @SerializedName("label")
    private String label;

    @SerializedName("msg")
    private String[] messages;

    @SerializedName("nb")
    private int nb;

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public String[] getMessages() {
        return messages;
    }

    public int getNb() {
        return nb;
    }
}

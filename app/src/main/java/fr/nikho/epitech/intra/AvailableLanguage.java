package fr.nikho.epitech.intra;

import java.util.Arrays;

public enum AvailableLanguage {

    FRENCH("french", "fr", R.drawable.ic_france_flag, "Language actuel: ", "Français", "French", "French"),
    ENGLISH("english", "en", R.drawable.ic_united_kingdom_flag, "Current: ", "Anglais", "English", "English"),
    CHINA("china", "cn", R.drawable.ic_china_flag, "Current: ", "Chinois", "China", "China");

    private String name;
    private String code;
    private int drawableResource;
    private String currentSentence;
    private String frenchName;
    private String englishName;
    private String chinaName;

    AvailableLanguage(String name, String code, int drawableResource, String currentSentence, String frenchName, String englishName, String chinaName) {
        this.name = name;
        this.code = code;
        this.drawableResource = drawableResource;
        this.currentSentence = currentSentence;
        this.frenchName = frenchName;
        this.englishName = englishName;
        this.chinaName = chinaName;
    }

    public String getFrenchName() {
        return frenchName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getChinaName() {
        return chinaName;
    }

    public String getCurrentSentence() {
        return this.currentSentence;
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public int getDrawableResource() {
        return this.drawableResource;
    }

    public static AvailableLanguage getByName(String name) {
        for (AvailableLanguage language: AvailableLanguage.values()) {
            if (name.equalsIgnoreCase(language.getName()))
                return language;
        }
        return null;
    }

    public static String getCurrentSetence(AvailableLanguage language) {
        if (language == FRENCH) {
            return language.getCurrentSentence() + language.getFrenchName();
        } else if (language == ENGLISH || language == CHINA) {
            return language.getCurrentSentence() + language.getEnglishName();
        }
        return "?";
    }
}

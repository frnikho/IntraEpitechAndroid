package fr.nikho.epitech.intra.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Marks {

    public class Mark implements Serializable {
        @SerializedName("scolaryear")
        private int scolarYear;

        @SerializedName("codemodule")
        private String codeModule;

        @SerializedName("titlemodule")
        private String titleModule;

        @SerializedName("codeinstance")
        private String codeInstance;

        @SerializedName("codeacti")
        private String codeActi;

        @SerializedName("title")
        private String title;

        @SerializedName("date")
        private String date;

        @SerializedName("correcteur")
        private String correcteur;

        @SerializedName("final_note")
        private float finalNote;

        @SerializedName("comment")
        private String comment;

        public int getScolarYear() {
            return scolarYear;
        }

        public String getCodeModule() {
            return codeModule;
        }

        public String getTitleModule() {
            return titleModule;
        }

        public String getCodeInstance() {
            return codeInstance;
        }

        public String getCodeActi() {
            return codeActi;
        }

        public String getTitle() {
            return title;
        }

        public String getDate() {
            return date;
        }

        public String getCorrecteur() {
            return correcteur;
        }

        public float getFinalNote() {
            return finalNote;
        }

        public String getComment() {
            return comment;
        }
    }

    public class Modules implements Serializable {
        @SerializedName("scolaryear")
        private int scolaryear;

        @SerializedName("id_user_history")
        private String idUserHistory;

        @SerializedName("codemodule")
        private String codeModule;

        @SerializedName("codeinstance")
        private String codeInstance;

        @SerializedName("title")
        private String title;

        @SerializedName("date_ins")
        private String dateIns;

        @SerializedName("cycle")
        private String cycle;

        @SerializedName("grade")
        private String grade;

        @SerializedName("credits")
        private int credits;

        @SerializedName("barrage")
        private int barrage;

        public int getScolaryear() {
            return scolaryear;
        }

        public String getIdUserHistory() {
            return idUserHistory;
        }

        public String getCodeModule() {
            return codeModule;
        }

        public String getCodeInstance() {
            return codeInstance;
        }

        public String getTitle() {
            return title;
        }

        public String getDateIns() {
            return dateIns;
        }

        public String getCycle() {
            return cycle;
        }

        public String getGrade() {
            return grade;
        }

        public int getCredits() {
            return credits;
        }

        public int getBarrage() {
            return barrage;
        }
    }

    @SerializedName("modules")
    private Modules[] modules;

    @SerializedName("notes")
    private Mark[] marks;

    public Modules[] getModules() {
        return modules;
    }

    public Mark[] getMarks() {
        return marks;
    }
}

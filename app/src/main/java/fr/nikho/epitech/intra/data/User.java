package fr.nikho.epitech.intra.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    public class UserBinomes implements Serializable {
        @SerializedName("binomes")
        private Binomes[] binomes;

        public Binomes[] getBinomes() {
            return binomes;
        }
    }

    public class Flags implements Serializable {

        public class AllFlags implements Serializable {

            @SerializedName("ghost")
            private AFlags ghost;

            @SerializedName("difficulty")
            private AFlags difficulty;

            @SerializedName("remarkable")
            private AFlags remarkable;

            @SerializedName("medal")
            private AFlags medal;

            public AFlags getGhost() {
                return ghost;
            }

            public AFlags getDifficulty() {
                return difficulty;
            }

            public AFlags getRemarkable() {
                return remarkable;
            }

            public AFlags getMedal() {
                return medal;
            }
        }

        public class AFlags implements Serializable {
            @SerializedName("value")
            private int value;

            @SerializedName("label")
            private String label;

            @SerializedName("modules")
            private List<Object> modules;

            @SerializedName("msg")
            private List<String> msg;

            @SerializedName("nb")
            private int nb;

            public int getValue() {
                return value;
            }

            public String getLabel() {
                return label;
            }

            public List<Object> getModules() {
                return modules;
            }

            public List<String> getMsg() {
                return msg;
            }

            public int getNb() {
                return nb;
            }
        }

        @SerializedName("currentLogin")
        private String currentLogin;

        @SerializedName("error")
        private String error;

        @SerializedName("flags")
        private AllFlags flags;

        public String getCurrentLogin() {
            return currentLogin;
        }

        public String getError() {
            return error;
        }

        public AllFlags getFlags() {
            return flags;
        }
    }

    public class UserInfoFields implements Serializable {
        @SerializedName("value")
        private String value;

        @SerializedName("adm")
        private boolean adm;

        @SerializedName("public")
        private boolean isPublic;

        public String getValue() {
            return value;
        }

        public boolean isAdm() {
            return adm;
        }

        public boolean isPublic() {
            return isPublic;
        }
    }

    public class UserInfo implements Serializable {
        @SerializedName("city")
        private UserInfoFields city;

        @SerializedName("telephone")
        private UserInfoFields phone;

        @SerializedName("birthday")
        private UserInfoFields birthday;

        @SerializedName("website")
        private UserInfoFields website;

        @SerializedName("address")
        private UserInfoFields address;

        @SerializedName("email")
        private UserInfoFields email;

        @SerializedName("country")
        private UserInfoFields country;

        @SerializedName("jib")
        private UserInfoFields job;

        @SerializedName("birthplace")
        private UserInfoFields birthplace;

        @SerializedName("poste")
        private UserInfoFields poste;

        @SerializedName("twitter")
        private UserInfoFields twitter;

        @SerializedName("facebook")
        private UserInfoFields facebook;

        @SerializedName("googleplus")
        private UserInfoFields googleplus;

        public UserInfoFields getCity() {
            return city;
        }

        public UserInfoFields getPhone() {
            return phone;
        }

        public UserInfoFields getBirthday() {
            return birthday;
        }

        public UserInfoFields getWebsite() {
            return website;
        }

        public UserInfoFields getAddress() {
            return address;
        }

        public UserInfoFields getEmail() {
            return email;
        }

        public UserInfoFields getCountry() {
            return country;
        }

        public UserInfoFields getJob() {
            return job;
        }

        public UserInfoFields getBirthplace() {
            return birthplace;
        }

        public UserInfoFields getPoste() {
            return poste;
        }

        public UserInfoFields getTwitter() {
            return twitter;
        }

        public UserInfoFields getFacebook() {
            return facebook;
        }

        public UserInfoFields getGoogleplus() {
            return googleplus;
        }
    }

    public class GPA implements Serializable {
        @SerializedName("gpa")
        private String gpa;

        @SerializedName("cycle")
        private String cycle;

        public String getGpa() {
            return gpa;
        }

        public String getCycle() {
            return cycle;
        }
    }

    public class Spice implements Serializable {
        @SerializedName("available_spice")
        private String available_spice;

        @SerializedName("consumed_spice")
        private int consumed_spice;

        public String getAvailable_spice() {
            return available_spice;
        }

        public int getConsumed_spice() {
            return consumed_spice;
        }
    }

    public class Autologin implements Serializable {
        @SerializedName("autologin")
        private String autologin;

        public String getAutologin() {
            return autologin;
        }
    }

    @SerializedName("login")
    private String login;

    @SerializedName("title")
    private String title;

    @SerializedName("internal_email")
    private String internalEmail;

    @SerializedName("lastname")
    private String lastname;

    @SerializedName("firstname")
    private String firstname;

    @SerializedName("userinfo")
    private UserInfo userInfo;

    @SerializedName("picture")
    private String pictureUrl;

    @SerializedName("scolaryear")
    private int scolarYear;

    @SerializedName("promo")
    private int promo;

    @SerializedName("semester")
    private int semester;

    @SerializedName("close")
    private boolean close;

    @SerializedName("ctime")
    private String createdTime;

    @SerializedName("mtime")
    private String updatedTime;

    @SerializedName("id_promo")
    private int idPromo;

    @SerializedName("semester_code")
    private String semesterCode;

    @SerializedName("studentyear")
    private int studentYear;

    @SerializedName("credits")
    private int credits;

    @SerializedName("gpa")
    private GPA[] gpa;

    @SerializedName("spice")
    private Spice spice;

    public String getLogin() {
        return login;
    }

    public String getTitle() {
        return title;
    }

    public String getInternalEmail() {
        return internalEmail;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public String getPicture() {
        return pictureUrl;
    }

    public int getScolarYear() {
        return scolarYear;
    }

    public int getPromo() {
        return promo;
    }

    public int getSemester() {
        return semester;
    }

    public boolean isClose() {
        return close;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public int getIdPromo() {
        return idPromo;
    }

    public String getSemesterCode() {
        return semesterCode;
    }

    public int getStudentYear() {
        return studentYear;
    }

    public int getCredits() {
        return credits;
    }

    public GPA[] getGpa() {
        return gpa;
    }

    public Spice getSpice() {
        return spice;
    }

    public String getPictureUrl() {
        return "https://intra.epitech.eu/" + this.pictureUrl;
    }
}

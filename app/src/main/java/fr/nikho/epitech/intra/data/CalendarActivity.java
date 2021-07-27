package fr.nikho.epitech.intra.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CalendarActivity {

    public class ProfInst implements Serializable {
        @SerializedName("type")
        private String type;

        @SerializedName("login")
        private String login;

        @SerializedName("title")
        private String title;

        @SerializedName("picture")
        private String picture;

        public String getType() {
            return type;
        }

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

    public class Room implements Serializable {
        @SerializedName("code")
        private String code;

        @SerializedName("type")
        private String type;

        @SerializedName("seats")
        private int seats;

        public String getCode() {
            return code;
        }

        public String getType() {
            return type;
        }

        public int getSeats() {
            return seats;
        }
    }

    @SerializedName("scolaryear")
    private String scolarYear;

    @SerializedName("codemodule")
    private String codeModule;

    @SerializedName("codeinstance")
    private String codeInstance;

    @SerializedName("codeacti")
    private String codeActi;

    @SerializedName("codeevent")
    private String codeEvent;

    @SerializedName("semester")
    private int semester;

    @SerializedName("instance_location")
    private String instanceLocation;

    @SerializedName("titlemodule")
    private String titleModule;

    @SerializedName("prof_inst")
    private ProfInst[] profInst;

    @SerializedName("acti_title")
    private String actiTitle;

    @SerializedName("num_event")
    private int num_event;

    @SerializedName("start")
    private String start;

    @SerializedName("end")
    private String end;

    @SerializedName("total_students_registered")
    private int totalStudentsRegistered;

    @SerializedName("title")
    private Object title;

    @SerializedName("type_title")
    private String typeTitle;

    @SerializedName("type_code")
    private String typeCode;

    @SerializedName("is_rdv")
    private String isRdv;

    @SerializedName("nb_hours")
    private String nbHours;

    @SerializedName("allowed_planning_start")
    private String allowedPlanningStart;

    @SerializedName("allowed_planning_end")
    private String allowedPlanningEnd;

    @SerializedName("nb_group")
    private int nbGroup;

    @SerializedName("nb_max_students_projet")
    private Object nbMaxStudentsProjet;

    @SerializedName("room")
    private Room room;

    @SerializedName("dates")
    private Object dates;

    @SerializedName("module_available")
    private boolean moduleAvailable;

    @SerializedName("module_registered")
    private boolean moduleRegistered;

    @SerializedName("past")
    private boolean past;

    @SerializedName("allow_register")
    private boolean allowRegister;

    @SerializedName("event_registered")
    private boolean eventRegistered;

    @SerializedName("display")
    private String display;

    @SerializedName("project")
    private boolean project;

    @SerializedName("rdv_group_registered")
    private Object rdvGroupRegistered;

    @SerializedName("rdv_indiv_registered")
    private Object rdvIndivRegistered;

    @SerializedName("allow_token")
    private boolean allowToken;

    @SerializedName("register_student")
    private boolean registerStudent;

    @SerializedName("register_prof")
    private boolean registerProf;

    @SerializedName("register_month")
    private boolean registerMonth;

    @SerializedName("in_more_than_one_month")
    private boolean inMoreThanOneMonth;

    public String getScolarYear() {
        return scolarYear;
    }

    public String getCodeModule() {
        return codeModule;
    }

    public String getCodeInstance() {
        return codeInstance;
    }

    public String getCodeActi() {
        return codeActi;
    }

    public String getCodeEvent() {
        return codeEvent;
    }

    public int getSemester() {
        return semester;
    }

    public String getInstanceLocation() {
        return instanceLocation;
    }

    public String getTitleModule() {
        return titleModule;
    }

    public ProfInst[] getProfInst() {
        return profInst;
    }

    public String getActiTitle() {
        return actiTitle;
    }

    public int getNum_event() {
        return num_event;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public int getTotalStudentsRegistered() {
        return totalStudentsRegistered;
    }

    public Object getTitle() {
        return title;
    }

    public String getTypeTitle() {
        return typeTitle;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public String getIsRdv() {
        return isRdv;
    }

    public String getNbHours() {
        return nbHours;
    }

    public String getAllowedPlanningStart() {
        return allowedPlanningStart;
    }

    public String getAllowedPlanningEnd() {
        return allowedPlanningEnd;
    }

    public int getNbGroup() {
        return nbGroup;
    }

    public Object getNbMaxStudentsProjet() {
        return nbMaxStudentsProjet;
    }

    public Room getRoom() {
        return room;
    }

    public Object getDates() {
        return dates;
    }

    public boolean isModuleAvailable() {
        return moduleAvailable;
    }

    public boolean isModuleRegistered() {
        return moduleRegistered;
    }

    public boolean isPast() {
        return past;
    }

    public boolean isAllowRegister() {
        return allowRegister;
    }

    public boolean isEventRegistered() {
        return eventRegistered;
    }

    public String getDisplay() {
        return display;
    }

    public boolean isProject() {
        return project;
    }

    public Object getRdvGroupRegistered() {
        return rdvGroupRegistered;
    }

    public Object getRdvIndivRegistered() {
        return rdvIndivRegistered;
    }

    public boolean isAllowToken() {
        return allowToken;
    }

    public boolean isRegisterStudent() {
        return registerStudent;
    }

    public boolean isRegisterProf() {
        return registerProf;
    }

    public boolean isRegisterMonth() {
        return registerMonth;
    }

    public boolean isInMoreThanOneMonth() {
        return inMoreThanOneMonth;
    }
}

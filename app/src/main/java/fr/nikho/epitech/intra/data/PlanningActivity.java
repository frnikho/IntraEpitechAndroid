package fr.nikho.epitech.intra.data;

import com.google.gson.annotations.SerializedName;

public class PlanningActivity {

    public class ProfInst {
        @SerializedName("type")
        public String type;

        @SerializedName("login")
        public String login;
    }

    public class Room {
        @SerializedName("code")
        public String code;

        @SerializedName("type")
        public String type;

        @SerializedName("seats")
        public int seats;
    }

    @SerializedName("scolaryear")
    public String scolaryear;

    @SerializedName("codemodule")
    public String codemodule;

    @SerializedName("codeinstance")
    public String codeinstance;

    @SerializedName("codeacti")
    public String codeacti;

    @SerializedName("codeevent")
    public String codeevent;

    @SerializedName("semester")
    public int semester;

    @SerializedName("instance_location")
    public String instanceLocation;

    @SerializedName("titlemodule")
    public String titleModule;

    @SerializedName("prof_inst")
    public ProfInst[] profInst;

    @SerializedName("acti_title")
    public String activityTitle;

    @SerializedName("num_event")
    public int numEvent;

    @SerializedName("start")
    public String start;

    @SerializedName("end")
    public String end;

    @SerializedName("total_students_registered")
    public int totalStudentsRegistered;

    @SerializedName("title")
    public Object title;

    @SerializedName("type_title")
    public String typeTitle;

    @SerializedName("type_code")
    public String typeCode;

    @SerializedName("is_rdv")
    public String isRdv;

    @SerializedName("nb_hours")
    public String nbHours;

    @SerializedName("allowed_planning_start")
    public String allowedPlanningStart;

    @SerializedName("allowed_planning_end")
    public String allowedPlanningEnd;

    @SerializedName("nb_group")
    public int nbGroup;

    @SerializedName("nb_max_students_projet")
    public int nbMaxStudentsProjet;

    @SerializedName("room")
    public Room room;

    @SerializedName("dates")
    public Object dates;

    @SerializedName("module_available")
    public boolean moduleAvailable;

    @SerializedName("module_registered")
    public boolean moduleRegistered;

    @SerializedName("past")
    public boolean past;

    @SerializedName("allow_register")
    public boolean allowRegister;

    @SerializedName("event_registered")
    public String eventRegistered;

    @SerializedName("display")
    public String display;

    @SerializedName("project")
    public boolean project;

    @SerializedName("rdv_group_registered")
    public Object rdvGroupRegistered;

    @SerializedName("rdv_indiv_registered")
    public Object rdvIndivRegistered;

    @SerializedName("allow_token")
    public boolean allowToken;

    @SerializedName("register_student")
    public boolean registerStudent;

    @SerializedName("register_prof")
    public boolean registerProf;

    @SerializedName("register_month")
    public boolean registerMonth;

    @SerializedName("in_more_than_one_month")
    public boolean inMoreThanOneMonth;

    public String getScolaryear() {
        return scolaryear;
    }

    public String getCodemodule() {
        return codemodule;
    }

    public String getCodeinstance() {
        return codeinstance;
    }

    public String getCodeacti() {
        return codeacti;
    }

    public String getCodeevent() {
        return codeevent;
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

    public String getActivityTitle() {
        return activityTitle;
    }

    public int getNumEvent() {
        return numEvent;
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

    public int getNbMaxStudentsProjet() {
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

    public String getEventRegistered() {
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

    public String getActivityUrl() {
        String url = "https://intra.epitech.eu/module/";
        url += this.scolaryear + "/" + this.codemodule + "/" + this.codeinstance + "/" + this.codeacti + "/";
        return url;
    }

}
package fr.nikho.epitech.intra.data;

import com.google.gson.annotations.SerializedName;

public class Activity {

    public class StudentRegistered{
        @SerializedName("registered")
        public String registered;
    }

    public class Event {
        @SerializedName("code")
        public String code;

        @SerializedName("num_event")
        public String numEvent;

        @SerializedName("seats")
        public String seats;

        @SerializedName("title")
        public Object title;

        @SerializedName("description")
        public Object description;

        @SerializedName("nb_inscrits")
        public String nbInscrits;

        @SerializedName("begin")
        public String begin;

        @SerializedName("end")
        public String end;

        @SerializedName("id_activite")
        public String idActivite;

        @SerializedName("location")
        public String location;

        @SerializedName("nb_max_students_projet")
        public Object nbMaxStudentsProjet;

        @SerializedName("already_register")
        public Object alreadyRegister;

        @SerializedName("user_status")
        public Object userStatus;

        @SerializedName("allow_token")
        public String allowToken;

        @SerializedName("assistants")
        public Object[] assistants;
    }

    @SerializedName("scolaryear")
    public String scolaryear;

    @SerializedName("codemodule")
    public String codemodule;

    @SerializedName("codeinstance")
    public String codeinstance;

    @SerializedName("codeacti")
    public String codeacti;

    @SerializedName("call_ihk")
    public Object callIhk;

    @SerializedName("slug")
    public Object slug;

    @SerializedName("instance_location")
    public String instanceLocation;

    @SerializedName("module_title")
    public String moduleTitle;

    @SerializedName("title")
    public String title;

    @SerializedName("description")
    public String description;

    @SerializedName("type_title")
    public String typeTitle;

    @SerializedName("type_code")
    public String typeCode;

    @SerializedName("begin")
    public String begin;

    @SerializedName("start")
    public String start;

    @SerializedName("end_register")
    public Object endRegister;

    @SerializedName("deadline")
    public Object deadline;

    @SerializedName("end")
    public String end;

    @SerializedName("nb_hours")
    public String nbHours;

    @SerializedName("nb_group")
    public int nbGroup;

    @SerializedName("num")
    public int num;

    @SerializedName("register")
    public String register;

    @SerializedName("register_by_bloc")
    public String registerByBloc;

    @SerializedName("register_prof")
    public String registerProf;

    @SerializedName("title_location_type")
    public Object titleLocationType;

    @SerializedName("is_projet")
    public boolean isProjet;

    @SerializedName("id_projet")
    public Object idProjet;

    @SerializedName("project_title")
    public Object projectTitle;

    @SerializedName("is_note")
    public boolean isNote;

    @SerializedName("nb_notes")
    public Object nbNotes;

    @SerializedName("is_blocins")
    public boolean isBlocins;

    @SerializedName("rdv_status")
    public String rdvStatus;

    @SerializedName("id_bareme")
    public Object idBareme;

    @SerializedName("title_bareme")
    public Object titleBareme;

    @SerializedName("archive")
    public String archive;

    @SerializedName("hash_elearning")
    public Object hashElearning;

    @SerializedName("ged_node_adm")
    public Object gedNodeAdm;

    @SerializedName("nb_planified")
    public int nbPlanified;

    @SerializedName("hidden")
    public boolean hidden;

    @SerializedName("project")
    public Object project;

    @SerializedName("StudentRegistered")
    public StudentRegistered studentRegistered;

    @SerializedName("events")
    public Event[] events;

}

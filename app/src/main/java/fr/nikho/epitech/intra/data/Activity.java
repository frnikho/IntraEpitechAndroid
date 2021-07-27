package fr.nikho.epitech.intra.data;

import com.google.gson.annotations.SerializedName;

public class Activity {

    public class StudentRegistered {
        @SerializedName("registered")
        private String registered;

        public String getRegistered() {
            return registered;
        }
    }

    public class Event {
        @SerializedName("code")
        private String code;

        @SerializedName("num_event")
        private String numEvent;

        @SerializedName("seats")
        private String seats;

        @SerializedName("title")
        private Object title;

        @SerializedName("description")
        private Object description;

        @SerializedName("nb_inscrits")
        private String nbInscrits;

        @SerializedName("begin")
        private String begin;

        @SerializedName("end")
        private String end;

        @SerializedName("id_activite")
        private String idActivite;

        @SerializedName("location")
        private String location;

        @SerializedName("nb_max_students_projet")
        private Object nbMaxStudentsProjet;

        @SerializedName("already_register")
        private Object alreadyRegister;

        @SerializedName("user_status")
        private Object userStatus;

        @SerializedName("allow_token")
        private String allowToken;

        @SerializedName("assistants")
        private Object[] assistants;

        public String getCode() {
            return code;
        }

        public String getNumEvent() {
            return numEvent;
        }

        public String getSeats() {
            return seats;
        }

        public Object getTitle() {
            return title;
        }

        public Object getDescription() {
            return description;
        }

        public String getNbInscrits() {
            return nbInscrits;
        }

        public String getBegin() {
            return begin;
        }

        public String getEnd() {
            return end;
        }

        public String getIdActivite() {
            return idActivite;
        }

        public String getLocation() {
            return location;
        }

        public Object getNbMaxStudentsProjet() {
            return nbMaxStudentsProjet;
        }

        public Object getAlreadyRegister() {
            return alreadyRegister;
        }

        public Object getUserStatus() {
            return userStatus;
        }

        public String getAllowToken() {
            return allowToken;
        }

        public Object[] getAssistants() {
            return assistants;
        }
    }

    @SerializedName("scolaryear")
    private String scolaryear;

    @SerializedName("codemodule")
    private String codemodule;

    @SerializedName("codeinstance")
    private String codeinstance;

    @SerializedName("codeacti")
    private String codeacti;

    @SerializedName("call_ihk")
    private Object callIhk;

    @SerializedName("slug")
    private Object slug;

    @SerializedName("instance_location")
    private String instanceLocation;

    @SerializedName("module_title")
    private String moduleTitle;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("type_title")
    private String typeTitle;

    @SerializedName("type_code")
    private String typeCode;

    @SerializedName("begin")
    private String begin;

    @SerializedName("start")
    private String start;

    @SerializedName("end_register")
    private Object endRegister;

    @SerializedName("deadline")
    private Object deadline;

    @SerializedName("end")
    private String end;

    @SerializedName("nb_hours")
    private String nbHours;

    @SerializedName("nb_group")
    private int nbGroup;

    @SerializedName("num")
    private int num;

    @SerializedName("register")
    private String register;

    @SerializedName("register_by_bloc")
    private String registerByBloc;

    @SerializedName("register_prof")
    private String registerProf;

    @SerializedName("title_location_type")
    private Object titleLocationType;

    @SerializedName("is_projet")
    private boolean isProjet;

    @SerializedName("id_projet")
    private Object idProjet;

    @SerializedName("project_title")
    private Object projectTitle;

    @SerializedName("is_note")
    private boolean isNote;

    @SerializedName("nb_notes")
    private Object nbNotes;

    @SerializedName("is_blocins")
    private boolean isBlocins;

    @SerializedName("rdv_status")
    private String rdvStatus;

    @SerializedName("id_bareme")
    private Object idBareme;

    @SerializedName("title_bareme")
    private Object titleBareme;

    @SerializedName("archive")
    private String archive;

    @SerializedName("hash_elearning")
    private Object hashElearning;

    @SerializedName("ged_node_adm")
    private Object gedNodeAdm;

    @SerializedName("nb_planified")
    private int nbPlanified;

    @SerializedName("hidden")
    private boolean hidden;

    @SerializedName("project")
    private Object project;

    @SerializedName("StudentRegistered")
    private StudentRegistered studentRegistered;

    @SerializedName("events")
    private Event[] events;

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

    public Object getCallIhk() {
        return callIhk;
    }

    public Object getSlug() {
        return slug;
    }

    public String getInstanceLocation() {
        return instanceLocation;
    }

    public String getModuleTitle() {
        return moduleTitle;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTypeTitle() {
        return typeTitle;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public String getBegin() {
        return begin;
    }

    public String getStart() {
        return start;
    }

    public Object getEndRegister() {
        return endRegister;
    }

    public Object getDeadline() {
        return deadline;
    }

    public String getEnd() {
        return end;
    }

    public String getNbHours() {
        return nbHours;
    }

    public int getNbGroup() {
        return nbGroup;
    }

    public int getNum() {
        return num;
    }

    public String getRegister() {
        return register;
    }

    public String getRegisterByBloc() {
        return registerByBloc;
    }

    public String getRegisterProf() {
        return registerProf;
    }

    public Object getTitleLocationType() {
        return titleLocationType;
    }

    public boolean isProjet() {
        return isProjet;
    }

    public Object getIdProjet() {
        return idProjet;
    }

    public Object getProjectTitle() {
        return projectTitle;
    }

    public boolean isNote() {
        return isNote;
    }

    public Object getNbNotes() {
        return nbNotes;
    }

    public boolean isBlocins() {
        return isBlocins;
    }

    public String getRdvStatus() {
        return rdvStatus;
    }

    public Object getIdBareme() {
        return idBareme;
    }

    public Object getTitleBareme() {
        return titleBareme;
    }

    public String getArchive() {
        return archive;
    }

    public Object getHashElearning() {
        return hashElearning;
    }

    public Object getGedNodeAdm() {
        return gedNodeAdm;
    }

    public int getNbPlanified() {
        return nbPlanified;
    }

    public boolean isHidden() {
        return hidden;
    }

    public Object getProject() {
        return project;
    }

    public StudentRegistered getStudentRegistered() {
        return studentRegistered;
    }

    public Event[] getEvents() {
        return events;
    }
}

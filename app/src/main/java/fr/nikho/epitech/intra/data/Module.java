package fr.nikho.epitech.intra.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Module {

    public class StudentRegistered {
        @SerializedName("login")
        private String login;

        @SerializedName("picture")
        private String picture;

        @SerializedName("title")
        private String title;

        @SerializedName("location")
        private String location;

        @SerializedName("promo")
        private int promo;

        @SerializedName("course_code")
        private String courseCode;

        @SerializedName("grade")
        private String grade;

        @SerializedName("cycle")
        private String cycle;

        @SerializedName("date_ins")
        private String dateIns;

        @SerializedName("credits")
        private int credits;

        @SerializedName("flags")
        private Object[] flags;

        @SerializedName("semester")
        private String semester;
    }

    public class Manager {
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

    public class Activity {

        public class Assistant implements Serializable {
            @SerializedName("login")
            private String login;

            @SerializedName("title")
            private String title;

            @SerializedName("picture")
            private Object picture;

            @SerializedName("manager_status")
            private String managerStatus;

            public String getLogin() {
                return login;
            }

            public String getTitle() {
                return title;
            }

            public Object getPicture() {
                return picture;
            }

            public String getManagerStatus() {
                return managerStatus;
            }
        }

        public class Event implements Serializable {
            @SerializedName("code")
            private String code;

            @SerializedName("num_event")
            private String num_event;

            @SerializedName("seats")
            private String seats;

            @SerializedName("title")
            private Object title;

            @SerializedName("description")
            private String description;

            @SerializedName("nb_inscrits")
            private String nb_inscrits;

            @SerializedName("begin")
            private String begin;

            @SerializedName("end")
            private String end;

            @SerializedName("id_activite")
            private String id_activite;

            @SerializedName("location")
            private String location;

            @SerializedName("nb_max_students_projet")
            private Object nb_max_students_projet;

            @SerializedName("already_register")
            private Object already_register;

            @SerializedName("user_status")
            private String user_status;

            @SerializedName("allow_token")
            private String allow_token;

            @SerializedName("assistants")
            private Assistant[] assistants;

            public String getCode() {
                return code;
            }

            public String getNumEvent() {
                return num_event;
            }

            public String getSeats() {
                return seats;
            }

            public Object getTitle() {
                return title;
            }

            public String getDescription() {
                return description;
            }

            public String getNbInscrits() {
                return nb_inscrits;
            }

            public String getBegin() {
                return begin;
            }

            public String getEnd() {
                return end;
            }

            public String getIdActivite() {
                return id_activite;
            }

            public String getLocation() {
                return location;
            }

            public Object getNbMaxStudentsProjet() {
                return nb_max_students_projet;
            }

            public Object getAlreadyRegister() {
                return already_register;
            }

            public String getUserStatus() {
                return user_status;
            }

            public String getAllowToken() {
                return allow_token;
            }

            public Assistant[] getAssistants() {
                return assistants;
            }
        }

        @SerializedName("codeacti")
        public String codeacti;

        @SerializedName("call_ihk")
        public Object call_ihk;

        @SerializedName("slug")
        public Object slug;

        @SerializedName("instance_location")
        public String instance_location;

        @SerializedName("module_title")
        public String module_title;

        @SerializedName("title")
        public String title;

        @SerializedName("description")
        public String description;

        @SerializedName("type_title")
        public String type_title;

        @SerializedName("type_code")
        public String type_code;

        @SerializedName("begin")
        public String begin;

        @SerializedName("start")
        public String start;

        @SerializedName("end_register")
        public Object end_register;

        @SerializedName("deadline")
        public Object deadline;

        @SerializedName("end")
        public String end;

        @SerializedName("nb_hours")
        public String nb_hours;

        @SerializedName("nb_group")
        public int nb_group;

        @SerializedName("num")
        public int num;

        @SerializedName("register")
        public String register;

        @SerializedName("register_by_bloc")
        public String register_by_bloc;

        @SerializedName("register_prof")
        public String register_prof;

        @SerializedName("title_location_type")
        public Object title_location_type;

        @SerializedName("is_projet")
        public boolean is_projet;

        @SerializedName("id_projet")
        public Object id_projet;

        @SerializedName("project_title")
        public Object project_title;

        @SerializedName("is_note")
        public boolean is_note;

        @SerializedName("nb_notes")
        public Object nb_notes;

        @SerializedName("is_blocins")
        public boolean is_blocins;

        @SerializedName("rdv_status")
        public String rdv_status;

        @SerializedName("id_bareme")
        public Object id_bareme;

        @SerializedName("title_bareme")
        public Object title_bareme;

        @SerializedName("archive")
        public String archive;

        @SerializedName("hash_elearning")
        public Object hash_elearning;

        @SerializedName("ged_node_adm")
        public Object ged_node_adm;

        @SerializedName("nb_planified")
        public int nb_planified;

        @SerializedName("hidden")
        public boolean hidden;

        @SerializedName("project")
        public Object project;

        @SerializedName("events")
        public Event[] events;

        public String getCodeActi() {
            return codeacti;
        }

        public Object getCallIhk() {
            return call_ihk;
        }

        public Object getSlug() {
            return slug;
        }

        public String getInstanceLocation() {
            return instance_location;
        }

        public String getModuleTitle() {
            return module_title;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getTypeTitle() {
            return type_title;
        }

        public String getTypeCode() {
            return type_code;
        }

        public String getBegin() {
            return begin;
        }

        public String getStart() {
            return start;
        }

        public Object getEndRegister() {
            return end_register;
        }

        public Object getDeadline() {
            return deadline;
        }

        public String getEnd() {
            return end;
        }

        public String getNbHours() {
            return nb_hours;
        }

        public int getNbGroup() {
            return nb_group;
        }

        public int getNum() {
            return num;
        }

        public String getRegister() {
            return register;
        }

        public String getRegisterByBloc() {
            return register_by_bloc;
        }

        public String getRegisterProf() {
            return register_prof;
        }

        public Object getTitleLocationType() {
            return title_location_type;
        }

        public boolean isProjet() {
            return is_projet;
        }

        public Object getIdProjet() {
            return id_projet;
        }

        public Object getProjectTitle() {
            return project_title;
        }

        public boolean isNote() {
            return is_note;
        }

        public Object getNbNotes() {
            return nb_notes;
        }

        public boolean isBlocins() {
            return is_blocins;
        }

        public String getRdvStatus() {
            return rdv_status;
        }

        public Object getIdBareme() {
            return id_bareme;
        }

        public Object getTitleBareme() {
            return title_bareme;
        }

        public String getArchive() {
            return archive;
        }

        public Object getHashElearning() {
            return hash_elearning;
        }

        public Object getGedNodeAdm() {
            return ged_node_adm;
        }

        public int getNbPlanified() {
            return nb_planified;
        }

        public boolean isHidden() {
            return hidden;
        }

        public Object getProject() {
            return project;
        }

        public Event[] getEvents() {
            return events;
        }
    }

    @SerializedName("scolaryear")
    public String scolaryear;

    @SerializedName("codemodule")
    public String codemodule;

    @SerializedName("codeinstance")
    public String codeinstance;

    @SerializedName("semester")
    public int semester;

    @SerializedName("scolaryear_template")
    public String scolaryearTemplate;

    @SerializedName("title")
    public String title;

    @SerializedName("begin")
    public String begin;

    @SerializedName("end_register")
    public String endRegister;

    @SerializedName("end")
    public String end;

    @SerializedName("past")
    public String past;

    @SerializedName("closed")
    public String closed;

    @SerializedName("opened")
    public String opened;

    @SerializedName("user_credits")
    public String userCredits;

    @SerializedName("credits")
    public int credits;

    @SerializedName("description")
    public String description;

    @SerializedName("competence")
    public String competence;

    @SerializedName("flags")
    public String flags;

    @SerializedName("instance_flags")
    public String instanceFlags;

    @SerializedName("max_ins")
    public Object maxIns;

    @SerializedName("instance_location")
    public String instanceLocation;

    @SerializedName("hidden")
    public String hidden;

    @SerializedName("old_acl_backup")
    public Object oldAclBackup;

    @SerializedName("resp")
    public Manager[] resp;

    @SerializedName("assistant")
    public Object[] assistant;

    @SerializedName("rights")
    public Object rights;

    @SerializedName("template_resp")
    public Manager[] templateResp;

    @SerializedName("allow_register")
    public int allowRegister;

    @SerializedName("date_ins")
    public String dateIns;

    @SerializedName("student_registered")
    public int studentRegistered;

    @SerializedName("student_grade")
    public String studentGrade;

    @SerializedName("student_credits")
    public int studentCredits;

    @SerializedName("color")
    public String color;

    @SerializedName("student_flags")
    public String studentFlags;

    @SerializedName("current_resp")
    public boolean currentResp;

    @SerializedName("activites")
    public Module.Activity[] activites;

    public String getScolaryear() {
        return scolaryear;
    }

    public String getCodemodule() {
        return codemodule;
    }

    public String getCodeinstance() {
        return codeinstance;
    }

    public int getSemester() {
        return semester;
    }

    public String getScolaryearTemplate() {
        return scolaryearTemplate;
    }

    public String getTitle() {
        return title;
    }

    public String getBegin() {
        return begin;
    }

    public String getEndRegister() {
        return endRegister;
    }

    public String getEnd() {
        return end;
    }

    public String getPast() {
        return past;
    }

    public String getClosed() {
        return closed;
    }

    public String getOpened() {
        return opened;
    }

    public String getUserCredits() {
        return userCredits;
    }

    public int getCredits() {
        return credits;
    }

    public String getDescription() {
        return description;
    }

    public String getCompetence() {
        return competence;
    }

    public String getFlags() {
        return flags;
    }

    public String getInstanceFlags() {
        return instanceFlags;
    }

    public Object getMaxIns() {
        return maxIns;
    }

    public String getInstanceLocation() {
        return instanceLocation;
    }

    public String getHidden() {
        return hidden;
    }

    public Object getOldAclBackup() {
        return oldAclBackup;
    }

    public Manager[] getResp() {
        return resp;
    }

    public Object[] getAssistant() {
        return assistant;
    }

    public Object getRights() {
        return rights;
    }

    public Manager[] getTemplateResp() {
        return templateResp;
    }

    public int getAllowRegister() {
        return allowRegister;
    }

    public String getDateIns() {
        return dateIns;
    }

    public int getStudentRegistered() {
        return studentRegistered;
    }

    public String getStudentGrade() {
        return studentGrade;
    }

    public int getStudentCredits() {
        return studentCredits;
    }

    public String getColor() {
        return color;
    }

    public String getStudentFlags() {
        return studentFlags;
    }

    public boolean isCurrentResp() {
        return currentResp;
    }

    public Module.Activity[] getActivites() {
        return activites;
    }
}

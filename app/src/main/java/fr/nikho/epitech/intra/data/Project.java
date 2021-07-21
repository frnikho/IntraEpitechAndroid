package fr.nikho.epitech.intra.data;

import com.google.gson.annotations.SerializedName;

public class Project {

    public class Student {
        @SerializedName("login")
        public String login;

        @SerializedName("date_ins")
        public String dateIns;

        @SerializedName("date_modif")
        public Object dateModif;

        @SerializedName("status")
        public String status;

        @SerializedName("picture")
        public String picture;

        @SerializedName("title")
        public String title;

        public String getLogin() {
            return login;
        }

        public String getDateIns() {
            return dateIns;
        }

        public Object getDateModif() {
            return dateModif;
        }

        public String getStatus() {
            return status;
        }

        public String getPicture() {
            return picture;
        }

        public String getTitle() {
            return title;
        }
    }

    public class Registered {
        @SerializedName("id")
        public String id;

        @SerializedName("title")
        public String title;

        @SerializedName("code")
        public String code;

        @SerializedName("final_note")
        public Object finalNote;

        @SerializedName("repository")
        public Object repository;

        @SerializedName("closed")
        public boolean closed;

        @SerializedName("master")
        public Student master;

        @SerializedName("members")
        public Student[] members;

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getCode() {
            return code;
        }

        public Object getFinalNote() {
            return finalNote;
        }

        public Object getRepository() {
            return repository;
        }

        public boolean isClosed() {
            return closed;
        }

        public Student getMaster() {
            return master;
        }

        public Student[] getMembers() {
            return members;
        }
    }

    @SerializedName("scolaryear")
    public String scolaryear;

    @SerializedName("codemodule")
    public String codemodule;

    @SerializedName("codeinstance")
    public String codeinstance;

    @SerializedName("codeacti")
    public String codeacti;

    @SerializedName("instance_location")
    public String instanceLocation;

    @SerializedName("module_title")
    public String moduleTitle;

    @SerializedName("id_activite")
    public String idActivite;

    @SerializedName("project_title")
    public String projectTitle;

    @SerializedName("type_title")
    public String typeTitle;

    @SerializedName("type_code")
    public String typeCode;

    @SerializedName("register")
    public boolean register;

    @SerializedName("register_by_bloc")
    public String registerByBloc;

    @SerializedName("register_prof")
    public String registerProf;

    @SerializedName("nb_min")
    public int nbMin;

    @SerializedName("nb_max")
    public int nbMax;

    @SerializedName("begin")
    public String begin;

    @SerializedName("end")
    public String end;

    @SerializedName("end_register")
    public String endRegister;

    @SerializedName("deadline")
    public Object deadline;

    @SerializedName("is_rdv")
    public boolean isRdv;

    @SerializedName("instance_allowed")
    public String instanceAllowed;

    @SerializedName("title")
    public String title;

    @SerializedName("description")
    public String description;

    @SerializedName("closed")
    public boolean closed;

    @SerializedName("over")
    public int over;

    @SerializedName("over_deadline")
    public Object overDeadline;

    @SerializedName("date_access")
    public boolean dateAccess;

    @SerializedName("instance_registered")
    public String instanceRegistered;

    @SerializedName("user_project_status")
    public Object userProjectStatus;

    @SerializedName("root_slug")
    public String rootSlug;

    @SerializedName("forum_path")
    public Object forum_path;

    @SerializedName("slug")
    public Object slug;

    @SerializedName("call_ihk")
    public String callIhk;

    @SerializedName("nb_notes")
    public int nbNotes;

    @SerializedName("user_project_master")
    public Object userProjectMaster;

    @SerializedName("user_project_code")
    public Object userProjectCode;

    @SerializedName("user_project_title")
    public Object userProjectTitle;

    @SerializedName("registered_instance")
    public int registeredInstance;

    @SerializedName("registered")
    public Registered[] registered;

    @SerializedName("notregistered")
    public Module.StudentRegistered[] notRegistered;

    @SerializedName("urls")
    public Object urls;

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

    public String getInstanceLocation() {
        return instanceLocation;
    }

    public String getModuleTitle() {
        return moduleTitle;
    }

    public String getIdActivite() {
        return idActivite;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public String getTypeTitle() {
        return typeTitle;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public boolean isRegister() {
        return register;
    }

    public String getRegisterByBloc() {
        return registerByBloc;
    }

    public String getRegisterProf() {
        return registerProf;
    }

    public int getNbMin() {
        return nbMin;
    }

    public int getNbMax() {
        return nbMax;
    }

    public String getBegin() {
        return begin;
    }

    public String getEnd() {
        return end;
    }

    public String getEndRegister() {
        return endRegister;
    }

    public Object getDeadline() {
        return deadline;
    }

    public boolean isRdv() {
        return isRdv;
    }

    public String getInstanceAllowed() {
        return instanceAllowed;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isClosed() {
        return closed;
    }

    public int getOver() {
        return over;
    }

    public Object getOverDeadline() {
        return overDeadline;
    }

    public boolean isDateAccess() {
        return dateAccess;
    }

    public String getInstanceRegistered() {
        return instanceRegistered;
    }

    public Object getUserProjectStatus() {
        return userProjectStatus;
    }

    public String getRootSlug() {
        return rootSlug;
    }

    public Object getForum_path() {
        return forum_path;
    }

    public Object getSlug() {
        return slug;
    }

    public String getCallIhk() {
        return callIhk;
    }

    public int getNbNotes() {
        return nbNotes;
    }

    public Object getUserProjectMaster() {
        return userProjectMaster;
    }

    public Object getUserProjectCode() {
        return userProjectCode;
    }

    public Object getUserProjectTitle() {
        return userProjectTitle;
    }

    public int getRegisteredInstance() {
        return registeredInstance;
    }

    public Registered[] getRegistered() {
        return registered;
    }

    public Module.StudentRegistered[] getNotRegistered() {
        return notRegistered;
    }

    public Object getUrls() {
        return urls;
    }
}

package fr.nikho.epitech.intra.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Arrays;

public class Dashboard implements Serializable {

    public class Board implements Serializable {
        public class Project implements Serializable {

            @SerializedName("title")
            private String title;

            @SerializedName("title_link")
            private String titleLink;

            @SerializedName("timeline_start")
            private String timelineStart;

            @SerializedName("timeline_end")
            private String timelineEnd;

            @SerializedName("timeline_barre")
            private String timelineBarre;

            @SerializedName("date_inscription")
            private boolean dateInscription;

            @SerializedName("id_activite")
            private String idActivite;

            @SerializedName("soutenance_name")
            private boolean soutenanceName;

            @SerializedName("soutenance_link")
            private boolean soutenanceLink;

            @SerializedName("soutenance_date")
            private boolean soutenanceDate;

            @SerializedName("soutenance_salle")
            private boolean soutenanceSalle;

            public String getTitle() {
                return title;
            }

            public String getTitleLink() {
                return titleLink;
            }

            public String getTimelineStart() {
                return timelineStart;
            }

            public String getTimelineEnd() {
                return timelineEnd;
            }

            public String getTimelineBarre() {
                return timelineBarre;
            }

            public boolean isDateInscription() {
                return dateInscription;
            }

            public String getIdActivite() {
                return idActivite;
            }

            public boolean isSoutenanceName() {
                return soutenanceName;
            }

            public boolean isSoutenanceLink() {
                return soutenanceLink;
            }

            public boolean isSoutenanceDate() {
                return soutenanceDate;
            }

            public boolean isSoutenanceSalle() {
                return soutenanceSalle;
            }
        }
        public class Note implements Serializable {

        }
        public class Susie implements Serializable {

        }
        public class Activity implements Serializable {
            @SerializedName("title")
            private String title;

            @SerializedName("module")
            private String module;

            @SerializedName("module_link")
            private String moduleLink;

            @SerializedName("module_code")
            private String moduleCode;

            @SerializedName("title_link")
            private String titleLink;

            @SerializedName("timeline_start")
            private String timelineStart;

            @SerializedName("timeline_end")
            private String timelineEnd;

            @SerializedName("timeline_barre")
            private String timelineBarre;

            @SerializedName("date_inscription")
            private String dateInscription;

            @SerializedName("salle")
            private String salle;

            @SerializedName("intervenant")
            private String intervenant;

            @SerializedName("token")
            private Object token;

            @SerializedName("token_link")
            private String tokenLink;

            @SerializedName("register_link")
            private String registerLink;

            public String getTitle() {
                return title;
            }

            public String getModule() {
                return module;
            }

            public String getModuleLink() {
                return moduleLink;
            }

            public String getModuleCode() {
                return moduleCode;
            }

            public String getTitleLink() {
                return titleLink;
            }

            public String getTimelineStart() {
                return timelineStart;
            }

            public String getTimelineEnd() {
                return timelineEnd;
            }

            public String getTimelineBarre() {
                return timelineBarre;
            }

            public String getDateInscription() {
                return dateInscription;
            }

            public String getSalle() {
                return salle;
            }

            public String getIntervenant() {
                return intervenant;
            }

            public Object getToken() {
                return token;
            }

            public String getTokenLink() {
                return tokenLink;
            }

            public String getRegisterLink() {
                return registerLink;
            }
        }
        public class Module implements Serializable {
            @SerializedName("title")
            private String title;

            @SerializedName("title_link")
            private String titleLink;

            @SerializedName("timeline_start")
            private String timelineStart;

            @SerializedName("timeline_end")
            private String timelineEnd;

            @SerializedName("timeline_barre")
            private String timelineBarre;

            @SerializedName("date_inscription")
            private String dateInscription;

            public String getTitle() {
                return title;
            }

            public String getTitleLink() {
                return titleLink;
            }

            public String getTimelineStart() {
                return timelineStart;
            }

            public String getTimelineEnd() {
                return timelineEnd;
            }

            public String getTimelineBarre() {
                return timelineBarre;
            }

            public String getDateInscription() {
                return dateInscription;
            }
        }
        public class Stage implements Serializable {

        }
        public class Ticket implements Serializable{

        }

        @SerializedName("projets")
        private Project[] projets;

        @SerializedName("notes")
        private Note[] notes;

        @SerializedName("susies")
        private Susie[] susies;

        @SerializedName("activites")
        private Activity[] activities;

        @SerializedName("modules")
        private Module[] modules;

        @SerializedName("stages")
        private Stage[] stages;

        @SerializedName("tickets")
        private Ticket[] ticket;

        public Project[] getProjects() {
            return projets;
        }

        public Note[] getNotes() {
            return notes;
        }

        public Susie[] getSusies() {
            return susies;
        }

        public Activity[] getActivities() {
            return activities;
        }

        public Module[] getModules() {
            return modules;
        }

        public Stage[] getStages() {
            return stages;
        }

        public Ticket[] getTicket() {
            return ticket;
        }
    }

    public class History {

        public class User {
            @SerializedName("picture")
            private String picture;

            @SerializedName("title")
            private String title;

            @SerializedName("url")
            private String url;
        }

        @SerializedName("title")
        private String title;

        @SerializedName("user")
        private User user;

        @SerializedName("content")
        private String content;

        @SerializedName("date")
        private String date;

        @SerializedName("id")
        private String id;

        @SerializedName("visible")
        private String visible;

        @SerializedName("id_activite")
        private String idActivite;

        @SerializedName("classes")
        private String classes;

        public String getTitle() {
            return title;
        }

        public User getUser() {
            return user;
        }

        public String getContent() {
            return content;
        }

        public String getDate() {
            return date;
        }

        public String getId() {
            return id;
        }

        public String getVisible() {
            return visible;
        }

        public String getIdActivite() {
            return idActivite;
        }

        public String getClasses() {
            return classes;
        }
    }

    public class Infos implements Serializable {
        @SerializedName("location")
        private String location;
    }

    public class Note implements Serializable {
        @SerializedName("credits_min")
        private String creditsMin;

        @SerializedName("credits_norm")
        private String creditsNorm;

        @SerializedName("credits_obj")
        private String creditsObj;

        @SerializedName("nslog_min")
        private String nslogMin;

        @SerializedName("nslog_norm")
        private String nslogNorm;

        @SerializedName("credits")
        private String credits;

        @SerializedName("grade")
        private String grade;

        @SerializedName("cycle")
        private String cycle;

        @SerializedName("code_module")
        private String codeModule;

        @SerializedName("current_cycle")
        private String currentCycle;

        @SerializedName("semester_code")
        private String semesterCode;

        @SerializedName("semester_num")
        private String semesterNum;

        @SerializedName("active_log")
        private Object activeLog;

        public String getCreditsMin() {
            return creditsMin;
        }

        public String getCreditsNorm() {
            return creditsNorm;
        }

        public String getCreditsObj() {
            return creditsObj;
        }

        public String getNslogMin() {
            return nslogMin;
        }

        public String getNslogNorm() {
            return nslogNorm;
        }

        public String getCredits() {
            return credits;
        }

        public String getGrade() {
            return grade;
        }

        public String getCycle() {
            return cycle;
        }

        public String getCodeModule() {
            return codeModule;
        }

        public String getCurrentCycle() {
            return currentCycle;
        }

        public String getSemesterCode() {
            return semesterCode;
        }

        public String getSemesterNum() {
            return semesterNum;
        }

        public Object getActiveLog() {
            return activeLog;
        }
    }

    @SerializedName("ip")
    private String ip;

    @SerializedName("board")
    private Board board;

    @SerializedName("history")
    private History[] history;

    @SerializedName("current")
    private Note[] notes;

    public String getIp() {
        return ip;
    }

    public Board getBoard() {
        return board;
    }

    public History[] getHistory() {
        return history;
    }

    public Note[] getNotes() {
        return notes;
    }

    public static int getAcquiredCredits(Note[] notes) {
        return Arrays.stream(notes)
                .filter(note -> !note.getGrade().equalsIgnoreCase("Echec"))
                .filter(note -> !note.getGrade().equalsIgnoreCase("-"))
                .mapToInt(note -> Integer.parseInt(note.getCredits())).sum();
    }

    public static int getMissedCredits(Note[] notes) {
        return Arrays.stream(notes)
                .filter(note -> note.getGrade().equalsIgnoreCase("Echec"))
                .mapToInt((note -> Integer.parseInt(note.getCredits()))).sum();
    }

    public static int getAvailableCredits(Note[] notes) {
        return Arrays.stream(notes)
                .filter(note -> note.getGrade().equalsIgnoreCase("-"))
                .mapToInt(note -> Integer.parseInt(note.getCredits())).sum();
    }

    public static int getTotalCredits(Note[] notes) {
        return Arrays.stream(notes)
                .mapToInt(note -> Integer.parseInt(note.getCredits())).sum();
    }

}

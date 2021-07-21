package fr.nikho.epitech.intra.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import fr.nikho.epitech.intra.data.Module;

public class HubUtils {

    public static final int TALK_PARTICIPATION_XP = 1;
    public static final int TALK_ABSENCE_MALUS_XP = 1;
    public static final int TALK_PARTICIPATION_LIMIT = 15;
    public static final int TALK_ORGANIZATION_XP = 1;
    public static final int TALK_ORGANIZATION_LIMIT = 15;
    public static final int TALK_ORGANIZATION_CANCELED_MALUS_XP = 6;

    public static final int WK_PARTICIPATION_XP = 2;
    public static final int WK_ABSENCE_MALUS_XP = 2;
    public static final int WK_PARTICIPATION_LIMIT = 10;
    public static final int WK_ORGANIZATION_XP = 7;
    public static final int WK_ORGANIZATION_CANCELED_MALUS_XP = 10;
    public static final int WK_ORGANIZATION_LIMIT = 3;

    public static final int HK_PARTICIPATION_XP = 6;
    public static final int HK_ABSENCE_MALUS_XP = 6;
    public static final int HK_PARTICIPATION_LIMIT = -1;
    public static final int HK_ORGANIZATION_XP = 15;
    public static final int HK_ORGANIZATION_CANCELED_MALUS_XP = 20;
    public static final int HK_ORGANIZATION_LIMIT = -1;

    public static final int EXP_PARTICIPATION_XP = 3;
    public static final int EXP_PARTICIPATION_LIMIT = 8;

    private Module module;

    public HubUtils(Module module) {
        this.module = module;
    }

    public int getTalkXp() {
        //TODO
        return 0;
    }

    /**
     *      TALKS, CONFERENCE AND MEETUP
     */


    public List<Module.Activity> getTalkAndMeetup() {
        return Arrays.stream(module.getActivites())
                .filter(activity -> activity.getTypeTitle().equalsIgnoreCase("Talk") || activity.getTypeTitle().equalsIgnoreCase("Meetup") || activity.getTypeTitle().equalsIgnoreCase("Conference"))
                .collect(Collectors.toList());
    }

    public List<Module.Activity> getUserPresentTalkAndMeetup() {
        return getTalkAndMeetup().stream()
                .map(talk -> {
                    boolean isPresent = Arrays.stream(talk.getEvents()).anyMatch(event -> event.getUserStatus() != null && event.getUserStatus().equalsIgnoreCase("present"));
                    return isPresent ? talk : null;
                }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public int getUserPresentTalkAndMeetupXp() {
        return getUserPresentTalkAndMeetup().size() * TALK_PARTICIPATION_XP;
    }

    public List<Module.Activity> getUserAbsentTalkAndMeetup() {
        return getTalkAndMeetup().stream()
                .map(talk -> {
                    boolean isPresent = Arrays.stream(talk.getEvents()).anyMatch(event -> event.getUserStatus() != null && event.getUserStatus().equalsIgnoreCase("absent"));
                    return isPresent ? talk : null;
                }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public int getUserAbsentTalkAndMeetupXp() {
        return getUserAbsentTalkAndMeetup().size() * TALK_ABSENCE_MALUS_XP;
    }

    public int getUserAbsentTalkAndMeetupCount() {
        return getUserAbsentTalkAndMeetup().size();
    }

    public int getUserPresentTalkAndMeetupCount() {
        return getUserPresentTalkAndMeetup().size();
    }

    /**
     *      WORKSHOP
     */

    public List<Module.Activity> getWorkshops() {
        return Arrays.stream(module.getActivites())
                .filter(activity -> activity.getTypeTitle().equalsIgnoreCase("Workshop"))
                .collect(Collectors.toList());
    }

    public List<Module.Activity> getUserPresentWorkshops() {
        return getWorkshops().stream()
                .map(talk -> {
                    boolean isPresent = Arrays.stream(talk.getEvents()).anyMatch(event -> event.getUserStatus() != null && event.getUserStatus().equalsIgnoreCase("present"));
                    return isPresent ? talk : null;
                }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public int getUserPresentWorkshopsXp() {
        return getUserPresentWorkshops().size() * WK_PARTICIPATION_XP;
    }

    public List<Module.Activity> getUserAbsentWorkshops() {
        return getWorkshops().stream()
                .map(talk -> {
                    boolean isPresent = Arrays.stream(talk.getEvents()).anyMatch(event -> event.getUserStatus() != null && event.getUserStatus().equalsIgnoreCase("absent"));
                    return isPresent ? talk : null;
                }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public int getUserAbsentWorkshopXp() {
        return getUserAbsentWorkshops().size() * WK_ABSENCE_MALUS_XP;
    }

    /**
     *      HACKATHONS
     */


    public List<Module.Activity> getHackathons() {
        return Arrays.stream(module.getActivites())
                .filter(activity -> activity.getTypeTitle().equalsIgnoreCase("Hackathon"))
                .collect(Collectors.toList());
    }

    /**
     *      EXPERIENCES
     */


    public List<Module.Activity> getExperiences() {
        return Arrays.stream(module.getActivites())
                .filter(activity -> activity.getTypeTitle().equalsIgnoreCase("Experience"))
                .collect(Collectors.toList());
    }

    /**
     *      PROJECTS
     */


    public List<Module.Activity> getProjects() {
        return Arrays.stream(module.getActivites())
                .filter(activity -> activity.getTypeTitle().equalsIgnoreCase("projects"))
                .collect(Collectors.toList());
    }

}

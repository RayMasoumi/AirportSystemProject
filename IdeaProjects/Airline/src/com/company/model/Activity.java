package com.company.model;

import java.io.*;
import java.util.ArrayList;

public class Activity implements java.io.Serializable {

    private String changedInfo;
    private String time;
    private String action;
    private String person;
    public enum role {PASSENGER, MANAGER, SUPERADMIN, EMPLOYEE; }
    public static role role1;
    private String role;
    private static ArrayList<Activity> activities = new ArrayList<>();

    //constructor :

    public Activity() {

    }

    public Activity(String changedInfo, String time, String action, String person, String role) {
        this.changedInfo = changedInfo;
        this.time = time;
        this.action = action;
        this.person = person;
        this.role = role;
    }
    //getters and setters :

    public String getChangedInfo() {
        return changedInfo;
    }

    public void setChangedInfo(String changedInfo) {
        this.changedInfo = changedInfo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public static Activity.role getRole1() {
        return role1;
    }

    public static void setRole1(Activity.role role1) {
        Activity.role1 = role1;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static ArrayList<Activity> getActivities() {
        return activities;
    }

    public static void setActivities(ArrayList<Activity> activities) {
        Activity.activities = activities;
    }


    //files :

    //write super admin to file :
    public static void writeActivity() throws IOException {
        try {

            //output file :
            FileOutputStream activityFileOut = new FileOutputStream("activity.txt");
            //serialize :
            ObjectOutputStream activityOut = new ObjectOutputStream(activityFileOut);
            activityOut.writeInt(activities.size());
            for (Activity activity : activities) {
                activityOut.writeObject(activity);
            }
            activityOut.close();
            activityFileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error/saving to file");
        }
    }

    //read feedback file :
    public static void readActivity() {
        try {
            //input file :
            FileInputStream activityFileIn = new FileInputStream("activity.txt");
            //serialize :
            ObjectInputStream activityIn = new ObjectInputStream(activityFileIn);
            int size = activityIn.readInt();
            for (int i = 0; i < size; i++) {
                Activity activity = (Activity) activityIn.readObject();
                activities.add(activity);
            }
            activityIn.close();
            activityFileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("error reading filesttt");
        }

    }
}
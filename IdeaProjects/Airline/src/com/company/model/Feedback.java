package com.company.model;

import java.io.*;
import java.util.ArrayList;

public class Feedback implements Serializable {

    private String msg;

    public Feedback(String msg) {
        this.msg = msg;
    }

    public Feedback () {

    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static ArrayList<Feedback> feedBacks = new ArrayList<Feedback>();

    //files :

    //write feedback file :
    public static void writeFeedback () throws IOException {
        try {

            //output file :
            FileOutputStream feedbackFileOut = new FileOutputStream("feedback.txt");
            //serialize :
            ObjectOutputStream feedbackOut = new ObjectOutputStream(feedbackFileOut);
            feedbackOut.writeInt(feedBacks.size());
            for (int i=0 ; i<feedBacks.size() ; i++) {
                feedbackOut.writeObject(feedBacks.get(i));
            }
            feedbackOut.close();
            feedbackFileOut.close();
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("error/saving to file");
        }
    }

//read feedback file :
    public static void readFeedback() {
        try {
            //input file :
            FileInputStream feedbackFileIn = new FileInputStream("feedback.txt");
            //serialize :
            ObjectInputStream feedbackIn = new ObjectInputStream(feedbackFileIn);
            int size = feedbackIn.readInt();
            for (int i=0 ; i<size ; i++) {
                Feedback feedback = (Feedback) feedbackIn.readObject();
                feedBacks.add(feedback);
            }
            feedbackIn.close();
            feedbackFileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }




////write files :
//    public static void writeFeedback() throws IOException {
//
//        FileOutputStream fileOutputStream = new FileOutputStream("feedback.txt");
//        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
//
//        outputStream.writeInt(feedBacks.size());
//        for (int i=0 ; i<feedBacks.size() ; i++) {
//            outputStream.writeObject(feedBacks.get(i));
//        }
//        fileOutputStream.close();
//        outputStream.close();
//    }
//
////read files ;
//    public static ArrayList<String> readFeedback () throws IOException, ClassNotFoundException {
//
//        FileInputStream fileInputStream = new FileInputStream("feedback.txt");
//        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//
//        int size = objectInputStream.readInt();
//
//        for (int i=0 ; i<size ; i++) {
//            String feedback = (String) objectInputStream.readObject();
//            feedBacks.add(feedback);
//        }
//
//        fileInputStream.close();
//        objectInputStream.close();
//
//        return feedBacks;
//    }


}

package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String text = input.nextLine();
        String[] words = text.split(" ");
        boolean result = true;

        for ( int i = 0 ; i<words.length ; i++) {
            String[] part = words[i].split("o" + "u" + "i" + "a" + "y" + "e");

            if (part.length > 4 && part[i].toUpperCase().equals(part[i])) {
                result = false;
                break;
            }

            if (!result){
                System.out.println(words[i]);
            }

        }
    }
}

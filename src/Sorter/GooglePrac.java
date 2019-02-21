package Sorter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GooglePrac {
    public static void main(String [] args) {
        try{
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            System.out.println("Please Enter the String You'd Like to Check");
            String string = br.readLine();
            System.out.println(firstRecurring(string));

        } catch(IOException e) {

        }

    }

    private static String firstRecurring(String string) {
        String firstChar = null;
        List<String> charList = new ArrayList<>();
        for(int i = 0;string.length() > i;i++) {
            String character = Character.toString(string.charAt(i));
            if(!charList.contains(character) && charList.size()>0) {
                firstChar = character;
                return firstChar;
            } else {
                charList.add(character);
            }
            //System.out.println(charList);
            //System.out.println(string.charAt(i));
        }
        return firstChar;
    }
    private void doThing(String string) {

    }

}

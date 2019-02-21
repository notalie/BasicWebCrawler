package Sorter;
import java.io.*;
import java.net.*;


public class Reader {
    private String fileName;
    private int i = 0;

    public void webReader(String URL) throws WebsiteSortException{
        try {
            File file = new File(fileName);
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);

            PrintWriter pw = new PrintWriter(br);
            URL oracle = new URL(URL);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {

                if(inputLine.equals("<li class=\"first-last\">")) {
                    String nextLine = in.readLine();
                    if(nextLine.substring(0,19).equals("<a class=\"page-last") && i==0) {
                        String URLEnd = nextLine.substring(51,nextLine.length()-1).split("\"")[0].split("=")[3];
                        int URLNumber = Integer.parseInt(URLEnd);
                        for(int i=1; i<URLNumber-26;i++) {
                            String newURL = URL.substring(0,URL.length()-1);
                            newURL+=i;
                            URLLoop(newURL);
                        }
                        i++;
                    }

                }

                if (inputLine.equals("<div class=\"brand-name\">")) {
                    pw.print(in.readLine());
                }

                if (inputLine.equals("<div class=\"product-name\">")) {
                    String[] priceArray = in.readLine().split("Go to Product: ");
                    String productName = priceArray[1].split("\"")[0];
                    pw.print("," + productName);
                }



                if (inputLine.equals("<div class=\"product-pricing\">")) {
                    read7Times(in);
                    String priceLine = in.readLine();
                    String[] priceSplit = priceLine.split(">");
                    String priceSpan = priceSplit[1];
                    String[] priceHolder = priceSpan.split("<");
                    String price = priceHolder[0];
                    pw.print("," + price);
                    pw.println();
                }

            }
            in.close();
            pw.close();

        } catch(Exception e) {
            throw new WebsiteSortException();
        }
    }

    public void URLLoop(String URL) throws WebsiteSortException{
        try {
            File file = new File(fileName);
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);
            PrintWriter pr = new PrintWriter(br);

            URL oracle = new URL(URL);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {

                if (inputLine.equals("<div class=\"brand-name\">")) {
                    pr.print(in.readLine());
                }

                if (inputLine.equals("<div class=\"product-name\">")) {
                    String[] priceArray = in.readLine().split("Go to Product: ");
                    String productName = priceArray[1].split("\"")[0];
                    pr.print("," + productName);
                }

                if (inputLine.equals("<div class=\"product-pricing\">")) {
                    read7Times(in);
                    String priceLine = in.readLine();
                    String[] priceSplit = priceLine.split(">");
                    String priceSpan = priceSplit[1];
                    String[] priceHolder = priceSpan.split("<");
                    String price = priceHolder[0];
                    pr.print("," + price);
                    pr.println();
                }
            }
            pr.close();
            br.close();
            fr.close();

        } catch(Exception e) {
            System.out.println(e.getMessage());
            throw new WebsiteSortException();
        }
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private void read7Times(BufferedReader br) {
        try{
            for(int i =0; i<7;i++) {
                br.readLine();
            }
        } catch (Exception e) {

        }
    }
}

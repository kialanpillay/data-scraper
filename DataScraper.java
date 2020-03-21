package src;

/**
 *
 * @author kialanpillay
 */
import org.jsoup.*;
import org.jsoup.helper.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.*; // Only needed if scraping a local File.
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DataScraper {


	public DataScraper(int update) {
            
                ArrayList<Integer>maleCases = new ArrayList<>();
                ArrayList<Integer>localCases = new ArrayList<>();
                ArrayList<String>provinces = new ArrayList<>();
                ArrayList<Integer>importedCases = new ArrayList<>();
                ArrayList<Integer>femaleCases = new ArrayList<>();

		Document doc = null;

		try {
			doc = Jsoup.connect("http://www.nicd.ac.za/covid-19-update-" + update + "/").get();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
                Elements document = doc.getElementsByClass("elementor-widget-container");
                Elements content = document.get(9).children();
                
                int index = -1;
                
		for (Element e : content) {
                    if(e.text().contains("Province") && e.text().length() < 25){
                        index++;
                        maleCases.add(index,0);
                        femaleCases.add(index,0);
                        localCases.add(index,0);
                        importedCases.add(index,0);
                        provinces.add(index,e.text());
                    }
                    if(e.tagName().equals("ul")){
                        Elements items = e.getElementsByTag("li");
                        for (int i = 0; i < items.size(); i++) {
                            //System.out.println(items.get(i).text());
                            if(items.get(i).text().contains("female")){
                                int c = femaleCases.get(index);
                                femaleCases.set(index,++c);
                            }else{
                                int c = maleCases.get(index);
                                maleCases.set(index,++c);
                            }
                            if(items.get(i).text().contains("no international travel history")){
                                int c = localCases.get(index);
                                localCases.set(index,++c);
                            }else{
                                int c = importedCases.get(index);
                                importedCases.set(index,++c);
                            }

                        }
                    }
			
		}
                System.out.println("New Cases by Gender/Province");
                System.out.println("============================");
                int totalCases = 0;
                int totalMaleCases = 0;
                int totalFemaleCases = 0;
                int totalLocalCases = 0;
                int totalImportedCases = 0;
                
                for (int i = 0; i < provinces.size(); i++) {
                    System.out.println(provinces.get(i).substring(0,provinces.get(i).lastIndexOf("P")));
                    System.out.println("---------------------------");
                    System.out.printf("%-4s%-2s%-2s", "M","| ","F");
                    System.out.println("");
                    System.out.printf("%-4s%-2s%-4s%-2s%-4s", maleCases.get(i),"| ",femaleCases.get(i),"| ",maleCases.get(i)+femaleCases.get(i));
                    System.out.println("\n-----------");
                    System.out.printf("%-4s%-2s%-2s", "I","| ","L");
                    System.out.println("");
                    System.out.printf("%-4s%-2s%-4s", importedCases.get(i),"| ",localCases.get(i));
                    System.out.println("");
                    totalCases+= maleCases.get(i) + femaleCases.get(i);
                    totalMaleCases +=maleCases.get(i);
                    totalFemaleCases +=femaleCases.get(i);
                    totalLocalCases +=localCases.get(i);
                    totalImportedCases +=importedCases.get(i);
                
            }
                System.out.println("");
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                System.out.println("Cumalative");
                System.out.println("---------------------------");
                System.out.printf("%-10s%-2s%-6s", "Male","| ","Female");
                System.out.println("");
                System.out.printf("%-10s%-2s%-6s", totalMaleCases,"| ",totalFemaleCases);
                System.out.println("");
                System.out.printf("%-10s%-2s%-10s", "Imported","| ","Local Transmission");
                System.out.println("");
                System.out.printf("%-10s%-2s%-10s", totalImportedCases,"| ",totalLocalCases);
                System.out.println("");
                System.out.println("Total New Cases (" + sdf.format(d) + "): " + totalCases);
	
	}
	
	public static void main (String args[]) {

		new DataScraper(args[0]);
	
	}
	
}

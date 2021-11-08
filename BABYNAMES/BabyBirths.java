import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;

/**
 * Write a description of class BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BabyBirths {
    public void printNames (){
        FileResource fr = new FileResource("us_babynames/us_babynames_test/example-small.csv");
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord rec : parser){
            System.out.println("Name: "+rec.get(0)+" Gender: "+rec.get(1)+" Number of borns: "+rec.get(2));
        }
    }
    public void totalBirths(FileResource fr){
        CSVParser parser = fr.getCSVParser(false);
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : parser){
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")){
                totalBoys += numBorn;
            }
            else {
                totalGirls += numBorn;
            }
        }
        System.out.println("Total Births: "+totalBirths);
        System.out.println("Total Girls: "+totalGirls);
        System.out.println("Total Boys: "+totalBoys);
    }
    public int getRank(int year,String name, String gender){
        int n = -1;
        int rank = 0;
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
        //System.out.println("us_babynames/us_babynames_test/yob"+year+"short.csv");
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord rec : parser){
            if(rec.get(1).equals(gender)){
                rank++;
                if(rec.get(0).equals(name)){
                return rank;
            }
            }
        }
        return n;
    }
    public String getName(int year,int rank,String gender){
        String name = "NO NAME";
        int n = 0;
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord rec : parser){
            if(rec.get(1).equals(gender)){
                n++;
                if (n == rank){
                    name = rec.get(0);
                }
            }
        }
        return name;
    }
    public void whatIsNameInYear(String name,int year,int newYear,String gender){
        int rank = getRank(year,name,gender);
        String newName = getName(newYear,rank,gender);
        System.out.println(name+" born in "+year+" would be "+newName+" if she was born in "+newYear);
    }
    public int yearOfHighestRank(String name,String gender){
        DirectoryResource dr = new DirectoryResource();
        int currRank = 0;
        int newRank = 0;
        int currYear = 0;
        int bestYear = 0;
        for (File f : dr.selectedFiles()){
            String fileName = f.getName();
            //System.out.println(fileName);
            FileResource fr = new FileResource("us_babynames/us_babynames_by_year/"+fileName);
            CSVParser parser = fr.getCSVParser(false);
            String sYear = fileName.substring(3,7);
            currYear = Integer.parseInt(sYear);
            currRank = getRank(currYear,name,gender);
            if(newRank == 0){
                newRank = currRank;
                bestYear = currYear;
            }
            if(currRank < newRank){
                newRank = currRank;
                bestYear = currYear;
            }
        }
        return bestYear;
    }
    public double getAverageRank(String name,String gender){
        DirectoryResource dr = new DirectoryResource();
        double avg = 0.0;
        int currYear = 0;
        int currRank = 0;
        int totalRank = 0;
        int n = 0;
        for (File f : dr.selectedFiles()){
            String fileName = f.getName();
            FileResource fr = new FileResource("us_babynames/us_babynames_by_year/"+fileName);
            CSVParser parser = fr.getCSVParser(false);
            String sYear = fileName.substring(3,7);
            currYear = Integer.parseInt(sYear);
            currRank = getRank(currYear,name,gender);
            totalRank = totalRank+currRank;
            n++;
        }
        Double dRank = new Double(totalRank);
        avg = dRank/n;
        return avg;
    }
    public int getTotalBirthsRankedHigher(int year,String name,String gender){
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
        CSVParser parser = fr.getCSVParser(false);
        int rank = 0;
        int totalBirths = 0;
        /*for (CSVRecord rec : parser){
            rank = getRank(year,name,gender);
            System.out.println("Rank = "+rank);
            if(rec.get(0).equals(name)){
                break;
            }
            totalRank = totalRank + rank;
            System.out.println("totalRank = "+totalRank);
        }*/
        for (CSVRecord rec : parser){
            if(rec.get(1).equals(gender)){
                rank++;
                int currBirth = Integer.parseInt(rec.get(2));
                if(rec.get(0).equals(name)){    
                return totalBirths;
             }
              totalBirths = totalBirths + currBirth;
            }
        }
        return totalBirths;
    }
    public void testTotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
}

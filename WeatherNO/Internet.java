
/**
 * Write a description of class Internet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;
public class Internet {
     /**
  * Write a method named coldestHourInFile that has one parameter, a CSVParser named parser. 
  * This method returns the CSVRecord with the coldest temperature in the file, 
  * and thus all the information about the coldest temperature, 
  * such as the hour of the coldest temperature. 
  * You should also write a void method named testColdestHourInFile() to test this method 
  * and print out information about that coldest temperature, such as the time of its occurrence.
  */
  
  public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestSoFar = null;
        
        for (CSVRecord current: parser) {
            if (coldestSoFar == null) coldestSoFar = current;
            double currentTem = Double.parseDouble(current.get("TemperatureF"));
            double coldestTem = Double.parseDouble(coldestSoFar.get("TemperatureF"));
            if (currentTem != -9999 && currentTem < coldestTem) {
                coldestSoFar = current;
            }
        }
        return coldestSoFar;
    }
    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord result = coldestHourInFile(parser);
        System.out.print("The coldest temperature on that way was ");
        System.out.println(result.get("TemperatureF"));
        
    }
    
 /**
  * Write the method fileWithColdestTemperature that has no parameters. 
  * This method should return a string that is the name of the file from selected files that has the coldest temperature. 
  * You should also write a void method named testFileWithColdestTemperature() to test this method. 
  * Note that after determining the filename, you could call the method coldestHourInFile to determine the coldest temperature on that day. 
  */  
  
  public String fileWithColdestTemperature() {
        CSVRecord coldestSoFar = null;
        String coldestFileName = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f:dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord current = coldestHourInFile(fr.getCSVParser());
            if (coldestSoFar == null) coldestSoFar = current;
            else {
                double currentTem = Double.parseDouble(current.get("TemperatureF"));
                double coldestTem = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if (currentTem != -9999 && currentTem < coldestTem) {
                    coldestSoFar = current;
                    coldestFileName = f.getName();
                }
            }
        }
        return coldestFileName;
    }
    
    public void testFileWithColdestTemperature() {
        String coldestname = fileWithColdestTemperature();
        System.out.print("Coldest day was in file ");
        System.out.println(coldestname);
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.print("The coldest temperature on that way was ");
        System.out.println(coldest.get("TemperatureF"));
        System.out.println("All the temperatures on the coldest day were");
        for (CSVRecord record:fr.getCSVParser()) {
            System.out.print(record.get("DateUTC"));
            System.out.print(" ");
            System.out.println(record.get("TemperatureF"));
        }
    }
    
 /**
  * Write a method named lowestHumidityInFile that has one parameter, a CSVParser named parser. 
  * This method returns the CSVRecord that has the lowest humidity. 
  * If there is a tie, then return the first such record that was found.
  */ 
  
  public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestSoFar = null;
        double lowest = 0;
        double current = 0;
        for (CSVRecord record: parser) {
            if (lowestSoFar == null) lowestSoFar = record;
            if (record.get("Humidity").equals("N/A")) {current = -999;}
            else {current = Double.parseDouble(record.get("Humidity"));}
            
            if (lowestSoFar.get("Humidity").equals("N/A")) {lowest = -999;}
            else {lowest = Double.parseDouble(lowestSoFar.get("Humidity"));}
            
            if (current < lowest && current != -999) lowestSoFar = record;
        }
        return lowestSoFar;
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVRecord result = lowestHumidityInFile(fr.getCSVParser());
        System.out.print("Lowest Humidity was ");
        System.out.print(result.get("Humidity"));
        System.out.print(" at ");
        System.out.println(result.get("DateUTC"));
    }

 /**
  * Write the method lowestHumidityInManyFiles that has no parameters. 
  * This method returns a CSVRecord that has the lowest humidity over all the files. 
  * If there is a tie, then return the first such record that was found. 
  * You should also write a void method named testLowestHumidityInManyFiles() 
  * to test this method and to print the lowest humidity AND the time the lowest humidity occurred.
  */ 
  
  public String lowestHumidityInManyFiles() {
        DirectoryResource dr =  new DirectoryResource();
        CSVRecord lowest = null;
        String filename = null;
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord current = lowestHumidityInFile(fr.getCSVParser());
            if (lowest == null) lowest = current;
            double currentHum = Double.parseDouble(current.get("Humidity"));
            double lowestHum = Double.parseDouble(lowest.get("Humidity"));
            if (currentHum < lowestHum) {
                lowest = current;
                filename = f.getName();
            }
        }
        return filename;
    }
    
    public void testLowestHumidityInManyFile() {
        String filename = lowestHumidityInManyFiles();
        System.out.print("Day with lowest humidity was in file ");
        System.out.println(filename);
        FileResource fr = new FileResource();
        CSVRecord lowest = lowestHumidityInFile(fr.getCSVParser());
        System.out.print("Lowest Humidity was ");
        System.out.print(lowest.get("Humidity"));
        System.out.print(" at ");
        System.out.println(lowest.get("DateUTC"));
    }
    
 /**
  * Write the method averageTemperatureInFile that has one parameter, a CSVParser named parser. 
  * This method returns a double that represents the average temperature in the file. 
  * You should also write a void method named testAverageTemperatureInFile() to test this method.
  */ 
  
  public double TemperatureInFile(CSVParser parser) {
        double sum = 0;
        int number = 0;
        for (CSVRecord record:parser) {
            double current = Double.parseDouble(record.get("TemperatureF"));
            sum = sum + current;
            number = number + 1;
        }
        sum = sum / number;
        return sum;
    }
    
    public void testTemperatureInFile() {
        FileResource fr = new FileResource();
        double average = TemperatureInFile(fr.getCSVParser());
        System.out.print("Average temperature in file is ");
        System.out.println(average);
    }
    
 /**
  * Write the method averageTemperatureWithHighHumidityInFile that has two parameters, a CSVParser named parser and an integer named value. 
  * This method returns a double that represents the average temperature of only those temperatures when the humidity was greater than or equal to value. 
  * You should also write a void method named testAverageTemperatureWithHighHumidityInFile() to test this method.
  */ 
  
  public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double sum = 0;
        double number = 0;
        double humidity = 0;
        for (CSVRecord record: parser) {
            if (record.get("Humidity").equals("N/A")) humidity = -999;
            else humidity = Double.parseDouble(record.get("Humidity"));
            if (humidity >= value) {
                number = number + 1;
                sum = sum + Double.parseDouble(record.get("TemperatureF"));
            }
        }
       
        return sum/number;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        double average = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if (average == 0) System.out.println("No temperatures with that humidity");
        else {
            System.out.print("Average temperature with high Humidity is ");
            System.out.println(average);
        }
    }
}


/**
 * Write a description of class Weather here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;
public class Weather {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestSoFar = null;
        for(CSVRecord currentRow: parser){
            if(coldestSoFar == null){
                coldestSoFar = currentRow;
            }
            else{
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if((currentTemp != -9999)&&(currentTemp < coldestTemp)){
                    coldestSoFar = currentRow;
                }
            }
        }
        return coldestSoFar;
    }
    public String fileWithColdestTemperature(){
        CSVRecord coldestSoFar = null;
        String coldestFileName = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentRow = coldestHourInFile(parser);
            if(coldestSoFar == null){
                coldestSoFar = currentRow;
            }
            else{
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if((currentTemp != -9999)&&(currentTemp < coldestTemp)){
                    coldestSoFar = currentRow;
                    coldestFileName = f.getName();
                }
            }
        }
        return coldestFileName;
    }
    public void testFileWithColdestTemperature(){
        //String coldestFile = fileWithColdestTemperature();
        //System.out.println("Coldest day was in file "+coldestFile);
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldest = coldestHourInFile(parser);
        System.out.println("The coldest temperature on that day that was "+coldest.get("TemperatureF"));
        for(CSVRecord record:parser){
            System.out.println(record.get("DateUTC")+" "+record.get("TemperatureF"));
        }
    }
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldest = coldestHourInFile(parser);
        System.out.println("Coldest temperature was "+coldest.get("TemperatureF")+" at "+coldest.get("TimeEST"));
    }
}

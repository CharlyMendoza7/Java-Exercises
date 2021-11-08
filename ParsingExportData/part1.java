
/**
 * Write a description of class part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class part1 {
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
       // String x = countryInfo(parser,"Nauru");
        //System.out.println(x);
       //listExportersTwoProducts(parser,"gold","diamonds");
       //int n = numberOfExporters(parser,"gold");
       //System.out.println(n);
       bigExporters(parser,"$999,999,999,999");
    }
    public String countryInfo(CSVParser parser,String country){
        for(CSVRecord record: parser){
            String name = record.get("Country");
            if(name.contains(country)){
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                System.out.println(country+": "+exports+": "+value);
            }
            //return "NOT FOUND";
        }
        return "";
    }
    public void listExportersTwoProducts(CSVParser parser,String exportItem1,String exportItem2){
        for(CSVRecord record: parser){
            String exports = record.get("Exports");
            if((exports.contains(exportItem1))&&(exports.contains(exportItem2))){
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    public int numberOfExporters(CSVParser parser,String exportItem){
        int n = 0;
        for(CSVRecord record: parser){
            String exports = record.get("Exports");
            if(exports.contains(exportItem)){
                n++;
            }
        }
        return n;
    }
    public void bigExporters(CSVParser parser,String amount){
        for(CSVRecord record: parser){
            String value = record.get("Value (dollars)");
            int lvalue = value.length();
            int lamount = amount.length();
            //System.out.println("LVALUE = "+lvalue);
            //System.out.println("LAMOUNT = "+lamount);
            if(lvalue > lamount){
                String country = record.get("Country");
                System.out.println(country+" "+value);
            }
        }
    }
}

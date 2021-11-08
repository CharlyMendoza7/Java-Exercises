import edu.duke.*;
import java.io.File;
/**
 * Write a description of class Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    public String getURLS(){
    URLResource file = new  URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
    String result = "";
    for (String item : file.words()) {
       	   String itemLower = item.toLowerCase();
       	   int pos = itemLower.indexOf("youtube.com");
       	   if (pos != -1) {
           	result = "MISSING CODE";
               }
           /*int beg = item.lastIndexOf("\"",pos);
           int end = item.indexOf("\"", pos+1);
           System.out.println(item.substring(beg+1,end));*/
           /*int beg = itemLower.lastIndexOf("\"",pos);
           int end = itemLower.indexOf("\"", pos+1);
           System.out.println(itemLower.substring(beg+1,end));*/
           int beg = itemLower.indexOf("\"",pos-9);
           int end = itemLower.indexOf("\"", pos+1);
           System.out.println(item.substring(beg+1,end));
   	}
    return result;
    }
}

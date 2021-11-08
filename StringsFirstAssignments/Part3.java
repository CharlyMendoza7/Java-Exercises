
/**
 * Write a description of class Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public String twoOccurrences(String stringa,String stringb){
        String result = "";
        int lengthStringa = stringa.length();
        int firstOcIndex = stringb.indexOf(stringa);
        if(firstOcIndex == -1){
            return "false";
        }
        int secondOcIndex = stringb.indexOf(stringa,firstOcIndex+lengthStringa);
        if(secondOcIndex > firstOcIndex){
            result = "true";
        }
        else{
            result = "false";
        }
        return result;
    }
    public String lastPart(String stringa,String stringb){
        String result = "";
        int lengthStringa = stringa.length();
        int firstOcIndex = stringb.indexOf(stringa);
        if(firstOcIndex == -1){
            return stringb;
        }
        result = stringb.substring(firstOcIndex,firstOcIndex+lengthStringa+1);
        return result;
    }
    public void testing(){
        /*String p1 = twoOccurrences("by","A story by abby long");
        String p2 = twoOccurrences("a","banana");
        String p3 = twoOccurrences("atg","ctgtatgta");*/
        
        String p1 = lastPart("stor","A story by abby long");
        String p2 = lastPart("an","banana");
        String p3 = lastPart("zoo","forest");
        
        System.out.println("The part of the string stor in A story by abby long is " + p1);
        System.out.println("The part of the string an in banana is "+ p2);
        System.out.println("The part of the string zoo in forest is "+ p3);
    }
}


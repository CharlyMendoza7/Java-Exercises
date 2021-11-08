
/**
 * Write a description of class Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb){
        int currIndex = stringb.indexOf(stringa);
        if (currIndex ==-1){
            return 0;
        }
        int n = 0;
        while(true){
            currIndex = stringb.indexOf(stringa,currIndex+stringa.length());
            n++;
            //System.out.println(currIndex);
            //System.out.println("n is " + n);
            //System.out.println("curr index is "+ currIndex);
            if(currIndex == -1){
                break;
            }
            //System.out.println(n);
        }
        return n;
    }
    public void testHowMany(){
        String stringa1 = "GAxA";
        String stringb1 = "ATGAACGAATTGAATC";
        String stringa2 = "AA";
        String stringb2 = "ATAAAA";
        
        int ex1 = howMany(stringa1,stringb1);
        int ex2 = howMany(stringa2,stringb2);
        
        System.out.println("Occurernces of "+stringa1+" in "+stringb1+" are "+ex1);
        System.out.println("Occurrences of "+stringa2+" in "+stringb2+" are "+ex2);
    }
}

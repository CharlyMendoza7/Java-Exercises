
/**
 * Write a description of class WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel(char ch){
        if(ch == 'a'|| ch == 'A'){
            return true;
        }
        if(ch == 'e' || ch == 'E'){
            return true;
        }
        if(ch == 'i' || ch == 'I'){
            return true;
        }
        if(ch == 'o' || ch == 'O'){
            return true;
        }
        if(ch == 'u' || ch == 'U'){
            return true;
        }
        return false;
    }
    public String replaceVowel(String phrase,char ch){
        StringBuilder ph = new StringBuilder(phrase);
        for(int i = 0;i < ph.length();i++){
            char currChar = ph.charAt(i);
            char newChar = ch;
            if(isVowel(currChar)){
                ph.setCharAt(i, newChar);
            }
        }
        return ph.toString();
    }
    public String emphasize(String phrase,char ch){
        StringBuilder ph = new StringBuilder(phrase);
        char uCh = Character.toUpperCase(ch);
        for(int i = 0;i < ph.length();i++){
            char currChar = ph.charAt(i);
            char oddChar = '*';
            char evenChar = '+';
            int oddOrEven = i + 1;
            if((oddOrEven % 2 == 0)&&(((currChar == ch))||(currChar == uCh))){
                ph.setCharAt(i, evenChar);
            }
            if((oddOrEven % 2 != 0)&&(((currChar == ch))||(currChar == uCh))){
                ph.setCharAt(i, oddChar);
            }
        }
        return ph.toString();
    }
    public void testIsVowel(){
        char a = 'a';
        char F = 'F';
        System.out.println(isVowel(a));
        System.out.println(isVowel(F));
    }
    public void testReplaceVowel(){
        System.out.println(replaceVowel("Hello My Name is Carlos Mendoza and yours is ANA",'*'));
    }
    public void testEmphasize(){
        System.out.println(emphasize("dna ctgaaactga",'a'));
        System.out.println(emphasize("Mary Bella Abracadabra",'a'));
        
    }
}

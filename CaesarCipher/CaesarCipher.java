import edu.duke.*;
/**
 * Write a description of class CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    public String encrypt(String input,int key){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = alphabet.toLowerCase();
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        String shiftedLower = shiftedAlphabet.toLowerCase();
        for(int i = 0;i < encrypted.length();i++){
            char currChar = encrypted.charAt(i);
            int idx1 = alphabet.indexOf(currChar);
            int idx2 = lowerAlphabet.indexOf(currChar);
            if (idx1 != -1 || idx2 != -1){
                if(Character.isLowerCase(currChar)){
                    char newChar = shiftedLower.charAt(idx2);
                    encrypted.setCharAt(i, newChar);
                }
                else{
                    char newChar = shiftedAlphabet.charAt(idx1);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }
    public String encryptTwoKeys(String input,int key1,int key2){
        StringBuilder encrypted = new StringBuilder(input);
        String firstEncrypted = encrypt(input,key1);
        String secondEncrypted = encrypt(input,key2);
        for(int i = 0;i < encrypted.length();i++){
            char currChar = encrypted.charAt(i);
            int oddOrEven = i + 1;
            if(oddOrEven % 2 == 0){
                char newChar = secondEncrypted.charAt(i);
                encrypted.setCharAt(i, newChar);
            }
            else{
                char newChar = firstEncrypted.charAt(i);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }
    public void testCaesar(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        System.out.println(encrypt(message,15));
        //System.out.println(encrypt(message,17));
    }
    public void tesTwoCaesar(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        System.out.println(encryptTwoKeys(message,8,21));
        
    }
}

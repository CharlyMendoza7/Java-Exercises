import edu.duke.*;
import java.io.*;
/**
 * Write a description of class CaesarUpd here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarUpd {
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
    public int[] countLetters(String message){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int k = 0;k < message.length(); k++){
            char ch = Character.toLowerCase(message.charAt(k));
            int idx = alph.indexOf(ch);
            if(idx != -1){
                counts[idx] += 1;
            }
        }
        return counts;
    }
    public int maxIndex(int[] vals){
        int maxDex = 0;
        for(int k = 0; k < vals.length; k++){
            if(vals[k] > vals[maxDex]){
                maxDex = k;
            }
        }
        return maxDex;
    }
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if(maxDex < 4){
            dkey = 26 - (4-maxDex);
        }
        return cc.encrypt(encrypted,26-dkey);
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
    public void testDecrypt(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        System.out.println(message);
        System.out.println(encrypt(message,15));
        System.out.println(decrypt(encrypt(message,15)));
    }
}

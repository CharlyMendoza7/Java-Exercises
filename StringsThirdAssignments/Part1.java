
/**
 * Write a description of class Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int currIndex = dna.indexOf(stopCodon, startIndex+3);
        while(currIndex != -1){
            int diff = currIndex-startIndex;
            if(diff % 3 == 0){
                return currIndex;
            }
            else{
                currIndex = dna.indexOf(stopCodon, currIndex+1);
            }
        }
        return -1;
    }
    public String findGene(String dna, int where){
        int StartIndex = dna.indexOf("ATG", where);
        if(StartIndex==-1){
            return "";
        }
        int taaIndex = findStopCodon(dna,StartIndex,"TAA");
        int tagIndex = findStopCodon(dna,StartIndex,"TAG");
        int tgaIndex = findStopCodon(dna,StartIndex,"TGA");
        //int minIndex = Math.min(taaIndex,tagIndex);
        //int newMinIndex = Math.min(minIndex,tgaIndex);
        int newMinIndex = 0;
        if(taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)){
            newMinIndex = tgaIndex;
        }
        else{
            newMinIndex = taaIndex;
        }
        if(newMinIndex == -1 || (tagIndex != -1 && tagIndex < newMinIndex)){
            newMinIndex = tagIndex;
        }
        if(newMinIndex == -1){
            return "";
        }
        String gene = dna.substring(StartIndex,newMinIndex+3);
        return gene;
    }
    public int countG(String dna){
        int n = 0;
        int IndexG = dna.indexOf("G");
        while(IndexG != -1){
        n++;
        IndexG = dna.indexOf("G",IndexG+1);
        }
        return n;
    }
    public int countC(String dna){
        int n = 0;
        int IndexC = dna.indexOf("C");
        while(IndexC != -1){
        n++;
        IndexC = dna.indexOf("C",IndexC+1);
        }
        return n;
    }
    public void printAllGenes(String dna){
        int StartIndex = 0;
        while(true){
            String currentGene = findGene(dna, StartIndex);
            if(currentGene.isEmpty()){
                break;
            }
            System.out.println(currentGene);
            StartIndex = dna.indexOf(currentGene, StartIndex)+ currentGene.length();
        }
    }
    public StorageResource getAllGenes(String dna){
        int n = 0;
        int StartIndex = 0;
        StorageResource geneList = new StorageResource();
        while(true){
            n++;
            String currentGene = findGene(dna, StartIndex);
            if(currentGene.isEmpty()){
                break;
            }
            geneList.add(currentGene);
            StartIndex = dna.indexOf(currentGene, StartIndex)+ currentGene.length();
        }
        System.out.println("NUMBER OF GENES: "+n);
        return geneList;
    }
    public float cgRatio(String dna){
        int startIndex = 0;
        String gene = findGene(dna,startIndex);
        float total = gene.length();
        float nG = countG(gene);
        float nC = countC(gene);
        /*System.out.println(nG);
        System.out.println(nC);
        System.out.println(total);*/
        float ratio = (nG+nC)/total;
        return ratio;
    }
    public int countCTG(String dna){
        int n = 0;
        int IndexCTG = dna.indexOf("CTG");
        while(IndexCTG != -1){
        n++;
        IndexCTG = dna.indexOf("CTG",IndexCTG+3);
        }
        return n;
    }
    public void processGenes(StorageResource sr){
        //print all string longer than 9 characters
        int n = 0;
        int p = 0;
        int iLength = 0;
        for(String s : sr.data()){
            if(s.length()>60){
                n++;
                System.out.println(s + " is longer then 60 characters");
            }
            if(cgRatio(s)>0.35){
                p++;
                System.out.println(s + " ratio is higher than 0.35");
            }
            int length = s.length();
            if(length > iLength){
                iLength = length;
            }
        }
        System.out.println("Strings longer than 60 characters: "+n);
        System.out.println("Strings with ratio > 0.35: "+p);
        System.out.println("Length of longest gene is: "+iLength);
    }
    public void testProcessGenes(){
        String dna1 = "ATGATTATTTAAXXXATGXXXXXXTAG";
        String dna2 = "ATGTAGXXXXATGTAA";
        String dna3 = "ATGCCCTAAATGXXXTAAATGGGGTAA";
        String dna4 = "ATGXXXTAAATGXXXTAG";
        
        StorageResource genes = getAllGenes(dna1);
        StorageResource genes2 = getAllGenes(dna2);
        StorageResource genes3 = getAllGenes(dna3);
        StorageResource genes4 = getAllGenes(dna4);
        
        
        
        System.out.println("DNA 1: ");
        processGenes(genes);
        System.out.println("DNA 2: ");
        processGenes(genes2);
        System.out.println("DNA 3: ");
        processGenes(genes3);
        System.out.println("DNA 4: ");
        processGenes(genes4);
    }
    public void testQuiz(){
        FileResource fr = new FileResource();
        String dna = fr.asString();
        String realDNA = dna.toUpperCase();
        StorageResource gene = getAllGenes(realDNA);
        //System.out.println("THE DNA IS: "+dna);
        //System.out.println("GENES ARE: "+gene);
        processGenes(gene);
        
    }
    public void testFindStopCodon(){
        String dna1 = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        String dna2 = "xxxyyzzzTAAxxyyyzzzTAAxx";
        String dna3 = "xxxyyyzzzTAGxxxyyyzzz";
        String dna4 = "xxxyyzzzTAGyxxxyyyzzzTAGxxx";
        
        int ex1 = findStopCodon(dna1,0,"TAA");
        int ex2 = findStopCodon(dna2,0,"TAA");
        int ex3 = findStopCodon(dna3,0,"TAG");
        int ex4 = findStopCodon(dna4,0,"TAG");
        
        System.out.println("The stopCodon in ex 1 is at index:   "+ex1);
        System.out.println("The stopCodon in ex 2 is at index:   "+ex2);
        System.out.println("The stopCodon in ex 3 is at index:   "+ex3);
        System.out.println("The stopCodon in ex 4 is at index:   "+ex4);
        
    }
    public void testFindGene(){
    String dna = "ATATTTAATATGTGATATATTATATATGTAATAATAGTGAATGTAGATTATAATGATTATTTAGATTATT";
    
    StorageResource genes = getAllGenes(dna);
    for(String g : genes.data()){
    System.out.println("Gene is " + g);
    }
    /*String gene1 = findGene(dna1);
    String gene2 = findGene(dna2);
    String gene3 = findGene(dna3);
    String gene4 = findGene(dna4);*/
    /*
    System.out.println("The gene in "+dna1+" is: ");
    System.out.println(gene1);
    System.out.println("The gene in "+dna2+" is: ");
    System.out.println(gene2);
    System.out.println("The gene in "+dna3+" is: ");
    System.out.println(gene3);
    System.out.println("The gene in "+dna4+" is: ");
    System.out.println(gene4);*/
    }
}

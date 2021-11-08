
/**
 * Write a description of class Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
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
    public int countGenes(String dna){
        int StartIndex = 0;
        int n = 0;
        while(true){
            String currentGene = findGene(dna, StartIndex);
            if(currentGene.isEmpty()){
                break;
            }
            n++;
            StartIndex = dna.indexOf(currentGene, StartIndex)+ currentGene.length();
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
    String dna1 = "ATATTTAATTATATTATATTAATAGTGA";
    String dna2 = "ATTATAATGATTATTTAGATTATT";
    String dna3 = "ATTATGATTATAATTTAAATTATATGTAGATTATATGTGA";
    String dna4 = "ATGATTAATAGTGA";
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
    public void testCountGenes(){
    System.out.println(countGenes("ATGTAAGATGCCCTAGT"));
    System.out.println(countGenes("ATGCCCTAAATGCCCTAGATGTGA"));
    }
}

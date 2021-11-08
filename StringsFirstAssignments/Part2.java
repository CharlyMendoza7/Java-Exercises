
/**
 * Write a description of class Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon){
        String result = "";
        String gene = "";
        String dnaUpper = dna.toUpperCase();
        String dnaLower = dna.toLowerCase();
        //System.out.println(dna);
        //System.out.println(dnaUpper);
        //System.out.println(dnaLower);
        int StartIndex = 0;
        int StopIndex = 0;
        if(dnaUpper == dna){
            String newStartCodon = startCodon.toUpperCase();
            String newStopCodon = stopCodon.toUpperCase();
            StartIndex = dnaUpper.indexOf(newStartCodon);
            StopIndex = dnaUpper.indexOf(newStopCodon,StartIndex+3);
            //System.out.println("DNAUP = DNA" + StartIndex +"    "+StopIndex);
        }
        //int StartIndex = dna.indexOf(startCodon);
        if(dnaLower == dna){
            String newStartCodon = startCodon.toLowerCase();
            String newStopCodon = stopCodon.toLowerCase();
            StartIndex = dnaLower.indexOf(newStartCodon);
            StopIndex = dnaLower.indexOf(newStopCodon,StartIndex+3);
            //System.out.println("DNALOW = DNA" + StartIndex +"    "+StopIndex);
        }
        //int StopIndex = dna.indexOf(stopCodon,StartIndex+3);
        int multiple3 = (StopIndex - StartIndex)%3;
        if(dnaUpper == dna){
            if(StartIndex == -1){
                return "";
            }
            if(StopIndex == -1){
                return "";
            }
            if(multiple3 == 0){
                gene = dna.substring(StartIndex,StopIndex+3);
                result = gene.toUpperCase();
            }
        }
        if(dnaLower == dna){
            //System.out.println(StartIndex);
            //System.out.println(StopIndex);
            //System.out.println("AQUIESTOY");
            if(StartIndex == -1){
                return "";
            }
            if(StopIndex == -1){
                return "";
            }
            if(multiple3 == 0){
                gene = dna.substring(StartIndex,StopIndex+3);
                result = gene.toLowerCase();
            }
        }
        return result;
    }
    public void testSimpleGene(){
        //NO ATG
        String dna1 = "ATTGTAATTTAA";
        //NO TAA
        String dna2 = "ATTATGATTTAT";
        //NO ATG OR TAA
        String dna3 = "ATTATTTGATAG";
        //ATG, TAA AND MULTIPLE OF 3
        String dna4 = "attattatgattatttaaatt";
        //ATG, TAA AND NOT MULTIPLE OF 3
        String dna5 = "ATTATTATGTTAATTAAATT";
        //PRINT DNA:
        String start = "ATG";
        String stop = "TAA";
        String gene1 = findSimpleGene(dna1,start,stop);
        String gene2 = findSimpleGene(dna2,start,stop);
        String gene3 = findSimpleGene(dna3,start,stop);
        String gene4 = findSimpleGene(dna4,start,stop);
        String gene5 = findSimpleGene(dna5,start,stop);
        System.out.println("DNA IS " + dna1);
        System.out.println("GENE IS " + gene1);
        System.out.println("DNA IS " + dna2);
        System.out.println("GENE IS " + gene2);
        System.out.println("DNA IS " + dna3);
        System.out.println("GENE IS " + gene3);
        System.out.println("DNA IS " + dna4);
        System.out.println("GENE IS " + gene4);
        System.out.println("DNA IS " + dna5);
        System.out.println("GENE IS " + gene5);
    }
}
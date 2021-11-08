
/**
 * Write a description of class Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna){
        String result = "";
        int StartIndex = dna.indexOf("ATG");
        if (StartIndex == -1){
            return "";
        }
        int StopIndex = dna.indexOf("TAA",StartIndex+3);
        if (StopIndex == -1){
            return "";
        }
        int multiple3 = (StopIndex - StartIndex)%3;
        if (multiple3 == 0){
            result = dna.substring(StartIndex,StopIndex + 3);
        }
        return result;
    }
    public void testSimpleGene(){
        //NO ATG
        String dna1 = "AAATGCCCTAACTAGATTAAGAAACC";
        //NO TAA
        String dna2 = "ATTATGATTTAT";
        //NO ATG OR TAA
        String dna3 = "ATTATTTGATAG";
        //ATG, TAA AND MULTIPLE OF 3
        String dna4 = "ATTATTATGATTATTTAAATT";
        //ATG, TAA AND NOT MULTIPLE OF 3
        String dna5 = "ATTATTATGTTAATTAAATT";
        //PRINT DNA:
        String gene1 = findSimpleGene(dna1);
        String gene2 = findSimpleGene(dna2);
        String gene3 = findSimpleGene(dna3);
        String gene4 = findSimpleGene(dna4);
        String gene5 = findSimpleGene(dna5);
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

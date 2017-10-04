import javax.sound.midi.Soundbank;
import java.io.*;

public class LecteurPlateau {
    private static final int MAX_SIZE = 7;

    public static int[][] lirePlateau(String fileName) {
        try{
            FileInputStream fstream = new FileInputStream(fileName+".peg");
            fileReader(fstream);

        }
        catch(FileNotFoundException fnfe){
            System.out.println("Message"+fnfe.getMessage());
        }

        return null;
    }

    private static int[][] fileReader(FileInputStream fstream) throws FileNotFoundException {
        int[][] tabCases = new int[MAX_SIZE][MAX_SIZE];
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String line = "";
            int rangee = 0;
            int colonne = 0;
            while((line = br.readLine()) != null){
                char[] lineInFile = line.toCharArray();
                for (int i=0; i<lineInFile.length; i++) {
                    tabCases[rangee][colonne] = (lineInFile[colonne]); //todo debug tab
                    colonne++;
                    if(colonne >= MAX_SIZE)
                        colonne = 0;
                    System.out.print(tabCases[rangee][colonne]);
                }
                rangee+=1;
                System.out.println("\n");
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return tabCases;
    }

}

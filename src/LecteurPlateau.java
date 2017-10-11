import javax.sound.midi.Soundbank;
import java.io.*;
import java.util.Scanner;

public class LecteurPlateau {
    private static final int MAX_SIZE = 7;

    public static int[][] lirePlateau(String fileName) {
        try{
//            FileInputStream fstream = new FileInputStream(fileName+".peg");
            return fileReader(fileName);

        }
        catch(FileNotFoundException fnfe){
            System.out.println("Message"+fnfe.getMessage());
        }

        return null;
    }

    private static int[][] fileReader(String fileName) throws FileNotFoundException {
        int[][] tabCases = new int[MAX_SIZE][MAX_SIZE];
        Scanner lineScanner = new Scanner(new File(fileName+".peg"));
        Scanner numberScanner = null;
        int tabLine = 0;
        int tabColumn = 0;

        while(lineScanner.hasNextLine()) { // ligne : 0
            String line = lineScanner.nextLine();
//            System.out.println(line);
            numberScanner = new Scanner(line);
            while(numberScanner.hasNext()) {
                String token = numberScanner.next();
                char[] tabToken = token.toCharArray();
                for (int i =0; i< tabToken.length; i++) {
                    tabCases[tabLine][tabColumn] = Character.getNumericValue(tabToken[i]);
                    tabColumn += 1;
                }
                tabColumn = 0;
            }
            tabLine += 1;
        }
        lineScanner.close();
        numberScanner.close();



        return tabCases;
    }

}

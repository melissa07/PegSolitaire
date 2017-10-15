import javax.sound.midi.Soundbank;
import java.io.*;
import java.util.Scanner;

public class LecteurPlateau {
    private static final int MAX_SIZE = 7;

    public static int[][] lirePlateau(String fileName) {
        try{
            return fileReader(fileName);
        }
        catch(FileNotFoundException fnfe){
            System.out.println("Message"+fnfe.getMessage());
        }

        return null;
    }

    private static int[][] fileReader(String fileName) throws FileNotFoundException {
        int[][] tabCases = new int[MAX_SIZE + 4][MAX_SIZE + 4];
        Scanner lineScanner = new Scanner(new File(fileName+".peg"));
        Scanner numberScanner = null;
        int tabLine = 0;
        int tabColumn = 0;

        //remplie le tableau de 2 case plus large et plus long de 8 pour eviter les null pointer
        for(int i = 0; i < tabCases.length; i++){
            for(int j = 0; j < tabCases.length; j++){
                tabCases[i][j] = 8;
            }
        }

        while(lineScanner.hasNextLine()) {
            String line = lineScanner.nextLine();
            numberScanner = new Scanner(line);
            while(numberScanner.hasNext()) {
                String token = numberScanner.next();
                char[] tabToken = token.toCharArray();
                for (int i =0; i< tabToken.length; i++) {
                    tabCases[tabLine+2][tabColumn+2] = Character.getNumericValue(tabToken[i]);
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

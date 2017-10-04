import java.io.*;

public class LecteurPlateau {

    public static int[][] lirePlateau(String fileName) {
        try{
            FileInputStream fstream = new FileInputStream(fileName+".peg");
            int[][] tabCases = fileReader(fstream);


        }
        catch(FileNotFoundException fnfe){
            System.out.println("Message"+fnfe.getMessage());
        }

        return null;
    }

    private static int[][] fileReader(FileInputStream fstream) throws FileNotFoundException {
        int[][] tabCases = new int[7][7];
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String line = "";

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return tabCases;
    }

}

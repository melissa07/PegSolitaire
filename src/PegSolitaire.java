public class PegSolitaire {
    private static final String PUZZLE_NAME = "Puzzles/puzzle1";


    public static void main(String[] args) {
        int[][] tabCases = LecteurPlateau.lirePlateau(PUZZLE_NAME);

        Puzzle lePuzz = new Puzzle(tabCases);
        // O(n^2)
        for(int i=0; i<= tabCases.length; i++) {
            for(int j=0; j<= tabCases.length; i++) {
                System.out.println("Nombre a la rangee " +i+ " et" +
                        "a la colonne " + j+ ": " +tabCases[i][j]);
            }
            System.out.println("\n");
        }
//        System.out.println("Hello world!");
    }
}

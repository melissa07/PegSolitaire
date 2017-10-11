public class PegSolitaire {
    private static final String PUZZLE_NAME = "Puzzles/puzzle1";


    public static void main(String[] args) {
        int[][] tabCases = LecteurPlateau.lirePlateau(PUZZLE_NAME);

        Puzzle lePuzz = new Puzzle(tabCases);
        for(int i=0; i< tabCases.length; i++) {
            for(int j=0; j< tabCases.length; j++) {
                System.out.print(tabCases[i][j]);
            }
            System.out.println();
        }
//        System.out.println("Hello world!");
    }
}

public class PegSolitaire {
    private static final String PUZZLE_NAME = "Puzzles/puzzle1";


    public static void main(String[] args) {
        int[][] tabCases = LecteurPlateau.lirePlateau(PUZZLE_NAME);

        Puzzle lePuzz = new Puzzle(tabCases);

        for(int i=0; i< tabCases.length; i++) {
            for(int j=0; j< tabCases.length; j++) {
                if(tabCases[i][j] == 2) {
                    lePuzz.setEmptyStartLine(i);
                    lePuzz.setEmptyStartColumn(j);
                }
            }
        }
        PegLogique pl = new PegLogique(lePuzz);


    }
}

public class PegSolitaire {
    private static final String PUZZLE_NAME = "puzzle1.peg";


    public static void main(String[] args) {
        int[][] tabCases = LecteurPlateau.lirePlateau(PUZZLE_NAME);

        Puzzle lePuzz = new Puzzle(tabCases);
        System.out.println("Hello world!");
    }
}

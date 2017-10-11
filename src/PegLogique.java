import jdk.management.resource.internal.inst.FileOutputStreamRMHooks;

public class PegLogique {
    private Direction d = Direction.NORD; // NORD, EST, SUD, OUEST
    private Puzzle p = null;
    private final int IS_EMPTY = 2;
    private final int IS_FILLED = 1;
    private final int OUT_OF_GAME = 0;
    private int curX;
    private int curY;

    public PegLogique(Puzzle p) {
        this.p = p;
        this.curX = p.getEmptyStartLine();
        this.curY = p.getEmptyStartColumn();
        movePeg();
        System.out.println("In PegLogique constructor");
        System.out.println("Is solution found? : " +isSolution());

    }

    public boolean movePeg() {
        if(isSolution()) {
            return true;
        }

        for (int i = curX; curX < p.getTabCases().length; curX++) {
            for (int j = curY; curY < p.getTabCases().length; curY++) {
                if(isOccupied(curX, curY)) { // has a peg on it

                }
            }
        }
        return false;
    }

    public boolean isOccupied(int curX, int curY) {
        return p.getTabCases()[curX][curY] == IS_FILLED;
    }

    public boolean isMovable(int curX, int curY) {

        return false;
    }

    public boolean isSolution() {
        int nbPegsFilled = 0;
        int[][] tabCases = p.getTabCases();
         for (int i=0; i < tabCases.length; i++) {
            for (int j=0; j < tabCases.length; j++) {
                if(tabCases[i][j] == IS_FILLED) {
                    nbPegsFilled += 1;
                }
            }
        }
        return nbPegsFilled == 1 ? true : false;
    }

}

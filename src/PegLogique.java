import jdk.management.resource.internal.inst.FileOutputStreamRMHooks;

public class PegLogique {
//    private Direction d = Direction.NORD; // NORD, EST, SUD, OUEST
    private Puzzle p = null;
    private final int IS_EMPTY = 2;
    private final int IS_FILLED = 1;
    private final int OUT_OF_GAME = 0;
    private int curX;
    private int curY;
//    private MementoDeplacement md = null;

    public PegLogique(Puzzle p) {
        this.p = p;
        this.curX = p.getEmptyStartLine();
        this.curY = p.getEmptyStartColumn();
        System.out.println("In PegLogique constructor");
        System.out.println("Empty position is at line: " +curX+ " and column: " +curY);
        movePeg(curX, curY);
//        System.out.println("Is solution found? : " +isSolution());

    }

    public boolean movePeg(int curX, int curY) {
        if(isSolution()) {
            return true;
        }
        for (int i = curX; curX < p.getTabCases().length; curX++) {
            for (int j = curY; curY < p.getTabCases().length; curY++) {
                if(isOccupied(curX, curY)) { // has a peg on it
                    // checker si la position dapres (peu importe la direction) est occupee
                    // checker si 2 positions apres (meme direction que la premiere) est occupee
                    // la fonction ismovable fait cette job
                    // p.S. notre curX, curY sont les positions d'un point vide ici
                    // sinon la logique ne marche plus
                    // a la fin resetter la position curx et cury a un nouveau point vide


//                    if(isSolution())
//                        continue;
                }
            }
        }
        return false;
    }

    public boolean isOccupied(int curX, int curY) {
        return p.getTabCases()[curX][curY] == IS_FILLED;
    }

    public boolean isMovable(int curX, int curY) {
        int[][] tabCases = p.getTabCases();

        if(tabCases[curX][curY] == IS_FILLED) {
            System.out.println("Current position is filled");
        }
        System.out.println("Current position is empty");
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

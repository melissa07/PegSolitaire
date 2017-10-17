import jdk.management.resource.internal.inst.FileOutputStreamRMHooks;

public class PegLogique {
    private Direction d = Direction.NORD; // NORD, EST, SUD, OUEST
    private Puzzle p;
    private final int IS_EMPTY = 2;
    private final int IS_FILLED = 1;
    private final int OUT_OF_GAME = 0;
    private int total_pegs = 0;
    private int curX;
    private int curY;
//    private MementoDeplacement md = null;

    public PegLogique(Puzzle p) {
        this.p = p;
        this.curX = p.getEmptyStartLine();
        this.curY = p.getEmptyStartColumn();
        System.out.println("Original board: ");
        p.printBoard();
        System.out.println("Solving the puzzle...");
        movePeg(curX, curY);
//        System.out.println("Is solution found? : " +isSolution());

    }

    public boolean movePeg(int curX, int curY) {
        if(isSolution()) {
            return true;
        }
        int[][] tabCases = p.getTabCases();
        p.printBoard();

        for(int i = 0; i < 7; i++){

            //quand on a fait tout les case on regarde lautre case qui as ete liberer par coup precedent
            //TODO remplacer NORD, EST, SUD, OUEST, par la direction du dernier coup a l aide du memento.
        /*  if(i == 4) {
                switch (d) {
                    case NORD: curY =curY - 1;
                    case EST: curX = curX - 1;
                    case SUD: curY = curY + 1;
                    case OUEST: curX = curX + 1;
                }
            }
        */
            //version temporaire en attendant le memento pour tester le reste de l<algo
            if(i == 4) {
                if(tabCases[curX][curY+1] == IS_EMPTY){curY = curY + 1;}
                else if(tabCases[curX+1][curY] == IS_EMPTY){curX = curX + 1;}
                else if(tabCases[curX][curY-1] == IS_EMPTY){curY = curY - 1;}
                else if(tabCases[curX-1][curY] == IS_EMPTY){curX = curX - 1;}
            }
            //fin version temporaire

            if(isMovable(curX, curY, d)) {
                System.out.println("Peg is movable");
                //deplacement (a mettre dans memento pour annuler si mauvais mouvement)
                switch (d) {
                    case NORD:
                        tabCases[curX][curY] = IS_FILLED;
                        tabCases[curX][curY-2] = IS_EMPTY;
                        tabCases[curX][curY-1] = IS_EMPTY;
                        break;
                    case EST:
                        tabCases[curX][curY] = IS_FILLED;
                        tabCases[curX+2][curY] = IS_EMPTY;
                        tabCases[curX+1][curY] = IS_EMPTY;
                        break;
                    case SUD:
                        tabCases[curX][curY] = IS_FILLED;
                        tabCases[curX][curY+2] = IS_EMPTY;
                        tabCases[curX][curY+1] = IS_EMPTY;
                        break;
                    case OUEST:
                        tabCases[curX][curY] = IS_FILLED;
                        tabCases[curX-2][curY] = IS_EMPTY;
                        tabCases[curX-1][curY] = IS_EMPTY;
                        break;
                }
                p.setTabCases(tabCases);

                //fin

                switch (d){
                    case NORD:
                        if (movePeg(curX, curY - 2) == true) {
                            return true;
                        }
                        break;

                    case EST:
                        if (movePeg(curX + 2, curY) == true) {
                            return true;
                        }
                        break;
                    case SUD:
                        if (movePeg(curX, curY + 2) == true) {
                            return true;
                        }
                        break;
                    case OUEST:
                        if (movePeg(curX - 2, curY) == true) {
                            return true;
                        }
                        break;
                }

                //TODO Il faut anuller le deplacement effectuer avec le memento
                //du code temporaire qui se veux undo le deplacement
                tabCases[curX][curY] = IS_EMPTY;
                tabCases[curX + 1][curY + 1] = IS_FILLED;
                tabCases[curX + 2][curY + 2] = IS_FILLED;
                //undo fin
            }
            //prochaine direction
            switch (d){
                case NORD:
                    d=Direction.EST;
                    break;
                case EST:
                    d=Direction.SUD;
                    break;
                case SUD:
                    d=Direction.OUEST;
                    break;
                case OUEST:
                    d=Direction.NORD;
                    break;
            }
        }

        return false;

      /*
        // je suis pas sur du double boucle for. Les 2 boucle for parcours quoi exactement? Il faut pas juste itere nos 7 possibilité?

        for (int i = curX; curX < p.getTabCases().length; curX++) {
            for (int j = curY; curY < p.getTabCases().length; curY++) {
                if(isOccupied(curX, curY)) { // has a peg on it
                    // checker si la position dapres (peu importe la direction) est occupee
                    // checker si 2 positions apres (meme direction que la premiere) est occupee
                    // la fonction ismovable fait cette job
                    // p.S. notre curX, curY sont les positions d'un point vide ici
                    // sinon la logique ne marche plus
                    // a la fin resetter la position curx et cury a un nouveau point vide
                    if(isMovable(curX, curY, d)) {
                        System.out.println("Peg is movable");
                    }

//                    if(isSolution())
//                        continue;
                }
            }
        }
        return false;

        */
    }

    public boolean isOccupied(int curX, int curY) {
        return p.getTabCases()[curX][curY] == IS_FILLED;
    }

    public boolean isMovable(int curX, int curY, Direction dir) {
        int[][] tabCases = p.getTabCases();

        switch (dir) {
            case NORD:
                return tabCases[curX][curY] == IS_EMPTY && tabCases[curX][curY-2] == IS_FILLED && tabCases[curX][curY-1] == IS_FILLED;
            case EST:
                return tabCases[curX][curY] == IS_EMPTY && tabCases[curX+2][curY] == IS_FILLED && tabCases[curX+1][curY] == IS_FILLED;
            case SUD:
                return tabCases[curX][curY] == IS_EMPTY && tabCases[curX][curY+2] == IS_FILLED && tabCases[curX][curY+1] == IS_FILLED;
            case OUEST:
                return tabCases[curX][curY] == IS_EMPTY && tabCases[curX-2][curY] == IS_FILLED && tabCases[curX-1][curY] == IS_FILLED;
        }
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

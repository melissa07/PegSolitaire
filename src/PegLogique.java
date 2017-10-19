public class PegLogique {
    private Direction d = Direction.NORD; // NORD, EST, SUD, OUEST
    private Puzzle p;
    private final int IS_EMPTY = 2;
    private final int IS_FILLED = 1;
    private final int OUT_OF_GAME = 0;
    private int total_pegs = 0;
    private int counter = 0;
    private DeplacementsCakeTaker depl = new DeplacementsCakeTaker();
//    private MementoDeplacement md = null;

    public PegLogique(Puzzle p) {
        this.p = p;
        System.out.println("Original board: ");
        p.printBoard();
        System.out.println("Solving the puzzle...");
        boolean foundSolution = movePeg();
        System.out.println("Is solution found? : " +foundSolution);
        System.out.println("nombre total de move : " + counter);
    }

    public boolean movePeg() {
        if(isSolution()) {
            return true;
        }
        int[][] tabCases = p.getTabCases();
        //p.printBoard();

        for(int curX = 0; curX < tabCases.length; curX++) {
            for(int curY = 0; curY < tabCases[curX].length; curY++){
                for(int k = 0; k < 4; k++){

                    if(isMovable(curX, curY, d)) {
                        counter++;
                        //System.out.println("Peg is movable");
                        //deplacement (a mettre dans memento pour annuler si mauvais mouvement)
                        switch (d) {
                            case NORD:
                                tabCases[curX][curY] = IS_FILLED;
                                tabCases[curX][curY - 2] = IS_EMPTY;
                                tabCases[curX][curY - 1] = IS_EMPTY;
                                depl.add(new MementoDeplacement(curX, curY, Direction.NORD)); // todo est-ce que ce sont les bonnes coordonnes a enregistrer ?
                                break;
                            case EST:
                                tabCases[curX][curY] = IS_FILLED;
                                tabCases[curX + 2][curY] = IS_EMPTY;
                                tabCases[curX + 1][curY] = IS_EMPTY;
                                depl.add(new MementoDeplacement(curX, curY, Direction.EST)); // todo est-ce que ce sont les bonnes coordonnes a enregistrer ?
                                break;
                            case SUD:
                                tabCases[curX][curY] = IS_FILLED;
                                tabCases[curX][curY + 2] = IS_EMPTY;
                                tabCases[curX][curY + 1] = IS_EMPTY;
                                depl.add(new MementoDeplacement(curX, curY, Direction.SUD)); // todo est-ce que ce sont les bonnes coordonnes a enregistrer ?
                                break;
                            case OUEST:
                                tabCases[curX][curY] = IS_FILLED;
                                tabCases[curX - 2][curY] = IS_EMPTY;
                                tabCases[curX - 1][curY] = IS_EMPTY;
                                depl.add(new MementoDeplacement(curX, curY, Direction.OUEST)); // todo est-ce que ce sont les bonnes coordonnes a enregistrer ?
                                break;
                        }
                        p.setTabCases(tabCases);

                     /*
                        for (int s=0; s<depl.getSize(); s++) {
                            System.out.println("Deplacement: " +depl.get(s).getDirection() + " " + depl.get(s).getPosX()
                                    + " " + depl.get(s).getPosY());
                        }
                    */
                        if (movePeg() == true) {
                            return true;
                        }

                        if(depl.getSize() > 0) {
                            curX = depl.get(depl.getSize() - 1).getPosX();
                            curY = depl.get(depl.getSize() - 1).getPosY();
                            d = depl.get(depl.getSize() - 1).getDirection();

                            switch (d) {
                                case NORD:
                                    tabCases[curX][curY] = IS_EMPTY;
                                    tabCases[curX][curY - 2] = IS_FILLED;
                                    tabCases[curX][curY - 1] = IS_FILLED;
                                    break;
                                case EST:
                                    tabCases[curX][curY] = IS_EMPTY;
                                    tabCases[curX + 2][curY] = IS_FILLED;
                                    tabCases[curX + 1][curY] = IS_FILLED;
                                    break;
                                case SUD:
                                    tabCases[curX][curY] = IS_EMPTY;
                                    tabCases[curX][curY + 2] = IS_FILLED;
                                    tabCases[curX][curY + 1] = IS_FILLED;
                                    break;
                                case OUEST:
                                    tabCases[curX][curY] = IS_EMPTY;
                                    tabCases[curX - 2][curY] = IS_FILLED;
                                    tabCases[curX - 1][curY] = IS_FILLED;
                                    break;
                            }
                            depl.undo(); // todo replace pegs at their previous places
                           // System.out.println("retour en arriere.");
                            //p.printBoard();
                        }
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
            }
        }

        return false;
    }

    public boolean isOccupied(int curX, int curY) {
        return p.getTabCases()[curX][curY] == IS_FILLED;
    }

    public boolean isMovable(int curX, int curY, Direction dir) {
        int[][] tabCases = p.getTabCases();

        switch (dir) {
            case NORD:
                if(curY-2 < 0)
                    return false;
                return tabCases[curX][curY] == IS_EMPTY && tabCases[curX][curY-2] == IS_FILLED && tabCases[curX][curY-1] == IS_FILLED;
            case EST:
                if(curX+2 >= tabCases.length)
                    return false;
                return tabCases[curX][curY] == IS_EMPTY && tabCases[curX+2][curY] == IS_FILLED && tabCases[curX+1][curY] == IS_FILLED;
            case SUD:
                if(curY+2 >= tabCases.length)
                    return false;
                return tabCases[curX][curY] == IS_EMPTY && tabCases[curX][curY+2] == IS_FILLED && tabCases[curX][curY+1] == IS_FILLED;
            case OUEST:
                if(curX-2 < 0)
                    return false;
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
        return nbPegsFilled == IS_FILLED ? true : false;
    }

}

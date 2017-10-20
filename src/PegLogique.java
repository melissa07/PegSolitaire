public class PegLogique {
    private Puzzle p;
    private final int IS_EMPTY = 2;
    private final int IS_FILLED = 1;
    private final int OUT_OF_GAME = 0;
    private int total_pegs = 0;
    private int counter = 0;

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

                    if(isMovable(curX, curY, k)) {
                        counter++;
                        //System.out.println("Peg is movable");
                        //deplacement (a mettre dans memento pour annuler si mauvais mouvement)
                        switch (k) {
                            case 1:
                                tabCases[curX][curY] = IS_FILLED;
                                tabCases[curX][curY - 2] = IS_EMPTY;
                                tabCases[curX][curY - 1] = IS_EMPTY;
                                break;
                            case 0:
                                tabCases[curX][curY] = IS_FILLED;
                                tabCases[curX + 2][curY] = IS_EMPTY;
                                tabCases[curX + 1][curY] = IS_EMPTY;
                                break;
                            case 3:
                                tabCases[curX][curY] = IS_FILLED;
                                tabCases[curX][curY + 2] = IS_EMPTY;
                                tabCases[curX][curY + 1] = IS_EMPTY;
                                break;
                            case 2:
                                tabCases[curX][curY] = IS_FILLED;
                                tabCases[curX - 2][curY] = IS_EMPTY;
                                tabCases[curX - 1][curY] = IS_EMPTY;
                                break;
                        }
                        p.setTabCases(tabCases);

                        if (movePeg() == true) {
                            return true;
                        }else {

                                switch (k) {
                                    case 1:
                                        tabCases[curX][curY] = IS_EMPTY;
                                        tabCases[curX][curY - 2] = IS_FILLED;
                                        tabCases[curX][curY - 1] = IS_FILLED;
                                        break;
                                    case 0:
                                        tabCases[curX][curY] = IS_EMPTY;
                                        tabCases[curX + 2][curY] = IS_FILLED;
                                        tabCases[curX + 1][curY] = IS_FILLED;
                                        break;
                                    case 3:
                                        tabCases[curX][curY] = IS_EMPTY;
                                        tabCases[curX][curY + 2] = IS_FILLED;
                                        tabCases[curX][curY + 1] = IS_FILLED;
                                        break;
                                    case 2:
                                        tabCases[curX][curY] = IS_EMPTY;
                                        tabCases[curX - 2][curY] = IS_FILLED;
                                        tabCases[curX - 1][curY] = IS_FILLED;
                                        break;
                                }
                                p.setTabCases(tabCases);
                                //System.out.println("retour en arriere.");
                                //p.printBoard();
                            }

                    }

                }
            }
        }

        return false;
    }

    public boolean isOccupied(int curX, int curY) {
        return p.getTabCases()[curX][curY] == IS_FILLED;
    }

    public boolean isMovable(int curX, int curY, int k) {
        int[][] tabCases = p.getTabCases();

        switch (k) {
            case 1:
                if(curY-2 < 0)
                    return false;
                return tabCases[curX][curY] == IS_EMPTY && tabCases[curX][curY-2] == IS_FILLED && tabCases[curX][curY-1] == IS_FILLED;
            case 0:
                if(curX+2 >= tabCases.length)
                    return false;
                return tabCases[curX][curY] == IS_EMPTY && tabCases[curX+2][curY] == IS_FILLED && tabCases[curX+1][curY] == IS_FILLED;
            case 3:
                if(curY+2 >= tabCases.length)
                    return false;
                return tabCases[curX][curY] == IS_EMPTY && tabCases[curX][curY+2] == IS_FILLED && tabCases[curX][curY+1] == IS_FILLED;
            case 2:
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

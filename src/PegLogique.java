public class PegLogique {
    private final int NORTH = 1;
    private final int EAST = 0;
    private final int WEST = 2;
    private final int SOUTH =3;

    private Puzzle p;
    private final int IS_EMPTY = 2;
    private final int IS_FILLED = 1;
    private final int OUT_OF_GAME = 0;
    private int total_pegs = 0;
    private int counter = 0;
    private int nbGoodMove = 0;
    private int [][][] tabSolution;

    public PegLogique(Puzzle p) {
        this.p = p;
        Chronometer chronometer = new Chronometer();
        tabSolution = new int[p.getNbPeg() - 1][7][7];
        total_pegs = p.getNbPeg();
        System.out.println("Original board: ");
        p.printBoard();
        System.out.println("Solving the puzzle...");
        chronometer.start();
        boolean foundSolution = movePeg();
        chronometer.stop();

        for(int i =0; i < total_pegs -1;i++){
                p.setTabCases(tabSolution[i]);
                p.printBoard();
        }

        System.out.println("Is solution found? : " +foundSolution);
        System.out.println("solution found in : " + chronometer.getDureeMs() + "ms");
        System.out.println("nomber of total move : " + counter);
        System.out.println("nomber of total good move : " + nbGoodMove);
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
                        tabCases = move(k, curX, curY, tabCases);
                        tabSolution = fillSolution(tabSolution, tabCases, nbGoodMove);
                        nbGoodMove++;
                        p.setTabCases(tabCases);

                        if (movePeg() == true) {
                            return true;
                        }else {
                            nbGoodMove--;
                            tabCases = unMove(k, curX, curY, tabCases);
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
            case NORTH:
                if(curY-2 < 0)
                    return false;
                return tabCases[curX][curY] == IS_EMPTY && tabCases[curX][curY-2] == IS_FILLED && tabCases[curX][curY-1] == IS_FILLED;
            case EAST:
                if(curX+2 >= tabCases.length)
                    return false;
                return tabCases[curX][curY] == IS_EMPTY && tabCases[curX+2][curY] == IS_FILLED && tabCases[curX+1][curY] == IS_FILLED;
            case SOUTH:
                if(curY+2 >= tabCases.length)
                    return false;
                return tabCases[curX][curY] == IS_EMPTY && tabCases[curX][curY+2] == IS_FILLED && tabCases[curX][curY+1] == IS_FILLED;
            case WEST:
                if(curX-2 < 0)
                    return false;
                return tabCases[curX][curY] == IS_EMPTY && tabCases[curX-2][curY] == IS_FILLED && tabCases[curX-1][curY] == IS_FILLED;
        }
        return false;
    }

    private int[][] move(int direction, int curX, int curY, int[][] board){

        switch (direction) {
            case NORTH:
                board[curX][curY] = IS_FILLED;
                board[curX][curY - 2] = IS_EMPTY;
                board[curX][curY - 1] = IS_EMPTY;
                break;
            case EAST:
                board[curX][curY] = IS_FILLED;
                board[curX + 2][curY] = IS_EMPTY;
                board[curX + 1][curY] = IS_EMPTY;
                break;
            case SOUTH:
                board[curX][curY] = IS_FILLED;
                board[curX][curY + 2] = IS_EMPTY;
                board[curX][curY + 1] = IS_EMPTY;
                break;
            case WEST:
                board[curX][curY] = IS_FILLED;
                board[curX - 2][curY] = IS_EMPTY;
                board[curX - 1][curY] = IS_EMPTY;
                break;
        }

        return board;
    }

    private int[][] unMove(int direction, int curX, int curY, int[][] board){

        switch (direction) {
            case NORTH:
                board[curX][curY] = IS_EMPTY;
                board[curX][curY - 2] = IS_FILLED;
                board[curX][curY - 1] = IS_FILLED;
                break;
            case EAST:
                board[curX][curY] = IS_EMPTY;
                board[curX + 2][curY] = IS_FILLED;
                board[curX + 1][curY] = IS_FILLED;
                break;
            case SOUTH:
                board[curX][curY] = IS_EMPTY;
                board[curX][curY + 2] = IS_FILLED;
                board[curX][curY + 1] = IS_FILLED;
                break;
            case WEST:
                board[curX][curY] = IS_EMPTY;
                board[curX - 2][curY] = IS_FILLED;
                board[curX - 1][curY] = IS_FILLED;
                break;
        }

        return board;
    }



    private int[][][] fillSolution(int[][][] tabSolution,int[][] tabCases, int ind){

        for (int i=0; i < tabCases.length; i++) {
            for (int j=0; j < tabCases.length; j++) {
                tabSolution[ind][i][j] = tabCases[i][j];
            }
        }

        return tabSolution;
    }

    private void excludSolution(){

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

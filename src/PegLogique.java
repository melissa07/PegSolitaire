public class PegLogique {
    private Direction d = Direction.NORD; // NORD, EST, SUD, OUEST
    private Puzzle p;
    private final int IS_EMPTY = 2;
    private final int IS_FILLED = 1;
    private final int OUT_OF_GAME = 0;
    private int total_pegs = 0;
    private int curX;
    private int curY;
    private DeplacementsCakeTaker depl = new DeplacementsCakeTaker();
//    private MementoDeplacement md = null;

    public PegLogique(Puzzle p) {
        this.p = p;
        this.curX = p.getEmptyStartLine();
        this.curY = p.getEmptyStartColumn();
        System.out.println("Original board: ");
        p.printBoard();
        System.out.println("Solving the puzzle...");
        boolean foundSolution = movePeg(curX, curY);
        System.out.println("Is solution found? : " +foundSolution);

    }

    public boolean movePeg(int curX, int curY) {
        if(isSolution()) {
            return true;
        }
        int[][] tabCases = p.getTabCases();
        p.printBoard();

        for(int i = 0; i < 7; i++) {
            if(isMovable(curX, curY, d)) {
                System.out.println("Peg is movable");
                //deplacement (a mettre dans memento pour annuler si mauvais mouvement)
                switch (d) {
                    case NORD:
                        tabCases[curX][curY] = IS_FILLED;
                        tabCases[curX][curY-2] = IS_EMPTY;
                        tabCases[curX][curY-1] = IS_EMPTY;
                        depl.add(new MementoDeplacement(curX, curY, Direction.NORD)); // todo est-ce que ce sont les bonnes coordonnes a enregistrer ?

                        break;
                    case EST:
                        tabCases[curX][curY] = IS_FILLED;
                        tabCases[curX+2][curY] = IS_EMPTY;
                        tabCases[curX+1][curY] = IS_EMPTY;
                        depl.add(new MementoDeplacement(curX, curY, Direction.EST)); // todo est-ce que ce sont les bonnes coordonnes a enregistrer ?
                        break;
                    case SUD:
                        tabCases[curX][curY] = IS_FILLED;
                        tabCases[curX][curY+2] = IS_EMPTY;
                        tabCases[curX][curY+1] = IS_EMPTY;
                        depl.add(new MementoDeplacement(curX, curY, Direction.SUD)); // todo est-ce que ce sont les bonnes coordonnes a enregistrer ?
                        break;
                    case OUEST:
                        tabCases[curX][curY] = IS_FILLED;
                        tabCases[curX-2][curY] = IS_EMPTY;
                        tabCases[curX-1][curY] = IS_EMPTY;
                        depl.add(new MementoDeplacement(curX, curY, Direction.OUEST)); // todo est-ce que ce sont les bonnes coordonnes a enregistrer ?
                        break;
                }
                p.setTabCases(tabCases);

                for (int s=0; s<depl.getSize(); s++) {
                    System.out.println("Deplacement: " +depl.get(s).getDirection() + " " + depl.get(s).getPosX()
                            + " " + depl.get(s).getPosY());
                }

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

                // recupere le dernier move du "memento"

//                if(depl.listDeplacements.size() > 0) {
//                    curX = depl.listDeplacements.get(depl.listDeplacements.size()-1).getDeplX();
//                    curY = depl.listDeplacements.get(depl.listDeplacements.size()-1).getDeplY();
//                }
                // si on atteint une bordure
                if(curX+2 < p.getTabCases().length && curY+2 < p.getTabCases().length) {
                    tabCases[curX][curY] = IS_EMPTY;
                    tabCases[curX + 1][curY + 1] = IS_FILLED;
                    tabCases[curX + 2][curY + 2] = IS_FILLED;
                }
                else {
                    depl.undo(); // todo replace pegs at their previous places
                    if(depl.getSize() > 0) {
                        curX = depl.get(depl.getSize()-1).getPosX();
                        curY = depl.get(depl.getSize()-1).getPosY();
                    }
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

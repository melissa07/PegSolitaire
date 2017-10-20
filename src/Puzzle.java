public class Puzzle {
    private int[][]tabCases = null;
    private int emptyStartLine;
    private int emptyStartColumn;
    private int newX;
    private int newY;


    public Puzzle(int[][] tabCases) {
        this.tabCases = tabCases;
    }

    public int getEmptyStartLine() {
        return emptyStartLine;
    }

    public void setEmptyStartLine(int emptyStartLine) {
        this.emptyStartLine = emptyStartLine;
    }

    public int getEmptyStartColumn() {
        return emptyStartColumn;
    }

    public void setEmptyStartColumn(int emptyStartColumn) {
        this.emptyStartColumn = emptyStartColumn;
    }

    public int getNewX() {
        return newX;
    }

    public void setNewX(int newX) {
        this.newX = newX;
    }

    public int getNewY() {
        return newY;
    }

    public void setNewY(int newY) {
        this.newY = newY;
    }

    public int[][] getTabCases() {
        return tabCases;
    }

    public void setTabCases(int[][] tabCases) {
        this.tabCases = tabCases;
    }

    public int getNbPeg(){
        int nbPeg = 0;
        for(int i = 0; i < this.tabCases.length; i++){
            for(int j = 0; j < this.tabCases.length; j++){
               if(tabCases[i][j] == 1){
                   nbPeg++;
               }
            }
        }
        return nbPeg;
    }

    public void printBoard(){
        for(int i = 0; i < this.tabCases.length; i++){
            for(int j = 0; j < this.tabCases.length; j++){
                    System.out.print(this.tabCases[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("*****************************");
    }
}

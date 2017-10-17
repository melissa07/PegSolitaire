public class Puzzle {
    private int[][]tabCases = null;
    private int emptyStartLine;
    private int emptyStartColumn;
    private int numberBorderOfBoard = 8;


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

    public int[][] getTabCases() {
        return tabCases;
    }

    public void setTabCases(int[][] tabCases) {
        this.tabCases = tabCases;
    }

    public void printBoard(){
        for(int i = 0; i < this.tabCases.length; i++){
            for(int j = 0; j < this.tabCases.length; j++){
                if(this.tabCases[j][i] != numberBorderOfBoard){
                    System.out.print(this.tabCases[j][i] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("*****************************");
    }
}

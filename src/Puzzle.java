public class Puzzle {
    private int[][]tabCases = null;
    private int emptyStartLine;
    private int emptyStartColumn;


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
}

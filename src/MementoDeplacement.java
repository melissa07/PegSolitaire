public class MementoDeplacement {
    private int curX;
    private int curY;
    private Direction direction;

    public MementoDeplacement(int curX, int curY, Direction direction){
        this.curX = curX;
        this.curY = curY;
        this.direction = direction;
    }

    public int getCurX() {

        return curX;
    }

    public void setCurX(int curX) {
        this.curX = curX;
    }

    public int getCurY() {
        return curY;
    }

    public void setCurY(int curY) {
        this.curY = curY;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}

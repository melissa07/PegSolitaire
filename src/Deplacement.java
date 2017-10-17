public class Deplacement {
    private int curX;
    private int curY;
    private Direction direction;

    public Deplacement(int curX, int curY, Direction direction){
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

    public MementoDeplacement saveDeplacementFromMemento(){
        return new MementoDeplacement(curX, curY, direction);
    }

    public void getDeplacementFromMemento(MementoDeplacement memento){
        curX = memento.getPosX();
        curY = memento.getPosY();
        direction = memento.getDirection();
    }
}

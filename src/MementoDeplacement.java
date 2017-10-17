public class MementoDeplacement {
    private int posX;
    private int posY;
    private Direction direction;

    public MementoDeplacement(int posX, int posY, Direction direction){
        this.posX = posX;
        this.posY = posY;
        this.direction = direction;
    }

    public int getPosX() {

        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}

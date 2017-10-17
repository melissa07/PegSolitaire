import java.util.ArrayList;
import java.util.List;

public class Deplacement {
    List<Deplacement> listDeplacements = new ArrayList<>();
    Direction d;
    int deplX;
    int deplY;

    public Deplacement() {}

    public Deplacement(Direction d, int deplX, int deplY) {
        this.d = d;
        this.deplX = deplX;
        this.deplY = deplY;
    }

    public Direction getD() {
        return d;
    }

    public void setD(Direction d) {
        this.d = d;
    }

    public int getDeplX() {
        return deplX;
    }

    public void setDeplX(int deplX) {
        this.deplX = deplX;
    }

    public int getDeplY() {
        return deplY;
    }

    public void setDeplY(int deplY) {
        this.deplY = deplY;
    }

    public void addDeplacement(Deplacement deplacement) {
        this.listDeplacements.add(deplacement);
    }

    public void undoDeplacement() {
        listDeplacements.remove(listDeplacements.size()-1);
    }
}

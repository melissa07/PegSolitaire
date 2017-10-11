import java.util.ArrayList;
import java.util.List;

public class DeplacementsCakeTaker {
    List<MementoDeplacement> listDeplacements = new ArrayList<>();

    public void addDeplacement(MementoDeplacement d) {
        listDeplacements.add(d);
    }

    public MementoDeplacement getDeplacement(int index) {
        return listDeplacements.get(index);
    }
}

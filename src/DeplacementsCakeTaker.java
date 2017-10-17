import java.util.ArrayList;
import java.util.List;

public class DeplacementsCakeTaker {
    List<MementoDeplacement> listMementoDeplacements = new ArrayList<MementoDeplacement>();

    public void add(MementoDeplacement mementoDeplacement) {
        listMementoDeplacements.add(mementoDeplacement);
    }

    public MementoDeplacement get(int index) {
        return listMementoDeplacements.get(index);
    }
}

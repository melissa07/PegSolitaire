import java.util.ArrayList;
import java.util.List;

public class DeplacementsCakeTaker {
    List<MementoDeplacement> listMementoDeplacements = new ArrayList<MementoDeplacement>();

    public void add(MementoDeplacement mementoDeplacement) {
        listMementoDeplacements.add(mementoDeplacement);
    }

    public void undo(){
        listMementoDeplacements.remove(listMementoDeplacements.size()-1);
    }

    public MementoDeplacement get(int index) {
        return listMementoDeplacements.get(index);
    }

    public int getSize() {
        return listMementoDeplacements.size();
    }
}

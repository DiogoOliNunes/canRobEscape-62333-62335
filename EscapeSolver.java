import java.util.ArrayList;
import java.util.List;

import UnionFind.*;
public class EscapeSolver {

    private final int length, width, robDiameter, numBeams;
    private UnionFind partition;
    private List<Beam> beams;
    private boolean canEscape;


    public EscapeSolver (int L, int W, int D, int B) {
        this.length = L;
        this.width = W;
        this.robDiameter = D;
        this.numBeams = B;
        this.beams = new ArrayList<>(numBeams+2);
        this.partition = new UnionFindInArray(numBeams+2);
        this.canEscape = true;
    }

    private void sortBeams() {
        this.beams.sort((beam1, beam2) -> {
            if (beam1.x != beam2.x)
                return Integer.compare(beam1.x, beam2.x);
            else
                return Integer.compare(beam1.y, beam2.y);
        });
    }

    public boolean getCanEscape() {
        addBeam(0, 0);
        addBeam(0, width);
        sortBeams();
        checkDistances();

        return canEscape;
    }

    private void checkDistances() {
        if (beams.size() > 3)
            beams.forEach(beam -> {
                int beamIndex = beams.indexOf(beam);
                if (beamIndex != 0 && beamIndex != 1)
                    for (int i = beamIndex + 1; i < beams.size(); i++)
                        if (beam.calculateDistance(beams.get(i)) < robDiameter)
                            unify(beamIndex, i);
            });

        for (int i = 2; i < beams.size(); i++) {
            Beam beam = beams.get(i);
            if (width - beam.y < robDiameter)
                unify(i, 1);
            if (beam.y < robDiameter)
                unify(i, 0);
            if (partition.find(0) == partition.find(1)) {
                canEscape = false;
                break;
            }
        }
    }

    private void unify(int index1, int index2) {
        int rep1 = partition.find(index1);
        int rep2 = partition.find(index2);
        partition.union(rep1, rep2);
    }

    public void addBeam(int x, int y) {
        beams.add(new Beam(x, y));
    }

}

import java.awt.geom.Point2D;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.awt.Point;

import UnionFind.*;
public class EscapeSolver {

    private final int length, width, robDiameter, numBeams;
    public boolean canEscape;
    private UnionFind partition;
    private Map<Point, Integer> beamToIndexMap;


    public EscapeSolver (int L, int W, int D, int B) {
        this.length = L;
        this.width = W;
        this.robDiameter = D;
        this.numBeams = B;
        this.canEscape = true;
        this.beamToIndexMap = new HashMap<>(B*2);
        this.partition = new UnionFindInArray(B*2);
    }

    private List<int[]> sortBeams(List<int[]> beams) {
        beams.sort((beam1, beam2) -> {
            if (beam1[0] != beam2[0])
                return Integer.compare(beam1[0], beam2[0]);
            else
                return Integer.compare(beam1[1], beam2[1]);
        });
        return beams;
    }

    public void addBeam(int x, int y) {
        Point2D beamPosition = new Point(x, y);
        if (this.beamToIndexMap.containsKey(beamPosition)){}
    }

    public boolean canEscape() {
        return this.canEscape;
    }

}

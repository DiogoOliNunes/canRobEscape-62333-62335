import java.awt.geom.Point2D;
import java.util.ArrayList;
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

    private List<Beam> sortBeams() {
        beams.sort((beam1, beam2) -> {
            if (beam1.getX() != beam2.getX())
                return Integer.compare(beam1.getX(), beam2.getX());
            else
                return Integer.compare(beam1.getY(), beam2.getY());
        });
        return beams;
    }

    public void addBeam(int x, int y) {
        beams.add(new Beam(x, y));
    }

    public void generatePartitions() {
        for (int i = 0; i < beams.size(); i++)
            for (int j = i+1; j < beams.size(); j++) {
                Beam beam1 = beams.get(i);
                Beam beam2 = beams.get(j);
                int distance = calculateDistance(beam1, beam2);
                if (distance < robDiameter) {
                    try {
                        partition.union(i, j);
                    } catch (InvalidElementException | NotRepresentativeException | EqualSetsException e) {
                        e.printStackTrace();
                    }
                }
            }
    }

    private int calculateDistance(Beam beam1, Beam beam2) {
        int dx = beam1.getX() - beam2.getX();
        int dy = beam1.getY() - beam2.getY();
        return Math.abs(dx + dy);
    }

    public boolean canEscape() {
        sortBeams();
        generatePartitions();

        //para cada beam ver se o representante da arvore correspondente tem espaço com a parede e o respetivo representante de baixo?

        for (int i = 0; i < beams.size(); i++) {
            if (width - beams.get(i).getY() < robDiameter) {
                //ver a arvore/particoes correspondentes a este beam e verificar se o representante
                //tem espaço com a parede de baixo ou nao
                if (beams.get(partition.find(i)).getY() < robDiameter) canEscape = false;    //o que meto no find?
            }
        }

        return this.canEscape;
    }

}
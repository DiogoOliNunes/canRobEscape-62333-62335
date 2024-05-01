import java.util.List;

public class EscapeSolver {

    int length, width, robDiameter;
    List<int[]> laserBeams;
    boolean canEscape;

    public EscapeSolver (int L, int W, int D, List<int[]> beams) {
        this.length = L;
        this.width = W;
        this.robDiameter = D;
        this.laserBeams = sortBeams(beams);
        this.canEscape = true;
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

    public boolean canEscape() {
        return this.canEscape;
    }

}

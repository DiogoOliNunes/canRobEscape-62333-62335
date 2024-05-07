import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            getEscapeSolver(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void getEscapeSolver(BufferedReader reader) throws IOException {
        String[] size = reader.readLine().split(" ");
        int L = Integer.parseInt(size[0]);
        int W = Integer.parseInt(size[1]);

        int D = Integer.parseInt(reader.readLine());

        int B = Integer.parseInt(reader.readLine());

        EscapeSolver escapeSolver = new EscapeSolver(L, W, D, B);

        for (int i = 0; i < B; i++) {
            String[] coordinates = reader.readLine().split(" ");
            int x = Integer.parseInt(coordinates[0]);
            int y = Integer.parseInt(coordinates[1]);

            escapeSolver.addBeam(x, y);
        }

        if (escapeSolver.getCanEscape())
            System.out.println("Rob manages to escape!");
        else
            System.out.println("Impossible to escape");
    }
}

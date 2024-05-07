public class Beam {
    int x, y;
    public Beam (int x, int y) {
        this.x = x;
        this.y = y;
    } //TODO: transformar isto num record

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double calculateDistance(Beam other) {
        double xRes = Math.pow(other.x - this.x, 2);
        double yRes = Math.pow(other.y - this.y, 2);
        return Math.sqrt(xRes + yRes);
    }
}

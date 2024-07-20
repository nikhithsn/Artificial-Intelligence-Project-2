import java.util.Random;

public class Mouse {
    private int x, y;
    private boolean isStationary;
    private Grid grid;
    private Random rand = new Random();

    public Mouse(Grid grid, boolean isStationary) {
        this.grid = grid;
        this.isStationary = isStationary;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        if (!isStationary) {
            int move = rand.nextInt(5); // 0: stay, 1: up, 2: down, 3: left, 4: right
            switch (move) {
                case 1 -> { if (x > 0) x--; }
                case 2 -> { if (x < grid.getSize() - 1) x++; }
                case 3 -> { if (y > 0) y--; }
                case 4 -> { if (y < grid.getSize() - 1) y++; }
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
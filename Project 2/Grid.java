import java.util.Random;

public class Grid {
    private final int size = 40;
    private final Cell[][] grid;
    private Bot bot;
    private Mouse mouse;

    public Grid() {
        grid = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    public void placeBot(Bot bot) {
        this.bot = bot;
        Random rand = new Random();
        int x = rand.nextInt(size);
        int y = rand.nextInt(size);
        bot.setPosition(x, y);
    }

    public void placeMouse(Mouse mouse) {
        this.mouse = mouse;
        Random rand = new Random();
        int x = rand.nextInt(size);
        int y = rand.nextInt(size);
        mouse.setPosition(x, y);
    }

    public Cell getCell(int x, int y) {
        return grid[x][y];
    }

    public int getSize() {
        return size;
    }
}
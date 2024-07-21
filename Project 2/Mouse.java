import java.util.Random;

public class Mouse 
{
    private int x, y;
    private boolean isStationary;
    private Grid grid;
    private Random rand = new Random();

    public Mouse(Grid grid, boolean isStationary) 
    {
        this.grid = grid;
        this.isStationary = isStationary;
        placeMouseInGrid();
    }

    public void placeMouseInGrid() 
    {
        Random rand = new Random();
        int x = rand.nextInt(grid.getSize());
        int y = rand.nextInt(grid.getSize());
        this.x = x;
        this.y = y;
        grid.getCell(x, y).setMouse(true);
    }

    public void setPosition(int x, int y) 
    {
        grid.getCell(this.x, this.y).setMouse(false); 
        this.x = x;
        this.y = y;
        grid.getCell(x, y).setMouse(true);
    }

    public void move() 
    {
        if (!isStationary) {
            grid.getCell(x, y).setMouse(false);
            int move = rand.nextInt(5); // 0: stay, 1: up, 2: down, 3: left, 4: right
            switch (move) {
                case 1 -> { if (x > 0) x--; }
                case 2 -> { if (x < grid.getSize() - 1) x++; }
                case 3 -> { if (y > 0) y--; }
                case 4 -> { if (y < grid.getSize() - 1) y++; }
            }
            grid.getCell(x, y).setMouse(true);
        }
    }

    public int getX() 
    {
        return this.x;
    }

    public int getY() 
    {
        return this.y;
    }
}

import java.util.Random;

public abstract class Bot 
{
    protected int x, y;
    protected Grid grid;
    protected Random rand = new Random();

    public Bot(Grid grid) 
    {
        this.grid = grid;
    }

    public void setPosition(int x, int y) 
    {
        this.x = x;
        this.y = y;
    }

    public void moveUp() 
    {
        if (x > 0) x--;
    }

    public void moveDown() 
    {
        if (x < grid.getSize() - 1) x++;
    }

    public void moveLeft() 
    {
        if (y > 0) y--;
    }

    public void moveRight() 
    {
        if (y < grid.getSize() - 1) y++;
    }

    public boolean hasFoundMouse()
    {
        return (grid.getCell(x, y).hasMouse());
    }

    public abstract void move();

    public abstract boolean sense();

    public abstract void executeStrategy();
}

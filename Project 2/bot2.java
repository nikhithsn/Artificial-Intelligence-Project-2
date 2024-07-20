public class bot2 extends Bot {

    public bot2(Grid grid) {
        super(grid);
    }

    @Override
    public void move() {
        // Move toward the location with the highest probability
    }

    @Override
    public boolean sense() {
        // Sense the mouse
        return false;
    }

    @Override
    public void executeStrategy() {
        // Implement the strategy for Bot 2
    }
}

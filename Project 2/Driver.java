public class Driver {
    public static void main(String[] args) {
        Grid grid = new Grid();
        Bot bot1 = new bot1(grid);
        Bot bot2 = new bot2(grid);
        Bot bot3 = new bot3(grid);

        Mouse stationaryMouse = new Mouse(grid, true);
        Mouse movingMouse = new Mouse(grid, false);

        grid.placeBot(bot1);
        grid.placeMouse(stationaryMouse);

        // Simulate and compare the bots
        // Implement the required analysis
    }
}
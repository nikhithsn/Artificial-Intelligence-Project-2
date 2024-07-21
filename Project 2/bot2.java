public class bot2 extends Bot 
{
    private double alpha;
    private boolean moveTurn = false;
    private double[][] probabilities;
    private int targetX, targetY;

    public bot2(Grid grid, double alpha) 
    {
        super(grid);
        this.alpha = alpha;
    }

    @Override
    public void move() 
    {
            //probabilities = calculateProbabilities();
            int[] bestMove = findBestMove(probabilities);
            setPosition(bestMove[0], bestMove[1]);
    }

    @Override
    public boolean sense() 
    {
        int mouseX = grid.getMouse().getX();
        int mouseY = grid.getMouse().getY();
        int manhattanDistance = Math.abs(x - mouseX) + Math.abs(y - mouseY);
        double probability = Math.exp(-alpha * (manhattanDistance - 1));

        if (manhattanDistance == 1) 
        {
            return true;
        }

        return rand.nextDouble() < probability;
    }

    @Override
    public void executeStrategy() 
    {
        if (!moveTurn) 
        {
            if (sense())
            {
                targetX = grid.getMouse().getX();
                targetY = grid.getMouse().getY();
                probabilities = calculateProbabilities();
                moveTurn = true;
            }
        } 
        else 
        {
            move();
            moveTurn = false;
        }
    }

    private double[][] calculateProbabilities() 
    {
        double[][] probabilities = new double[grid.getSize()][grid.getSize()];

        for (int i = 0; i < grid.getSize(); i++) 
        {
            for (int j = 0; j < grid.getSize(); j++) 
            {
                int manhattanDistance = Math.abs(i - targetX) + Math.abs(j - targetY);
                probabilities[i][j] = Math.exp(-alpha * (manhattanDistance - 1));
            }
        }

        return probabilities;
    }

    private int[] findBestMove(double[][] probabilities) 
    {
        int bestX = x;
        int bestY = y;
        double highestProb = probabilities[bestX][bestY];

        int[][] directions = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };
        for (int[] dir : directions) 
        {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (newX >= 0 && newX < grid.getSize() && newY >= 0 && newY < grid.getSize() && probabilities[newX][newY] > highestProb) 
            {
                //System.out.println("Bot 2 - Found best move: condition satisfied");
                highestProb = probabilities[newX][newY];
                bestX = newX;
                bestY = newY;
            }
        }

        //System.out.println("Bot 2 - Found best move:" + bestX + ", " + bestY);
        return new int[] { bestX, bestY };
    }
}

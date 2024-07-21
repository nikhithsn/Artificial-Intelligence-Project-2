import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class bot3 extends Bot 
{
    private double alpha;
    private boolean moveTurn = false;
    private double[][] probabilities;
    private int targetX, targetY;
    private Queue<Boolean> senseHistory;
    private static final int historyLimit = 5;

    public bot3(Grid grid, double alpha) 
    {
        super(grid);
        this.alpha = alpha;
        this.senseHistory = new LinkedList<>();
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

        boolean beep = rand.nextDouble() < probability;
        updateSenseHistory(beep);

        return beep;
    }

    @Override
    public void executeStrategy() 
    {   
        int manhattanDistance = Math.abs(x - targetX) + Math.abs(y - targetY);

        if (manhattanDistance > 25)
        {
            if (!moveTurn) 
            {
                if (sense())
                {
                    targetX = grid.getMouse().getX();
                    targetY = grid.getMouse().getY();
                    probabilities = calculateProbabilities();
                    //System.out.println("Sensed mouse at (" + targetX + ", " + targetY + ")");
                }
            } 
            else 
            {
                move();
                //System.out.println("Moving to (" + x + ", " + y + ")");
                //System.out.println("Sensed mouse at (" + targetX + ", " + targetY + ")");
            }

        
            if (shouldMove())
            {
                moveTurn = true;
                probabilities = calculateProbabilities();
            }
            else
            {
                moveTurn = false;
            }
        }
        else
        {
            if (!moveTurn)
        {
            if (sense())
            {
                targetX = grid.getMouse().getX();
                targetY = grid.getMouse().getY();
                probabilities = calculateProbabilities();
                moveTurn = true;
                //System.out.println("Sensed mouse at (" + targetX + ", " + targetY + ")");
            }
        }
        else
        {
            move();
            //System.out.println("Moving to (" + x + ", " + y + ")");
            //System.out.println("Sensed mouse at (" + targetX + ", " + targetY + ")");
            if (grid.getCell(x, y) == grid.getCell(targetX, targetY))
            {
                moveTurn = false;
            }
            else
            {
                probabilities = calculateProbabilities();
            }
        }
        }
    }

    private void updateSenseHistory(boolean beep) 
    {
        if (senseHistory.size() >= historyLimit) 
        {
            senseHistory.poll();
        }

        senseHistory.offer(beep);
    }

    private boolean shouldMove() 
    {
        int beepCount = 0;

        for (boolean beep : senseHistory) 
        {
            if (beep) 
            {
                beepCount++;
            }
        }

        // Strategy: Prioritize moving if there have been few recent successful beep occurances 
            // Exception: if there have  been no beeps, which means the bot still needs direction
        double averageBeepOccurance = (double) beepCount / senseHistory.size();
        int manhattanDistance = Math.abs(x - targetX) + Math.abs(y - targetY);

        /*if (manhattanDistance > 5)
        {
            return averageBeepOccurance < 0.5 && averageBeepOccurance > 0;
        }
        else
        {
            return true;
        }*/

        return averageBeepOccurance < 0.5 && averageBeepOccurance > 0;
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
                highestProb = probabilities[newX][newY];
                bestX = newX;
                bestY = newY;
            }
        }

        //System.out.println("Bot 3 - Found best move:" + bestX + ", " + bestY);
        return new int[] { bestX, bestY };
    }
}

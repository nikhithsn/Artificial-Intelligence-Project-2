public class Driver 
{
    private static int[] bot1MovesTaken = new int[150];
    private static int[] bot2MovesTaken = new int[150];
    private static int[] bot3MovesTaken = new int[150];

    public static void main(String[] args) 
    {
        // Reminder: make a for loop to run multiple times, 

        for (int i = 0; i < 3; i += 3) //TEMP ALTER LOOP
        {
            System.out.println("loop 1: " + i);
            double iterationAlpha = 0;

            for (int j = 0; j < 50; j ++)
            {
                System.out.println("loop 2: " + j);
                iterationAlpha += 0.01;
                int bot1MoveCounter = 0;
                int bot2MoveCounter = 0;
                int bot3MoveCounter = 0;

                Grid grid1 = new Grid();
                Grid grid2 = new Grid();
                Grid grid3 = new Grid();

                Bot bot1 = new bot1(grid1, iterationAlpha);
                Bot bot2 = new bot2(grid2, iterationAlpha);
                Bot bot3 = new bot3(grid3, iterationAlpha);

                Mouse stationaryMouse1 = new Mouse(grid1, true);
                Mouse stationaryMouse2 = new Mouse(grid2, true);
                Mouse stationaryMouse3 = new Mouse(grid3, true);
                Mouse movingMouse1 = new Mouse(grid1, false);
                Mouse movingMouse2 = new Mouse(grid2, false);
                Mouse movingMouse3 = new Mouse(grid3, false);

                grid1.placeBot(bot1);
                grid2.placeBot(bot2);
                grid3.placeBot(bot3);
                
                if (i == 0)
                {
                    grid1.placeMouse(stationaryMouse1);
                    grid2.placeMouse(stationaryMouse2);
                    grid3.placeMouse(stationaryMouse3);
                }
                else if (i == 1)
                {
                    grid1.placeMouse(movingMouse1);
                    grid2.placeMouse(movingMouse2);
                    grid3.placeMouse(movingMouse3);
                }
                else
                {
                    grid1.placeMouse(movingMouse1);
                    grid2.placeMouse(movingMouse2);
                    grid3.placeMouse(movingMouse3);
                    grid1.placeMouse(movingMouse1);
                    grid2.placeMouse(movingMouse2);
                    grid3.placeMouse(movingMouse3);
                }

                // Reminder: Simulate and compare the bots
                // Implement the required analysis
                // Finish move counter
                while (!bot1.hasFoundMouse() || !bot2.hasFoundMouse() || !bot3.hasFoundMouse())
                {
                    //System.out.println("Checking bots:");
                    System.out.println("Bot1 found mouse: " + bot1.hasFoundMouse());
                    System.out.println("Bot2 found mouse: " + bot2.hasFoundMouse());
                    System.out.println("Bot3 found mouse: " + bot3.hasFoundMouse());
                    System.out.println("loop 3");
                    if (!bot1.hasFoundMouse())
                    {
                        //System.out.println(j);
                        System.out.println("Bot 1 is at " + bot1.x + ", " + bot1.y);
                        System.out.println("Mouse 1 is at " + grid1.getMouse().getX() + ", " + grid1.getMouse().getY());
                        bot1.executeStrategy();
                        grid1.getMouse().move();

                        bot1MoveCounter ++;
                    }
                    
                    if (!bot2.hasFoundMouse())
                    {
                        //System.out.println(j);
                        System.out.println("Bot 2 is at " + bot2.x + ", " + bot2.y);
                        System.out.println("Mouse 2 is at " + grid2.getMouse().getX() + ", " + grid2.getMouse().getY());
                        bot2.executeStrategy();
                        grid2.getMouse().move();

                        bot2MoveCounter ++;
                    }

                    if (!bot3.hasFoundMouse())
                    {
                        //System.out.println(j);
                        System.out.println("Bot 3 is at " + bot3.x + ", " + bot3.y);
                        System.out.println("Mouse 3 is at " + grid3.getMouse().getX() + ", " + grid3.getMouse().getY());
                        bot3.executeStrategy();
                        grid3.getMouse().move();

                        bot2MoveCounter ++;
                    }
                }

                bot1MovesTaken[(i * 50) + j] = bot1MoveCounter;
                bot2MovesTaken[(i * 50) + j] = bot2MoveCounter;
                bot3MovesTaken[(i * 50) + j] = bot3MoveCounter;

            }
        }

        printResults();
    }

    public static void printResults()
    {
        for (int i = 0; i < 3; i ++)
        {
            if (i == 0)
            {
                System.out.println("Bot 1 | Moves Taken Table:");
            }
            else if (i == 1)
            {
                System.out.println("Bot 2 | Moves Taken Table:");
            }
            else
            {
                System.out.println("Bot 3 | Moves Taken Table:");    
            }

            for (int j = 0; j < 150; j ++)
            {
                if (j == 0)
                {
                    System.out.println("Trial 1 ~ Stationary Mouse");
                    System.out.println();
                }
                else if (j == 50)
                {
                    System.out.println("Trial 2 ~ Moving Mouse");
                    System.out.println();
                }
                else if (j == 100)
                {
                    System.out.println("Trial 1 ~ 2 Moving Mice");
                    System.out.println();
                }

                if (i == 0)
                {
                    System.out.print(bot1MovesTaken[j]);
                }
                else if (i == 1)
                {
                    System.out.print(bot2MovesTaken[j]);
                }
                else
                {
                    System.out.print(bot3MovesTaken[j]);
                }
            }
        }
    }
}

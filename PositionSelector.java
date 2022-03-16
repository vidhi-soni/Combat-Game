/* 
PositionSelecter class sets the probability distribution for attacks 
*/

import java.util.Random;

public class PositionSelector
{
    double point1 = 0.33333333;
    double point2 = 0.66666666;

    static final int LOW = 0;
    static final int MEDIUM = 1;
    static final int HIGH = 2;

    Random random = new Random();

    PositionSelector() 
    {
    }

    void setPositionFractions(double first, double second, double third)
    {
        if (Math.abs(first + second + third - 1) > 0.001)
        {
            System.out.println("Percentages don't sum to 100. Ignoring...");
            return;
        }

        // Cumulative values:
        point1 = first;
        point2 = (first + second);
    }

    int choosePosition()
    {
        double randomValue = random.nextDouble();
        if (randomValue >= 0 && randomValue < point1)
            return LOW;
        else if (randomValue >= point1 && randomValue < point2)
            return MEDIUM;
        else
            return HIGH;
    }

    String positionDescription(int type)
    {
        if (type == 0)
            return "Low";
        else if (type == 1)
            return "Medium";
        else
            return "High";
    }
}

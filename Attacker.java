/* 
Attacker class mainly generates the attacks 
It also communicates attack type to the other classes  
*/

import java.util.Scanner;

public class Attacker
{
    PositionSelector positionSelector = new PositionSelector();
    int attackTypes[] = new int[3];
    int lastAttack;
    int totalTurns;

    Attacker(int totalTurns)
    {
        this.totalTurns = totalTurns;

        Scanner in = new Scanner(System.in);
        System.out.println("Enter percentages for the number of attacks. Must sum to 100");

        System.out.print("Percentage of attacks that will be aimed low: ");
        Integer lowAttackPercentage = new java.lang.Integer(in.nextLine());

        System.out.print("Percentage of attacks that will be aimed medium: ");
        Integer mediumAttackPercentage = new java.lang.Integer(in.nextLine());

        System.out.print("Percentage of attacks that will be aimed high: ");
        Integer highAttackPercentage = new java.lang.Integer(in.nextLine());

        positionSelector.setPositionFractions(lowAttackPercentage / 100.0, 
            mediumAttackPercentage / 100.0, highAttackPercentage / 100.0);
        in.close();
    }

    int generateAttack()
    {
        int attackType =  positionSelector.choosePosition();
        attackTypes[attackType]++;
        lastAttack = attackType;
        return attackType;
    }

    int lastAttackType()
    {
        return lastAttack;
    }

    double lowProportion()
    {
        return attackTypes[0] * 100.0 / totalTurns;
    }

    double mediumProportion()
    {
        return attackTypes[1] * 100.0 / totalTurns;
    }

    double highProportion()
    {
        return attackTypes[2] * 100.0 / totalTurns;
    }
}

/* 
Defender class mainly generates defense types 
It also determines result of attacks and generates reports 
*/

import java.util.Scanner;
import java.lang.Math;
import java.text.DecimalFormat;

public class Defender
{
    final int BLOCK = 0;
    final int HIT = 1;
    
    PositionSelector positionSelector = new PositionSelector();
    int currentCounter = 0;
    int totalBlocks = 0;
    int totalHits = 0;
    int defenseTypes[] = new int[3];
    int attackTypesToAnalyze[] = new int[3];

    int lastRoundResult;
    int lastDefenseType;
    Attacker attacker;
    int numberOfTurnsToAnalyzeAttacker;
    int totalRounds;
    
    Defender(Attacker attacker, int totalRounds)
    {
        // Assignment requirement: Attacker will maintain the statistics
        // So defender can enquire Attacker for them.

        this.totalRounds = totalRounds;
        this.attacker = attacker;
        this.numberOfTurnsToAnalyzeAttacker = 3;
    }

    void generateDefense(int attackType)
    {
        int defenseType = positionSelector.choosePosition();
        lastDefenseType = defenseType;
        if (attackType == defenseType)
        {
            totalBlocks++;
            lastRoundResult = BLOCK;
        }
        else
        {
            totalHits++;
            lastRoundResult = HIT;
        }

        defenseTypes[defenseType]++;
        attackTypesToAnalyze[attackType]++;
        ++currentCounter;
        analyzeAttacksAndAdjust();
    }

    void analyzeAttacksAndAdjust()
    {
        if ( (currentCounter % numberOfTurnsToAnalyzeAttacker) == 0)
        {
            double lowFraction = attackTypesToAnalyze[0] * 1.0 / numberOfTurnsToAnalyzeAttacker;
            double mediumFraction = attackTypesToAnalyze[1] * 1.0 / numberOfTurnsToAnalyzeAttacker;
            double highFraction = attackTypesToAnalyze[2] * 1.0 / numberOfTurnsToAnalyzeAttacker;

            // Make sure that the fractions sum to 1:
            highFraction = 1 - lowFraction - mediumFraction;
            positionSelector.setPositionFractions(lowFraction, mediumFraction, highFraction);

            // Reset
            attackTypesToAnalyze[0] = attackTypesToAnalyze[1] = attackTypesToAnalyze[2] = 0;
        }
    }

    void displayLastRoundResults()
    {
        System.out.print("Round " + currentCounter + "....     ");
        System.out.print("Attacker: " + positionSelector.positionDescription(attacker.lastAttackType()));
        System.out.print("  Defender: " + positionSelector.positionDescription(lastDefenseType));
        System.out.println("   " + lastRoundResultDescription());
    }

    void displayProportions(double low, double medium, double high)
    {
        System.out.print("   Low: " + low + "%");
        System.out.print("   Medium: " + medium + "%");
        System.out.println("   High: " + high + "%");
    }

    void displayEndSimulationReport()
    {
        System.out.println("Summary of Kombat");
        System.out.print("Total Hits: " + totalHits + "   Total Blocks: " + totalBlocks + "  ");
        System.out.println("Attacker Proportions:  ");
        displayProportions(attacker.lowProportion(), attacker.mediumProportion(), 
            attacker.highProportion());

        System.out.print("Defender Proportions:  ");

        displayProportions(defenseTypes[0] * 100.0 / totalRounds, 
            defenseTypes[1] * 100.0 / totalRounds, 
            defenseTypes[2] * 100.0 / totalRounds);
    }

    String lastRoundResultDescription()
    {
        if (lastRoundResult == HIT)
            return "Hit";
        else
            return "Block";
    }
}

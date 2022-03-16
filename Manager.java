/* 
Manager class runs the main loop (round of combat) 
It creates instances of the Attacker and Defender class

*/


import java.util.Scanner;

public class Manager
{
    int numberOfAttackRounds = 10;
    
    Manager()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Number of attack rounds: ");
        Integer count = new java.lang.Integer(in.nextLine());
        if (count >= 1 && count <= 100)
            numberOfAttackRounds = count;
        else
            System.out.println("Incorrect number of attack rounds. Ignoring ...");
    }

    void simulate()
    {
        Attacker attacker = new Attacker(numberOfAttackRounds);
        Defender defender = new Defender(attacker, numberOfAttackRounds);

        System.out.println("Kombat begins ...");
        for (int turn = 0; turn < numberOfAttackRounds; turn++)
        {
            int attackType = attacker.generateAttack();
            defender.generateDefense(attackType);
            defender.displayLastRoundResults();
        }
        defender.displayEndSimulationReport();
    }

    public static void main(String args[])
    {
        Manager manager = new Manager();
        manager.simulate();
    }
}

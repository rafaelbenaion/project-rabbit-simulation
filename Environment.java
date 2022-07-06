import java.util.ArrayList;
import java.util.List;

/* -------------------------------------------------------------------- */
/*                  TP04 - Simulation, L2 Informatique                  */
/*                                                                      */
/*                 Rafael BAPTISTA BENAION et Liza TOUMI                */
/*                            ( Groupe gI )                             */
/* -------------------------------------------------------------------- */

/* -------------------------------------------------------------------- */
/*                         Environment class                            */
/* -------------------------------------------------------------------- */
/*        This class represents the Environment for the simulation      */
/* -------------------------------------------------------------------- */

public class Environment
{
    public static List<List<Integer>> replications    = new ArrayList<>();

    /* ---------------------------------------------------------------- */
    /*  Function: simulationYears()                                     */
    /* ---------------------------------------------------------------- */
    /*  Input:  Integer : months    (Number of months to simulate)      */
    /*          Integer : couples   (Number of initial couples)         */
    /* ---------------------------------------------------------------- */
    /*  Output: void                                                    */
    /* ---------------------------------------------------------------- */

    public static void newSimulation(int months, int couples)
    {
        Colony colony = new Colony();

        FemaleRabbit   female;
        MaleRabbit     male;
        
        for (int j = 0; j<couples; j++)
        {
            female = new FemaleRabbit();
            female.initialPopulationConfig();
            colony.addFemale(female);

            male = new MaleRabbit();
            colony.addMale(male);
        }

        for (int j = 0; j<months; j++)
        {
            colony.monthCicle();
        } 
        replications.add(colony.getStats());
    } 

    /* ---------------------------------------------------------------- */
    /*  Function: confidenceInterval()                                  */
    /* ---------------------------------------------------------------- */
    /*  Input:  Integer : List<List<Integer>> (All replications data)   */
    /*          Integer : void                                          */
    /* ---------------------------------------------------------------- */
    /*  Output: void                                                    */
    /* ---------------------------------------------------------------- */

    public static void confidenceInterval(List<List<Integer>> replications)
    {
        /* ------------------------------------------------------------ */
        /*  Calculates the mean of all simulation results :             */
        /* ------------------------------------------------------------ */

        double[] mean = new double[4];
        double   n    = replications.size();

        for(int i=0; i<n; i++)
        {
            mean[0] += replications.get(i).get(0);
            mean[1] += replications.get(i).get(1);
            mean[2] += replications.get(i).get(2);
            mean[3] += replications.get(i).get(3);
        } 
        for(int i=0; i<4; i++)
        {
            mean[i] = mean[i] / n;
        } 

        /* ------------------------------------------------------------ */
        /*  Calculates the estimate without bias of the variance :      */
        /* ------------------------------------------------------------ */

        double[] sn = new double[4];

        for(int i=0; i<n; i++)
        {
            sn[0] += Math.pow( (replications.get(i).get(0) - mean[0]), 2); 
            sn[1] += Math.pow( (replications.get(i).get(1) - mean[1]), 2); 
            sn[2] += Math.pow( (replications.get(i).get(2) - mean[2]), 2); 
            sn[3] += Math.pow( (replications.get(i).get(3) - mean[3]), 2); 
        } 

        for(int i=0; i<4; i++)
        {
            sn[i] = sn[i] / (n - 1);
        } 

        /* ------------------------------------------------------------ */
        /*  Calculates the confidence interval :                        */
        /* ------------------------------------------------------------ */

        double   studentLaw  = 1.960;           // For 120 < replications.
        double[] confid      = new double[4];
        
        for(int i=0; i<4; i++)
        {
            confid[i] = studentLaw * ( Math.sqrt( sn[i] / n ) );
        } 
        
        System.out.println("Alpha male pop: "       + confid[0]);
        System.out.println("Alpha female pop: "     + confid[1]);
        System.out.println("Alpha infertile pop:  " + confid[2]);
        System.out.println("Alpha babies pop:  "    + confid[3]);
    } 

    public static void main(String[] args) {

        for(int i=0; i<100000; i++)         // The number of replications.
        {
            newSimulation( 360, 10 );
        } 
        confidenceInterval( replications );
    }    
}
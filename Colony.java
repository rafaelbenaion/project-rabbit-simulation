/* -------------------------------------------------------------------- */
/*                  TP04 - Simulation, L2 Informatique                  */
/*                                                                      */
/*                 Rafael BAPTISTA BENAION et Liza TOUMI                */
/*                            ( Groupe gI )                             */
/* -------------------------------------------------------------------- */

/* -------------------------------------------------------------------- */
/*                            Colony class                              */
/* -------------------------------------------------------------------- */
/*        This class is responsible for the managing the colony         */
/* -------------------------------------------------------------------- */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Colony {

    private final List<FemaleRabbit>   females       = new LinkedList<>();
    private final List<FemaleRabbit>   infertiles    = new LinkedList<>();
    private final List<MaleRabbit>     males         = new LinkedList<>();
    private final List<Rabbit>         babies        = new LinkedList<>();
    
    private       int                  totalDeath    = 0;
    private       int                  newBorn       = 0;
    private       int                  time          = 0;

    /* ---------------------------------------------------------------- */
    /*  Function: birthSeason()                              */
    /* ---------------------------------------------------------------- */
    /*  Calls giveBirth() to fertile females and add new borns to list. */
    /* ---------------------------------------------------------------- */
    /*  Input:  void                                                    */
    /*  Output: void                                                    */
    /* ---------------------------------------------------------------- */

    public void birthSeason()
    {  
        if(males.size() > 0)
        {
            int counter = 0;
        
            for(FemaleRabbit female : females)
            {
                if( !female.isAlive() || !female.isFertile() )     { return; } 
            
                /* Birth Season code: ------------------------------------- */
            
                Rabbit newBorn = female.giveBirth();

                if( newBorn != null )
                {
                    babies.add(newBorn);
                    counter++;
                } 
            } 
            newBorn = counter;
        } 
        
    }  

    /* ---------------------------------------------------------------- */
    /*  Function: agingDeathSeason()                                    */
    /* ---------------------------------------------------------------- */
    /*  Calls ages() to all rabbits adding 1 month of life.             */
    /*  Calls deathRoulette() to all rabbits.                           */
    /* ---------------------------------------------------------------- */
    /*  Input:  void                                                    */
    /*  Output: void                                                    */
    /* ---------------------------------------------------------------- */

    public void agingDeathSeason()
    {   
        for(FemaleRabbit female : females)
        {
            female.ages();
            female.deathRoulette();
        } 

        for(FemaleRabbit infertile : infertiles)
        {
            infertile.ages();
            infertile.deathRoulette();
        } 

        for(MaleRabbit male : males)
        {
            male.ages();
            male.deathRoulette();
        } 

        for(Rabbit baby : babies)
        {
            baby.ages();
            baby.deathRoulette();
        } 
    }  

    /* ---------------------------------------------------------------- */
    /*  Function: fertilitySeason()                                     */
    /* ---------------------------------------------------------------- */
    /*  Calls fertilityRoulette() to all fertile females (yearly).      */
    /*  Calls infertilityRoulette() to all fertile females.             */
    /* ---------------------------------------------------------------- */
    /*  Input:  void                                                    */
    /*  Output: void                                                    */
    /* ---------------------------------------------------------------- */

    public void fertilitySeason()
    {   
        for(FemaleRabbit female : females)
        {
            female.fertilityRoulette();
            female.infertilityRoulette();
            female.maternalDeathRoulette();
        } 
    } 

    /* ---------------------------------------------------------------- */
    /*  Function: colonyManager()                                       */
    /* ---------------------------------------------------------------- */
    /*  Responsable for managing death, infertility & maturity changes. */
    /* ---------------------------------------------------------------- */
    /*  Input:  void                                                    */
    /*  Output: void                                                    */
    /* ---------------------------------------------------------------- */

    public void colonyManager()
    {

        int counterNewMaleAdults     = 0;
        int counterNewFemaleAdults   = 0;
        int counterNewInfertiles     = 0;
        int counterDeaths            = 0;     

        /* ------------------------------------------------------------ */
        ListIterator<Rabbit> babyIterator         = babies.listIterator();
        while(babyIterator.hasNext()) 
        {   
            Rabbit baby = babyIterator.next();

            // Check if there are dead babies
            if( !baby.isAlive() )
            {
                babyIterator.remove();
                counterDeaths++;

            }else{
                // Check if there are new adults
                if( baby.isMature() )
                {
                    if(baby.getClass() == FemaleRabbit.class)
                    {
                        females.add((FemaleRabbit)baby);
                        counterNewFemaleAdults++;
                    } 
                    if(baby.getClass() == MaleRabbit.class)
                    {
                        males.add((MaleRabbit)baby);
                        counterNewMaleAdults++;
                    } 
                    babyIterator.remove();
                } 
            } 
        } 

        /* ------------------------------------------------------------ */
        ListIterator<FemaleRabbit> femaleIterator =females.listIterator();
        while(femaleIterator.hasNext()){

            FemaleRabbit female = femaleIterator.next();

            // Check if there are dead females
            if( !female.isAlive() )
            {
                femaleIterator.remove();
                counterDeaths++;

            }else{
                // Check if there are infertile females
                if( !female.isFertile() )
                {
                    infertiles.add(female);
                    femaleIterator.remove();
                    counterNewInfertiles++;
                } 
            } 
        } 

        /* ------------------------------------------------------------ */
        ListIterator<MaleRabbit> maleIterator      = males.listIterator();
        while(maleIterator.hasNext())
        {
            MaleRabbit male = maleIterator.next();

            // Check if there are dead males
            if( !male.isAlive() )
            {
                maleIterator.remove();
                counterDeaths++;
            } 
        } 

        /* ------------------------------------------------------------ */
        ListIterator<FemaleRabbit> infertileIterator =          infertiles
                                                          .listIterator();
        while(infertileIterator.hasNext())
        {
            FemaleRabbit infertile = infertileIterator.next();

            // Check if there are dead males
            if( !infertile.isAlive() )
            {
                infertileIterator.remove();
                counterDeaths++;
            } 
        }

        totalDeath = totalDeath + counterDeaths;

        System.out.println("\n-----------------------------------------");
        System.out.println("New deaths: " +                counterDeaths);
        System.out.println("Newborns: " +                        newBorn);
        System.out.println("New infertiles: " +     counterNewInfertiles);
        System.out.println("New female adults: " +counterNewFemaleAdults);
        System.out.println("New male adults: " +    counterNewMaleAdults);
        System.out.println("-------------------------------------------");
        System.out.println("Total males: " +                males.size());
        System.out.println("Total females: " +            females.size());
        System.out.println("Total infertiles: " +      infertiles.size());
        System.out.println("Total babies: " +              babies.size());
        System.out.println("Total deaths:" +                  totalDeath);
        System.out.println("-------------------------------------------");        

        newBorn = 0;
    }

    /* ---------------------------------------------------------------- */
    /*  Function: monthCicle()                                          */
    /* ---------------------------------------------------------------- */
    /*  Simulates a month in the virtual time.                          */
    /* ---------------------------------------------------------------- */
    /*  Input:  void                                                    */
    /*  Output: void                                                    */
    /* ---------------------------------------------------------------- */

    public void monthCicle()
    {
        time++;

        birthSeason();
        agingDeathSeason();

        if(time % 12 == 0)
        {
            fertilitySeason();
        } 

        colonyManager();
        System.out.println("Month:" + time);
    }

    /* ---------------------------------------------------------------- */
    /*  Function: getStats()                                            */
    /* ---------------------------------------------------------------- */
    /*  Return total statistics from the colony.                        */
    /* ---------------------------------------------------------------- */
    /*  Input:  void                                                    */
    /*  Output: Integer array : stats                                   */
    /* ---------------------------------------------------------------- */

    public List<Integer> getStats()
    {
        List<Integer> stats = new ArrayList<>();

        stats.add(males.size());
        stats.add(females.size());
        stats.add(infertiles.size());
        stats.add(babies.size());

        return stats;
    } 

    //Only for testing code: ---------------------------------------------
    public void addFemale(FemaleRabbit female)
    {
        females.add(female);
    }

    public void addMale(MaleRabbit male)
    {
        males.add(male);
    }

    
}

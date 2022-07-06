/* -------------------------------------------------------------------- */
/*                  TP04 - Simulation, L2 Informatique                  */
/*                                                                      */
/*                 Rafael BAPTISTA BENAION et Liza TOUMI                */
/*                            ( Groupe gI )                             */
/* -------------------------------------------------------------------- */

/* -------------------------------------------------------------------- */
/*                        Rabbit's female class                         */
/* -------------------------------------------------------------------- */
/*        This class is responsible for the reproduction function       */
/* -------------------------------------------------------------------- */

public class FemaleRabbit extends Rabbit {
    
    private Boolean isFertile;             /* Fertility status for life */
    private int     annualFertilityRate;   /* Number of babies per year */
    private double  maternalDeathRate;     /* Chances of dying on birth */
    private double  infertilityRate;       /* Infertility rate per year */    

    public FemaleRabbit()
    {
        super();
        this.fertilityRoulette();
        this.isFertile = true;
        
    }

    /* ---------------------------------------------------------------- */
    /*  Function: giveBirth()                                           */
    /*  Creates new Rabbit, uses MTRand for choosing baby's sex.        */
    /* ---------------------------------------------------------------- */
    /*  Input:  void                                                    */
    /*  Output: Rabbit                                                  */
    /* ---------------------------------------------------------------- */
    
    public Rabbit giveBirth()
    {
        double monthPercentage           = ( (annualFertilityRate/12.0) );

        MersenneTwisterFast master       = MTRandom.getMaster();
        double              randDouble   = master.nextDouble(true, false);

        if( randDouble < monthPercentage )
        {
            int  sex = master.nextInt(2);

            if(sex == 0)
            {
                return (Rabbit) new FemaleRabbit();
            }else{
                return (Rabbit) new MaleRabbit();
            } 
        } 

        return null;       
    } 

    /* ---------------------------------------------------------------- */
    /*  Function: maternalDeathRoulette()                               */
    /*  Uses maternalDeathRate to know if she dies giving giving birth. */
    /* ---------------------------------------------------------------- */
    /*  Input:  void                                                    */
    /*  Output: void                                                    */
    /* ---------------------------------------------------------------- */

    public void maternalDeathRoulette()
    {
        maternalDeathRate = 0.15;

        /* If the random numb < maternal DeathRate then the female dies */

        MersenneTwisterFast master        = MTRandom.getMaster();
        double              randDouble    = master.nextDouble(true,false);

        if( randDouble < maternalDeathRate )
        {
            this.dies();
        } 
    } 

    /* ---------------------------------------------------------------- */
    /*  Function: infertilityRoulette()                                 */
    /*  Test 10% of chances that a female can get infertily every year. */
    /* ---------------------------------------------------------------- */
    /*  Input:  void                                                    */
    /*  Output: void                                                    */
    /* ---------------------------------------------------------------- */

    public void infertilityRoulette()
    {

        /* 10% (annual) rate */
        infertilityRate = 0.10;

        /* If random numb < infertility Rate then she becomes infertile */

        MersenneTwisterFast master       = MTRandom.getMaster();
        double              randDouble   = master.nextDouble(true, false);

        if( randDouble < infertilityRate )              
        {
            isFertile = false;
        } 
    } 

    /* ---------------------------------------------------------------- */
    /*  Function: fertilityRoulette()                                   */
    /* ---------------------------------------------------------------- */
    /*  Uses the cumulative probability and a random number to decide   */
    /*  the total number of babies the female can have in the year.     */
    /* ---------------------------------------------------------------- */
    /*  Input:  void                                                    */
    /*  Output: void                                                    */
    /* ---------------------------------------------------------------- */

    public void fertilityRoulette()
    {
        /* ------------------------------------------------------------ */
        /*    0 = 1%        4 = 18%        8  = 92%       12 = 100%     */
        /*    1 = 2%        5 = 38%        9  = 97%                     */
        /*    2 = 3%        6 = 62%        10 = 98%                     */
        /*    3 = 8%        7 = 82%        11 = 99%                     */
        /* ------------------------------------------------------------ */ 
        
        MersenneTwisterFast master       = MTRandom.getMaster();
        double              randDouble   = master.nextDouble(true, false);

       // System.out.println("RandFertility = " + randDouble);

        if(randDouble < 0.1)
        {
            annualFertilityRate = 0;
        }
        else if(randDouble < 0.2)
        {
            annualFertilityRate = 1;
        }
        else if(randDouble < 0.3)
        {
            annualFertilityRate = 2;
        } 
        else if(randDouble < 0.8)
        {
            annualFertilityRate = 3;
        } 
        else if(randDouble < 0.18)
        {
            annualFertilityRate = 4;
        } 
        else if(randDouble < 0.38)
        {
            annualFertilityRate = 5;
        } 
        else if(randDouble < 0.62)
        {
            annualFertilityRate = 6;
        } 
        else if(randDouble < 0.82)
        {
            annualFertilityRate = 7;
        } 
        else if(randDouble < 0.92)
        {
            annualFertilityRate = 8;
        } 
        else if(randDouble < 0.97)
        {
            annualFertilityRate = 9;
        } 
        else if(randDouble < 0.98)
        {
            annualFertilityRate = 10;
        } 
        else if(randDouble < 0.99)
        {
            annualFertilityRate = 11;
        } 
        else{
            annualFertilityRate = 12;
        }      
    } 

    public boolean isFertile()
    {
        return isFertile;
    } 

    //Only for setting the first rabbits:
    public void initialPopulationConfig()
    {
        isFertile = true;
        this.initialPopulationDemo();
    } 

}

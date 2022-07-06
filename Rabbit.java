/* -------------------------------------------------------------------- */
/*                  TP04 - Simulation, L2 Informatique                  */
/*                                                                      */
/*                 Rafael BAPTISTA BENAION et Liza TOUMI                */
/*                            ( Groupe gI )                             */
/* -------------------------------------------------------------------- */

/* -------------------------------------------------------------------- */
/*                        Rabbit's mother class                         */
/* -------------------------------------------------------------------- */
/*     Contains all the generic methods used by the different genders   */
/* -------------------------------------------------------------------- */

abstract class Rabbit
{
    private final int        MAX_LIFE          = 144;
    private final int        ELDERLY           = 84;
    private       int        age;
    private       double     surviveRate;
    private       int        maturityRate;
    private       boolean    maturityState;
    private       boolean    alive;

    
    public Rabbit()
    {

        /* MaturityRate is between 5 and 8 months : 5 + random nb [0,3] */

        MersenneTwisterFast master              = MTRandom   .getMaster();
        int                 maturity            = (5 + master.nextInt(4));

        alive           = true;
        age             = 0;
        surviveRate     = 0.50;
        maturityState   = false;
        maturityRate    = maturity;

    }

    /* ---------------------------------------------------------------- */
    /*  Function: dies()                                                */
    /*  Sinalize that the rabbit is dead to the colonyManager.          */
    /* ---------------------------------------------------------------- */
    /*  Input:  void                                                    */
    /*  Output: void                                                    */
    /* ---------------------------------------------------------------- */

    public void dies()
    {
        alive = false;
    } 

    /* ---------------------------------------------------------------- */
    /*  Function: ages()                                                */
    /*  Increase the rabbit age and verifies other conditions.          */
    /* ---------------------------------------------------------------- */
    /*  Input:  void                                                    */
    /*  Output: void                                                    */
    /* ---------------------------------------------------------------- */

    public void ages()
    {
        age++;                               /* Ages rabbit by one month*/

        if(age > MAX_LIFE)                   /* Check if is end of life */
        {
            this.dies();
        } 

        if(age > ELDERLY && age % 12 == 0)   /* If old decrease survive */
        {
            surviveRate  -= 0.15;
        }

        if(age == maturityRate)              /* Check if it is an adult */
        {
            maturityState = true;
            surviveRate   = 0.75  ;
        }
    } 

    /* ---------------------------------------------------------------- */
    /*  Function: deathRoulette()                                       */
    /*  Uses surviveRate and MTRand to decides if the rabbit dies.      */
    /* ---------------------------------------------------------------- */
    /*  Input:  void                                                    */
    /*  Output: void                                                    */
    /* ---------------------------------------------------------------- */

    public void deathRoulette()
    {
        /* If rand 0,100 > the monthly survive rate, so the rabbit dies */

        MersenneTwisterFast master                 = MTRandom.getMaster();
        int                 deathRoulette          = master .nextInt(100);

        /* Calculates a % survive monthly = surviveRate (annual) ^ 1\12 */

        if( deathRoulette > ( Math.pow( surviveRate, (1.0/12.0) ) * 100 ))  
        {
            this.dies();
        } 
    } 
    
    public boolean isAlive()
    {
        return alive;
    } 

    public boolean isMature()
    {
        return maturityState;
    } 

    //Only for setting the first rabbits:
    public void   initialPopulationDemo()
    {
        surviveRate     = 0.75;
        maturityState   = true;
        age             = 10;
    } 
}

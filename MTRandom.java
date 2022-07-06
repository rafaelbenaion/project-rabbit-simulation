/* -------------------------------------------------------------------- */
/*                  TP04 - Simulation, L2 Informatique                  */
/*                                                                      */
/*                 Rafael BAPTISTA BENAION et Liza TOUMI                */
/*                            ( Groupe gI )                             */
/* -------------------------------------------------------------------- */

/* -------------------------------------------------------------------- */
/*                   Mersenne Twister Manager class                     */
/* -------------------------------------------------------------------- */
/*         Contains the seed for intializing MersenneTwisterFast()      */
/* -------------------------------------------------------------------- */

public class MTRandom 
{
    private static MersenneTwisterFast master = new MersenneTwisterFast(new int[]{0x123, 0x234, 0x345, 0x456});
            
    public static MersenneTwisterFast getMaster() {
        return master;
    }
}

/******************************************************************************
 * Program:     Jampot                                                        *
 * File:        sinewave.hpp                                                  *
 * Author:      Will Weaver                                                   *
 * Description: The definition of a class used to represent pure sine waves   *
 *              assuming a cosine base function.                              *
 ******************************************************************************/

 #ifndef SINEWAVE_HPP
 #define SINEWAVE_HPP

/******************************************************************************
 * INCLUDES                                                                   *
 ******************************************************************************/

// Local project files
 #include "wave.hpp"

/******************************************************************************
 * CLASSES                                                                    *
 ******************************************************************************/

/******************************************************************************
 * Class:       SineWave                                                      *
 * Base Class:  WaveTerm                                                      *
 * Description: Represents a pure sine wave, using the cosine function as the *
 *              core function. The defining parameters are amplitude,         *
 *              period, and phase.                                            *
 ******************************************************************************/
class SineWave : public WaveTerm {
	
	public:
		/******************************************************************************
		 * Function:     SineWave::SineWave                                           *
		 * Arguments:    amplitude - The distance from 0 to the peak of the wave.     *
		 *               period    - The number of times the wave repeats each        *
		 *                           second.                                          *
		 *               phase     - The time offset from a pure cosine in seconds.   *
		 * Returns:      void                                                         *
		 * Side Effects: Initializes a new SineWave object.                           *
		 ******************************************************************************/
		SineWave(double amplitude, int period, double phase);
		
		// Overloaded operators make it easier to represent common mathematical
		// operations how they are normally symbolically represented in
		// mathematics.
		
		/******************************************************************************
		 * Function:     SineWave::operator=                                          *
		 * Arguments:    other - The SineWave function on the right side of the       *
		 *                       assignment operator.                                 *
		 * Returns:      A copy of other.                                             *
		 * Side Effects: Makes the lefthand side of the assignment operator equal to  *
		 *               the function represented by the righthand side.              *
		 ******************************************************************************/
		SineWave     operator= (const SineWave&     other);
		
		/******************************************************************************
		 * Function:     SineWave::operator+=                                         *
		 * Arguments:    other - The SineWave function on the right side of the       *
		 *                       compound assignment operator to be added to the left *
		 *                       SineWave function.                                   *
		 * Returns:      A copy of the result of the addition of the lefthand object  *
		 *               to the righthand object.                                     *
		 * Side Effects: If the periods of the lefthand function and the              *
		 *               righthand function are identical, it computes the single     *
		 *               sine wave parameters defining the sum of the two operands    *
		 *               and assigns these parameters to the left side function. If   *
		 *               the periods do not match, it throws an exception.            *
		 ******************************************************************************/
		SineWave     operator+=(const SineWave&     other);
		
		/******************************************************************************
		 * Function:     SineWave::operator-=                                         *
		 * Arguments:    other - The SineWave function on the right side of the       *
		 *                       compound assignment operator to be subtracted from   *
		 *                       the left SineWave function.                          *
		 * Returns:      A copy of the result of the subtraction of the righthand     *
		 *               object from the lefthand object.                             *
		 * Side Effects: If the periods of the lefthand function and the              *
		 *               righthand function are identical, it computes the single     *
		 *               sine wave parameters defining the difference of the two      *
		 *               operands and assigns these parameters to the left side       *
		 *               function. If the periods do not match, it throws an          *
		 *               exception.                                                   *
		 ******************************************************************************/
		SineWave     operator-=(const SineWave&     other);
		
		/******************************************************************************
		 * Function:     SineWave::operator+                                          *
		 * Arguments:    other - The function on the right side of the addition       *
		 *                       operator to be added to the function on the left     *
		 *                       side.                                                *
		 * Returns:      A new CompoundWave object equal to the sum of the two waves  *
		 *               added together.                                              *
		 * Side Effects: [None]                                                       *
		 ******************************************************************************/
		CompoundWave operator+ (const Wave&         other);
		
		/******************************************************************************
		 * Function:     SineWave::operator-                                          *
		 * Arguments:    other - The function on the right side of the subtraction    *
		 *                       operator to be subtracted from the function on the   *
		 *                       left side.                                           *
		 * Returns:      A new CompoundWave object equal to the difference of the two *
		 *               waves.                                                       *
		 * Side Effects: [None]                                                       *
		 ******************************************************************************/
		CompoundWave operator- (const Wave&         other);
		
		/******************************************************************************
		 * Function:     SineWave::operator-                                          *
		 * Arguments:    [None]                                                       *
		 * Returns:      The negation of the righthand SineWave function.             *
		 * Side Effects: [None]                                                       *
		 ******************************************************************************/
		SineWave     operator- (                         );

		/******************************************************************************
		 * Function:     SineWave::operator*                                          *
		 * Arguments:    other - The function on the right side of the multiplication *
		 *                        operator to be multiplied by the left side.         *
		 * Returns:      The product of the functions on either side of the operator. *
		 * Side Effects: [None]                                                       *
		 ******************************************************************************/
		WaveProduct  operator* (const WaveTerm&     other);
		CompoundWave operator* (const CompoundWave& other);
	
	private:
		double phase;		// Offset from pure cosine in seconds (between -1/2 period and 1/2 period)
};

 #endif // SINEWAVE_HPP

/******************************************************************************
 * END OF FILE                                                                *
 ******************************************************************************/

/******************************************************************************
 * Program:     Jampot                                                        *
 * File:        wave.hpp                                                      *
 * Author:      Will Weaver                                                   *
 * Description: A collection of classes used to represent and manipulate      *
 *              sinusoidal waveforms.                                         *
 ******************************************************************************/

#ifndef WAVE_HPP
#define WAVE_HPP
 
/******************************************************************************
 * INCLUDES                                                                   *
 ******************************************************************************/

// Standard libraries
#include <vector>
#include <exception>

/******************************************************************************
 * CLASSES                                                                    *
 ******************************************************************************/

class FrequencyMismatchException;
class Wave;
class SineWave;
class CompoundWave;
 
/******************************************************************************
 * Class:       FrequencyMismatchException                                    *
 * Base Class:  exception                                                     *
 * Subclasses:  [None]                                                        *
 * Description: An exception thrown when a compound assignment operator is    *
 *              used with two SineWave objects whose frequencies do not       *
 *              match. Two SineWaves can only be added together to produce    *
 *              another SineWave when the frequencies are identical.          *
 ******************************************************************************/
class FrequencyMismatchException : public std::exception {};

/******************************************************************************
 * Class:       Wave                                                          *
 * Base Class:  [None]                                                        *
 * Subclasses:  CompoundWave                                                  *
 *              SineWave                                                      *
 * Description: Represents any periodic sinusoid based wave.                  *
 ******************************************************************************/
class Wave {
		
	protected:
		double amplitude;	// Fraction of device's motion capacity to be used (between 0 and 1)
		double frequency;	// Number of times the wave repeats itself completely in 1 second
};

/******************************************************************************
 * Class:       SineWave                                                      *
 * Base Class:  Wave                                                          *
 * Subclasses:  [None]                                                        *
 * Description: Represents a pure sine wave, using the cosine function as the *
 *              core function. The defining parameters are amplitude,         *
 *              frequency, and phase.                                         *
 ******************************************************************************/
class SineWave : public Wave {
	friend class CompoundWave;
	
	public:
		/******************************************************************************
		 * Function:     SineWave::SineWave                                           *
		 * Arguments:    amplitude - The distance from 0 to the peak of the wave.     *
		 *               frequency - The number of times the wave repeats each        *
		 *                           second.                                          *
		 *               phase     - The time offset from a pure cosine.              *
		 * Returns:      void                                                         *
		 * Side Effects: Initializes a new SineWave object.                           *
		 ******************************************************************************/
		SineWave(double, double, double);
		
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
		 * Side Effects: If the frequencies of the lefthand function and the          *
		 *               righthand function are identical, it computes the single     *
		 *               sine wave parameters defining the sum of the two operands    *
		 *               and assigns these parameters to the left side function. If   *
		 *               the frequencies do not match, it throws an exception.        *
		 ******************************************************************************/
		SineWave     operator+=(const SineWave&     other);
		
		/******************************************************************************
		 * Function:     SineWave::operator-=                                         *
		 * Arguments:    other - The SineWave function on the right side of the       *
		 *                       compound assignment operator to be subtracted from   *
		 *                       the left SineWave function.                          *
		 * Returns:      A copy of the result of the subtraction of the righthand     *
		 *               object from the lefthand object.                             *
		 * Side Effects: If the frequencies of the lefthand function and the          *
		 *               righthand function are identical, it computes the single     *
		 *               sine wave parameters defining the difference of the two      *
		 *               operands and assigns these parameters to the left side       *
		 *               function. If the frequencies do not match, it throws an      *
		 *               exception.                                                   *
		 ******************************************************************************/
		SineWave     operator-=(const SineWave&     other);
		
		/******************************************************************************
		 * Function:     SineWave::operator+                                          *
		 * Arguments:    other - The SineWave function on the right side of the       *
		 *                       addition operator to be added to the left SineWave   *
		 *                       function.                                            *
		 * Returns:      A new CompoundWave object equal to the sum of the two waves  *
		 *               added together.                                              *
		 * Side Effects: [None]                                                       *
		 ******************************************************************************/
		CompoundWave operator+ (const SineWave&     other);
		
		/******************************************************************************
		 * Function:     SineWave::operator+                                          *
		 * Arguments:    other - The CompoundWave function on the right side of the   *
		 *                       addition operator to be added to the left SineWave   *
		 *                       function.                                            *
		 * Returns:      A new CompoundWave object equal to the sum of the two waves  *
		 *               added together.                                              *
		 * Side Effects: [None]                                                       *
		 ******************************************************************************/
		CompoundWave operator+ (const CompoundWave& other);
		
		/******************************************************************************
		 * Function:     SineWave::operator-                                          *
		 * Arguments:    other - The SineWave function on the right side of the       *
		 *                       subtraction operator to be subtracted from the left  *
		 *                       SineWave function.                                   *
		 * Returns:      A new CompoundWave object equal to the difference of the two *
		 *               waves.                                                       *
		 * Side Effects: [None]                                                       *
		 ******************************************************************************/
		CompoundWave operator- (const SineWave&     other);
		
		/******************************************************************************
		 * Function:     SineWave::operator-                                          *
		 * Arguments:    other - The CompoundWave function on the right side of the   *
		 *                       subtraction operator to be subtracted from the left  *
		 *                       SineWave function.                                   *
		 * Returns:      A new CompoundWave object equal to the difference of the two *
		 *               waves.                                                       *
		 * Side Effects: [None]                                                       *
		 ******************************************************************************/
		CompoundWave operator- (const CompoundWave& other);
		
		/******************************************************************************
		 * Function:     SineWave::operator-                                          *
		 * Arguments:    [None]                                                       *
		 * Returns:      The negation of the righthand SineWave function.             *
		 * Side Effects: [None]                                                       *
		 ******************************************************************************/
		SineWave     operator- (                         );
	
	private:
		double phase;		// Offset from pure cosine in seconds (between -1/2 period and 1/2 period)
};

/******************************************************************************
 * Class:       CompoundWave                                                  *
 * Base Class:  Wave                                                          *
 * Subclasses:  [None]                                                        *
 * Description: Represents the sum of an arbitrary number of SineWaves.       *
 ******************************************************************************/
class CompoundWave : public Wave {
	friend class SineWave;
	
	public:
		CompoundWave();
		
		// Overloaded operators make it easier to represent common mathematical
		// operations how they are normally symbolically represented in
		// mathematics.
		CompoundWave operator= (const CompoundWave& other);
		CompoundWave operator= (const SineWave&     other);
		CompoundWave operator+=(const CompoundWave& other);
		CompoundWave operator+=(const SineWave&     other);
		CompoundWave operator-=(const CompoundWave& other);
		CompoundWave operator-=(const SineWave&     other);
		CompoundWave operator+ (const CompoundWave& other);
		CompoundWave operator+ (const SineWave&     other);
		CompoundWave operator- (const CompoundWave& other);
		CompoundWave operator- (const SineWave&     other);
		CompoundWave operator- (                         );
	
	private:
		std::vector<SineWave> components;
};

#endif

/******************************************************************************
 * END OF FILE                                                                *
 ******************************************************************************/

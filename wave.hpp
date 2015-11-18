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

// Component classes
#include "periodmismatchexception.hpp"
#include "waveterm.hpp"
#include "sinewave.hpp"
#include "compoundwave.hpp"
#include "waveproduct.hpp"

/******************************************************************************
 * HELPER FUNCTION DECLARATIONS                                               *
 ******************************************************************************/

int lcm(int a, int b);
int gcd(int a, int b);

/******************************************************************************
 * CLASSES                                                                    *
 ******************************************************************************/

/******************************************************************************
 * Class:       Wave                                                          *
 * Base Class:  [None]                                                        *
 * Description: Represents any periodic sinusoid based wave.                  *
 ******************************************************************************/
class Wave {
		
	protected:
		double amplitude;	// Fraction of device's motion capacity to be used (between 0 and 1)
		int period;	        // Number of microseconds in each self-repeatable cycle of the wave
};

#endif

/******************************************************************************
 * END OF FILE                                                                *
 ******************************************************************************/

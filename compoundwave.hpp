/******************************************************************************
 * Program:     Jampot                                                        *
 * File:        compoundwave.hpp                                              *
 * Author:      Will Weaver                                                   *
 * Description: A collection of classes used to represent and manipulate      *
 *              sinusoidal waveforms.                                         *
 ******************************************************************************/

#ifndef COMPOUNDWAVE_HPP
#define COMPOUNDWAVE_HPP
 
/******************************************************************************
 * INCLUDES                                                                   *
 ******************************************************************************/

// Standard libraries
#include <vector>

// Local project files
#include "wave.hpp"
#include "waveterm.hpp"

/******************************************************************************
 * CLASSES                                                                    *
 ******************************************************************************/

/******************************************************************************
 * Class:       CompoundWave                                                  *
 * Base Class:  Wave                                                          *
 * Description: Represents the sum of an arbitrary number of SineWaves.       *
 ******************************************************************************/
class CompoundWave : public Wave {
	
	public:
		CompoundWave();
		
		// Overloaded operators make it easier to represent common mathematical
		// operations how they are normally symbolically represented in
		// mathematics.
		CompoundWave operator= (const Wave&     other);
		CompoundWave operator+=(const Wave&     other);
		CompoundWave operator-=(const Wave&     other);
		CompoundWave operator*=(const Wave&     other);
		CompoundWave operator/=(const WaveTerm& other);
		CompoundWave operator+ (const Wave&     other);
		CompoundWave operator- (const Wave&     other);
		CompoundWave operator- (                     );
		CompoundWave operator* (const Wave&     other);
		CompoundWave operator/ (const WaveTerm& other);
	
	private:
		std::vector<WaveTerm*> components;

		int updatePeriod(int newWavePeriod);
		double updateAmplitude();
};

#endif // COMPOUNDWAVE_HPP

/******************************************************************************
 * END OF FILE                                                                *
 ******************************************************************************/

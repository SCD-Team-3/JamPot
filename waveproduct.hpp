/******************************************************************************
 * Program:     Jampot                                                        *
 * File:        waveproduct.hpp                                               *
 * Author:      Will Weaver                                                   *
 * Description: The definition of a class used to represent the               *
 *              multiplication of pure sine wave functions together.          *
 ******************************************************************************/

#ifndef WAVEPRODUCT_HPP
#define WAVEPRODUCT_HPP
 
/******************************************************************************
 * INCLUDES                                                                   *
 ******************************************************************************/

// Standard libraries
#include <vector>

// Local project files
#include "waveterm.hpp"
#include "sinewave.hpp"

/******************************************************************************
 * CLASSES                                                                    *
 ******************************************************************************/

/******************************************************************************
 * Class:       WaveProduct                                                   *
 * Base Class:  WaveTerm                                                      *
 * Description: Represents a pure sine wave, using the cosine function as the *
 *              core function. The defining parameters are amplitude,         *
 *              period, and phase.                                            *
 ******************************************************************************/
class WaveProduct : public WaveTerm {

	public:
		WaveProduct();

		// Overloaded operators make it easier to represent common mathematical
		// operations how they are normally symbolically represented in
		// mathematics
		WaveProduct  operator= (const WaveTerm&     other);
		WaveProduct  operator*=(const WaveTerm&     other);
		WaveProduct  operator/=(const WaveTerm&     other);
		CompoundWave operator+ (const Wave&         other);
		CompoundWave operator- (const Wave&         other);
		WaveProduct  operator- (                         );
		CompoundWave operator* (const CompoundWave& other);
		WaveProduct  operator* (const WaveTerm&     other);
		WaveProduct  operator/ (const WaveTerm&     other);
		bool         operator==(const Wave&         other);

	private:
		std::vector<SineWave*> components;

		int updatePeriod();
		double updateAmplitude();

};

#endif // WAVEPRODUCT_HPP

/******************************************************************************
 * END OF FILE                                                                *
 ******************************************************************************/

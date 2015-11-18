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

class WaveProduct : public WaveTerm {
	friend class Wave;
	friend class SineWave;
	friend class CompoundWave;

	public:
		WaveProduct();

		// Overloaded operators make it easier to represent common mathematical
		// operations how they are normally symbolically represented in
		// mathematics
		WaveProduct  operator= (const WaveProduct&  other);
		WaveProduct  operator= (const SineWave&     other);
		WaveProduct  operator*=(const WaveProduct&  other);
		WaveProduct  operator*=(const SineWave&     other);
		WaveProduct  operator/=(const WaveProduct&  other);
		WaveProduct  operator/=(const SineWave&     other);
		CompoundWave operator+ (const WaveProduct&  other);
		CompoundWave operator+ (const SineWave&     other);
		CompoundWave operator+ (const CompoundWave& other);
		CompoundWave operator- (const WaveProduct&  other);
		CompoundWave operator- (const SineWave&     other);
		CompoundWave operator- (const CompoundWave& other);
		WaveProduct  operator* (const WaveProduct&  other);
		WaveProduct  operator* (const SineWave&     other);
		WaveProduct  operator/ (const WaveProduct&  other);
		WaveProduct  operator/ (const SineWave&     other);

	private:
		std::vector<SineWave*> components;

		int updatePeriod();
		double updateAmplitude();

};

#endif // WAVEPRODUCT_HPP

/******************************************************************************
 * END OF FILE                                                                *
 ******************************************************************************/

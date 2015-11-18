/******************************************************************************
 * Program:     Jampot                                                        *
 * File:        sinewave.cpp                                                  *
 * Author:      Will Weaver                                                   *
 * Description: The implementation of the SineWave class. See sinewave.hpp    *
 *              for more details.                                             *
 ******************************************************************************/

/******************************************************************************
 * INCLUDES                                                                   *
 ******************************************************************************/ 

// Standard libraries
#include <cmath>

// Other project files
#include "sinewave.hpp"

/******************************************************************************
 * SineWave METHOD DEFINITIONS                                                *
 ******************************************************************************/

SineWave::SineWave(double amplitude, int period, double phase) {
	this->amplitude = amplitude;
	this->period = period;
	this->phase = phase;
}

SineWave SineWave::operator=(const SineWave& other) {
	
	// Copy wave characteristics
	this->amplitude = other.amplitude;
	this->period = other.period;
	this->phase = other.phase;
	
	return *this;
}

SineWave SineWave::operator+=(const SineWave& other) {
	if (this->period == other.period) {
		// Two sine waves with the same period can be added together to
		// produce a single pure sine wave.
		
		double a2 = pow(this->amplitude, 2);
		double b2 = pow(other.amplitude, 2);
		double phaseAdj = 2 * this->amplitude * other.amplitude * cos(this->phase - other.phase);
		this->amplitude = sqrt(a2 + b2 + phaseAdj);
		
		double cosSol = this->amplitude * cos(this->phase) + other.amplitude * cos(other.phase);
		double sinSol = this->amplitude * sin(this->phase) + other.amplitude * sin(other.phase);
		this->phase = atan2(cosSol, sinSol);
	} else {
		
		// Sine waves with different periods cannot be added together
		// to produce another pure sine wave, so the += operator cannot operate
		// on these waves.
		throw periodMismatchException();
	}
	
	return *this;
}

SineWave SineWave::operator-=(const SineWave& other) {
	return *this += -((SineWave) other);
}

CompoundWave SineWave::operator+(const SineWave& other) {
	CompoundWave result;
	result = *this;
	return result += other;
}

CompoundWave SineWave::operator+(const CompoundWave& other) {
	return ((CompoundWave) other) + *this;
}

CompoundWave SineWave::operator-(const SineWave& other) {
	return *this + -((SineWave) other);
}

CompoundWave SineWave::operator-(const CompoundWave& other) {
	return -((CompoundWave) other) + *this;
}

SineWave SineWave::operator-() {
	SineWave result = *this;
	
	result.amplitude = -result.amplitude;
	
	return result;
}

/******************************************************************************
 * END OF FILE                                                                *
 ******************************************************************************/

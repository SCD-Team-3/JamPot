/******************************************************************************
 * Program:     Jampot                                                        *
 * File:        wave.cpp                                                      *
 * Author:      Will Weaver                                                   *
 * Description: The implementation of a collection of classes used to         *
 *              represent and manipulate sinusoidal waveforms.                *
 ******************************************************************************/

/******************************************************************************
 * INCLUDES                                                                   *
 ******************************************************************************/ 

// C standard libraries
#include <math.h>

// Other project files
#include "wave.hpp"

/******************************************************************************
 * SineWave METHOD DEFINITIONS                                                *
 ******************************************************************************/

SineWave::SineWave(double amplitude, double frequency, double phase) {
	this->amplitude = amplitude;
	this->frequency = frequency;
	this->phase = phase;
}

SineWave SineWave::operator=(const SineWave& other) {
	
	// Copy wave characteristics
	this->amplitude = other.amplitude;
	this->frequency = other.frequency;
	this->phase = other.phase;
	
	return *this;
}

SineWave SineWave::operator+=(const SineWave& other) {
	if (this->frequency == other.frequency) {
		// Two sine waves with the same frequency can be added together to
		// produce a single pure sine wave.
		
		double a2 = pow(this->amplitude, 2);
		double b2 = pow(other.amplitude, 2);
		double phaseAdj = 2 * this->amplitude * other.amplitude * cos(this->phase - other.phase);
		this->amplitude = sqrt(a2 + b2 + phaseAdj);
		
		double cosSol = this->amplitude * cos(this->phase) + other.amplitude * cos(other.phase);
		double sinSol = this->amplitude * sin(this->phase) + other.amplitude * sin(other.phase);
		this->phase = atan2(cosSol, sinSol);
	} else {
		
		// Sine waves with different frequencies cannot be added together
		// to produce another pure sine wave, so the += operator cannot operate
		// on these waves.
		throw FrequencyMismatchException();
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
 * CompoundWave METHOD DEFINITIONS                                           *
 ******************************************************************************/

CompoundWave::CompoundWave() {
	this->amplitude = 0;
	this->frequency = 0;
}
 
CompoundWave CompoundWave::operator= (const CompoundWave& other) {
	
	// Copy wave statistics
	this->amplitude = other.amplitude;
	this->frequency = other.frequency;
	
	// Copy wave components
	std::vector<SineWave>::const_iterator i;
	this->components.clear();
	this->components.reserve(other.components.size());
	for (i = other.components.begin(); i < other.components.end(); i++)
		this->components.push_back(*i);
	
	return *this;
}

CompoundWave CompoundWave::operator= (const SineWave& other) {
	
	// Set compound statistics to sine values
	this->amplitude = other.amplitude;
	this->frequency = other.frequency;
	
	// Make the sine the compound's only component
	this->components.clear();
	this->components.push_back(other);
	
	return *this;
}

CompoundWave CompoundWave::operator+=(const CompoundWave& other) {
	
	// Add each component to the current wave
	std::vector<SineWave>::const_iterator i;
	for (i = other.components.begin(); i < other.components.end(); i++)
		*this += *i;
	
	return *this;
}

CompoundWave CompoundWave::operator+=(const SineWave& other) {
	
	// If a SineWave with the same frequency already exists, other can be merged
	// with it.
	std::vector<SineWave>::iterator i;
	for (i = this->components.begin(); i < this->components.end(); i++) {
		if (i->frequency == other.frequency) {
			(*i) += other;
			if (i->amplitude == 0)
				this->components.erase(i);
			return *this;
		}
	}
	
	// New frequency in the component list
	this->components.push_back(other);
	
	// TODO: update amplitude and frequency
	
	return *this;
}

CompoundWave CompoundWave::operator-=(const CompoundWave& other) {
	return *this += -((CompoundWave) other);
}

CompoundWave CompoundWave::operator-=(const SineWave& other) {
	return *this += -((SineWave) other);
}

CompoundWave CompoundWave::operator+(const CompoundWave& other) {
	CompoundWave result = *this;
	return result += other;
}

CompoundWave CompoundWave::operator+(const SineWave& other) {
	CompoundWave result = *this;
	return result += other;
}

CompoundWave CompoundWave::operator-(const CompoundWave& other) {
	return *this + -((CompoundWave) other);
}

CompoundWave CompoundWave::operator-(const SineWave& other) {
	return *this + -((SineWave) other);
}

CompoundWave CompoundWave::operator-() {
	CompoundWave result = *this;
	
	std::vector<SineWave>::iterator i;
	for (i = result.components.begin(); i < result.components.end(); i++)
		(*i) = -(*i);
	
	return result;
}

/******************************************************************************
 * END OF FILE                                                                *
 ******************************************************************************/

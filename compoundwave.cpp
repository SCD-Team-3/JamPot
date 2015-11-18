/******************************************************************************
 * Program:     Jampot                                                        *
 * File:        compoundwave.cpp                                              *
 * Author:      Will Weaver                                                   *
 * Description: The implementation of the CompoundWave class. See             *
 *              sinewave.hpp for more details.                                *
 ******************************************************************************/

/******************************************************************************
 * INCLUDES                                                                   *
 ******************************************************************************/ 

// Standard libraries
#include <cmath>
#include <vector>

// Other project files
#include "compoundwave.hpp"

/******************************************************************************
 * CompoundWave METHOD DEFINITIONS                                            *
 ******************************************************************************/

CompoundWave::CompoundWave() {
	this->amplitude = 0;
	this->period = 0;
}
 
CompoundWave CompoundWave::operator= (const CompoundWave& other) {
	
	// Copy wave statistics
	this->amplitude = other.amplitude;
	this->period = other.period;
	
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
	this->period = other.period;
	
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
	
	// If a SineWave with the same period already exists, other can be merged
	// with it.
	std::vector<SineWave>::iterator i;
	for (i = this->components.begin(); i < this->components.end(); i++) {
		if (i->period == other.period) {
			(*i) += other;
			if (i->amplitude == 0)
				this->components.erase(i);
			return *this;
		}
	}
	
	// New period in the component list
	this->components.push_back(other);
	
	// TODO: update amplitude and period
	
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

int CompoundWave::newPeriod(int newWavePeriod) {
	return this->period = lcm(this->period, newWavePeriod);
}

/******************************************************************************
 * END OF FILE                                                                *
 ******************************************************************************/

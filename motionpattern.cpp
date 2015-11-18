/******************************************************************************
 * Program:     Jampot                                                        *
 * File:        motionpattern.cpp                                             *
 * Author:      Will Weaver                                                   *
 * Description: A collection of classes used to represent JamJel time-        *
 *              dependent motion patterns.                                    *
 ******************************************************************************/

/******************************************************************************
 * INCLUDES                                                                   *
 ******************************************************************************/ 

// C standard libraries
#include <string>

// Other project files
#include "motionpattern.hpp"

/******************************************************************************
 * MotionPattern METHOD DEFINITIONS                                           *
 ******************************************************************************/
 
void MotionPattern::MotionPattern(std::string name) {
	this->name = name;
}

void MotionPattern::~MotionPattern() {
	return;
}

/******************************************************************************
 * END OF FILE                                                                *
 ******************************************************************************/

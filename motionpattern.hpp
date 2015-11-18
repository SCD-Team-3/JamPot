/******************************************************************************
 * Program:     Jampot                                                        *
 * File:        motionpattern.hpp                                             *
 * Author:      Will Weaver                                                   *
 * Description: A collection of classes used to represent JamJel time-        *
 *              dependent motion patterns.                                    *
 ******************************************************************************/

#ifndef MOTIONPATTERN_HPP
#define MOTIONPATTERN_HPP
 
/******************************************************************************
 * INCLUDES                                                                   *
 ******************************************************************************/

// Standard Libraries
#include <string>
 
// Other project files
#include "wave.hpp"

/******************************************************************************
 * CLASSES                                                                    *
 ******************************************************************************/

/******************************************************************************
 * Class:       MotionPattern                                                 *
 * Base Class:  [None]                                                        *
 * Subclasses:  [None]                                                        *
 * Description: Represents a two-dimensional motion pattern with respect to   *
 *              time that the JamJel device traces                            *
 ******************************************************************************/
class MotionPattern {
	public:
		void MotionPattern(std::string);
		void ~MotionPattern();
		
	private:
		std::string name;
		Wave xFunc;
		Wave yFunc;
}

#endif

/******************************************************************************
 * END OF FILE                                                                *
 ******************************************************************************/

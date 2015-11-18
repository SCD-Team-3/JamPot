/******************************************************************************
 * Program:     Jampot                                                        *
 * File:        periodmismatchexception.hpp                                   *
 * Author:      Will Weaver                                                   *
 * Description: The definition of a class used to indicate that a period      *
 *              mismatch in SineWave addition has occurred.                   *
 ******************************************************************************/

#ifndef PERIODMISMATCHEXCEPTION_HPP
#define PERIODMISMATCHEXCEPTION_HPP

/******************************************************************************
 * INCLUDES                                                                   *
 ******************************************************************************/

#include <exception>

/******************************************************************************
 * CLASSES                                                                    *
 ******************************************************************************/

/******************************************************************************
 * Class:       PeriodMismatchException                                       *
 * Base Class:  exception                                                     *
 * Subclasses:  [None]                                                        *
 * Description: An exception thrown when a compound assignment operator is    *
 *              used with two SineWave objects whose periods do not           *
 *              match. Two SineWaves can only be added together to produce    *
 *              another SineWave when the periods are identical.              *
 ******************************************************************************/
class PeriodMismatchException : public std::exception {};

#endif // PERIODMISMATCHEXCEPTION_HPP

/******************************************************************************
 * END OF FILE                                                                *
 ******************************************************************************/

package com.github.mgoeminne

package object iban
{
   /**
    * Replace all the letters [A-Z] by a number.
    * A=10, B=11, etc.
    * @param s a string
    * @return the same string, in which all the letters have been replaced by numbers.
    */
   def replaceLetterByNumber(s: String): String =
   {
      s.toUpperCase.map(character => {
         if(character.isLetter) (character.toInt - 'A'.toInt + 10).toString
         else character.toString
      }).mkString
   }
}

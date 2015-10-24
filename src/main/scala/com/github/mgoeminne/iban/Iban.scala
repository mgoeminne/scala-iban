package com.github.mgoeminne.iban

import java.util.Locale

/**
 * Represents an IBAN account number
 */
case class Iban(locale: Locale, check: Int, account: String)
{
   /**
    * @return a digital representation of the IBAN account
    */
   def digital = locale.getCountry.toUpperCase + "%02d".format(check) + account

   /**
    * @return a printable representation of the IBAN account
    */
   override def toString = locale.getCountry + "%02d".format(check) + " " + account.grouped(4).map(_.toUpperCase).mkString(" ")

   def isValid = {
      val head = digital.take(4)
      val tail = digital.drop(4)

      val t1 = tail + head
      val t2 = replaceLetterByNumber(t1)
      (BigInt(t2) mod 97) == 1
   }


   /**
    * Replace all the letters [A-Z] by a number.
    * A=10, B=11, etc.
    * @param s a string
    * @return the same string, in which all the letters have been replaced by numbers.
    */
   private def replaceLetterByNumber(s: String): String =
   {
      s.toUpperCase.map(character => {
          if(character.isLetter) (character.toInt - 'A'.toInt + 10).toString
          else character.toString
      }).mkString
   }

   override def equals(o: Any) =
   {
       o match {
          case i: Iban => this.locale.getCountry == i.locale.getCountry && this.check == i.check && this.account == i.account
          case _ => false
       }
   }
}

object Iban
{
   def apply(s: String): Option[Iban] =
   {
      val cleaned = s.filter(c => c.isLetterOrDigit)

      if((cleaned.size < 5) || (cleaned.size > 34)) return None
      if(!cleaned(0).isLetter || !cleaned(1).isLetter) return None
      if(!cleaned(2).isDigit || !cleaned(3).isDigit) return None

      Some(new Iban(new Locale("", cleaned.take(2)), cleaned.drop(2).take(2).toInt, cleaned.drop(4)))
   }
}

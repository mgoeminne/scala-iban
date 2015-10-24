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
   def digital = locale.getCountry.toUpperCase + check + account

   /**
    * @return a printable representation of the IBAN account
    */
   override def toString = locale.getCountry + check + " " + account.grouped(4).map(_.toUpperCase).mkString(" ")
}

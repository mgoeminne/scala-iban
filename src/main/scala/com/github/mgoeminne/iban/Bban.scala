package com.github.mgoeminne.iban

import java.util.Locale

import com.github.mgoeminne.iban

/**
 * A representation of a basic bank account number.
 */
abstract class BBAN(val account: String, val countryCode: String)
{
   /**
    * Transform the BBAN into its IBAN representation.
    * @return the IBAN representaion of the BBAN.
    */
   def toIBAN =
   {
      val t1 = account + countryCode + "00"
      val t2 = iban.replaceLetterByNumber(t1)
      val mod = (BigInt(t2) mod 97).toInt
      val check = 98 - mod

      Iban(new Locale("", countryCode), check, account)
   }

   /**
    *
    * @return Some(true) if the account is valid, Some(false) if the account is not valid,
    *         and None if the validity of the account can not be determined.
    */
   def isValid: Option[Boolean]
}

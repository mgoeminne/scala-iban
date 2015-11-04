package com.github.mgoeminne.iban

/**
 * A Portuguese BBAN.
 */
class BBANPortugal(number: String) extends BBAN(number.replaceAll("""[^\d\w]""", ""), "PT")
{
   /**
    * @return Some(true) if the account is valid, Some(false) if the account is not valid,
    *         and None if the validity of the account can not be determined.
    */
   override def isValid: Option[Boolean] =
   {
      val toValidate = account.take(19).map(_.asDigit)
      val wi = Array(73, 17, 89, 38, 62, 45, 53, 15, 50, 5, 49, 34, 81, 76, 27, 90, 9, 30, 3);
      val z = toValidate zip wi

      val sum = z.foldLeft(0)((sum, e) => sum + e._1 * e._2)
      return Some(key.toInt == 98 - (sum % 97))
   }

   /**
    * @return a 4-digit code representing the bank responsible of the BBAN.
    */
   def bankCode(): String = account take 4

   /**
    * @return the 4-digit sort code of the considered BBAN
    */
   def sortCode(): String = account.drop(4).take(4)

   /**
    * @return a 11-digit code representing the bank account number.
    */
   def bankAccount(): String = account.drop(8).take(11)

   /**
    * @return a 2-digit code representing the checking key
    */
   def key(): String = account takeRight 2

   override def toString = bankCode + " " + sortCode + " " + bankAccount + " " + key
}
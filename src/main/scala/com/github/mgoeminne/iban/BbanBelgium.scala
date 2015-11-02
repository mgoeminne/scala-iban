package com.github.mgoeminne.iban

/**
 * A BBAN for a belgian account.
 */
case class BBANBelgium(account: String) extends BBAN("BE")
{
   override def digital: String = account

   override def toString = account.take(3) + "-" + account.drop(3).take(7) + "-" + account.takeRight(2)

   /**
    * @return Some(true) if the account is valid, Some(false) if the account is not valid,
    *         and None if the validity of the account can not be determined.
    */
   override def isValid: Option[Boolean] =
   {
      Some(account.forall(_.isDigit) &&
           (BigInt(account.dropRight(2)) mod 97).toInt == account.takeRight(2).toInt)
   }

   /**
    * @return a 3-digit code representing the bank responsible of the BBAN.
    */
   def bankCode(): String = account take 3

   /**
    * @return a 7-digit code representing the bank account number.
    */
   def bankAccount(): String = account.drop(3).take(7)

   /**
    * @return a 2-digit code representing the checking key
    */
   def key(): String = account takeRight 2
}

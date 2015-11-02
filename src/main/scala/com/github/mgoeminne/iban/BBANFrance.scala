package com.github.mgoeminne.iban

/**
 * A French BBAN, also known as RIB.
 */
class BBANFrance(number: String) extends BBAN(number.replaceAll("""[^\d\w]""", ""), "FR")
{
   /**
    * @return Some(true) if the account is valid, Some(false) if the account is not valid,
    *         and None if the validity of the account can not be determined.
    */
   override def isValid: Option[Boolean] =
   {
      def convert(char: Char): Int = {
         val c = char.toUpper

         if(c >= '0' && c <= '9') return c.asDigit
         if(c >= 'A' && c <= 'I') return c.asDigit - 'A'.asDigit + 1
         if(c >= 'J' && c <= 'R') return c.asDigit - 'J'.asDigit + 1
         return c.asDigit - 'S'.asDigit + 2
      }

      val b = BigInt(bankCode.map(c => (convert(c) + '0').toChar))
      val s = BigInt(sortCode.map(c => (convert(c) + '0').toChar))
      val a = BigInt(bankAccount.map(c => (convert(c) + '0').toChar))
      val k = BigInt(key.map(c => (convert(c) + '0').toChar))

      return Some((b*89 + s*15 + a*3 + k) % 97 == 0)
   }

   /**
    * @return a 5-digit code representing the bank responsible of the BBAN.
    */
   def bankCode(): String = account take 5

   /**
    * @return the 5-digit sort code of the considered BBAN
    */
   def sortCode(): String = account.drop(5).take(5)

   /**
    * @return a 11-digit code representing the bank account number.
    */
   def bankAccount(): String = account.drop(10).take(11)

   /**
    * @return a 2-digit code representing the checking key
    */
   def key(): String = account takeRight 2

   override def toString = bankCode + " " + sortCode + " " + bankAccount + " " + key
}

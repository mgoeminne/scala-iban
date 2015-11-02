package com.github.mgoeminne.iban

import org.scalatest.{Inspectors, Matchers, FlatSpec}

/**
 * Tests dedicated to the French BBAN
 */
class BBANFranceTest extends FlatSpec with Matchers with Inspectors
{
   val validBBANs = Seq("30002 00550 0000157845Z 02",  // LCL
                        "18206 00210 54872667002 17", // Crédit Agricole Ile de France
                        "20041 10020 0058741005T 15"  // La Banque postale
   )

   "All valid BBAN" should "pass the validation test" in {
      forAll(validBBANs){ x => new BBANFrance(x).isValid shouldBe Some(true) }
   }

   it should "produce correct string representation" in {
      validBBANs.map(new BBANFrance(_).toString) shouldEqual validBBANs
   }


}

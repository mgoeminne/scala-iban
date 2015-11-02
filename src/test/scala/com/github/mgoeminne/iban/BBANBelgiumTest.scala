package com.github.mgoeminne.iban

import org.scalatest.{Inspectors, Matchers, FlatSpec}

/**
 * Tests dedicated to Belgian BBANs.
 */
class BBANBelgiumTest extends FlatSpec with Matchers with Inspectors
{
   val validBBANs = Seq("068249252641", "310126985517",
                        "539007547034")

   "All valid BBAN" should "pass the validation test" in {
      forAll(validBBANs){ x => BBANBelgium(x).isValid shouldBe Some(true) }
   }

   it should "produce correct string representation" in {
      validBBANs.map(BBANBelgium(_).toString) shouldEqual(
         Seq("068-2492526-41",
             "310-1269855-17",
             "539-0075470-34")
      )
   }
}

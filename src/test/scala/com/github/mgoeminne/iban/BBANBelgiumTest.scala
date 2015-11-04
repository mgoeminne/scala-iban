package com.github.mgoeminne.iban

import org.scalatest.{Inspectors, Matchers, FlatSpec}

/**
 * Tests dedicated to Belgian BBANs.
 */
class BBANBelgiumTest extends FlatSpec with Matchers with Inspectors
{
   val validBBANs = Seq("068-2492526-41", "310-1269855-17",
                        "539-0075470-34")

   val invalidBBANs = Seq("844-0103701-34")

   "All valid BBAN" should "pass the validation test" in {
      forAll(validBBANs){ x => new BBANBelgium(x).isValid shouldBe Some(true) }
   }

   it should "produce correct string representation" in {
      forAll(invalidBBANs){x => new BBANBelgium(x).toString shouldBe x}
   }

   "All invalid BBAN" should "not pass the validation test" in {
      forAll(invalidBBANs){ x=> new BBANBelgium(x).isValid shouldBe Some(false)}
   }
}

package com.github.mgoeminne.iban

import org.scalatest.{Inspectors, Matchers, FlatSpec}

/**
 * Tests dedicated to the Portuguese BBAN
 */
class BBANPortugalTest extends FlatSpec with Matchers with Inspectors
{
   val validBBANs = Seq("0002 0123 12345678901 54",
                        "0035 0683 00000007843 11"
   )

   val invalidBBANs = Seq("0781 0112 00000007843 10")

   "All valid BBAN" should "pass the validation test" in {
      forAll(validBBANs){ x =>
         new BBANPortugal(x).isValid shouldBe Some(true)
      }
   }

   it should "produce correct string representation" in {
      validBBANs.map(new BBANPortugal(_).toString) shouldEqual validBBANs
   }

   "All invalid BBAN" should "not pass the validation test" in {
      forAll(invalidBBANs){ x=> new BBANPortugal(x).isValid shouldBe Some(false)}
   }
}

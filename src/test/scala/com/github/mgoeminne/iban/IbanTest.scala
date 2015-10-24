package com.github.mgoeminne.iban

import java.util.Locale

import org.scalatest.FlatSpec

class IbanTest extends FlatSpec {

   val lampiris = Iban(new Locale("fr", "BE"), 38, "001509424272")

   "An IBAN account" should "have a correct digital representation" in {
       assert(lampiris.digital == "BE38001509424272")
   }

   it should "have a correct printable representation" in {
      assert(lampiris.toString == "BE38 0015 0942 4272")
   }
}
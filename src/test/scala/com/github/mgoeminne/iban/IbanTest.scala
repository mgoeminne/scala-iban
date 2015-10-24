package com.github.mgoeminne.iban

import java.util.Locale

import org.scalatest.{Matchers, FlatSpec}

class IbanTest extends FlatSpec with Matchers {

   val lampiris = Iban(new Locale("fr", "BE"), 38, "001509424272")
   val greekBank = Iban(new Locale("gr", "GR"), 3, "01108950000089529800453")

   "The Lampiris IBAN account" should "have a correct digital representation" in {
       lampiris.digital should be ("BE38001509424272")
   }

   it should "have a correct printable representation" in {
      lampiris.toString should be ("BE38 0015 0942 4272")
   }

   it should "be a valid account" in {
      lampiris should be a 'valid
   }

   it should "be created from a simple string" in {
      Iban("BE38001509424272") should be a 'defined
      Iban("BE38001509424272").get should equal (lampiris)
      Iban("BE38 0015 0942 4272") should be a 'defined
      Iban("BE38 0015 0942 4272").get should equal (lampiris)
   }


   "The IBAN account of the national greek bank" should "have a correct digital representation" in {
      greekBank.digital should be ("GR0301108950000089529800453")
   }

   it should "have a correct printable representation" in {
      greekBank.toString should be ("GR03 0110 8950 0000 8952 9800 453")
   }

   it should "be a valid account" in {
      greekBank should be a 'valid
   }

   it should "be created from a simple string" in {
      Iban("GR0301108950000089529800453") should be a 'defined
      Iban("GR0301108950000089529800453").get should equal (greekBank)
      Iban("GR03 0110 8950 0000 8952 9800 453") should be a 'defined
      Iban("GR03 0110 8950 0000 8952 9800 453").get should equal (greekBank)
   }

}
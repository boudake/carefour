package product

import org.scalatest.FunSuite

class ReferencesMagasinTest  extends FunSuite {


  test("test ReferencesMagasin")
    {
      var mapReferenceProduct : Map[String, MapProductPrice]= Map()
      var map : Map[Long,Double]= Map()
      map += (1L -> 1.1,2L -> 2.1)
      val mapProductPricee :MapProductPrice = new MapProductPrice(map)
      //var mapProductPrice : MapProductPrice = new MapProductPrice(x)
      //mapProductPrice += (1L -> 1.1,2L -> 2.1)

      mapReferenceProduct += ("mag1" -> mapProductPricee)

      val referencesMagasin : ReferencesMagasin = new ReferencesMagasin(mapReferenceProduct)

      assert(referencesMagasin.referencesMagasin.get("mag3" ).isEmpty)
      assert(referencesMagasin.referencesMagasin.get("mag1" ).contains(mapProductPricee))


    }
  }

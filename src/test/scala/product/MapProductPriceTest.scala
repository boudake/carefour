package product

import org.scalatest.FlatSpec

import scala.collection.mutable

import org.scalatest.FunSuite

class MapProductPriceTest extends FunSuite {


  test("test MapProductPrice") {

    var map : Map[Long,Double]= Map()
    map += (1L -> 1.1,2L -> 2.1)
    val mapProductPricee :MapProductPrice = new MapProductPrice(map)
    assert(mapProductPricee.mapProductPrice.getOrElse(1L, 0.0)== 1.1)
    assert(mapProductPricee.mapProductPrice.getOrElse(2L, 0.0)== 2.1)
    assert(mapProductPricee.mapProductPrice.keySet == Set(1L,2L))
  }
}

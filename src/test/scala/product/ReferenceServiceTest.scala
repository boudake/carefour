package product


import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.FunSuite
class ReferenceServiceTest extends FunSuite  with MockitoSugar{

  val pathReference = "src\\test\\resources\\reference\\"
  val referencesService : ReferencesServiceImpl = new ReferencesServiceImpl()

  test("test ReferencesMagasin")
  {
    var map: Map[Long, Double] = Map()
    map += (1L -> 1.1)
    map += (2L -> 2.1)
    val seqValue = Seq(("20140201", "idMagasin", map))
    val ref = mock[ReferencesServiceImpl]
    when(ref.getListProducts("")).thenReturn(seqValue)
    when(ref.getReferencesByMagasinDate("")).thenCallRealMethod()

    val mapProductMagasinDate =ref.getReferencesByMagasinDate("").mapProductMagasinDate

    assert(Set("20140201") === mapProductMagasinDate.keySet)
    assert(Some(2.1) === mapProductMagasinDate.getOrElse("20140201",
      new ReferencesMagasin(Map())).referencesMagasin.getOrElse("idMagasin", new MapProductPrice(Map())).mapProductPrice.get(2L) )

    assert(Some(1.1) === mapProductMagasinDate.getOrElse("20140201",
      new ReferencesMagasin(Map())).referencesMagasin.getOrElse("idMagasin", new MapProductPrice(Map())).mapProductPrice.get(1L) )

    //assert(referencesService.mapReferencesByMagasinDate(pathReference).mapProductMagasinDate.keySet === Set("20170514"))
  }


  test("test the extract date and magasin ID from fileName"){
    assert(Some("20170514")===
      referencesService.getDateFromPath("reference_prod-2a4b6b81-5aa2-4ad8-8ba9-ae1a006e7d71_20170514.data"))

    assert(Some("2a4b6b81-5aa2-4ad8-8ba9-ae1a006e7d71")===
      referencesService.getIdMagasinFromPath("reference_prod-2a4b6b81-5aa2-4ad8-8ba9-ae1a006e7d71_20170514.data"))

  }
}

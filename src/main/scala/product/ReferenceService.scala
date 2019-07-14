package product

trait ReferenceService {

  def getListProducts(directory : String): Seq[(String, String, Map[Long, Double])]
  def mapReferencesByMagasinDate(directory : String) :  MapProductMagasinDate
}

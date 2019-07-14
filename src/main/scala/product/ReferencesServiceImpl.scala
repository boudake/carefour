package product

import java.io.File

import scala.io.Source

class ReferencesServiceImpl {

  val reference = "reference_prod"

  def  getListProducts(directory : String): Seq[(String, String, Map[Long, Double])] =  new File(directory).listFiles().toList
    .filter(_.getName.startsWith(reference))
    .map(file => (getDateFromPath(file.getName).getOrElse(""),getIdMagasinFromPath(file.getName).getOrElse(""), Source.fromFile(file)
      .getLines()
      .toList
      .map(x =>  (x.split('|')(0).toLong, x.split('|')(1).toDouble))
      .toMap
    ))

  def getReferencesByMagasinDate(directory : String) :  MapProductMagasinDate = {

    val mapProductMagasinDate : MapProductMagasinDate = new MapProductMagasinDate(Map())
    val listProduct = getListProducts(directory)

    listProduct.foreach(x =>{
      val date = x._1
      val IdMagasin = x._2
      val mapReference = x._3
      val mapProductPrice: MapProductPrice = new MapProductPrice (mapReference)
      val referencesMagasin : ReferencesMagasin = mapProductMagasinDate.mapProductMagasinDate.getOrElse(date, new ReferencesMagasin(Map() ))
      referencesMagasin.referencesMagasin += (IdMagasin -> mapProductPrice)

      mapProductMagasinDate.mapProductMagasinDate += (date -> referencesMagasin)
      }

    )
    mapProductMagasinDate
  }


  def getDateFromPath(fileName: String): Option[String] = {
    val pattern = "^reference_prod-.*_(\\d{8}).data$".r

    fileName match {
      case pattern(date) => Some(date)
      case _ => None
    }
  }

  def getIdMagasinFromPath(fileName: String): Option[String] = {
    val pattern = "^reference_prod-(.*)_\\d{8}.data$".r

    fileName match {
      case pattern(id) => Some(id)
      case _ => None
    }

  }
}



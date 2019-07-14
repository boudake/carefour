import calculated.OutputData
import product.{ReferencesMagasin, ReferencesServiceImpl}
import transaction.{MapTransactionDate, Transaction}

object launcher {

  def main(args: Array[String]): Unit ={

    val pathIn = args(0)
    val pathOut = args(1)

    val referencesService = new ReferencesServiceImpl()
    val mapTransactionDate: MapTransactionDate = new MapTransactionDate()
    //println(mapTransactionDate.getMapContentByDate(path).transactionDate.keySet)
    val outputData = new OutputData(pathIn,pathOut )

    //referencesService.getReferencesByMagasinDate(path).mapProductMagasinDate.foreach(println)
    //System.out.println(outputData.test)
    outputData.getTop_100_ventes_GLOBAL_YYYYMMDD
    outputData.getTop_100_ventes_MAGASIN_YYYYMMDD
    outputData.getTop_100_ca_ventes_GLOBAL_YYYYMMDD
    outputData.getTop_ca_100_ventes_MAGASIN_YYYYMMDD
    System.out.println("fer")
  }


}

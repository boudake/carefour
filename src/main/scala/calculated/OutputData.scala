package calculated

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

import better.files._
import product.{MapProductPrice, ReferencesMagasin, ReferencesServiceImpl}
import transaction.MapTransactionDate

import scala.collection.SortedSet

class OutputData(directory :String, pathOut : String) {

  val referenceService = new ReferencesServiceImpl()
  val mapTransactionDate = new MapTransactionDate ()
  type Date = String
  type Magasin = String

  val transactionsCalculatedByDate : Map[Date, List[TransactionCalculated]] ={
    var transactionsCalculatedByDateTemp : Map[Date, List[TransactionCalculated]] = Map()
    mapTransactionDate.getMapTransactionByDate(directory).transactionDate.foreach(x => {
      val date = x._1
      val transactions = x._2
      val referenceMagasin: ReferencesMagasin = referenceService.getReferencesByMagasinDate(directory).mapProductMagasinDate.getOrElse(date, new ReferencesMagasin(Map()))
      var listCalculatedTransaction: List[TransactionCalculated] = List[TransactionCalculated]()
      for (transaction <- transactions) {
        val map: MapProductPrice = referenceMagasin.referencesMagasin(transaction.magasin)
        val value: Double = map.mapProductPrice.getOrElse(transaction.produit, 0.0) * transaction.qte
        val transactionCalculated = TransactionCalculated(transaction.magasin, transaction.produit, transaction.qte, value)
        listCalculatedTransaction= listCalculatedTransaction :+ transactionCalculated
      }
      transactionsCalculatedByDateTemp += (date -> listCalculatedTransaction)
    })
    transactionsCalculatedByDateTemp
  }


  val transactionsCalculatedByMagasinDate : Map[Date, Map[Magasin, List[TransactionCalculated]]] ={

    var transactionsCalculatedByMagasinDateTemp : Map[Date, Map[String, List[TransactionCalculated]]]  = Map()

    mapTransactionDate.getMapTransactionByDate(directory).transactionDate.foreach(f = x => {
      val date = x._1
      val transactions = x._2
      val referenceMagasin: ReferencesMagasin = referenceService.getReferencesByMagasinDate(directory).mapProductMagasinDate.getOrElse(date, new ReferencesMagasin(Map()))

      var map3 = transactionsCalculatedByMagasinDateTemp.getOrElse(date, Map())

      for (transaction <- transactions) {

        val map: MapProductPrice = referenceMagasin.referencesMagasin(transaction.magasin)
        val value: Double = map.mapProductPrice.getOrElse(transaction.produit, 0.0) * transaction.qte
        val list = map3.getOrElse(transaction.magasin, List[TransactionCalculated]()) :+ TransactionCalculated(transaction.magasin, transaction.produit, transaction.qte, value)
        map3 += (transaction.magasin -> list)
      }
      transactionsCalculatedByMagasinDateTemp += (date -> map3)
    })
    transactionsCalculatedByMagasinDateTemp
  }


  def transactionsCalculatedByMagasinDate_7J() : Map[Date, Map[Magasin, List[TransactionCalculated]]] ={
    transactionsCalculatedByMagasinDate.filter(date => isInLast7Days(date._1))
  }

  def getTop_100_ventes_GLOBAL_YYYYMMDD: Unit ={
    var mapSumByProductList: Map[Date, List[(String, Int)]]  = Map()
    transactionsCalculatedByDate.foreach(x => {
      val date: String = x._1
      val listTransactionCalculated:List[TransactionCalculated] = x._2
      mapSumByProductList+=(date -> sumByCountProduct (listTransactionCalculated))

    })

    mapSumByProductList.foreach(x => {
      val fileName = pathOut + "Top_100_ventes_GLOBAL_"+ x._1 + ".data"
      writeTopOnFile(fileName,  topN(x._2, 10))
    })
  }

  def getTop_100_ca_ventes_GLOBAL_YYYYMMDD: Unit ={
    var mapSumByProductList: Map[Date, List[(String, Double)]]  = Map()
    transactionsCalculatedByDate.foreach(x => {
      val date: String = x._1
      val listTransactionCalculated:List[TransactionCalculated] = x._2
      mapSumByProductList+=(date -> sumByCAProduct (listTransactionCalculated))
    })
    mapSumByProductList.foreach(x => {
      val fileName = pathOut + "Top_100_ca_ventes_GLOBAL_"+ x._1 + ".data"
      writeTopOnFile(fileName,  topN(x._2, 10))
    })
  }

  def getTop_100_ventes_MAGASIN_YYYYMMDD(): Unit ={

    transactionsCalculatedByMagasinDate.foreach(x => {
      val date: String = x._1
      x._2.foreach( y => {
        val magagsinID = y._1
        val listTransactionCalculated:List[TransactionCalculated] = y._2
        val fileName = pathOut + "Top_100_ventes_" +magagsinID+ "_" + date + ".data"
        writeTopOnFile(fileName,  topN(sumCountByMagasin(listTransactionCalculated), 10))
      }
      )

     // mapSumByProductList+=(date -> sumByProduct (listTransactionCalculated))
    })

    }


  def getTop_ca_100_ventes_MAGASIN_YYYYMMDD(): Unit ={

    transactionsCalculatedByMagasinDate.foreach(x => {
      val date: String = x._1
      x._2.foreach { y => {
        val magagsinID = y._1
        val listTransactionCalculated: List[TransactionCalculated] = y._2
        val fileName = pathOut + "Top_ca_100_ventes_" + magagsinID + "_" + date + ".data"
        writeTopOnFile(fileName, topN(sumByCAProduct(listTransactionCalculated), 10))
      }
      }

      // mapSumByProductList+=(date -> sumByProduct (listTransactionCalculated))
    })

  }

  def getTop_ca_100_ventes_MAGASIN_YYYYMMDD_J7(): Unit ={

    transactionsCalculatedByMagasinDate_7J().foreach(x => {
      val date: String = x._1
      x._2.foreach { y => {
        val magagsinID = y._1
        val listTransactionCalculated: List[TransactionCalculated] = y._2
        val fileName = pathOut + "Top_ca_100_ventes_J7_" + magagsinID + "_" + date + ".data"
        writeTopOnFile(fileName, topN(sumByCAProduct(listTransactionCalculated), 10))
      }
      }

      // mapSumByProductList+=(date -> sumByProduct (listTransactionCalculated))
    })

  }

  def sumCAByMagasin (tuples: List[TransactionCalculated]): List[(String, Double)] =
    tuples groupBy (_.magasin) map { case (k, v) => (k, v.map(_.transactionValue).sum) }toList

  def sumCountByMagasin (tuples: List[TransactionCalculated]): List[(String, Int)] =
    tuples groupBy (_.produit.toString) map { case (k, v) => (k, v.map(_.qte).sum) }toList

 def sumByCountProduct(tuples: List[TransactionCalculated]): List[(String, Int)] =
     tuples groupBy (_.produit.toString) map { case (k, v) => (k, v.map(_.qte).sum) }toList

  def sumByCAProduct(tuples: List[TransactionCalculated]): List[(String, Double)] =
    tuples groupBy (_.produit.toString) map { case (k, v) => (k, v.map(_.transactionValue).sum) }toList

  def topN[T] (list: List[(String, T)], n: Int) (implicit numeric: Numeric[T]): Set[(String, T)] = {

    implicit def ordering(implicit genericOrdering: Ordering[T]) = new Ordering[(String, T)] {
      def compare(x: (String, T), y: (String, T)): Int = {
        genericOrdering.compare(x._2, y._2)
      }
    }
    list.foldLeft(SortedSet.empty[(String, T)]) {
      (top, i) =>

        if (top.size < n) {
          top + i
        }
        else if (numeric.min(i._2,top.min._2 ).equals(top.min._2)) {
          top - top.min + i
        }
        else {
          top
        }
    }
  }


  def writeTopOnFile[T] (path : String, setData :Set[(String, T)] ): Unit ={
    val file: File = path.toFile.createIfNotExists()
    setData.foreach(x => {
      file.appendLine(x._1 + ";"+  x._2)
    })
  }

  def isInLast7Days( date : String) : Boolean ={
    val pattern = DateTimeFormatter.ofPattern("yyyyMMdd")
    LocalDate.parse(date, pattern)
    LocalDate.parse(date, pattern).isBefore(LocalDate.now().minusDays(7))
  }

}
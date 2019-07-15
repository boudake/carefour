package transaction


import java.io._

import scala.io.Source

class MapTransactionDate {

  val transactionFile = "transactions"
  type Date = String
  type  Transactions = List[String]

  def getMapTransactionByDate(directory : String): TransactionDate = {

   val transactions = listTransactionsByDate(directory)
    val mapTransaction=  transactions.map(x => {
      (x._1, x._2.map(x => Transaction(x.split('|')(0).toInt, x.split('|')(1),
        x.split('|')(2), x.split('|')(3).toInt,x.split('|')(4).toInt )))
    }).toMap

    new TransactionDate(mapTransaction)
  }

  def listTransactionsByDate(directory : String): List[(Date, Transactions)] = {

    new File(directory).listFiles().toList.filter(_.getName.startsWith(transactionFile))
      .map(file => (getDateFromPath(file.getName).getOrElse(""), Source.fromFile(file).getLines()
        .toList))
  }


  def getDateFromPath(fileName: String): Option[String] = {
    val pattern = "(\\d{8})".r
    pattern findFirstIn fileName

  }



}

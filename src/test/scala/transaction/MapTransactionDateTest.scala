package transaction


import better.files._
import better.files.File._

import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.FunSuite
class MapTransactionDateTest extends FunSuite  with MockitoSugar{
  type Date = String
  type  Transactions = List[String]

  test("mapTransactionDate")
{
  val listTransctionDate : List[(Date, Transactions)] =
    List(("20170101", List("1|20170514T223544+0100|2a4b6b81-5aa2-4ad8-8ba9-ae1a006e7d71|531|5","1|20170514T224008+0100|bdc2a431-797d-4b07-9567-67c565a67b84|55|3")))
  val mapTransactionDate = mock[MapTransactionDate]

  when(mapTransactionDate.listTransactionsByDate("")).thenReturn(listTransctionDate )
  when(mapTransactionDate.getMapTransactionByDate("")).thenCallRealMethod()

  val mapTransactionByDate = mapTransactionDate.getMapTransactionByDate("").transactionDate

  //mapTransactionByDate.foreach(println)

  assert(mapTransactionByDate.keySet == Set("20170101"))
}

  test("write a ocument"){

    val file1: File = "C:\\Formations\\data\\output\\file1.txt"
      .toFile
      .createIfNotExists()
    var map: Map[Long, Double] = Map()
    map += (1L -> 1.1)
    map += (2L -> 2.1)

    map.toSeq


  /*  map.foreach(x => {
      val line = x._1 + ";"+ x._2
      file1.appendLine(line)
    }) */
  }
}


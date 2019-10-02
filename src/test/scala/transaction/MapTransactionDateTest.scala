package transaction


import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{BeforeAndAfter, FunSuite}
class MapTransactionDateTest extends FunSuite  with MockitoSugar with BeforeAndAfter{
  type Date = String
  type  Transactions = List[String]
  var listTransction = List.empty[(Date, Transactions)]
  val mapTransactionDate: MapTransactionDate = mock[MapTransactionDate]
  val listTransactions = List(Transaction(1,"20170514T223544+0100","2a4b6b81-5aa2-4ad8-8ba9-ae1a006e7d71",531,5),
    Transaction(1,"20170514T224008+0100","bdc2a431-797d-4b07-9567-67c565a67b84",55,3))
    listTransction =
      List(("20170101", List("1|20170514T223544+0100|2a4b6b81-5aa2-4ad8-8ba9-ae1a006e7d71|531|5","1|20170514T224008+0100|bdc2a431-797d-4b07-9567-67c565a67b84|55|3")))
    when(mapTransactionDate.listTransactionsByDate("")).thenReturn(listTransction )
    when(mapTransactionDate.getMapTransactionByDate("")).thenCallRealMethod()



  test("test the transactions list By Date"){
    val transactions : Transactions = List("1|20170514T223544+0100|2a4b6b81-5aa2-4ad8-8ba9-ae1a006e7d71|531|5", "1|20170514T224008+0100|bdc2a431-797d-4b07-9567-67c565a67b84|55|3")
    val date : Date = "20170101"
    val listTransactionsByDate: List[(Date, Transactions)] = List((date,transactions))
    assert(mapTransactionDate.listTransactionsByDate("").size === 1)
    assert(mapTransactionDate.listTransactionsByDate("") === listTransactionsByDate)
  }

  test("test map Transaction by Date") {

  val mapTransactionByDate = mapTransactionDate.getMapTransactionByDate("").transactionDate
    assert(mapTransactionByDate.keySet == Set("20170101"))
    assert(mapTransactionByDate.getOrElse("20170101", List.empty[Transaction]).size == 2)
    assert(mapTransactionByDate.getOrElse("20170101", List.empty[Transaction]) == listTransactions)
}



}


package calculated

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import org.scalatest.FunSuite
import org.scalatest.mockito.MockitoSugar

class OutputTest extends  FunSuite with MockitoSugar{

  new TransactionCalculated ("magasin : String",1,2, 1)

  val listTransaction : List[TransactionCalculated] = List(
     TransactionCalculated ("magasin1",1,2, 1)
    , TransactionCalculated ("magasin2",1,2, 1)
  )

  val OutputData = new OutputData("", "")

 // val outputData =  new OutputData("","")

  test("get the"){

    println(OutputData.isInLast7Days("20191003"))


  }

  def isInLast7Days( date : String) : Boolean ={
    val pattern = DateTimeFormatter.ofPattern("yyyyMMdd")
    try
    {
      LocalDate.parse(date, pattern).isAfter(LocalDate.now().minusDays(7))
    }catch {
      case exception: Exception =>
        false
    }
  }

}

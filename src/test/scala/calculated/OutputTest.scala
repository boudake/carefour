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

  val OutputData = mock[OutputData]

 // val outputData =  new OutputData("","")

  test("get the"){

    println(OutputData.isInLast7Days("20191007"))

  }


}

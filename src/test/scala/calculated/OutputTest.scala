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


  }

test ( "thes the function getTopN"){
  val testData = List(("P1",200), ("P2",300),("P5",900),("P7",10))
  val testData1 = List(("P1",200.23), ("P2",300.53),("P5",900.34),("P7",10.4545))
  assert(Set(("P2",300), ("P5",900))=== OutputData.topN(testData,2))
  assert(Set(("P2",300.53), ("P5",900.34))=== OutputData.topN(testData1,2))

}


}

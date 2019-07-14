import java.io.File

import org.mockito.Mockito._
import org.scalatest.{FlatSpec, FunSpec}
import org.scalatest.mockito.MockitoSugar

class test extends FlatSpec with MockitoSugar  {

  var map: Map[Long, Double] = Map()


  "A stuff" should "write stuff to db" in {
    map += (1L -> 1.1)
    map += (2L -> 2.1)
    val seqValue = Seq(("20140201", "id", map))

    val m : MyTest= mock[MyTest]








  }
}

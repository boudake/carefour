import calculated.OutputData

object launcher {

  def main(args: Array[String]): Unit ={

    val pathIn =  "C:\\Formations\\data\\carefour\\"//args(0)
    val pathOut = "C:\\Formations\\data\\output"//args(1)

    val outputData = new OutputData(pathIn,pathOut )

    outputData.getTop_N_ventes_GLOBAL_YYYYMMDD(10).foreach(println)
   // outputData.getTop_N_ca_ventes_GLOBAL_YYYYMMDD(10).foreach(println)
  //  outputData.getTop_N_ventes_MAGASIN_YYYYMMDD(10).foreach(println)
   // outputData.getTop_ca_N_ventes_MAGASIN_YYYYMMDD(10).foreach(println)
    /*outputData.getTop_100_ventes_GLOBAL_YYYYMMDD
    outputData.getTop_100_ventes_MAGASIN_YYYYMMDD
    outputData.getTop_100_ca_ventes_GLOBAL_YYYYMMDD
    outputData.getTop_ca_100_ventes_MAGASIN_YYYYMMDD
    outputData.getTop_ca_100_ventes_MAGASIN_YYYYMMDD_J7()
*/
  }


}

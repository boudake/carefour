import calculated.OutputData

object launcher {

  def main(args: Array[String]): Unit ={

    val pathIn =  args(0)
    val pathOut = args(1)

    val outputData = new OutputData(pathIn,pathOut )
    outputData.getTop_100_ventes_GLOBAL_YYYYMMDD
    outputData.getTop_100_ventes_MAGASIN_YYYYMMDD
    outputData.getTop_100_ca_ventes_GLOBAL_YYYYMMDD
    outputData.getTop_ca_100_ventes_MAGASIN_YYYYMMDD
    outputData.getTop_ca_100_ventes_MAGASIN_YYYYMMDD_J7()

  }


}

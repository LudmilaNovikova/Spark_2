import java.util.concurrent.ThreadLocalRandom

import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.{SparkConf, SparkContext}

object MLlibTest extends App {
  def makeData(points: Int, min: Int, max: Int) = {
    for (i <- 0 until points)
    yield (
      ThreadLocalRandom.current().nextDouble(min, max + 1),
      ThreadLocalRandom.current().nextDouble(min, max + 1)
      )
  }

  def makeCheckData(points: Int) = {
    for (i <- 1 until points)
    yield (
      i.toDouble,
      i.toDouble
      )
  }

  val conf = new SparkConf().setAppName("KMeans application")
  conf.setMaster("local[2]")
  val sc = new SparkContext(conf)

  try{
    val testData = makeData(500, 0, 100)
//    val testData = makeCheckData(6)
    testData take 6 foreach println

    val vectors = testData.map(s => Vectors.dense(Array(s._1, s._2)))

    val vectorsRDD = sc.parallelize(vectors).cache()
    val clusters = KMeans.train(vectorsRDD, 2, 20)

    clusters.clusterCenters.foreach(println(_))

  }finally {
    sc.stop()
  }

}

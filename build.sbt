name := "Spark_2"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-core_2.10" % "1.5.2",
  "org.apache.spark" % "spark-mllib_2.10" % "1.5.2",
  "javax.servlet" % "javax.servlet-api" % "3.0.1"
)

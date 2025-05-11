import java.io._

import DSLFiles._

object collatzFiles {
  def main(args: Array[String]): Unit = {
    val file = "temp.txt"

    writeFile(file) {
      Iterator.iterate(5) {
        case x if x % 2 == 0 => x / 2
        case x => 3 * x + 1
      }
      .takeWhile(_ != 1)
      .foreach(println)
    }

    appendFile(file) {
      println("...I almost forgot")
      println(1)
    }

    readFile(file) {
      Iterator.continually(scala.io.StdIn.readLine())
      .takeWhile(_ != null)
      .filter(line => line.trim.nonEmpty && !line.exists(_.isLetter))
      .foreach(println)
    }
  }
}

import java.io._
import scala.Console

def readFile(fileName: String)(block: => Unit): Unit = {
  var fis: FileInputStream = null
  try Console.withIn({ fis = new FileInputStream(fileName); fis })(block)
  catch { case e: IOException => Console.err.println(s"Read error '$fileName': ${e.getMessage}") }
  finally if (fis != null) fis.close()
}

def writeFile(fileName: String)(block: => Unit): Unit = {
  var fos: FileOutputStream = null
  try Console.withOut({ fos = new FileOutputStream(fileName, false); fos })(block)
  catch { case e: IOException => Console.err.println(s"Write error '$fileName': ${e.getMessage}") }
  finally if (fos != null) fos.close()
}

def appendFile(fileName: String)(block: => Unit): Unit = {
  var fos: FileOutputStream = null
  try Console.withOut({ fos = new FileOutputStream(fileName, true); fos })(block)
  catch { case e: IOException => Console.err.println(s"Append error '$fileName': ${e.getMessage}") }
  finally if (fos != null) fos.close()
}

object Main {
  def main(args: Array[String]): Unit = {
    val file = "temp.txt"

    writeFile(file) {
      Iterator.iterate(5) {
        case 1 => 1
        case x if x % 2 == 0 => x / 2
        case x => 3 * x + 1
      }
      .takeWhile(_ != 1)
      .foreach(println)
    }

    appendFile(file) {
      println(1)
    }

    readFile(file) {
      Iterator.continually(scala.io.StdIn.readLine())
      .takeWhile(_ != null)
      .foreach(println)
    }
  }
}

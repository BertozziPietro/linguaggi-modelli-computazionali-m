import java.io.{FileInputStream, FileOutputStream, IOException}

object DSLFiles {

  def readFile(fileName: String)(block: => Unit): Unit = {
    var fis: FileInputStream = null
    try { fis = new FileInputStream(fileName); Console.withIn(fis)(block) } 
    catch { case e: IOException => Console.err.println(s"Read error '$fileName': ${e.getMessage}") } 
    finally { if (fis != null) fis.close() }
  }

  def writeFile(fileName: String)(block: => Unit): Unit = {
    var fos: FileOutputStream = null
    try { fos = new FileOutputStream(fileName, false); Console.withOut(fos)(block) } 
    catch { case e: IOException => Console.err.println(s"Write error '$fileName': ${e.getMessage}") } 
    finally { if (fos != null) fos.close() }
  }

  def appendFile(fileName: String)(block: => Unit): Unit = {
    var fos: FileOutputStream = null
    try { fos = new FileOutputStream(fileName, true); Console.withOut(fos)(block) } 
    catch { case e: IOException => Console.err.println(s"Append error '$fileName': ${e.getMessage}") } 
    finally { if (fos != null) fos.close() }
  }
}

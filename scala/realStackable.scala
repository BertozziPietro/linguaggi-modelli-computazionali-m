import DSLFiles._

abstract class BehaviourStacker[T] {
  var stackedBehaviour: List[() => Unit] = List()
  def addBehaviour(input: T): Unit = {}
  def resetBehaviour(): Unit = stackedBehaviour = List()
  def executeBehaviour(): Unit = stackedBehaviour.foreach(_())
}

trait ConsoleLogger extends BehaviourStacker[String] {
  override def addBehaviour(input: String): Unit = {
    super.addBehaviour(input)
    stackedBehaviour :+= (() => println(s"Console log: $input"))
  }
}

trait FileLogger extends BehaviourStacker[String] {
  override def addBehaviour(input: String): Unit = {
    super.addBehaviour(input)
    stackedBehaviour :+= (() => 
      appendFile("log.txt") {
        println(s"File log: $input")
      })
  }
}

/*
trait ConsoleLogger[T] extends BehaviourStacker[T] {
  override def addBehaviour(input: T): Unit = {
    super.addBehaviour(input)
    stackedBehaviour :+= (() => println(s"Console log: ${input}"))
  }
}*/

/*
trait DatabaseLogger[T] extends BehaviourStacker[T] {
  override def addBehaviour(input: T): Unit = {
    super.addBehaviour(input)
    stackedBehaviour :+= (() => println(s"Database log: ${input}"))
  }
}
*/
class MyLogger() extends ConsoleLogger/*[String]*/ with FileLogger {
  def log(message: String): Unit = {
    resetBehaviour()
    addBehaviour(message)
    executeBehaviour()
  }
}

object traits {
  def main(args: Array[String]): Unit = {
    val logger = new MyLogger()
    logger.log("Scala stuff's done.")
    logger.log("Time for some JavaScript.")
  }
}

package dev.zerosum.invokergame

class Invoker {

  private var elements: Elements = Elements(Void, Void, Void)
  private var primarySpell: Option[Spell] = None
  private var secondarySpell: Option[Spell] = None

  val cast: Char => Unit = {
    case 'q' => castElement(Quas)
    case 'w' => castElement(Wex)
    case 'e' => castElement(Exort)
    case 'r' => invoke()
    case 'd' => castPrimarySpell()
    case 'f' => castSecondarySpell()
  }

  private def castElement(e: Element): Unit = {
    elements = elements.add(e)
    println(s"Cast [$e]")
  }

  private def invoke(): Unit = {
    secondarySpell = primarySpell
    primarySpell = Option(Spell.invokedWith(elements))
    primarySpell.fold(println("Nothing has been invoked"))(spell => println(s"Invoke `$spell`"))
  }

  private def castPrimarySpell(): Unit = {
    primarySpell.fold(println("Nothing has been casted"))(spell => println(s"Cast `$spell`"))
  }

  private def castSecondarySpell(): Unit = {
    secondarySpell.fold(println("Nothing has been casted"))(spell => println(s"Cast `$spell`"))
  }
}

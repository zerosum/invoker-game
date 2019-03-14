package dev.zerosum.invokergame

sealed trait Element {
  val degree: Int

  override def toString: String = this.getClass.getSimpleName.head.toString
}

case object Quas extends Element {
  override val degree: Int = 3
}

case object Wex extends Element {
  override val degree: Int = 2
}

case object Exort extends Element {
  override val degree: Int = 1
}

case object Void extends Element {
  override val degree: Int = 0
}

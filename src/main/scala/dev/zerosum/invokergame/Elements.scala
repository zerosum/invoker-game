package dev.zerosum.invokergame

case class Elements(s1: Element, s2: Element, s3: Element) {

  def add(e: Element): Elements = Elements(this.s2, this.s3, e)

  private def eval: Int = Vector(s1, s2, s3).map(e => Math.pow(4, e.degree)).sum.toInt

  override def equals(obj: Any): Boolean = {
    obj.isInstanceOf[Elements] &&
      (obj.asInstanceOf[Elements].eval == this.eval)
  }

  override def toString: String = s"[$s1][$s2][$s3]"
}

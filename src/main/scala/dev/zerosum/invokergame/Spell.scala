package dev.zerosum.invokergame

import dev.zerosum.invokergame.{Exort => E, Quas => Q, Wex => W}

sealed trait Spell {
  val elems: Elements
}

object Spell {
  def invokedWith(elements: Elements): Spell = {
    type PF = PartialFunction[Elements, Spell]
    Vector(ColdSnap, GhostWalk, IceWall, EMP, Tornado, Alacrity, SunStrike, ForgeSpirit, ChaosMeteor, DeafeningBlast)
      .map { spell =>
        val pf: PF = { case es if es == spell.elems => spell }
        pf
      }
      .foldRight[PF]({ case _ => null })(_ orElse _)(elements)
  }
}

case object ColdSnap extends Spell {
  override val elems = Elements(Q, Q, Q)

  override def toString: String = "Cold Snap"
}

case object GhostWalk extends Spell {
  override val elems = Elements(Q, Q, W)

  override def toString: String = "Ghost Walk"
}

case object IceWall extends Spell {
  override val elems = Elements(Q, Q, E)

  override def toString: String = "Ice Wall"
}

case object EMP extends Spell {
  override val elems = Elements(W, W, W)

  override def toString: String = "EMP"
}

case object Tornado extends Spell {
  override val elems = Elements(W, W, Q)

  override def toString: String = "Tornado"
}

case object Alacrity extends Spell {
  override val elems = Elements(W, W, E)

  override def toString: String = "Alacrity"
}

case object SunStrike extends Spell {
  override val elems = Elements(E, E, E)

  override def toString: String = "Sun Strike"
}

case object ForgeSpirit extends Spell {
  override val elems = Elements(E, E, Q)

  override def toString: String = "Forge Spirit"
}

case object ChaosMeteor extends Spell {
  override val elems = Elements(E, E, W)

  override def toString: String = "Chaos Meteor"
}

case object DeafeningBlast extends Spell {
  override val elems = Elements(Q, W, E)

  override def toString: String = "Deafening Blast"
}

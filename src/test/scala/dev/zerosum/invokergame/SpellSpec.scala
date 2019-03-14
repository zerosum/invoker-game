package dev.zerosum.invokergame

import org.scalatest.FlatSpec
import dev.zerosum.invokergame.{Quas => Q, Wex => W, Exort => E, Void => V}

class SpellSpec extends FlatSpec {

  def assertSpell(expected: Spell): Elements => Unit = { elems =>
    it should s"be invoked with $elems" in {
      val actual = Spell.invokedWith(elems)
      assert(actual == expected)
    }
  }

  "ColdSnap" should ""
  Vector(Elements(Q, Q, Q)) foreach assertSpell(ColdSnap)

  "GhostWalk" should ""
  Vector(Elements(Q, Q, W), Elements(Q, W, Q), Elements(W, Q, Q)) foreach assertSpell(GhostWalk)

  "IceWall" should ""
  Vector(Elements(Q, Q, E), Elements(Q, E, Q), Elements(E, Q, Q)) foreach assertSpell(IceWall)

  "EMP" should ""
  Vector(Elements(W, W, W)) foreach assertSpell(EMP)

  "Tornado" should ""
  Vector(Elements(W, W, Q), Elements(W, Q, W), Elements(Q, W, W)) foreach assertSpell(Tornado)

  "Alacrity" should ""
  Vector(Elements(W, W, E), Elements(W, E, W), Elements(E, W, W)) foreach assertSpell(Alacrity)

  "SunStrike" should ""
  Vector(Elements(E, E, E)) foreach assertSpell(SunStrike)

  "ForgeSpirit" should ""
  Vector(Elements(E, E, Q), Elements(E, Q, E), Elements(Q, E, E)) foreach assertSpell(ForgeSpirit)

  "ChaosMeteor" should ""
  Vector(Elements(E, E, W), Elements(E, W, E), Elements(W, E, E)) foreach assertSpell(ChaosMeteor)

  "DeafeningBlast" should ""
  Vector(Elements(Q, W, E)) foreach assertSpell(DeafeningBlast)

  "Any Spell" should ""
  Vector(
    List(V, V, V), List(V, V, Q), List(V, V, W), List(V, V, E), List(V, Q, Q),
    List(V, W, W), List(V, E, E), List(V, Q, W), List(V, W, E), List(V, E, Q)
  ).flatMap(_.permutations.toList).foreach { case s1 :: s2 :: s3 :: Nil =>
    val elems = Elements(s1, s2, s3)
    it should s"not be invoked with $elems" in {
      assert(Spell.invokedWith(elems) == null)
    }
  }
}

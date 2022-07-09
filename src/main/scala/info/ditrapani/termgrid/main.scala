package info.ditrapani.termgrid

import org.jline.terminal.TerminalBuilder
import org.jline.keymap.{BindingReader, KeyMap}
import KeyMap.{ctrl, esc, key}
import org.jline.utils.InfoCmp.Capability
import zio.ZIO
import zio.Console

sealed trait Action extends Key[Action]
case class Quit(termGrid: ITermGrid) extends Action:
  val keys: List[String] = List("Q", "q", ctrl('c').nn, esc.nn)
  def convert: Action = this
case class Space(termGrid: ITermGrid) extends Action:
  val keys: List[String] = List(" ")
  def convert: Action = this
case class Up(termGrid: ITermGrid) extends Action:
  val keys: List[String] = List(key(termGrid.terminal, Capability.cursor_up).nn, "k")
  def convert: Action = this
case class Down(termGrid: ITermGrid) extends Action:
  val keys: List[String] = List(key(termGrid.terminal, Capability.cursor_down).nn, "j")
  def convert: Action = this

object Main extends zio.ZIOAppDefault:

  def run =
    for
      termGrid <- newTermGrid(64, 40)
      _ <- repl(List(Quit(termGrid), Space(termGrid), Up(termGrid), Down(termGrid)), termGrid) {
        action =>
          Console.printLine(action).orDie
      }
    yield (): Unit

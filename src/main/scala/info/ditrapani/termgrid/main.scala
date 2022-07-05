package info.ditrapani.termgrid

import org.jline.terminal.TerminalBuilder
import org.jline.keymap.{BindingReader, KeyMap}
import org.jline.keymap.KeyMap.{ctrl, esc, key}
import org.jline.utils.InfoCmp.Capability

@main def main: Unit =

  enum Action:
    case Quit
    case Space
    case Up
    case Down

  val s: String = ctrl('c').nn
  println(s"s.length = ${s.length}")
  println(s.map(x => Integer.toHexString(x.toInt)).toList)
  val keyMap = new KeyMap[Action]()
  val terminal = TerminalBuilder.terminal().nn
  keyMap.bind(Action.Quit, "Q", "q", ctrl('c'), esc)
  keyMap.bind(Action.Space, " ")
  keyMap.bind(Action.Up, key(terminal, Capability.cursor_up), "k")
  keyMap.bind(Action.Down, key(terminal, Capability.cursor_down), "j")
  terminal.enterRawMode()
  val reader = terminal.reader().nn
  val bindingReader = new BindingReader(terminal.reader())

  println("Term-grid!\n")
  println(reader.read())
  val action = bindingReader.readBinding(keyMap)
  println(action)

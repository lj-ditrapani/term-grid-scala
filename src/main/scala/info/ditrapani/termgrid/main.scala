package info.ditrapani.termgrid

import org.jline.terminal.TerminalBuilder
import org.jline.keymap.{BindingReader, KeyMap}
import org.jline.keymap.KeyMap.ctrl

@main def main: Unit =
  println("Term-grid!\n")
  val terminal = TerminalBuilder.terminal().nn
  terminal.enterRawMode()
  val reader = terminal.reader().nn
  val keyReader = new BindingReader(terminal.reader())

  enum Action:
    case QUIT
    case SPACE

  val keyMap = new KeyMap[Action]()
  keyMap.bind(Action.QUIT, "Q", "q", ctrl('c'))
  keyMap.bind(Action.SPACE, " ")
  val action = keyReader.readBinding(keyMap, null, true)
  println(action)

  println(reader.peek(500))
  val c = reader.read()
  println(c)

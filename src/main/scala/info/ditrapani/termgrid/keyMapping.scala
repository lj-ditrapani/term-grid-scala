package info.ditrapani.termgrid

import org.jline.keymap.KeyMap
import KeyMap.{ctrl, esc, key}
import org.jline.terminal.Terminal
import org.jline.utils.InfoCmp.Capability

def makeKeyMapping(terminal: Terminal): KeyMap[Key] =
  import AsciiPrintable.*
  import Other.*
  val keyMap = new KeyMap[Key]()
  keyMap.bind(A, "A")
  keyMap.bind(Q, "Q")
  keyMap.bind(j, "j")
  keyMap.bind(k, "k")
  keyMap.bind(q, "q")
  keyMap.bind(CtrlC, ctrl('c'))
  keyMap.bind(Esc, esc)
  keyMap.bind(Space, " ")
  keyMap.bind(ArrowUp, key(terminal, Capability.cursor_up))
  keyMap.bind(ArrowDown, key(terminal, Capability.cursor_down))
  keyMap

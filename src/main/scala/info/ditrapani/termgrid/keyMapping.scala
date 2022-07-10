package info.ditrapani.termgrid

import org.jline.keymap.KeyMap
import KeyMap.{ctrl, esc, key}
import org.jline.terminal.Terminal
import org.jline.utils.InfoCmp.Capability

def makeKeyMapping(terminal: Terminal): KeyMap[Key] =
  import Alpha.*
  import Other.*
  val keyMap = new KeyMap[Key]()

  new {
    val pairs: List[(Key, String)] =
      Alpha.values.toList.map { key => (key, key.toString) }
    pairs.foreach { case (key, string) =>
      keyMap.bind(key, string)
    }
  }

  new {
    val pairs: List[(Key, String)] =
      Number.values.toList.map { key => (key, key.ordinal.toString) }
    pairs.foreach { case (key, string) =>
      keyMap.bind(key, string)
    }
  }

  keyMap.bind(Space, " ")
  keyMap.bind(CtrlC, ctrl('c'))
  keyMap.bind(Esc, esc)
  keyMap.bind(ArrowUp, key(terminal, Capability.cursor_up))
  // down and left map to B and D !!!  Or nothing, if B & D are not mapped
  keyMap.bind(ArrowDown, key(terminal, Capability.cursor_down))
  keyMap.bind(ArrowLeft, key(terminal, Capability.cursor_left))
  keyMap.bind(ArrowRight, key(terminal, Capability.cursor_right))

  keyMap

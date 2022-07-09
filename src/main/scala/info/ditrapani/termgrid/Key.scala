package info.ditrapani.termgrid

sealed trait Key

enum AsciiPrintable extends Key:
  case A
  case B
  case C
  case J
  case K
  case Q
  case a
  case b
  case c
  case j
  case k
  case q

enum Other extends Key:
  case Esc
  case CtrlC
  case Space
  case ArrowUp
  case ArrowDown

package info.ditrapani.termgrid

sealed trait Key

enum Alpha extends Key:
  case A
  case B
  case C
  case D
  case E
  case F
  case G
  case H
  case I
  case J
  case K
  case L
  case M
  case N
  case O
  case P
  case Q
  case R
  case S
  case T
  case U
  case V
  case W
  case X
  case Y
  case Z
  case a
  case b
  case c
  case d
  case e
  case f
  case g
  case h
  case i
  case j
  case k
  case l
  case m
  case n
  case o
  case p
  case q
  case r
  case s
  case t
  case u
  case v
  case w
  case x
  case y
  case z

enum Number extends Key:
  case N0
  case N1
  case N2
  case N3
  case N4
  case N5
  case N6
  case N7
  case N8
  case N9

enum Other extends Key:
  case Esc
  case CtrlC
  case Space
  case ArrowUp
  case ArrowDown
  case ArrowLeft
  case ArrowRight

package info.ditrapani.termgrid

import org.jline.terminal.TerminalBuilder

@main def main: Unit =
  println("Term-grid!\n")
  val terminal = TerminalBuilder.terminal().nn
  terminal.enterRawMode()
  val reader = terminal.reader()

  val c = reader.nn.read()
  println(c)

package info.ditrapani.termgrid

trait ITermGrid:
  def clear(): Unit
  def draw(): Unit
  def onInput(handler: String => Unit): Unit
  def reset(): Unit
  def set(y: Int, x: Int, char: Char, fg: Int, bg: Int): Unit
  def textk(y: Int, x: Int, text: String, fg: Int, bg: Int): Unit

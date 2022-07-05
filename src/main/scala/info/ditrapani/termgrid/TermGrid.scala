package info.ditrapani.termgrid

import org.jline.terminal.Terminal
import zio.{Task, Queue, UIO}

trait ITermGrid:
  def clear(): UIO[Unit]
  def draw(): UIO[Unit]
  def reset(): UIO[Unit]
  def set(y: Int, x: Int, char: Char, fg: Int, bg: Int): UIO[Unit]
  def textk(y: Int, x: Int, text: String, fg: Int, bg: Int): UIO[Unit]
  def terminal: Terminal

trait Action[T]:
  val keys: List[String]
  def convert: T

def newTermGrid(height: Int, width: Int): Task[ITermGrid] = ???
def inputLoop[T](actions: List[Action[T]], eventQueue: Queue[T], termGrid: ITermGrid): Task[Unit] =
  ???
def repl[T](actions: List[Action[T]], termGrid: ITermGrid)(logic: T => UIO[Unit]): UIO[Unit] = ???

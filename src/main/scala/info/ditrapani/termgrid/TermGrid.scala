package info.ditrapani.termgrid

import org.jline.terminal.{Terminal, TerminalBuilder}
import zio.{Task, Queue, Schedule, UIO, ZIO}

trait ITermGrid:
  def clear(): UIO[Unit]
  def draw(): UIO[Unit]
  def reset(): UIO[Unit]
  def set(y: Int, x: Int, char: Char, fg: Int, bg: Int): UIO[Unit]
  def textk(y: Int, x: Int, text: String, fg: Int, bg: Int): UIO[Unit]
  def terminal: Terminal

class TermGrid(height: Int, width: Int, override val terminal: Terminal) extends ITermGrid:
  def clear(): UIO[Unit] = ???
  def draw(): UIO[Unit] = ???
  def reset(): UIO[Unit] = ???
  def set(y: Int, x: Int, char: Char, fg: Int, bg: Int): UIO[Unit] = ???
  def textk(y: Int, x: Int, text: String, fg: Int, bg: Int): UIO[Unit] = ???

def newTermGrid(height: Int, width: Int): UIO[ITermGrid] =
  require(height >= 1, "Height must be positive.")
  require(width >= 1, "Width must be positive.")
  ZIO.attemptBlocking {
    val terminal = TerminalBuilder.terminal().nn
    TermGrid(height, width, terminal)
  }.orDie

def inputLoop[T](eventQueue: Queue[T], termGrid: ITermGrid)(convert: Int => T): UIO[Unit] =
  val terminal = termGrid.terminal
  ZIO
    .attemptBlocking {
      terminal.enterRawMode()
    }
    .orDie
    .flatMap { _ =>
      import org.jline.utils.NonBlockingReader
      val reader: NonBlockingReader = terminal.reader().nn
      val operation: UIO[Unit] = {
        for
          keyCode <- ZIO.attemptBlocking { reader.read() }.orDie
          _ <- eventQueue.offer(convert(keyCode))
        yield (): Unit
      }
      operation.repeat(Schedule.forever).map(_ => (): Unit)
    }

def repl(termGrid: ITermGrid)(logic: Int => UIO[Unit]): UIO[Unit] =
  val terminal = termGrid.terminal
  ZIO
    .attemptBlocking {
      terminal.enterRawMode()
    }
    .orDie
    .flatMap { _ =>
      val reader = terminal.reader().nn
      val operation: UIO[Unit] = {
        for
          keyCode <- ZIO.attemptBlocking { reader.read() }.orDie
          _ <- logic(keyCode)
        yield (): Unit
      }
      // TODO: change to recurUntil; and need continue function?
      operation.repeat(Schedule.recurs(5)).map(_ => (): Unit)
    }

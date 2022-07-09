package info.ditrapani.termgrid

import org.jline.keymap.{BindingReader, KeyMap}
import KeyMap.{ctrl, esc, key}
import org.jline.terminal.{Terminal, TerminalBuilder}
import org.jline.utils.InfoCmp.Capability
import zio.{Task, Queue, Schedule, UIO, ZIO}

trait ITermGrid:
  def clear(): UIO[Unit]
  def draw(): UIO[Unit]
  def reset(): UIO[Unit]
  def set(y: Int, x: Int, char: Char, fg: Int, bg: Int): UIO[Unit]
  def textk(y: Int, x: Int, text: String, fg: Int, bg: Int): UIO[Unit]
  def terminal: Terminal

trait Key[T]:
  val keys: List[String]
  def convert: T

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

def inputLoop[T](actions: List[Key[T]], eventQueue: Queue[T], termGrid: ITermGrid): UIO[Unit] =
  val keyMap = createKeyMap(actions)
  val terminal = termGrid.terminal
  ZIO
    .attemptBlocking {
      terminal.enterRawMode()
    }
    .orDie
    .flatMap { _ =>
      val bindingReader = new BindingReader(terminal.reader())
      val operation: UIO[Unit] = {
        for
          action <- ZIO.attemptBlocking { bindingReader.readBinding(keyMap).nn }.orDie
          _ <- eventQueue.offer(action)
        yield (): Unit
      }
      operation.repeat(Schedule.forever).map(_ => (): Unit)
    }

def repl[T](actions: List[Key[T]], termGrid: ITermGrid)(logic: T => UIO[Unit]): UIO[Unit] =
  val keyMap = createKeyMap(actions)
  val terminal = termGrid.terminal
  ZIO
    .attemptBlocking {
      terminal.enterRawMode()
    }
    .orDie
    .flatMap { _ =>
      val bindingReader = new BindingReader(terminal.reader())
      val operation: UIO[Unit] = {
        for
          action <- ZIO.attemptBlocking { bindingReader.readBinding(keyMap).nn }.orDie
          _ <- logic(action)
        yield (): Unit
      }
      // TODO: change to recurUntil; and need continue function?
      operation.repeat(Schedule.recurs(5)).map(_ => (): Unit)
    }

private def createKeyMap[T](actions: List[Key[T]]): KeyMap[T] =
  val keyMap = new KeyMap[T]
  actions.foreach { action =>
    keyMap.bind(action.convert, action.keys*)
  }
  keyMap

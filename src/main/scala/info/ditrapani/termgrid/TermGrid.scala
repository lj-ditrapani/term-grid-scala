package info.ditrapani.termgrid

import org.jline.keymap.{BindingReader, KeyMap}
import org.jline.terminal.{Terminal, TerminalBuilder}
import zio.{Ref, Queue, Task, Schedule, UIO, ZIO}

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

/** Waits for keypresses and puts the Key on the queue after transforming it into a T
  *
  * @param loopControlRef
  *   The input loop will shutdown when this is Stop. The initial value should be Loop. The
  *   application code should set the ref to Stop when input is no longer needed or app is ready to
  *   shutdown.
  * @param eventQueue
  *   The loop will convert each Key into a T and offer it to this Queue for the application to
  *   process at its leisure. The Queue is intended to service multiple event streams, hence why the
  *   application author gets to pick the T.
  * @param termGrid
  *   The instance of termGrid to listen for key presses on.
  *
  * Intended for multi event source apps, for single-event source use inputLoop instead. The
  * eventQueue is intended to be shared across mulitple event streams (like timers, websockets,
  * kafka, etc).
  */
def inputLoop[T](loopControlRef: Ref[LoopControl], eventQueue: Queue[T], termGrid: ITermGrid)(
    convert: Key => T,
): UIO[Unit] =
  val terminal = termGrid.terminal
  ZIO
    .attemptBlocking {
      terminal.enterRawMode()
    }
    .orDie
    .flatMap { _ =>
      import org.jline.utils.NonBlockingReader
      val keyMap = makeKeyMapping(terminal)
      val bindingReader = new BindingReader(terminal.reader())
      val operation: UIO[LoopControl] = {
        for
          key <- ZIO.attemptBlocking { bindingReader.readBinding(keyMap).nn }.orDie
          _ <- eventQueue.offer(convert(key))
          loopControl <- loopControlRef.get
        yield loopControl
      }
      operation.repeat(Schedule.recurUntil(_.toBool)).map(_ => (): Unit)
    }

/** Waits for keypresses and executes logic on each Key
  *
  * @param termGrid
  *   The instance of termGrid to listen for key presses on.
  *
  * @param logic
  *   The logic to execute on each key press. The return value of this function will be used to
  *   decide if the repl should loop again or stop.
  *
  * Intended for applications whose only event source is user key presses. For multi-event source
  * apps, use inputLoop instead. The eventQueue is intended to be shared across multiple event
  * streams (like timers, websockets messages, kafka records, etc).
  */
def repl(termGrid: ITermGrid)(logic: Key => UIO[LoopControl]): UIO[Unit] =
  val terminal = termGrid.terminal
  ZIO
    .attemptBlocking {
      terminal.enterRawMode()
    }
    .orDie
    .flatMap { _ =>
      val keyMap = makeKeyMapping(terminal)
      val bindingReader = new BindingReader(terminal.reader())
      val operation: UIO[LoopControl] = {
        for
          keyCode <- ZIO.attemptBlocking { bindingReader.readBinding(keyMap).nn }.orDie
          loopControl <- logic(keyCode)
        yield loopControl
      }
      // TODO: change to recurUntil; and need continue function?
      operation.repeat(Schedule.recurUntil(_.toBool)).map(_ => (): Unit)
    }

enum LoopControl(val toBool: Boolean):
  case Loop extends LoopControl(false)
  case Stop extends LoopControl(true)

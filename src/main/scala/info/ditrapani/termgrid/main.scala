package info.ditrapani.termgrid

import zio.ZIO
import zio.Console

object Main extends zio.ZIOAppDefault:

  def run =
    for
      termGrid <- newTermGrid(64, 40)
      _ <- repl(termGrid) { key =>
        Console.printLine(key).orDie.map { _ =>
          key match
            case Alpha.q | Alpha.Q | Other.Esc => LoopControl.Stop
            case _ => LoopControl.Loop
        }
      }
    yield (): Unit

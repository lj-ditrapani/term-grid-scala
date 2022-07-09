package info.ditrapani.termgrid

import zio.ZIO
import zio.Console

object Main extends zio.ZIOAppDefault:

  def run =
    for
      termGrid <- newTermGrid(64, 40)
      _ <- repl(termGrid) { keyCode =>
        Console.printLine(keyCode).orDie
      }
    yield (): Unit

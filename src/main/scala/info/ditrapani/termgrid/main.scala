package info.ditrapani.termgrid

import colors.b
import zio.ZIO
import zio.Console

object Main extends zio.ZIOAppDefault:
  def run =
    for
      termGrid <- newTermGrid(10, 10)
      _ <- termGrid.clear()
      _ <- termGrid.draw()
      _ <- repl(termGrid) { key =>
        for
          _ <- termGrid.text(3, 5, key.toString, 5, 9)
          _ <- termGrid.draw()
        yield {
          key match
            case Alpha.q | Alpha.Q | Other.Esc => LoopControl.Stop
            case _ => LoopControl.Loop
        }
      }
      _ <- termGrid.reset()
    yield (): Unit

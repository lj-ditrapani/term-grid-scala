package info.ditrapani.termgrid

import colors.b
import zio.ZIO
import zio.Console

object Main extends zio.ZIOAppDefault:
  def run =
    val sb = new StringBuilder(10)
    sb.append("\u001b[35m\u2506--hi--\u2507\u001b[0m")
    // sb.insert(
    println("1111".b)
    System.out.nn.append(sb)
    println(sb.length)
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

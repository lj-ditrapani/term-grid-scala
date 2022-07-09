term-grid-ui for scala zio
==========================

Developing
----------

Develop:

Start sbt console with `sbt`.
From sbt console you can run these useful commands while you develop:

    compile
    test
    run     # this is broken; stage instead
    fmt

Deploying:

Stage a package that you can run
directly with `target/universal/stage/bin/<project>`

    sbt stage

Generate a zip artifact
packaged here: `target/universal/<project>-<version>.zip`

    sbt Universal / packageBin

Dependencies:

check for outdated dependencies

    sbt dependencyUpdates


Design Idea
-----------

```
trait Action[T]:
    val keys: List[String]
    def convert: T

newTermGrid(): Task[TermGrid]
inputLoop[T](actions: List[Action[T]], eventQueue: ZioQueue[T]): Task[Unit]
```

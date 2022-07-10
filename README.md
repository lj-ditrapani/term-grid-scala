term-grid-ui for scala zio
==========================

Provides terminal I/O for interactive command line scala zio apps.
- Input: Knocks the terminal into raw mode so each key press can be detected
  and processed instead of waiting for the user to press enter.
- Output: Simple 2D color character grid abstraction for terminal apps.


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

Check for outdated dependencies

    sbt dependencyUpdates

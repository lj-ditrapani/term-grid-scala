https://stackoverflow.com/questions/4531791/execute-stty-raw-command-in-same-terminal
https://groups.google.com/g/comp.lang.java.programmer/c/rXLnl-Lw1sc?pli=1
http://www.javawenti.com/?post=2963
https://developer.mozilla.org/en-US/docs/Web/API/WebSockets_API/Writing_WebSocket_client_applications

- Keyboard input options:
    - Raw user controlled.  User gets individual bytes.  Do what you want with it.
      Can provide some variables with key codes like in term-grid-node version.
        - it's never wrong.  max flexibility.  Haven't tried it yet.
        - get rid of jline's KeyMay and Bindings
    - Use single KeyMap for life of app
        - simple.  works,  now
        - not fleixible
        - if you want FSM: you would have 2 layers
            - layer 1 (term-grid): define all possible key presses in life of app
            - layer 2 (user code): state machine that switches between states
              each state has set of valid keys.
    - Term-grid allows user to define FSM of Action sets
        - seems complicated
        - multiple queues?  weird
        - what type would the Action be?

- I think I want to get rid of the jline KeyMay/Bindings.
    - can I just read keypresses as stream of bytes?
- Also, need a way to signal completion to repl/inputLoop.

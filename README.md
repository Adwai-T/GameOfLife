# Game Of Life

Game of life implemented to run in command line. 

Given the size or matrix to play the game of life, the program generates a random first generation and then plays till inifinte generations.

If the code does not work as intended, that is if the `cmd` or `Powershell` is not cleared after every new generation then run the program in a console emulator like `cmder`.

Even if the cmd screen is not cleared, it will still print every sage one after the other. 

In the Main class the Thread.sleep(300) value can be changed to increase time between generations.

Performace of the game will depend on the computer, it is recommended to not use matrix size above 40 so it runs smoothly, also to keep all the lines of matrix on screen.

import model.Direction
import model.MazeRoom
import model.Player
import model.TriviaMaze
import java.awt.Color
import javax.swing.JFrame
import kotlin.system.exitProcess
import kotlin.system.measureTimeMillis

fun main() {

    val visualFrame = JFrame()
    visualFrame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    visualFrame.isResizable = false
    visualFrame.background = Color.BLACK

    val maze: TriviaMaze
    val time = measureTimeMillis { maze = TriviaMaze(7, 7) }
    println("Maze Generation took $time ms.")

    visualFrame.add(maze)
    visualFrame.pack()
    visualFrame.setLocationRelativeTo(null)
    visualFrame.isVisible = true


    val room1 = maze.getRoom(1, 1)
    val testPlayer = Player(room1)
    maze.addPlayer(testPlayer)
    Thread.sleep(1000)

    val seenList = mutableListOf<MazeRoom>(room1)
    for (i in 1 until 5000) {
        var direction = Direction.random()
        var chosenDoor = testPlayer.currentRoom.getDoor(direction)
        var unique = false
        var attempts = 0

        while (!unique) {
            direction = Direction.random()
            chosenDoor = testPlayer.currentRoom.getDoor(direction)
            chosenDoor.ifPresent{ door -> unique = door.roomBehind() !in seenList }
            if (attempts++ > 200) seenList.clear()
        }
        val foundDoor = chosenDoor.get()
        val ques = foundDoor.question
        foundDoor.tryAnswer(ques.correctAnswer)
        maze.movePlayer(direction)
        seenList.add(testPlayer.currentRoom)
        Thread.sleep(50)
    }
    Thread.sleep(500)
    exitProcess(0)
}

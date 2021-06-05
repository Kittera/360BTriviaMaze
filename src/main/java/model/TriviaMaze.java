package model;

import controller.MazePlayer;
import controller.QuestionFactory;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Random;

/**
 * Container for all of the maze rooms, capable of being added to a Swing container and
 * rendering itself appropriately for testing.
 *
 * @author Kittera Ashleigh McCloud
 */
public class TriviaMaze extends JPanel implements Maze {

   //////////  SWING FIELDS  //////////

   private static final JPanel myGridPanel = new JPanel();



   /**
    * Largest value that can be assigned to any dimensions of the maze.
    */
   public static final int MAX_DIM = 30;
   private static final int ROOM_SIZE = 50;
   
   private int myRows;
   private int myCols;
   
   private MazePlayer myPlayer;
   private final MazeRoom[][] myRooms;
   
   /**
    * Constructs a fresh Trivia Maze with pre-made input.
    *
    * @param theRows  first dimension of the maze grid
    * @param theCols  second dimension of the maze grid
    * @param theRooms pre-made rooms to be imported
    */
   TriviaMaze(final int theRows, final int theCols, final MazeRoom[][] theRooms) {
      if (badDimensions(theRows, theCols, MAX_DIM, MAX_DIM)) {
         throw new MazeIndexOutOfBoundsError();
      }
      myRows = theRows;
      myCols = theCols;
      myGridPanel.setLayout(new GridLayout(myRows, myCols));
      myRooms = importRooms(theRooms);
      myPlayer = null;
      initSwingGraphics();
   }
   
   /**
    * Constructs a fresh Trivia Maze with auto-generated input.
    *
    * @param theRows       first dimension of the maze grid
    * @param theCols       second dimension of the maze grid
    * @param theCategory   category to pull questions from
    * @param theDifficulty difficulty of questions to pull
    */
   public TriviaMaze(
         final int theRows,
         final int theCols,
         final Category theCategory,
         final Difficulty theDifficulty
   ) {
      if (badDimensions(theRows, theCols, MAX_DIM, MAX_DIM)) {
         throw new MazeIndexOutOfBoundsError();
      }
      myRows = theRows;
      myCols = theCols;
      myGridPanel.setLayout(new GridLayout(myRows, myCols));
      myPlayer = null;
      myRooms = generateRooms(theRows, theCols);
      initSwingGraphics();
      generateDoors(myRooms, theCategory, theDifficulty);
   }
   
   /**
    * Overload of standard constructor to provide parameter position flexibility.
    *
    * @param theRows       first dimension of the maze grid
    * @param theCols       second dimension of the maze grid
    * @param theCategory   category to pull questions from
    * @param theDifficulty difficulty of questions to pull
    */
   public TriviaMaze(
         final int theRows,
         final int theCols,
         final Difficulty theDifficulty,
         final Category theCategory
   ) {
      this(theRows, theCols, theCategory, theDifficulty);
   }
   
   /**
    * Constructs a fresh Trivia Maze with auto-generated input and random
    * category/difficulty.
    *
    * @param theRows first dimension of the maze grid
    * @param theCols second dimension of the maze grid
    */
   public TriviaMaze(
         final int theRows,
         final int theCols
   ) {
      this(theRows, theCols, Category.random(), Difficulty.random());
   }
   
   @Override
   public boolean addPlayer(final MazePlayer thePlayer) {
      boolean result;
      if (Objects.isNull(myPlayer)) {
         myPlayer = thePlayer;
         result = true;
      } else {
         result = false;
      }
      return result;
   }
   
   @Override
   public MazeRoom getRoom(final int theRow, final int theCol) {
      if (badDimensions(theRow, theCol, myRows, myCols)) {
         throw new MazeIndexOutOfBoundsError();
      }
      return myRooms[theRow][theCol];
   }
   
   @Override
   public boolean movePlayer(final Direction theDirection) {
      if (Objects.isNull(myPlayer)) {
         throw new IllegalStateException(
               "The maze does not yet have a player to be moved."
         );
      }
      
      boolean result;
      MazeRoom currentRoom = myPlayer.getCurrentRoom();
      Optional<MazeDoor> doorInDirection = currentRoom.getDoor(theDirection);
      
      try {
         if (theDirection == Direction.CENTER) {
            result = true;
            System.err.println("CENTER(in TriviaMaze)");
         } else if (doorInDirection.isPresent()) {
            if (doorInDirection.get().isLocked()) {
               result = false; // the door was locked, return false to indicate no move
            } else {
               myPlayer.sendToRoom(doorInDirection.get().roomBehind());
               result = true; // the door was unlocked, return true to indicate move
            }
         } else {
            result = false; // if nothing else, return false to indicate no operation
         }
      } catch (final IllegalStateException shallNotPass) {
         result = false;
      }
      return result;
   }
   
   private MazeRoom[][] generateRooms(final int theRows, final int theCols) {
      MazeRoom[][] mazeResult = new MazeRoom[theRows + 2][theCols + 2];
      
      // build out the 2D array with null buffer
      // tell each room where it's at
      for (int row = 1; row <= theRows; row++) {
         for (int col = 1; col <= theCols; col++) {
            TriviaRoom newRoom = new TriviaRoom(row, col);
            mazeResult[row][col] = newRoom;
            myGridPanel.add(newRoom);
            if (row == 1 && col == 1) {
               newRoom.setStartingRoom();
            } else if (row == myRows && col == myCols) {
               newRoom.setEndingRoom();
            }
         }
      }
      return mazeResult;
   }
   
   /**
    * Uses a randomized depth-first-search to iteratively add doors to the maze.
    *
    * @param theRooms      2D array of rooms to join with doors
    * @param theCategory   category to pull questions from, defaults to any
    * @param theDifficulty difficulty of questions to pull, defaults to any
    */
   private void generateDoors(
         final MazeRoom[][] theRooms,
         final Category theCategory,
         final Difficulty theDifficulty
   ) {
      ArrayDeque<MazeRoom> mazeStack = new ArrayDeque<>();
      Random rand = new Random();
      
      //choose an initial cell, mark it as visited, push it to mazeStack
      MazeRoom initialRoom = theRooms[1][1];
      initialRoom.markVisited();
      mazeStack.push(initialRoom);
      
      while (!mazeStack.isEmpty()) {
         MazeRoom currentRoom = mazeStack.pop(); // pop a room off the stack
         List<MazeRoom> currentUnexploredNeighbors =
               findUnexploredNeighbors(currentRoom.getLocation(), theRooms);
         
         if (currentUnexploredNeighbors.size() > 0) {
            Question question1 = QuestionFactory.get(theCategory, theDifficulty);
            Question question2 = QuestionFactory.get(theCategory, theDifficulty);
            
            mazeStack.push(currentRoom); //push current room
            MazeRoom chosenRoom = currentUnexploredNeighbors
                  .get(rand.nextInt(currentUnexploredNeighbors.size()));
            
            // to "remove the wall" we add a door in each Room on the shared wall.
            // first let's make the doors
            MazeDoor doorForChosenRoom = new TriviaDoor(question1, currentRoom);
            MazeDoor doorForCurrentRoom = new TriviaDoor(question2, chosenRoom);
            
            // link the doors up so that when the room is entered, it can be left
            doorForChosenRoom.linkOtherSide(doorForCurrentRoom);
            doorForCurrentRoom.linkOtherSide(doorForChosenRoom);
            
            // now add each door to its respective room
            chosenRoom.addDoor(
                  doorForChosenRoom,
                  Direction.betweenRooms(
                        chosenRoom.getLocation(),
                        currentRoom.getLocation()
                  )
            );
            currentRoom.addDoor(
                  doorForCurrentRoom,
                  Direction.betweenRooms(
                        currentRoom.getLocation(),
                        chosenRoom.getLocation()
                  )
            );
            chosenRoom.markVisited();
            mazeStack.push(chosenRoom);
         }
      }
   }
   
   private List<MazeRoom> findUnexploredNeighbors(
         final Point theLocation,
         final MazeRoom[][] theRooms
   ) {
      final int row = theLocation.y;
      final int col = theLocation.x;
      return Arrays.stream(
            new MazeRoom[]{
                  theRooms[row - 1][col],
                  theRooms[row][col - 1],
                  theRooms[row + 1][col],
                  theRooms[row][col + 1]
            })
            .filter(Objects::nonNull)
            .filter(mazeRoom -> !mazeRoom.isVisited())
            .collect(Collectors.toList());
   }
   
   private MazeRoom[][] importRooms(final MazeRoom[][] theRooms) {
      // participate in construction
      myRows = theRooms.length;
      myCols = theRooms[0].length;
      
      // copy array contents
      MazeRoom[][] result = new MazeRoom[myRows + 2][myCols + 2];
      for (int inRow = 0; inRow < theRooms.length; inRow++) {
         System.arraycopy(
               theRooms[inRow],
               0,
               result[inRow + 1],
               1,
               theRooms[inRow].length
         );
      }
      
      // now we have a null-buffer
      return result;
   }
   
   private boolean badDimensions(
         final int theRows,
         final int theCols,
         final int theRMax,
         final int theCMax
   ) {
      boolean result = theRows <= 0 || theRows > theRMax;
      if (theCols <= 0 || theCols > theCMax) result = true;
      return result;
   }
   
   
   //////////////////  SWING CODE  //////////////////
   
   
   private void initSwingGraphics() {
      setLayout(new BorderLayout());
      setBackground(Color.BLACK);
      setPreferredSize(new Dimension(ROOM_SIZE * myCols, ROOM_SIZE * myRows));
      add(myGridPanel, BorderLayout.CENTER);
      revalidate();
   }
   
   
   public static class MazeIndexOutOfBoundsError extends IllegalArgumentException {
      public MazeIndexOutOfBoundsError() {
         super("Row or column given does not exit on this maze.");
      }
   }
}

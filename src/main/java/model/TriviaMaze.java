package model;

import controller.MazePlayer;
import controller.QuestionFactory;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class TriviaMaze implements Maze {
   
   //use a hasBeenDiscovered boolean to control whether a room renders
   //use a GridLayout and make Rooms also extend JPanel OR make a view class as a
   // wrapper for a room
   
   /**
    * Largest value that can be assigned to any dimensions of the maze.
    */
   public static final int MAX_DIM = 30;
   
   private int myRows;
   private int myCols;
   
   private MazePlayer myPlayer;
   private final MazeRoom[][] myRooms;
   
   /**
    * Constructs a fresh Trivia Maze with pre-made input.
    *
    * @param theRows first dimension of the maze grid
    * @param theCols second dimension of the maze grid
    */
   TriviaMaze(final int theRows, final int theCols, final MazeRoom[][] theRooms) {
      throwIfBadDimensions(
            theRows,
            theCols,
            MAX_DIM,
            MAX_DIM
      );
      myRooms = importRooms(theRooms); // + 2 for null-buffer
      myPlayer = null;
   }
   
   /**
    * Constructs a fresh Trivia Maze with auto-generated input.
    *
    * @param theRows first dimension of the maze grid
    * @param theCols second dimension of the maze grid
    */
   public TriviaMaze(final int theRows, final int theCols) {
      throwIfBadDimensions(
            theRows,
            theCols,
            MAX_DIM,
            MAX_DIM
      );
      myRooms = generateMaze(theRows, theCols); // + 2 for null-buffer
      myPlayer = null;
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
      throwIfBadDimensions(theRow, theCol, myRows, myCols);
      return myRooms[theRow][theCol];
   }
   
   /**
    * Attempts to move the player in a certain direction.
    *
    * @param theDirection direction to attempt move
    * @return <code>true</code> iff move was successful
    */
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
         if (doorInDirection.isPresent()) {
            myPlayer.sendToRoom(doorInDirection.get().roomBehind());
            result = true;
         } else {
            result = false;
         }
      } catch (final IllegalStateException shallNotPass) {
         result = false;
      }
      return result;
   }
   
   private MazeRoom[][] generateMaze(final int theRows, final int theCols) {
      MazeRoom[][] mazeResult = new MazeRoom[theRows + 2][theCols + 2];
      
      // build out the 2D array with null buffer
      // tell each room where it's at
      for (int row = 1; row <= theRows; row++) {
         for (int col = 1; col <= theCols; col++) {
            MazeRoom newRoom = new TriviaRoom(row, col);
            mazeResult[row][col] = newRoom;
         }
      }
      generateDoors(mazeResult);
      return mazeResult;
   }
   
   private void generateDoors(final MazeRoom[][] theRooms) {
      MazeRoom initialRoom = theRooms[1][1];
      ArrayDeque<MazeRoom> mazeStack = new ArrayDeque<>();
      
      initialRoom.visit();
      mazeStack.push(initialRoom);
      
      while (!mazeStack.isEmpty()) {
         
         MazeRoom currentRoom = mazeStack.pop();
         List<MazeRoom> currentUnexploredNeighbors =
               gatherNeighbors(currentRoom.getLocation(), theRooms);
         
         // this will be true when the current Room has any unvisited neighbors
         if (currentUnexploredNeighbors.size() > 0) {
            mazeStack.push(currentRoom);
            Collections.shuffle(currentUnexploredNeighbors);
            MazeRoom chosenRoom = currentUnexploredNeighbors.get(0);
            
            //to "remove the wall" we add a door in each Room on the shared wall.
            chosenRoom.addDoor(
                  new TriviaDoor(QuestionFactory.get(), currentRoom),
                  Direction.betweenRooms(
                        chosenRoom.getLocation(), currentRoom.getLocation()
                  )
            );
            //now from the other side
            currentRoom.addDoor(
                  new TriviaDoor(QuestionFactory.get(), chosenRoom),
                  Direction.betweenRooms(
                        currentRoom.getLocation(), chosenRoom.getLocation()
                  )
            );
            
            chosenRoom.visit();
            mazeStack.push(chosenRoom);
         }
      }
   }
   
   private List<MazeRoom> gatherNeighbors(
         final Point theLocation,
         final MazeRoom[][] theRooms
   ) {
      final int row = theLocation.x;
      final int col = theLocation.y;
      
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
   
   private void throwIfBadDimensions(
         final int theRows,
         final int theCols,
         final int theRMax,
         final int theCMax
   ) {
      if (theRows <= 0 || theRows > theRMax) {
         throw new IllegalArgumentException(String.format(
               "Row count given is out of range. Range is 0 < x < %d", theRMax
         ));
      }
      if (theCols <= 0 || theCols > theCMax) {
         throw new IllegalArgumentException(String.format(
               "Column count given is out of range. Range is 0 < x < %d", theCMax
         ));
      }
   }
}

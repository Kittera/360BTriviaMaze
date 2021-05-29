package model;

import controller.MazePlayer;

import java.util.Objects;

public class TriviaMaze implements Maze {
   
   /**
    * Largest value that can be assigned to any dimensions of the maze.
    */
   public static final int MAX_DIM = 30;
   
   private int myRows;
   private int myCols;
   
   private MazePlayer myPlayer;
   private final MazeRoom[][] myRooms;
   
   /**
    * Constructs a fresh Maze ready to be populated.
    * @param theRows first dimension of the maze grid
    * @param theCols second dimension of the maze grid
    */
   public TriviaMaze(final int theRows, final int theCols, final MazeRoom[][] theRooms) {
      throwIfBadDimensions(
            theRows,
            theCols,
            MAX_DIM,
            MAX_DIM
      );
      myRooms = importRooms(theRooms); // + 2 for null-buffer
      myPlayer = null;
   }
   
   private MazeRoom[][] importRooms(MazeRoom[][] theRooms) {
      int maxCols = theRooms[0].length;
   
      // determine longest row just in case
      for (MazeRoom[] arr : theRooms) {
         maxCols = Math.max(arr.length, maxCols);
      }
      myRows = theRooms.length;
      myCols = maxCols;
      
      // copy array contents
      MazeRoom[][] result = new MazeRoom[theRooms.length + 2][maxCols + 2];
      for (int inRow = 0 ; inRow < theRooms.length ; inRow++) {
         System.arraycopy(theRooms[inRow], 0, result[inRow + 1], 1,
               theRooms[inRow].length);
      }
      
      // now we have a null-buffer
      return result;
   }
   
   @Override
   public MazeRoom getRoom(int theRow, int theCol) {
      throwIfBadDimensions(theRow, theCol, myRows, myCols);
      return myRooms[theRow][theCol];
   }
   
   @Override
   public boolean movePlayer(Direction theDirection) {
      
      
      return false; //TODO
   }
   
   @Override
   public boolean addPlayer(MazePlayer thePlayer) {
      boolean result;
      if (Objects.isNull(myPlayer)) {
         myPlayer = thePlayer;
         result = true;
      } else {
         result = false;
      }
      return result;
   }
   
   private void throwIfBadDimensions(int theRows, int theCols, int theRMax, int theCMax) {
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

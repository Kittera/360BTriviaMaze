package model;

import java.awt.*;
import java.util.Collection;
import java.util.Observer;
import java.util.Optional;

public interface MazeRoom {
   
   /**
    * Minimum of one door allows dead ends
    */
   int MIN_DOORS = 1;
   
   /**
    * On a 2D grid we want a limit of four sides per cell.
    */
   int MAX_DOORS = 4;
   
   /**
    * Adds a door to the room on a particular wall if said wall is not already inhabited
    * by a door.
    */
   boolean addDoor(final MazeDoor theDoor, final Direction theDirection);
   
   /**
    * Get a door on a specific wall.
    * @param theDirection of the wall to find a door on
    * @return door if found
    */
   Optional<MazeDoor> getDoor(final Direction theDirection);
   
   /**
    * Shortcut to get all doors in a room.
    * @return list of doors found
    */
   Collection<? extends MazeDoor> getDoors();
   
   /**
    * Accessor for this Room's location in the grid.
    * @return 2D java.awt.Point containing row and col indexes
    */
   Point getLocation();
   
   /**
    * Used for maze generation, this provides information on whether the algorithm has
    * "visited" this room already.
    * @return algorithm visit status
    */
   boolean isVisited();
   
   /** Marks a room as visited by the maze generation algorithm. */
   void markVisited();
}

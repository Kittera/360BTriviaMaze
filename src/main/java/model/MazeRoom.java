package model;

import controller.MazePlayer;

import java.awt.*;
import java.util.Collection;
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
   
   /** Used by the Player to turn on the roo's player presence indicator. Marks a room as
    *  discovered by the player if they haven't been here before. */
   void enter(MazePlayer thePlayer);
   
   /**
    * Sets the player presence indicator to false in this room.
    */
   void leave();
}

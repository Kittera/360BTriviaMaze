package model;

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
    * Shortcut to get all doors in a room.
    * @return list of doors found
    */
   Collection<? extends MazeDoor> getDoors();
   
   /**
    * Get a door on a specific wall.
    * @param theDirection of the wall to find a door on
    * @return door if found
    */
   Optional<MazeDoor> getDoor(final Direction theDirection);
   
   /**
    * Adds a door to the room on a particular wall if said wall is not already inhabited
    * by a door.
    */
   boolean addDoor(final MazeDoor theDoor);
}

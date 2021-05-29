package model;

import java.util.List;
import java.util.Observer;
import java.util.Optional;

public interface MazeRoom extends Observer {
   
   int MIN_DOORS = 1;
   int MAX_DOORS = 4;
   
   /**
    * Shortcut to get all doors in a room.
    * @return list of doors found
    */
   List<MazeDoor> getDoors();
   
   /**
    * Get a door on a specific wall.
    * @param theDirection of the wall to find a door on
    * @return door if found
    */
   Optional<MazeDoor> getDoor(Direction theDirection);
   
}

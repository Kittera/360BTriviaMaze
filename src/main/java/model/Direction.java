package model;

import java.awt.*;
import java.io.Serializable;
import java.util.Random;

public enum Direction implements Serializable {
   EAST, NORTH, WEST, SOUTH, CENTER;
   
   /**
    * Given two Points where x represents columns and y rows, and where 0, 0 is at the
    * "top left," determines the appropriate direction constant for travelling FROM
    * theSrcLocation, TO theDestLocation.
    * @param theSrcLocation location being vacated
    * @param theDestLocation location being targeted
    * @return appropriate enum constant for the direction of travel
    */
   public static Direction betweenRooms(
         final Point theSrcLocation,
         final Point theDestLocation
   ) {
      Direction result;
      if (theSrcLocation.x < theDestLocation.x
            && theSrcLocation.y == theDestLocation.y) {
         result = EAST;
      }
      else if (theSrcLocation.x > theDestLocation.x
            && theSrcLocation.y == theDestLocation.y) {
         result = WEST;
      }
      else if (theSrcLocation.x == theDestLocation.x
            && theSrcLocation.y < theDestLocation.y) {
         result = SOUTH;
      }
      else if (theSrcLocation.x == theDestLocation.x
            && theSrcLocation.y > theDestLocation.y) {
         result = NORTH;
      } else {
         result = CENTER;
      }
      return result;
   }
   
   /**
    * Gets a random direction.
    * @return random Direction
    */
   public static Direction random() {
      Random rand = new Random();
      int draw = rand.nextInt(values().length);
      return values()[draw];
   }
}

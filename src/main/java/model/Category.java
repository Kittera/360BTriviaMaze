package model;

import java.util.Arrays;
import java.util.Optional;

public enum Category {
   ANY        ( 0, "All Categories"),
   GENERAL    ( 9, "General Knowledge"),
   FILM       (11, "Entertainment: Film"),
   MUSIC      (12, "Entertainment: Music"),
   VID_GAMES  (15, "Entertainment: Video Games"),
   COMPSCI    (18, "Science: Computers"),
   MATH       (19, "Science: Mathematics"),
   MYTHOLOGY  (20, "Mythology"),
   HISTORY    (23, "History"),
   ANIMALS    (27, "Animals"),
   VEHICLES   (28, "Vehicles");
   
   /**
    * A unique integer id for a category that happens to correspond to the id of that
    * category on Open Trivia DB.
    */
   public final int categoryID;
   
   /**
    * Name of the category in question.
    */
   public final String title;
   
   Category(final int theId, final String theName) {
      categoryID = theId;
      title = theName;
   }
   
   public static Category fromName(final String theName) {
      final Optional<Category> result = Arrays.stream(values())
            .filter(category -> category.title.equals(theName))
            .findFirst();
      
      final Category out;
      
      if (result.isEmpty()) {
         out = Category.ANY;
      } else {
         out = result.get();
      }
      
      return out;
   }
}

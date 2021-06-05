package model;

import java.util.*;

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
   public final String name;
   
   Category(final int theId, final String theName) {
      categoryID = theId;
      name = theName;
   }
   
   /**
    * Given a name, gives back an appropriate category constant.
    * @param theName name of desired category
    * @return category found by that name or Category.ANY
    */
   public static Category fromName(final String theName) {
      
      final Optional<Category> result = Arrays.stream(values())
            .filter(category -> category.name.equals(theName))
            .findFirst();
      
      if (result.isEmpty()) {
         return Category.ANY;
      } else {
         return result.get();
      }
   }
   
   /**
    * Picks a random Category.
    * @return a random Category
    */
   public static Category random() {
      List<Category> hat = new ArrayList<>(Arrays.asList(values()));
      hat.remove(Category.ANY);
      Collections.shuffle(hat);
      return hat.remove(0);
   }
   
}

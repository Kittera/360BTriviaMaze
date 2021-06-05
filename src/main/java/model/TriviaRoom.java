package model;

import controller.MazePlayer;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;
import java.util.Objects;
import java.util.Optional;

/**
 * Container for up to four doors or walls. Can me added to Java Swing containers.
 *
 * @author Kittera Ashleigh McCloud
 */
public class TriviaRoom extends JPanel implements MazeRoom {
   
   //////////  SWING FIELDS  //////////
   
   private static final Color BG_COLOR = new Color(0x171717);
   private static final Color WALL_COLOR = Color.BLUE;
//   private static final Color BG_COLOR = Color.DARK_GRAY;
   private final JPanel myPlayerIndicator;
   
   //////////  ROOM FIELDS  //////////
   
   private final EnumMap<Direction, MazeDoor> myDoors;
   
   private final int myRowLocation;
   private final int myColLocation;
   
   private boolean myVisited;
   private boolean myDiscovered;
   
   public TriviaRoom(
         final EnumMap<Direction, MazeDoor> theDoors,
         final int theRow,
         final int theCol
   ) {
      myDoors = theDoors;
      myRowLocation = theRow;
      myColLocation = theCol;
      myVisited = false;
      myDiscovered = false;
      myPlayerIndicator = new JPanel();
      initSwingGraphics();
   }
   
   public TriviaRoom(final int theRow, final int theCol) {
      this(new EnumMap<>(Direction.class), theRow, theCol);
   }
   
   @Override
   public boolean addDoor(final MazeDoor theDoor, final Direction theDirection) {
      // putIfAbsent returns null if the given key wasn't already mapped to a door
      boolean result = Objects.isNull(myDoors.putIfAbsent(theDirection, theDoor));
      if (result) {
//         addDoorIndicator(theDirection);
         revalidate();
         repaint();
      }
      return result;
   }
   
   @Override
   public Optional<MazeDoor> getDoor(Direction theDirection) {
      return Optional.ofNullable(myDoors.get(theDirection));
   }
   
   @Override
   public Point getLocation() {
      return new Point(myColLocation, myRowLocation);
   }
   
   @Override
   public boolean isVisited() {
      return myVisited;
   }
   
   @Override
   public void markVisited() {
      if (!myVisited) {
         myVisited = true;
      }
   }
   
   @Override
   public void enter(MazePlayer thePlayer) {
      if (thePlayer.getCurrentRoom() == this) myDiscovered = true;
      myPlayerIndicator.setVisible(true);
   }
   
   @Override
   public void leave() {
      myPlayerIndicator.setVisible(false);
   }
   
   //////////////////  SWING CODE  //////////////////
   
   private void initSwingGraphics() {
      setLayout(new BorderLayout());
      setBackground(BG_COLOR);
      setPreferredSize(new Dimension(30, 30));
      
      myPlayerIndicator.setBackground(Color.MAGENTA);
      myPlayerIndicator.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
      myPlayerIndicator.setVisible(false);
      add(myPlayerIndicator, BorderLayout.CENTER);
      //TODO blackout based on discovered status
   }
   
   @Override
   public void paintComponent(Graphics g) {
//      if (!myDiscovered) {
//         g.setColor(new Color(0x000077));
//         g.fillRect(0, 0, getWidth(), getHeight());
//      } else {
         Color savedColor = g.getColor(); //get color from graphics
         g.setColor(Color.BLACK);
         ((Graphics2D) g).setStroke(new BasicStroke(8));
         g.fillRect(0, 0, getWidth(), getHeight());
   
         if (getDoor(Direction.NORTH).isPresent()) {
            g.setColor(switchBarColor(getDoor(Direction.NORTH).get()));
         } else {
            g.setColor(WALL_COLOR);
         }
         g.drawLine(0,0,getWidth(),0);
   
         if (getDoor(Direction.EAST).isPresent()) {
            g.setColor(switchBarColor(getDoor(Direction.EAST).get()));
         } else {
            g.setColor(WALL_COLOR);
         }
         g.drawLine(getWidth(),0,getWidth(),getHeight());
   
         if (getDoor(Direction.SOUTH).isPresent()) {
            g.setColor(switchBarColor(getDoor(Direction.SOUTH).get()));
         } else {
            g.setColor(WALL_COLOR);
         }
         g.drawLine(getWidth(),getHeight(),0,getHeight());
   
         if (getDoor(Direction.WEST).isPresent()) {
            g.setColor(switchBarColor(getDoor(Direction.WEST).get()));
         } else {
            g.setColor(WALL_COLOR);
         }
         g.drawLine(0,getHeight(),0, 0);
         
         g.setColor(savedColor);
         // blue = wall
         // BG color = unlocked door
         // ?? = locked door
         // red = jammed door
         
         // Idea: Lines drawn here from ne corner to another depending ong door presence
         // set the color dynamically based on discovered status
      
//      }
   }
   
   private Color switchBarColor(MazeDoor theDoor) {
      Color result;
      if (theDoor.isLocked()) result = BG_COLOR;
      else if (theDoor.isJammed()) result = Color.RED;
      else result = Color.BLACK;
      return result;
   }
}

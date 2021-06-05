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
   
   private static final Color BG_COLOR = new Color(0x0F0F0F);
   private static final Color WALL_COLOR = Color.BLUE;
   private static final Color PLAYER_COLOR = Color.MAGENTA;
   private static final Dimension ROOM_DIMENSION = new Dimension(30, 30);
//   private static final Color BG_COLOR = Color.DARK_GRAY;
   
   private static final int PLAYER_IND_INSET = 10;
   private static final int WALL_THICKNESS = 4;
   private static final int WALL_LINE_PX = WALL_THICKNESS / 2;
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
      myPlayerIndicator.setBackground(PLAYER_COLOR);
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
      if (getLocation().x == 1 && getLocation().y == 1) {
         setBackground(new Color(0xab00ff));
      } else {
         setBackground(BG_COLOR);
      }
      setPreferredSize(ROOM_DIMENSION);
      
      myPlayerIndicator.setOpaque(true);
      myPlayerIndicator.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
      myPlayerIndicator.setVisible(false);
      add(myPlayerIndicator);
      //TODO blackout based on discovered status
   }
   
   @Override
   public void paintComponent(Graphics g) {
      if (!myDiscovered) {
         g.setColor(new Color(0x000077));
         g.fillRect(0, 0, getWidth(), getHeight());
      } else {
         Color savedColor = g.getColor(); //get color from graphics
         if (myPlayerIndicator.isVisible()) {
            g.setColor(Color.CYAN);
            g.fillRect(
                  PLAYER_IND_INSET,
                  PLAYER_IND_INSET,
                  getWidth() - 2 * PLAYER_IND_INSET,
                  getHeight() - 2 * PLAYER_IND_INSET
                  );
         }
   
         ((Graphics2D) g).setStroke(new BasicStroke(WALL_THICKNESS));
         g.setColor(getBackground());
         g.fillRect(
               WALL_THICKNESS,
               WALL_THICKNESS,
               getWidth() - WALL_THICKNESS,
               getHeight() - WALL_THICKNESS
         );
   
         g.setColor(WALL_COLOR);
         g.drawRect(
               WALL_LINE_PX,
               WALL_LINE_PX,
               getWidth() - WALL_LINE_PX - 1,
               getHeight() - WALL_LINE_PX - 1
         );
   
         if (getDoor(Direction.NORTH).isPresent()) {
            g.setColor(switchBarColor(getDoor(Direction.NORTH).get()));
            g.drawLine(
                  3 * WALL_THICKNESS,
                  WALL_LINE_PX,
                  getWidth() - 3 * WALL_THICKNESS,
                  WALL_LINE_PX
            );
         }
   
         if (getDoor(Direction.EAST).isPresent()) {
            g.setColor(switchBarColor(getDoor(Direction.EAST).get()));
            g.drawLine(
                  getWidth() - WALL_LINE_PX,
                  WALL_LINE_PX + 3 * WALL_THICKNESS,
                  getWidth() - WALL_LINE_PX,
                  getHeight() - 3 * WALL_THICKNESS
            );
         }
   
         if (getDoor(Direction.SOUTH).isPresent()) {
            g.setColor(switchBarColor(getDoor(Direction.SOUTH).get()));
            g.drawLine(
                  getWidth() - 3 * WALL_THICKNESS,
                  getHeight() - WALL_LINE_PX,
                  3 * WALL_THICKNESS,
                  getHeight() - WALL_LINE_PX
            );
         }
   
         if (getDoor(Direction.WEST).isPresent()) {
            g.setColor(switchBarColor(getDoor(Direction.WEST).get()));
            g.drawLine(
                  WALL_LINE_PX,
                  getHeight() - 3 * WALL_THICKNESS,
                  WALL_LINE_PX,
                  WALL_LINE_PX + 3 * WALL_THICKNESS
            );
         }
         
         g.setColor(savedColor);
         // blue = wall
         // BG color = unlocked door
         // ?? = locked door
         // red = jammed door
         
         // Idea: Lines drawn here from ne corner to another depending ong door presence
         // set the color dynamically based on discovered status
      
      }
   }
   
   private Color switchBarColor(MazeDoor theDoor) {
      Color result;
      if (theDoor.isLocked()) result = Color.YELLOW;
      else if (theDoor.isJammed()) result = Color.RED;
      else result = BG_COLOR;
      return result;
   }
}

package ooga.view;

import static ooga.util.ResourceRetriever.getWord;
import static ooga.util.ResourceRetriever.showAlert;

import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import ooga.logic.board.spot.Spot;


/**
 * JavaFX GridPane that creates the chessboard on which all of the game will be executed
 * Relies on appropriate resourcebundles being configured and JavaFX
 *
 * @author marcusdeans
 */
public class GridView implements GridChessView {

  private String[] myGridColours;
  private GridPane myGameGrid;
  private PanelListener myGameView;
  private int myWidthNumber;
  private int myHeightNumber;
  private int myGridDimensions;
  private int myCellWidth;
  private int myCellHeight;

  private Logger myLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  private static final String GRID_VIEW_RESOURCES_PATH = "ooga.view.viewresources.GridViewResources";
  private static final ResourceBundle gridViewResources = ResourceBundle.getBundle(GRID_VIEW_RESOURCES_PATH);
  private final List<String> piecesNames= Arrays.asList(gridViewResources.getString("pieceTypes").split(","));

  /**
   * Create a new GridView that will provide a representation of the chessboard as a JavaFX GridPane
   * @param heightNumber the vertical number of cells of this grid
   * @param widthNumber the horizontal number of cells of this grid
   * @param gridColours the specified colours that the grid should be set to
   * @param gridDisplayLength the length in pixels of the display
   * @param gameView a reference to the GameView to allow for communication and information propagation
   */
  public GridView(int heightNumber, int widthNumber, String[] gridColours, int gridDisplayLength, PanelListener gameView) {
    myGameGrid = new GridPane();
    myGameGrid.setId("game-grid");
    myWidthNumber = widthNumber;
    myHeightNumber = heightNumber;
    myGridColours = gridColours;
    myGridDimensions = gridDisplayLength;
    myGameView = gameView;
    determineCellDimensions();
    populateNewGrid();
  }

  //determine the cell dimensions that will apply for all cells in teh grid
  private void determineCellDimensions() {
    myCellWidth = (myGridDimensions - getInt("lineSize") - getInt("letter_label_width")) / myWidthNumber;
    myCellHeight = (myGridDimensions - getInt("lineSize")) / myHeightNumber;
  }

  //create a new cell that has a specified colour
  private Rectangle createNewColouredCellView(int state, boolean hasPiece, String hexColour){
    Rectangle newCell = createNewCellView(state, hasPiece);
    newCell.setFill(Color.web(hexColour));
    return newCell;
  }

  //create an individual cell on the grid representing a square of provided colour
  private Rectangle createNewCellView(int state, boolean hasPiece) {
    Rectangle newCell = new Rectangle();
    if(!hasPiece){
      newCell.setOnMouseClicked(this::clickOnGrid);
    }
    newCell.setWidth(myCellWidth);
    newCell.setHeight(myCellHeight);
    newCell.setId("cell-view");
    newCell.setFill(Color.web(myGridColours[state]));
    return newCell;
  }

  //create a new chess cell that has a piece on it and is also coloured
  private Pane createNewColouredCellWithPiece(int state, ImageView pieceImage, String hexColour){
    Pane newCellGroup = new Pane();
    newCellGroup.getChildren().addAll(createNewColouredCellView(state, true, hexColour), pieceImage);
    try {
      newCellGroup.setOnMouseClicked(this::clickOnGrid);
    }
    catch(Exception e){
      showAlert(AlertType.ERROR, getWord("click_error_title"), getWord("click_error_message"));
    }
    return newCellGroup;
  }

  //create a new chess cel that also has a pieceo nit
  private Pane createNewCellWithPiece(int state, ImageView pieceImage){
    Pane newCellGroup = new Pane();
    newCellGroup.getChildren().addAll(createNewCellView(state, true), pieceImage);
    try {
      newCellGroup.setOnMouseClicked(this::clickOnGrid);
    }
    catch(Exception e){
      showAlert(AlertType.ERROR, getWord("click_error_title"), getWord("click_error_message"));
    }
    return newCellGroup;
  }

  //a chess board alternates in colour hence the state must switch depending on position
  private int determineCellColour(int column, int row){
    int myCol = column % 2;
    int myRow = row % 2;
    return (myCol == myRow) ? 0 :  1;
  }

  //create the appropriate labeling on the chess board to mark rows and columns
  private StackPane createGridLabelView(int textValue, boolean intoAscii){
    StackPane letterLabelView = new StackPane();
    letterLabelView.setId("letter-label-view");

    Rectangle labelColourBox = new Rectangle();
    labelColourBox.setId("letter-label-colour-box");

    Text letterLabelText;
    if(intoAscii){
      letterLabelText = new Text(Character.toString((char) textValue));
      labelColourBox.setWidth(myCellWidth);
      labelColourBox.setHeight(getInt("letter_label_width"));
    } else {
      letterLabelText = new Text(String.valueOf(textValue));
      labelColourBox.setWidth(getInt("letter_label_width"));
      labelColourBox.setHeight(myCellHeight);
    }
    letterLabelText.setId("letter-label-text");
    letterLabelView.getChildren().addAll(labelColourBox, letterLabelText);

    return letterLabelView;
  }

  //create the new chess grid of appropriate size
  private void populateNewGrid() {
    for (int column = 0; column < myWidthNumber; column++) {
      myGameGrid.add(createGridLabelView(getInt("ascii_alpha_int_value")+column, true), column, myHeightNumber);
      for (int row = 0; row < myHeightNumber; row++) {
        if(column+1 == myWidthNumber){
          myGameGrid.add(createGridLabelView(row+1, false), column+1, row);
        }
        myGameGrid.add(createNewCellView(determineCellColour(column, row), false), column, row);
      }
    }
  }

  /**
   * Obtain the GameGrid in order to display the chess board
   * @return the JavaFX Gridpane representing the board
   */
  @Override
  public GridPane getMyGameGrid() {
    myGameGrid.setGridLinesVisible(true);
    return myGameGrid;
  }

  //determine which cell the user clicked on and process via reflection
  private void clickOnGrid(MouseEvent event) {
    Node nod = (Node) event.getSource();
    Parent par = nod.getParent();

    while (par != myGameGrid){
      nod = par;
      par = par.getParent();
    }

    Integer colIndex = GridPane.getColumnIndex(nod);
    Integer rowIndex = GridPane.getRowIndex(nod);
    try {
      myGameView.getBoardClick(colIndex, rowIndex);
    } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
      myLogger.log(Level.INFO, getWord("reflection_error"));
    }

  }

  /**
   * Allow for the modification of a specific chess cell
   * @param spot the spot that should be modified
   */
  @Override
  public void updateChessCell(Spot spot){
    changeChessCell(spot, false, getString("empty_cell_colour"));
  }

  /**
   * Allow for the change of a specific spot to a supplied colour
   * @param spot the spot to be changed
   * @param hexColour the colour that it should be changed to
   */
  @Override
  public void colourChessCell(Spot spot, String hexColour){
    changeChessCell(spot, true, hexColour);
  }

  //chance a specific cell on the chessboard
  private void changeChessCell(Spot spot, boolean isColoured, String hexColour){
    int columnIndex = spot.getCoordinate().getX_pos();
    int rowIndex = spot.getCoordinate().getY_pos();
    if(spot.getPiece() != null){
      ImageView pieceImageview = createNewPieceImageView(spot);
      pieceImageview.setFitHeight(myCellHeight-getInt("cell-piece-spacing"));
      pieceImageview.setFitWidth(myCellWidth-getInt("cell-piece-spacing"));

      Pane newCellWithPiece;
      if(!isColoured){
        newCellWithPiece = createNewCellWithPiece(determineCellColour(columnIndex, rowIndex), pieceImageview);
      } else {
        newCellWithPiece = createNewColouredCellWithPiece(determineCellColour(columnIndex, rowIndex), pieceImageview, hexColour);
      }
      myGameGrid.add(newCellWithPiece, columnIndex, rowIndex);

    } else {
      Rectangle newCellWithoutPiece;
      if(!isColoured){
        newCellWithoutPiece = createNewCellView(determineCellColour(columnIndex, rowIndex), false);
      } else {
        newCellWithoutPiece = createNewColouredCellView(determineCellColour(columnIndex, rowIndex), false, hexColour);
      }
      myGameGrid.add(newCellWithoutPiece, columnIndex, rowIndex);
    }
  }

  //create a new spot that also has a chess piece on it
  private ImageView createNewPieceImageView(Spot spot){
    ImageView newPieceImageView;
    try {
      String teamName = determineTeamColour(spot.getPiece().getTeam());
      String pieceName = spot.getPiece().getClass().getSimpleName();
      String capitalizedPieceName = pieceName.substring(0, 1).toUpperCase() + pieceName.substring(1);
      String pieceImageResource = String.format("%s-%s.png",teamName, capitalizedPieceName);
      //System.out.println("spot.getPiece().getTeam(): " + spot.getPiece().getTeam() + "Team Name:" + teamName + " CapitalizedPieceName:" + capitalizedPieceName + " PieceImageResource: " + pieceImageResource);
      FileInputStream input = new FileInputStream("data/" + pieceImageResource);
      newPieceImageView = new ImageView(new Image(input));
    } catch(Exception e){
      newPieceImageView = new ImageView(new Image(getClass().getResourceAsStream("White-Bishop.png")));
    }
    return newPieceImageView;
  }

  //determine the colour of the team
  private String determineTeamColour(int teamNumber){
    Map<Integer, String> intMap = Map.of(1, "Black", 2, "White");
    return (intMap.containsKey(teamNumber))? intMap.get(teamNumber) : "Error";
  }


  /**
   * Update a specific cell in the grid
   * @param row position of cell
   * @param column position of cell
   * @param state the state that the piece should be set to
   */
  @Override
  public void update(int row, int column, int state) {
    myGameGrid.add(createNewCellView(determineCellColour(column, row), false), column, row);
    //TODO: built iterator interface to extract which pieces are in what location ont the board, interact wi the model's list
  }

  //return the integer from the resource file based on the provided string
  private int getInt(String key){
    int value;
    try {
      value = Integer.parseInt(gridViewResources.getString(key));
    } catch(Exception e){
      value =-1;
    }
    return value;
  }

  //retrieves relevant word from the "words" ResourceBundle
  private String getString(String key) {
    String value;
    try {
      value = gridViewResources.getString(key);
    } catch (Exception exception) {
      value = "error";
    }
    return value;
  }
}
package ooga.view;

import java.awt.Panel;
import java.io.FileInputStream;
import java.util.*;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import ooga.logic.board.spot.Spot;


/**
 * JavaFX View class
 */
public class GridView implements GridListener {

  private String[] myGridColours;
  private GridPane myGameGrid;
  private PanelListener myGameView;
  private int myWidthNumber;
  private int myHeightNumber;
  private int myGridDimensions;
  private int myCellWidth;
  private int myCellHeight;
  private static final int LINE_SIZE = 6;

  private static final String GRID_VIEW_RESOURCES_PATH = "ooga.view.viewresources.GridViewResources";
  private static final ResourceBundle gridViewResources = ResourceBundle.getBundle(GRID_VIEW_RESOURCES_PATH);
  private final List<String> piecesNames= Arrays.asList(gridViewResources.getString("pieceTypes").split(","));

  public GridView(int height, int width, String[] gridColours, int gridDisplayLength, PanelListener gameView) {
    myGameGrid = new GridPane();
    myGameGrid.setId("game-grid");
    myWidthNumber = width;
    myHeightNumber = height;
    myGridColours = gridColours;
    myGridDimensions = gridDisplayLength;
    myGameView = gameView;
    determineCellDimensions();
    populateNewGrid();
  }

  private void determineCellDimensions() {
    myCellWidth = (myGridDimensions - getInt("lineSize")) / myWidthNumber;
    myCellHeight = (myGridDimensions - getInt("lineSize")) / myHeightNumber;
  }

  //create an individual cell on the grid representing a square of provided colour
  private Rectangle createNewCellView(int state, boolean isHighlighted) {
    Rectangle newCell = new Rectangle();
    newCell.setOnMouseClicked(this::clickOnGrid);
    newCell.setWidth(myCellWidth);
    newCell.setHeight(myCellHeight);
    newCell.setId("cell-view");
    if(isHighlighted){
      newCell.setId("highlighted-cell-view");
    }
    newCell.setFill(Color.web(myGridColours[state]));
    return newCell;
  }

  private Group createNewCellWithPiece(int state, ImageView pieceImage, boolean isHighlighted){
    Group newCellGroup = new Group();
    newCellGroup.getChildren().addAll(createNewCellView(state, isHighlighted), pieceImage);
    return newCellGroup;
  }

  //a chess board alternates in colour hence the state must switch depending on position
  private int determineCellColour(int column, int row){
    if((column %2 == 0)&&(row %2 ==0)){
      return 0;
    } else if ((column %2 ==0)&&(row %2 == 1)){
      return 1;
    } else if ((column %2 == 1)&&(row %2 == 0)){
      return 1;
    } else {
      return 0;
    }
  }

  //create the new chess grid of appropriate size
  private void populateNewGrid() {
    for (int column = 0; column < myWidthNumber; column++) {
      for (int row = 0; row < myHeightNumber; row++) {
        myGameGrid.add(createNewCellView(determineCellColour(column, row), false), column, row);
      }
    }
  }

  public GridPane getMyGameGrid() {
    myGameGrid.setGridLinesVisible(true);
    return myGameGrid;
  }
  public int getMyCellWidth() {
    return myCellWidth;
  }
  public int getMyCellHeight() {
    return myCellHeight;
  }

  private void clickOnGrid(MouseEvent event){
    Node clickedNode = event.getPickResult().getIntersectedNode();
    if(clickedNode != myGameGrid){
      Integer colIndex = GridPane.getColumnIndex(clickedNode);
      Integer rowIndex = GridPane.getRowIndex(clickedNode);
      myGameView.getBoardClick(colIndex, rowIndex);
    }
  }


  public int[] updateCellOnClick(double x, double y) {
    //TODO: compoute which cell this corresponds to in terms of chess grid
    return new int[]{0,0};
  }

  // row, column, colour, piece type
  //Spot -> extract row, column, 'team'=colour, Spot.getPiece() -> reflect on the piece, makes corresponding JavaFX images
  public void updateChessCell(Spot spot){
    changeChessCell(spot, false);
  }

  public void highlightChessCell(Spot spot){
    changeChessCell(spot, true);
  }

  private void changeChessCell(Spot spot, boolean isHighlighted){
    int columnIndex = spot.getCoordinate().getX_pos();
    int rowIndex = spot.getCoordinate().getY_pos();


    if(spot.getPiece() != null){
      ImageView pieceImageview = createNewPieceImageView(spot);
      pieceImageview.setFitHeight(myCellHeight-getInt("cell-piece-spacing"));
      pieceImageview.setFitWidth(myCellWidth-getInt("cell-piece-spacing"));

      Group newCellWithPiece = createNewCellWithPiece(determineCellColour(columnIndex, rowIndex), pieceImageview, isHighlighted);
      myGameGrid.add(newCellWithPiece, columnIndex, rowIndex);

    } else {
      Rectangle newCellWithoutPiece = createNewCellView(determineCellColour(columnIndex, rowIndex), isHighlighted);
      myGameGrid.add(newCellWithoutPiece, columnIndex, rowIndex);
    }

    //TODO: make cell updates
  }

  private ImageView createNewPieceImageView(Spot spot){
    ImageView newPieceImageView;
    try {
      String teamName = determineTeamColour(spot.getPiece().getTeam());
      String pieceName = spot.getPiece().getClass().getSimpleName();
      String capitalizedPieceName = pieceName.substring(0, 1).toUpperCase() + pieceName.substring(1);
//      newPieceImageView = new ImageView(new Image(String.format("ooga.view.viewresources.pieceimages.%s-%s.png",teamName, capitalizedPieceName)));
      String pieceImageResource = String.format("%s-%s.png",teamName, capitalizedPieceName);
      System.out.println("spot.getPiece().getTeam(): " + spot.getPiece().getTeam() + "Team Name:" + teamName + " CapitalizedPieceName:" + capitalizedPieceName + " PieceImageResource: " + pieceImageResource);
//      newPieceImageView = new ImageView(new Image(getClass().getResourceAsStream(pieceImageResource)));
      FileInputStream input = new FileInputStream("data/" + pieceImageResource);
      Image newImage = new Image(input);
      newPieceImageView = new ImageView(newImage);
    } catch(Exception e){
//      newPieceImageView = new ImageView(new Image("White-Bishop.png"));
      System.out.println("In the catch part...");
      newPieceImageView = new ImageView(new Image(getClass().getResourceAsStream("White-Bishop.png")));
      System.out.println("Error making piece image representation");
    }
    return newPieceImageView;
  }

  private String determineTeamColour(int teamNumber){
    switch(teamNumber){
      case 0, 1 -> {
        return "Black";
      }
      case 6, 7 -> {
        return "White";
      }
      default -> {
        return "Error";
      }
    }
  }


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
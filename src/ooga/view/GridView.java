package ooga.view;

import java.util.ResourceBundle;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;


/**
 * JavaFX View class
 */
public class GridView implements ooga.view.GridListener {

  private String[] myGridColours;
  private GridPane myGameGrid;
  private int myWidthNumber;
  private int myHeightNumber;
  private int myGridDimensions;
  private int myCellWidth;
  private int myCellHeight;
  private static final int LINE_SIZE = 6;

  private static final String GRID_VIEW_RESOURCES_PATH = "ooga.view.viewresources.GridViewResources";
  private static final ResourceBundle gridViewResources = ResourceBundle.getBundle(GRID_VIEW_RESOURCES_PATH);

  public GridView(int height, int width, String[] gridColours, int gridDisplayLength) {
    myGameGrid = new GridPane();
    myWidthNumber = width;
    myHeightNumber = height;
    myGridColours = gridColours;
    myGridDimensions = gridDisplayLength;
    determineCellDimensions();
    populateNewGrid();
  }

  private void determineCellDimensions() {
    myCellWidth = (myGridDimensions - getInt("lineSize")) / myWidthNumber;
    myCellHeight = (myGridDimensions - getInt("lineSize")) / myHeightNumber;
  }

  //create an individual cell on the grid representing a square of provided colour
  private Rectangle createNewCellView(int state) {
    Rectangle newCell = new Rectangle();
    newCell.setWidth(myCellWidth);
    newCell.setHeight(myCellHeight);
    newCell.setId("cell-view");
    newCell.setFill(Paint.valueOf(myGridColours[state]));
    return newCell;
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
        myGameGrid.add(createNewCellView(determineCellColour(column, row)), column, row);
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

  public int[] updateCellOnClick(double x, double y) {

    return new int[]{0,0};
  }

  @Override
  public void update(int row, int column, int state) {
    myGameGrid.add(createNewCellView(determineCellColour(column, row)), column, row);
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
}
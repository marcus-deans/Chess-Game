//package ooga.logic.board.edgepolicies;
//
//import ooga.logic.board.coordinate.Coordinate;
//import ooga.logic.board.coordinate.GameCoordinate;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class BasicEdgePoliciesTest {
//    @Test
//    public void allValidTest()
//    {
//        List<List<Coordinate>> bigList = new ArrayList<>();
//        List<Coordinate> a=new ArrayList<>();
//        List<Coordinate> c;
//        a.add(new GameCoordinate(3,4));
//        a.add(new GameCoordinate(0,0));
//        a.add(new GameCoordinate(2,1));
//        BasicEdgePolicies b=new BasicEdgePolicies(5,5);
//        bigList.add(a);
//        c=b.filterList(a);
//        Assertions.assertEquals(a.size(),c.size());
//    }
//
//    @Test
//    public void notAllValidTest()
//    {
//        List<List<Coordinate>> myBigList = new ArrayList<>();
//        List<Coordinate> a=new ArrayList<>();
//        a.add(new GameCoordinate(3,4));
//        a.add(new GameCoordinate(5,0));
//        a.add(new GameCoordinate(0,5));
//        a.add(new GameCoordinate(-2,0));
//        a.add(new GameCoordinate(0,-2));
//        a.add(new GameCoordinate(8,-2));
//        a.add(new GameCoordinate(-2,8));
//        a.add(new GameCoordinate(2,1));
//        BasicEdgePolicies b=new BasicEdgePolicies(5,5);
//        myBigList.add(a);
//        List<List<Coordinate>> c =b.filterList(myBigList);
//        Assertions.assertEquals(2,c.size());
//    }
//
//    @Test
//    public void noneValidTest()
//    {
//        List<Coordinate> a=new ArrayList<>();
//        List<Coordinate> c;
//        a.add(new GameCoordinate(5,0));
//        a.add(new GameCoordinate(0,5));
//        a.add(new GameCoordinate(-2,0));
//        a.add(new GameCoordinate(0,-2));
//        a.add(new GameCoordinate(8,-2));
//        a.add(new GameCoordinate(-2,8));
//        BasicEdgePolicies b=new BasicEdgePolicies(5,5);
//        c=b.filterList(a);
//        Assertions.assertEquals(0,c.size());
//    }
//
//  private int getSize(List<List<Coordinate>> myCoords) {
//    int size = 0;
//    for (List<Coordinate> x : myCoords){
//      size += x.size();
//    }
//    return size;
//  }
//}

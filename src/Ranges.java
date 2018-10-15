import java.util.ArrayList;

public class Ranges {

    private static  Coord size;
    private static ArrayList<Coord> allCoords;

    public static void setSize(Coord coord){
        size = coord;
        allCoords = new ArrayList<>();
        for (int y=0; y<size.getY(); y++) {
            for (int x=0; x<size.getX(); x++) {
                allCoords.add(new Coord(x,y));
            }
        }
    }

    public static Coord getSize(){
        return size;
    }

    public static ArrayList<Coord> getAllCoord() {
        return allCoords;
    }

    static boolean inRange(Coord coord) {
        return coord.getX() >= 0 && coord.getX() < size.getX() &&
                coord.getY() >= 0 && coord.getY() < size.getY();
    }

}

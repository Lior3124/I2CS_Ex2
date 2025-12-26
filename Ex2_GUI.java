package assignments.Ex2;
/**
 * Intro2CS_2026A
 * This class represents a Graphical User Interface (GUI) for Map2D.
 * The class has save and load functions, and a GUI draw function.
 * You should implement this class, it is recommender to use the StdDraw class, as in:
 * https://introcs.cs.princeton.edu/java/stdlib/javadoc/StdDraw.html
 *
 *
 */
public class Ex2_GUI {

    /**
     * uses StdDraw to drow the map,
     * gets the height and the width of the map and draws grids, then goes through the map and fills each square with the value of the color that is inside the map
     * and then draws the grids again on top of the color(the first print of the grids is used for the StdDraw.fillSquare func)
     * @param map
     */
    public static void drawMap(Map2D map) {
        int height = map.getHeight(); //change to getHeight
        int width = map.getWidth();  //change to getWidth

        //set the scale for the map
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);

        //pen color
        StdDraw.setPenColor(StdDraw.BLACK);

        //draws the grid
        for(int i=0;i<=height;i++) {
            StdDraw.line(0,i,width,i);
        }
        for(int i=0;i<=width;i++){
            StdDraw.line(i,0,i,height);
        }

        //puts the desired color in each square of the map
        int colorValue;
        java.awt.Color actualColor;
        for(int i=0;i<height;i++){
            for(int j =0;j<width;j++){
                colorValue = map.getPixel(j,i);
                actualColor = new java.awt.Color(colorValue);

                StdDraw.setPenColor(actualColor);
                StdDraw.filledSquare(j+0.5,height - 0.5 - i,0.5); //+0.5 because StdDraw fills the square based on it's center
            }
        }

        //draws the grid
        for(int i=0;i<=height;i++) {
            StdDraw.line(0,i,width,i);
        }
        for(int i=0;i<=width;i++){
            StdDraw.line(i,0,i,height);
        }
    }

    /**
     * @param mapFileName
     * @return
     */
    public static Map2D loadMap(String mapFileName) {
        Map2D ans = null;

        return ans;
    }

    /**
     *
     * @param map
     * @param mapFileName
     */
    public static void saveMap(Map2D map, String mapFileName) {


    }
    public static void main(String[] a) {
        String mapFile = "map.txt";
        //Map2D map = loadMap(mapFile);
        int [][] arr = {
                {-65536,-16711936,-16776961},
                {-23296,1,-65281}
        };
        Map2D map = new Map(arr);
        drawMap(map);
    }
    /// ///////////// Private functions ///////////////
}

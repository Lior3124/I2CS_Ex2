package assignments.Ex2;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

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
     * gets a file and turns the input in the file into a map
     * @param mapFileName - the name of the file
     * @return Map2D with the map that is inside the file
     */
    public static Map2D loadMap(String mapFileName) {
        Map2D ans = null;
        try{
            File f = new File(mapFileName);
            Scanner s = new Scanner(f);
            int width =0;
            if(s.hasNextLine()){
                String line = s.nextLine();
                String[] split = line.split(" ");
                width = split.length;
            }
            int height=1;
            while(s.hasNextLine()){
                s.nextLine();
                height++;
            }
            s.close();
            ans = new Map(height,width,0);
            Scanner s2 = new Scanner(f);
            for(int i=0;i<height;i++) {
                for(int j=0;j<width;j++) {
                    ans.setPixel(j,i,s2.nextInt());
                }
                s2.nextLine();
            }
            s2.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return ans;
    }

    /**
     * get a map and saves the map into a file with the given name
     * @param map - Map2D the map we want to save into ourfile
     * @param mapFileName - the name of the file
     */
    public static void saveMap(Map2D map, String mapFileName) {
        try{
            FileWriter fw = new FileWriter(mapFileName);
            for(int i=0;i<map.getHeight();i++) {
                for(int j=0;j<map.getWidth();j++) {
                    fw.write(map.getPixel(j,i)+" ");
                }
                fw.write("\n");
            }
            fw.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void main(String[] a) {
        String mapFile = "map.txt";
        int [][] arr = {        //red green blue orange black different blue
                {-65536,-16711936,-16776961},
                {-23296,1,-65281}
        };
        Map2D map = new Map(arr);
        saveMap(map, mapFile);
        Map2D map2 = loadMap(mapFile);
        drawMap(map2);
    }
    /// ///////////// Private functions ///////////////
}

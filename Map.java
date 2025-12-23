package assignments.Ex2;
import java.io.Serializable;
/**
 * This class represents a 2D map (int[w][h]) as a "screen" or a raster matrix or maze over integers.
 * This is the main class needed to be implemented.
 *
 * @author boaz.benmoshe
 *
 */
public class Map implements Map2D, Serializable{

    // edit this class below

    private int [][] map;
	/**
	 * Constructs a w*h 2D raster map with an init value v.
	 * @param w - the width of the underlying 2D array.
	 * @param h - the height of the underlying 2D array.
	 * @param v - the init value of all the entries in the 2D array.
	 */
	public Map(int w, int h, int v) {
        init(w, h, v);
    }

	/**
	 * Constructs a square map (size*size).
	 * @param size - the width and the height of the underlying 2d square array
	 */
	public Map(int size) {
        this(size,size, 0);
    }
	
	/**
	 * Constructs a map from a given 2D array.
	 * @param data - 2D array that will represent the Map
	 */
	public Map(int[][] data) {
		init(data);
	}

    /**
     * Constructs a 2D array of ints, filled with a given value
     *
     * @param w the width of the underlying 2D array.
     * @param h the height of the underlying 2D array.
     * @param v the init value of all the entries in the 2D array.
     *
     * Sets map with the given width and height and loop over all the indexes and fills each index with the given value v
     */
	@Override
	public void init(int w, int h, int v) {
        map = new int[w][h];
        for(int i = 0; i < w; i++){
            for(int j = 0; j < h; j++){
                map[i][j] = v;
            }
        }
	}

    /**
     * Constructs a 2D array of ints from a given array
     * @param arr a 2D int array.
     * @throws if the given array is empty, null or ragged
     *
     * First, checks if the array is null or the length is zero(empty) and then check if each collum has the same length as the first
     * than goes over the given array and puts each value in its index in map
     */
	@Override
	public void init(int[][] arr) {
        if(arr == null || arr.length == 0){
            throw new RuntimeException("Array is null");
        }
        int check_ragged = arr[0].length;
        for(int i = 1; i < arr.length; i++){
            if(arr[i].length != check_ragged){
                throw new RuntimeException("Array is ragged");
            }
        }
        this.map = new int[arr.length][check_ragged];
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < check_ragged; j++){
                this.map[i][j] = arr[i][j];
            }
        }
	}

    /**
     * makes a deep copy of the underlying 2D array
     * @return a deep copy of the underline matrix.
     *
     * makes a new 2D array and copies each value of this array to the new array
     */
	@Override
	public int[][] getMap() {
		int[][] ans = null;
        ans = new int[map.length][map[0].length];
        for(int i = 0; i < this.map.length; i++){
            for(int j = 0; j < this.map[i].length; j++){
                ans[i][j] = this.map[i][j];
            }
        }
		return ans;
	}

    /**
     * @return the width of the map
     * the width of the map is the x value of the array constructor
     * int[][] new arr = new int[x][y]
     * (the number of columns)
     */
	@Override
	public int getWidth() {
        int ans = -1;
        ans = map[0].length;
        return ans;
    }

    /**
     * @return the height of the map
     * the height of the map is the y value of the array constructor
     * int[][] new arr = new int[x][y]
     *(the number of rows)
     */
	@Override
	public int getHeight() {
        int ans = -1;
        ans = map.length;
        return ans;
    }

    /**
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the value of the map at the given coordinates
     * the value is map[x][y], but first it check if the coordinates are inside the map
     */
	@Override
	public int getPixel(int x, int y) {
        int ans = -1;
        if(x<this.map[0].length && y<this.map.length){
            ans = this.map[y][x];
        }
        return ans;
    }

    /**
     * @param p a Index2D representing a point in the map
     * @return the value of the map at the given point(Index2D)
     * the value is map[p.x][p.y], but first it check if the coordinates are inside the map
     */
	@Override
	public int getPixel(Pixel2D p) {
        int ans = -1;
        int x = p.getX();
        int y = p.getY();
        if(x<this.map[0].length && y<this.map.length) {
            ans = map[y][x];
        }
        return ans;
	}

    /**
     * @param x the x coordinate
     * @param y the y coordinate
     * @param v the value that will be assigned to map in the given coordinates.
     * first it check if the coordinates are inside the map
     * and then assigns map[x][y] to v
     */
	@Override
	public void setPixel(int x, int y, int v) {
        if(x<this.map[0].length && y<this.map.length) {
            map[y][x] = v;
        }
    }

    /**
     * @param p a Index2D representing a point in the map
     * @param v the value that will be assigned to map in the given point(Index2D).
     * first it check if the coordinates are inside the map
     * and then assigns map[p.x][p.y] to v
     */
	@Override
	public void setPixel(Pixel2D p, int v) {
        int x = p.getX();
        int y = p.getY();
        if(x<this.map[0].length && y<this.map.length) {
            map[y][x] = v;
        }
	}

    /**
     * @param p a Index2D representing a point
     * @return true if the point(Index2D) is inside the map and false if isn't
     * finds the height and width of this map and check if p.x and p.y are higher than width and height of the map(respectively)
     * if then the point(Index2D) is not inside
     */
    @Override
    public boolean isInside(Pixel2D p) {
        boolean ans = true;
        int height = this.map.length;
        int width = this.map[0].length;
        if(p.getX()>width || p.getY()>height || p.getX()<0 || p.getY()<0){
            ans = false;
        }
        return ans;
    }

    /**
     * @param p a given map
     * @return true if p and this map have the same dimensions, otherwise false
     * finds this maps height and width and then compares it to p.getHeight and p.getWidth
     */
    @Override
    public boolean sameDimensions(Map2D p) {
        boolean ans = false;
        int height = this.map.length;
        int width = this.map[0].length;
        if(p.getWidth() ==width && p.getHeight()== height){
            return true;
        }
        return ans;
    }

    /**
     * @param p - the map that should be added to this map (just in case they have the same dimensions).
     * if this map and p have the same dimensions add each value at the indexes of p to the values at the same indexes in this map
     */
    @Override
    public void addMap2D(Map2D p) {
        if(this.sameDimensions(p)){
            for(int i = 0; i < this.map.length; i++){
                for(int j = 0; j < this.map[i].length; j++){
                    this.map[i][j]  = this.map[i][j] + p.getPixel(i,j);
                }
            }
        }
    }

    /**
     * @param scalar - a number to multiply the map(matrix) by
     * goes through each value at this map and multiplies it by teh given scalar
     */
    @Override
    public void mul(double scalar) {
        for(int i = 0; i < this.map.length; i++){
            for(int j = 0; j < this.map[i].length; j++){
                double new_value =  scalar*this.map[i][j];
                this.map[i][j] = (int) new_value;
            }
        }
    }

    /**there are 4 different cases to rescale
     *1.sx >1 and sy>1
     *2.sx <1 and sy>1
     *3.sx >1 and sy<1
     *4.sx <1 and sy<1
     * for each case we convert the existing values different
     * if sx>1 than j will loop over from 0 to this map's height
     * and will put this map's value in j to arr j*sx value, same with the sy
     * if sx<1 than j will loop over from 0 to new map's j height
     * and will put this map's value in j/sx in the new map's j height, same with sy
     */
    @Override
    public void rescale(double sx, double sy) {
        double dheight = this.getHeight()*sy;
        double dwidth = this.getWidth()*sx;
        int new_height = (int)(dheight);
        int new_width = (int)(dwidth);
        int[][] arr = new int[new_height][new_width];
        if(new_height >= this.getHeight() && (new_width >= this.getWidth())){
            for(int i = 0; i < this.getHeight(); i++){
                for(int j = 0; j < this.getWidth(); j++){
                    arr[(int)(i*sy)][(int)(j*sx)] = this.map[i][j];
                }
            }
        }
        if(new_height <= this.getHeight() && (new_width <= this.getWidth())){
            for(int i = 0; i < new_height; i++){
                for(int j = 0; j < new_width; j++){
                    arr[i][j] = this.map[(int)(i/sy)][(int)(j/sx)];
                }
            }
        }
        if(new_height >= this.getHeight() && (new_width <= this.getWidth())){
            for(int i = 0; i < this.getHeight(); i++){
                for(int j = 0; j < new_width; j++){
                    arr[(int)(i*sy)][j] = this.map[i][(int)(j/sx)];
                }
            }
        }
        if(new_height <= this.getHeight() && (new_width >= this.getWidth())){
            for(int i = 0; i < new_height; i++){
                for(int j = 0; j < this.getWidth(); j++){
                    arr[i][(int)(j*sx)] = this.map[(int)(i/sy)][j];
                }
            }
        }



        this.map = arr;
    }

    @Override
    public void drawCircle(Pixel2D center, double rad, int color) {
        if(this.isInside(center)) {
            for (int i = 0; i < this.map.length; i++) {
                for (int j = 0; j < this.map[i].length; j++) {
                    Index2D p = new Index2D(i, j);
                    if (center.distance2D(p) < rad) {
                        this.map[i][j] = color;
                    }
                }

            }
        }else{
            throw new RuntimeException("Not inside this circle");
        }
    }

    @Override
    public void drawLine(Pixel2D p1, Pixel2D p2, int color) {
        int dx = Math.abs(p1.getX() - p2.getX());
        int dy = Math.abs(p1.getY() - p2.getY());
        double delta = (double) dy/dx;

        if(p1.equals(p2)){
            this.setPixel(p1, color);
        }

        if(dx>=dy && p1.getX()<p2.getX()){
           for(int i = 0; i < dx+1; i++){
               double y = p1.getY() + delta * (p1.getX()+i - p1.getX());
               int f = Math.round((float) y);
               this.setPixel(p1.getX()+i,f, color);
           }
        }

        if(dx>=dy && p1.getX()>p2.getX()){drawLine(p2,p1,color);}

        if(dx<dy && p1.getY()<p2.getY()){
            double new_d = 1/delta;
            for(int i = 0; i < dy+1; i++){
                double x = new_d*(p1.getY()+i- p1.getY())+p1.getX();
                int g = Math.round((float) x);
                this.setPixel(g,p1.getY()+i, color);
            }

        }
        if(dx<dy && p1.getY()>p2.getY()){drawLine(p2,p1,color);}
    }

    @Override
    public void drawRect(Pixel2D p1, Pixel2D p2, int color) {
        if(this.isInside(p1) && this.isInside(p2)){
            int smallerX =p1.getX();
            int biggerX=p2.getX();
            if(p1.getY()>p2.getX()){
                smallerX = p2.getX();
                biggerX = p1.getX();

            } else if (p1.getX()<p2.getX()) {
                smallerX = p1.getX();
                biggerX = p2.getX();
            }
            for(int i = 0; i < this.map.length; i++){
                for(int j = smallerX; j <= biggerX; j++){
                    if((p1.getY()>=i && p2.getY()<=i)||(p1.getY()<=i && p2.getY()>=i)){
                        this.map[i][j] = color;
                    }
                }
            }
        }
    }

    /**
     * @param ob the reference object with which to compare.
     * @return true if the two maps are equals(have the same dimension and values) otherwise false
     * first uses sameDimensions to check both dimensions and then loops over all the indexes to check that all the values are the same
     */
    @Override
    public boolean equals(Object ob) {
        boolean ans = false;
        if(ob instanceof Map2D){
            if(!(this.sameDimensions((Map2D)ob))){
                return false;
            }
            for(int i = 0; i < this.map.length; i++){
                for(int j = 0; j < this.map[i].length; j++){
                    if(this.getPixel(i,j) != ((Map2D) ob).getPixel(i,j)){
                        return false;
                    }
                }
            }
            return true;
        }
        return ans;
    }

	@Override
	/** 
	 * Fills this map with the new color (new_v) starting from p.
	 * https://en.wikipedia.org/wiki/Flood_fill
	 */
	public int fill(Pixel2D xy, int new_v,  boolean cyclic) {
        int old_v = this.getPixel(xy);

		return floodFill(xy,new_v,old_v,cyclic);
	}

    public int floodFill(Pixel2D xy, int new_v, int old_v, boolean cyclic) {
        int ans =0;
            if(!(this.isInside(xy))){           // if point is outside map do nothing
                return 0;
            }
            if(this.getPixel(xy) != old_v){     // if point doesn't have the same color do nothing
                return 0;
            }
            if(this.getPixel(xy) == new_v){
                return 0;                       //if point already is the new color do nothing
            }
            this.setPixel(xy, new_v);           // change the color of the point
            ans ++;
            //recursive call to each side(Up,Down,Left,Right)\
            Index2D p_right = new Index2D(xy.getX()+1,xy.getY());
            if(cyclic){
                p_right = new Index2D((xy.getX()+1)%this.getWidth(),xy.getY());
            }
            if(this.isInside(p_right) &&  this.getPixel(p_right) == old_v) {
                ans = ans + floodFill(p_right, new_v, old_v, cyclic);
            }

            Index2D p_left = new Index2D(xy.getX()-1,xy.getY());
            if(cyclic){
                p_left = new Index2D((xy.getX()-1+this.getWidth())%this.getWidth(),xy.getY());
            }
            if(this.isInside(p_left) &&  this.getPixel(p_left) == old_v) {
                ans = ans + floodFill(p_left, new_v, old_v, cyclic);
            }

            Index2D p_up = new Index2D(xy.getX(),xy.getY()+1);
            if(cyclic){
                p_up = new Index2D(xy.getX(),(xy.getY()+1)%this.getHeight());
            }
            if(this.isInside(p_up) &&  this.getPixel(p_up) == old_v) {
                ans = ans + floodFill(p_up, new_v, old_v, cyclic);
            }

            Index2D p_down = new Index2D(xy.getX(),xy.getY()-1);
            if(cyclic){
                p_down = new Index2D(xy.getX(),(xy.getY()-1+this.getHeight())%this.getHeight());
            }
            if(this.isInside(p_down) &&  this.getPixel(p_down) == old_v) {
                ans = ans + floodFill(p_down, new_v, old_v, cyclic);
            }

        return ans;
    }

	@Override
	/**
	 * BFS like shortest the computation based on iterative raster implementation of BFS, see:
	 * https://en.wikipedia.org/wiki/Breadth-first_search
	 */
	public Pixel2D[] shortestPath(Pixel2D p1, Pixel2D p2, int obsColor, boolean cyclic) {
		Pixel2D[] ans = null;  // the result.

		return ans;
	}

    @Override
    public Map2D allDistance(Pixel2D start, int obsColor, boolean cyclic) {
        Map2D ans = null;  // the result.

        return ans;
    }
	////////////////////// Private Methods ///////////////////////

}

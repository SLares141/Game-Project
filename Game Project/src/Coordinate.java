
public class Coordinate {
	    int x;
	    int y;
	    Coordinate(int x, int y) { this.x = x ; this.y = y; }
	    public int hashCode() { return x >> 16 & y; }
	    public boolean equals(Object o) { if (o instanceof Coordinate) { Coordinate c = (Coordinate)o; return c.x==x && c.y==y;} return false; }
	    public void setX(int a){x = a;}
	    public void setY(int a){y = a;}
	    public void set(int a, int b){
	    	x = a;
	    	y = b;
	    }
	    public void set(Coordinate c){
	    	this.x = c.x;
	    	this.y = c.y;
	    }
	}
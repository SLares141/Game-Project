
public class Coordinate {
	    final int x;
	    final int y;
	    Coordinate(int x, int y) { this.x = x ; this.y = y; }
	    public int hashCode() { return x >> 16 & y; }
	    public boolean equals(Object o) { if (o instanceof Coordinate) { Coordinate c = (Coordinate)o; return c.x==x && c.y==y;} return false; }
	}
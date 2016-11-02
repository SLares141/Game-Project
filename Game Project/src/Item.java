import java.awt.Image;

public class Item {
	
	protected String name;
	protected Image sprite;
	protected String description;
	protected int restoreAmt;
	
	public Item(String name, int restoreAmt) {
		this.name = name;
		this.restoreAmt = restoreAmt;
	}
}
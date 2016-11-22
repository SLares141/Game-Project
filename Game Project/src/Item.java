import java.awt.Image;

public class Item {
	
	protected String name;
	protected Image sprite;
	protected String description;
	
	public Item(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public boolean itemMatches(Item i) {
		if(name.equals(i.getName()))
			return true;
		return false;
	}
}
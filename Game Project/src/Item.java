import java.awt.Image;

public abstract class Item {
	
	protected String name;
	protected Image sprite;
	protected String description;
	
	public Item(String name) {
		this.name = name;
	}
	
	public abstract void use(Character c);
	
	public String getName() {
		return name;
	}
	public boolean equals(Item i) {
		if(name == i.getName())
			return true;
		return false;
	}
}
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JPanel;


	public Game() {
	
	}
	
	public static void main(String[] args) {
		
       
		Player player = new Player();
		
		player.setArmor(new Armor("none", 0, 0));
		player.setWeapon(new Weapon("none", 0));
		
		
		PartyMember pm1 = new PartyMember();
		PartyMember pm2 = new PartyMember();
		PartyMember pm3 = new PartyMember();
		Inventory inv = new Inventory();
		
		inv.add(new Consumable("Strawberry Juice", 10, true));
		inv.add(new Consumable("Strawberry Jam", 20, true), 5);
		inv.add(new Consumable("Strawberry Jelly", 30, true));
		inv.add(new Consumable("Strawberry Jelly", 30, true));
		inv.add(new Consumable("Elixer", 10, false), 10);
		
		inv.add(new Weapon("Sword", 10));
		inv.add(new Weapon("Sword", 10));
		inv.add(new Weapon("Staff", 10));
		inv.add(new Armor("Shield", 10, 10));
		inv.add(new Armor("Leather Cap", 1, 1), 10);
		
		
        //put all states into statemap
		StateMapSingleton stateMap = StateMapSingleton.getInstance();
		stateMap.put("menu", new MainMenuState());
		stateMap.put("field", new FieldState(player));
		stateMap.put("battle", new BattleState());
		stateMap.put("inventory", new InventoryMenuState(player, pm1, pm2, pm3, inv));
		
		StateStackSingleton stateStack = StateStackSingleton.getInstance();
		//the main menu is set as the initial state
		
		stateStack.push("inventory");
	}
}
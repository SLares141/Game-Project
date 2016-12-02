public class Game {
  
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
		
		inv.add(new Consumable("Strawberry Juice", 10, true), 99);
		inv.add(new Consumable("Strawberry Jam", 20, true), 5);
		inv.add(new Consumable("Strawberry Jelly", 30, true));
		inv.add(new Consumable("Strawberry Jelly", 30, true));
		inv.add(new Consumable("Elixer", 10, false), 10);
		inv.add(new Consumable("S", 30, true));
		inv.add(new Consumable("T", 30, true));
		inv.add(new Consumable("R", 30, true));
		inv.add(new Consumable("A", 30, true));
		inv.add(new Consumable("W", 30, true));
		inv.add(new Consumable("Berry", 30, true));
		inv.add(new Consumable("item", 30, true));
		inv.add(new Consumable("item2", 30, true));
		inv.add(new Consumable("i3", 30, true));
		inv.add(new Consumable("i4", 30, true));
		inv.add(new Consumable("!", 30, true));
		inv.add(new Consumable("~", 30, true));
		inv.add(new Consumable("lol", 30, true));
		inv.add(new Consumable("lolz", 30, true));
		inv.add(new Consumable("loz", 30, true));
		inv.add(new Consumable("moo moo milk", 30, true));
		
		
		inv.add(new Weapon("Sword", 10));
		inv.add(new Weapon("Sword", 10));
		inv.add(new Weapon("Staff", 10));
		inv.add(new Armor("Shield", 10, 10));
		inv.add(new Armor("Leather Cap", 1, 1), 10);
		
		
        //put all states into statemap
		StateMapSingleton stateMap = StateMapSingleton.getInstance();
		
		stateMap.put("menu", new MainMenuState(inv));
		stateMap.put("field", new FieldState(player, inv));
		stateMap.put("battle", new BattleState());
		
		stateMap.put("inventory", new InventoryMenuState(player, pm1, pm2, pm3, inv));
		
		StateStackSingleton stateStack = StateStackSingleton.getInstance();
		//the main menu is set as the initial state
		
		stateStack.push("menu");
	}
}
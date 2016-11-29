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
		
		
		inv.add(new Consumable("Strawberry Juice", 10));
		inv.add(new Consumable("Strawberry Jam", 20));
		inv.add(new Consumable("Strawberry Jelly", 30));
		inv.add(new Consumable("Strawberry Jelly", 30));
		inv.add(new Consumable("item", 30));
		inv.add(new Consumable("Long Item Name asdfghjkl", 30));
		inv.add(new Consumable("Moo moo milk", 30), 99);
		inv.add(new Consumable("TM87", 30));
		inv.add(new Consumable("item2", 30));
		inv.add(new Consumable("atm card", 30));
		inv.add(new Consumable("lemonade", 30));
		inv.add(new Consumable("key item", 30), 100);
		inv.add(new Consumable("item3", 30));
		inv.add(new Consumable("soda", 30));
		inv.add(new Weapon("sword", 10));
		inv.add(new Armor("armour", 30, 35));
		inv.add(new Consumable("random items", 30));
		inv.add(new Consumable("...", 30));
		inv.add(new Consumable("booty", 30));
		inv.add(new Consumable("booty", 30));
		
        //put all states into statemap
		StateMapSingleton stateMap = StateMapSingleton.getInstance();
		
		stateMap.put("menu", new MainMenuState());
		stateMap.put("field", new FieldState(player));
		stateMap.put("battle", new BattleState());
		
		stateMap.put("inventory", new InventoryMenuState(player, pm1, pm2, pm3, inv));
		
		StateStackSingleton stateStack = StateStackSingleton.getInstance();
		//the main menu is set as the initial state
		
		stateStack.push("menu");
	}
}
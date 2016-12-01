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
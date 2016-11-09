import java.util.HashMap;
import java.util.Stack;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class Game extends JPanel {
	private Dimension _screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private double _windowWidth = _screenSize.getWidth();
    private double _windowHeight = _screenSize.getHeight();
    private static WindowFrame _frame;
    private Graphics g;
   

	
	public Game() {
		createWindow();
		g = _frame.getGraphics();
	}
	//with the create window method within the game constructor,
	//we will be able to simplify the setup in the main method.
	private void createWindow() {
		_frame = WindowFrame.getInstance();
	}
	
	
	public static void main(String[] args) {
	
		Game g = new Game();  
       
		Player player = new Player();
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
		stateMap.put("field", new FieldState());
		stateMap.put("battle", new BattleState());
		
		stateMap.put("inventory", new InventoryMenuState(player, pm1, pm2, pm3, inv));
		
		StateStackSingleton stateStack = StateStackSingleton.getInstance();
		//the main menu is set as the initial state
		
		stateStack.push("inventory");
	}
}
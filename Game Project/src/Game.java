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
		
		inv.add(new Consumable("Strawberry Juice", 10, true));
		inv.add(new Consumable("Strawberry Jam", 20, true));
		inv.add(new Consumable("Strawberry Jelly", 30, true));
		inv.add(new Consumable("Strawberry Jelly", 30, true));
		inv.add(new Consumable("item", 30, false));
		inv.add(new Consumable("Long Item Name asdfghjkl", 30, false));
		inv.add(new Consumable("Moo moo milk", 30, false), 3);
		
		inv.add(new Weapon("sword", 10));
		inv.add(new Weapon("sword", 10));
		inv.add(new Weapon("staff", 10));
		inv.add(new Weapon("pencil", 10));
		inv.add(new Weapon("gun", 10));
		inv.add(new Armor("shield", 10, 10));
		
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
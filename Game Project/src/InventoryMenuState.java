import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class InventoryMenuState extends JPanel implements State {

	Character c;
	Inventory inv;
	

	public InventoryMenuState() {

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				update();
				render();
			}
		});
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEnter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub

	}
}

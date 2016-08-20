/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import Framework.GameObject;
import Framework.ObjectId;
import Framework.Texture;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import window.Game;

/**
 *
 * @author theBurlyone
 */
public class Block extends GameObject{
	
	Texture tex = Game.getInstance();
	private int type;
	
	public Block(float x, float y, int type, ObjectId id){
		super(x, y, id);
		this.type = type;
	}


	public void tick(LinkedList<GameObject> object) {
		
	}


	public void render(Graphics g) {
		if(type == 0) // dirt block
			g.drawImage(tex.block[0], (int)x, (int)y, null);
		if(type == 1) // grass block
			g.drawImage(tex.block[1], (int)x, (int)y, null);
		if(type == 2) // water top block
			g.drawImage(tex.block[2], (int)x, (int)y, null);
		if(type == 3) // water fill block
			g.drawImage(tex.block[3], (int)x, (int)y, null);
		if(type == 4) // lava top block
			g.drawImage(tex.block[4], (int)x, (int)y, null);
		if(type == 5) // lava fill block
			g.drawImage(tex.block[5], (int)x, (int)y, null);
		if(type == 6) // clouds
			g.drawImage(tex.block[6], (int)x, (int)y, null);
		if(type == 7) // door
			g.drawImage(tex.block[7], (int)x, (int)y, null);
	}

	public Rectangle getBounds(int w, int h) {
		return new Rectangle((int)x, (int) y, w, h);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int) y, 32, 32);
	}
}

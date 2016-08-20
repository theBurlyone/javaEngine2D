/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import Framework.GameObject;
import Framework.ObjectId;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author theBurlyone
 */
public class Bullet extends GameObject{

	public Bullet(float x, float y, ObjectId id, int velX) {
		super(x, y, id);
		this.velX = velX;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, 16, 16);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16,16);
	}
	
}

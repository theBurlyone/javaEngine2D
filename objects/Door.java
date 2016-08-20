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
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import window.BufferedImageLoader;

/**
 *
 * @author theBurlyone
 */
public class Door extends GameObject{
	private BufferedImage Door = null;
	public Door(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		
	}

	@Override
	public void render(Graphics g) {
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			Door = loader.loadImage("/block_sheet.png");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16,16);
	}
	
}

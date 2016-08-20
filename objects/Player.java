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
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import window.Animation;
import window.Game;
import window.Handler;

/**
 *
 * @author theBurlyone
 */
public class Player extends GameObject{

	private float width = 48, height=96;
	private float gravity = 0.4f;
	private final float MAX_SPEED = 500;
	
	
	private Handler handler;
	
	Texture tex = Game.getInstance();
	
	private Animation playerWalkRight;
	private Animation playerWalkLeft;
	
	public Player(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		
	  playerWalkRight = new Animation(5, tex.player[1]);
		playerWalkLeft = new Animation(5, tex.player[2]);
		
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		
		if(velX < 0) facing = -1;
		else if(velX > 0) facing = 1;
		
		if(falling || jumping){
			velY += gravity;
			
			if(velY > MAX_SPEED){
				velY = MAX_SPEED;
			}
		}
		Collision(object);
		
		playerWalkRight.runAnimation();
		playerWalkLeft.runAnimation();
	}
	
	private void Collision(LinkedList <GameObject> object){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Block){
				
				if(getBoundsTop().intersects(tempObject.getBounds())){
					y = tempObject.getY() + height/2;
					velY = 0;
				}
				
				if(getBounds().intersects(tempObject.getBounds())){
					y = tempObject.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;
				}else{
					falling = true;
				}
				
				if(getBoundsRight().intersects(tempObject.getBounds())){
					x = tempObject.getX() - width;
				}
				
				if(getBoundsLeft().intersects(tempObject.getBounds())){
					x = tempObject.getX() + (width-13);
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		if(jumping){
			if(facing == 1){
					g.drawImage(tex.player[0], (int) x, (int) y, 48, 96, null);
			}else{
				g.drawImage(tex.player[0], (int) x, (int) y, 48, 96, null);
			}
		}else{
			if(velX != 0){
				if(facing == 1){
					playerWalkRight.drawAnimation(g, (int)x, (int)y, 48, 96);
				}else{
					playerWalkLeft.drawAnimation(g, (int)x, (int)y, 48, 96);
				}
			}else{
				if(facing == 1){
					g.drawImage(tex.player[0], (int) x, (int) y, 48, 96, null);
				}else{
					g.drawImage(tex.player[0], (int) x, (int) y, 48, 96, null);
				}
			}
		}
	}


	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) ((int)x+(width/2)-((width/2)/2)), (int) ((int)y+(height/2)), (int)width/2, (int)height/2);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int)x+(width/2)-((width/2)/2)), (int)y, (int)width/2, (int)height/2);
	}
	
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int)x+width-5), (int)y+5, (int)5, (int)height-10);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y+5, (int)5, (int)height-10);
	}
	
}

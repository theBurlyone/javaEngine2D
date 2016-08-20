/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package window;
import Framework.KeyInput;
import Framework.ObjectId;
import Framework.Texture;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import objects.Block;
import objects.Player;


public class Game extends Canvas implements Runnable {
	
	private boolean running = false;
	private Thread thread;
	public static int frames = 0;
	
	public static int WIDTH, HEIGHT;
	
	
	private BufferedImage level = null;
	private BufferedImage clouds = null;
	
	Handler handler;
	Camera cam;
	static Texture tex;
	
	private void init(){
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		tex = new Texture();
		
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/level.png"); // loading the level
		clouds = loader.loadImage("/cloud.png"); // cloud image
		
		handler = new Handler();
		
		cam = new Camera(0,0);
		
		loadImageLevel(level);
		
		this.addKeyListener(new KeyInput(handler));
	}
	
	public synchronized void start(){
		if(running)
			return;
		
			running = true;
			thread = new Thread(this);
			thread.start();
			return;
		
	}
	
	@Override
  public void run(){
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}
	private void tick(){
		handler.tick();
		for(int i = 0; i < handler.object.size(); i++){
			if(handler.object.get(i).getId() == ObjectId.Player){
				cam.tick(handler.object.get(i));
			}
		}
		
	}
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		//////////////////////////////////
		
		//Draw here
		g.setColor(Color.decode("#d3e0ff"));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g2d.translate(cam.getX(), cam.getY()); // Begin cam
		
		for(int xx = 0; xx < clouds.getWidth() * 50; xx += clouds.getWidth()){
			g.drawImage(clouds, xx, -10, this);
		}
		handler.render(g);
		
		
		
	  g2d.translate(-cam.getX(), -cam.getY()); // End cam

/////////////////////////////////
		g.dispose();
		bs.show();
	}
	
	private void loadImageLevel(BufferedImage image){
		int w = image.getWidth();
		int h = image.getHeight();
		
		for(int xx = 0; xx < h; xx++){
			for(int yy = 0; yy < w; yy++){
				int pixel = image.getRGB(xx, yy);
				
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 162 && green == 116 && blue == 43){
					handler.addObject(new Block(xx*32, yy*32, 0, ObjectId.Block));
				}
				if(red == 0 && green == 255 && blue == 0){
					handler.addObject(new Block(xx*32, yy*32, 1, ObjectId.Block));
				}
				if(red == 0 && green == 187 && blue == 255){
					handler.addObject(new Block(xx*32, yy*32, 2, ObjectId.Block));
				}
				if(red == 0 && green == 0 && blue == 255){
					handler.addObject(new Block(xx*32, yy*32, 3, ObjectId.Block));
				}
				if(red == 255 && green == 106 && blue == 0){
					handler.addObject(new Block(xx*32, yy*32, 4, ObjectId.Block));
				}
				if(red == 255 && green == 0 && blue == 0){
					handler.addObject(new Block(xx*32, yy*32, 5, ObjectId.Block));
				}
				if(red == 255 && green == 255 && blue == 255){
					handler.addObject(new Block(xx*32, yy*32, 6, ObjectId.Block));
				}
				if(red == 255 && green == 242 && blue == 0){
					handler.addObject(new Block(xx*32, yy*32, 7, ObjectId.Door));
				}
				
				if(red == 196 && green == 0 && blue == 255){
					handler.addObject(new Player(xx*32, yy*32, handler, ObjectId.Player));
				}
			}
		}
	}
	
	public static Texture getInstance(){
		return tex;
	}
	
	public static void main(String args[]){
		new Window(800, 600, "The Legend - In Progress", new Game());
	}
}
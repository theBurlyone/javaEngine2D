/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework;

import java.awt.image.BufferedImage;
import window.BufferedImageLoader;

/**
 *
 * @author theBurlyone
 */
public class Texture {
	SpriteSheet bs, ps;
	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;
	
	public BufferedImage[] block = new BufferedImage[20];
	public BufferedImage[] player = new BufferedImage[20];
	public BufferedImage[] player_jump = new BufferedImage[20];
	
	
	public Texture(){
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try{
			block_sheet = loader.loadImage("/block_sheet.png");
			player_sheet = loader.loadImage("/player_sheet.png");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		bs = new SpriteSheet(block_sheet);
		ps = new SpriteSheet(player_sheet);
		
		getTextures();
	}
	
	private void getTextures(){
		block[0] = bs.grabImage(1, 1, 32, 32); // dirt block
		block[1] = bs.grabImage(2, 1, 32, 32); // grass block
	  block[2] = bs.grabImage(3, 1, 32, 32); // water top block
		block[3] = bs.grabImage(4, 1, 32, 32); // water fill block
		block[4] = bs.grabImage(5, 1, 32, 32); // lava top block
		block[5] = bs.grabImage(6, 1, 32, 32); // lava fill block
		block[6] = bs.grabImage(7, 1, 32, 32); // cloud block
		block[7] = bs.grabImage(8, 1, 32, 32); // door block
		
		player[0] = ps.grabImage(1, 1, 48, 64); // idle frame for player
		
		
		
		
		player[1] = ps.grabImage(2, 1, 48, 64); // walk right frame for player
		
		
		
		
		player[2] = ps.grabImage(3, 1, 48, 64); // walk left frame for player
		
		
		
		player_jump[0] = ps.grabImage(1, 1, 48, 64);
	}
}

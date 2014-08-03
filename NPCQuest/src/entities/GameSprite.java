package entities;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import entities.helpers.Animation;

import Levels.Camera;
import Levels.Room;
import Managers.ResourceManager;

public class GameSprite extends GameObject{
	public String img_name;
	public Animation animation;
	public boolean visible = true;
	public float opacity = 1.0f;
	
	public GameSprite(float x, float y, int lb, int tb, int rb, int bb, String img_name){
		super(x, y, lb, tb, rb, bb);
		type = "GameSprite";
		this.img_name = img_name;
		
		animation = new Animation(1, 16, 16, 16);
	}
	
	@Override
	public void Update(Room room){
		animation.Update();
		super.Update(room);
	}
	
	@Override
	public void Render(Graphics2D g2d, Camera camera){
		if (!visible) return;
		Animation a = animation;
		int row = animation.rel_ani_y;
		int column = animation.rel_ani_x + animation.curr_frame;
		int dx = (int)Math.round(this.x-camera.x+camera.screen_offset_x+a.x_offset);
		int dy = (int)Math.round(this.y-camera.y+camera.screen_offset_y+a.y_offset);
		int sx = a.frame_width*column+a.abs_ani_x;
		int sy = a.frame_height*row+a.abs_ani_y;
		
		Image image = ResourceManager.images.get(img_name);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
		g2d.drawImage(image,
			//DESTINATION
			dx, dy, dx+a.frame_width, dy+a.frame_height,
			//SOURCE
			sx, sy, sx + a.frame_width, sy + a.frame_height,
			//OBSERVER
			null); 
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
	}
}

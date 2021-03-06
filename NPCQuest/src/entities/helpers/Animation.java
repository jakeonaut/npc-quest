package entities.helpers;

public class Animation {
	public int max_frame;
	public int curr_frame;
	public int frame_count = 0;
	public int frame_delay;
	public int frame_width;
	public int frame_height;
	public int rel_ani_x = 0;
	public int rel_ani_y = 0;
	public int abs_ani_x = 0;
	public int abs_ani_y = 0;
	public int x_offset = 0;
	public int y_offset = 0;
	public boolean animation_end = false;
	public boolean frame_change = false;
	public boolean repeat = true;
	
	public Animation(int max_frame, int frame_delay, int frame_width, int frame_height){
		this.max_frame = max_frame;
		this.frame_delay = frame_delay;
		this.frame_width = frame_width;
		this.frame_height = frame_height;
		
		Restart();
	}
	
	public void Restart(){
		curr_frame = 0;
		frame_count = 0;
		animation_end = false;
		frame_change = false;
	}
	
	public void Change(int rax, int ray, int mf){
		if (!(rel_ani_x == rax && rel_ani_y == ray && max_frame == mf)){
			rel_ani_x = rax;
			rel_ani_y = ray;
			max_frame = mf;
			Restart();
		}
	}
	
	public void Update(){
		frame_change = false;
		animation_end = false;
		
		frame_count++;
		if (frame_count >= frame_delay){
			if (curr_frame < max_frame) curr_frame++;
			if (curr_frame >= max_frame){
				if (repeat)
					curr_frame = 0;
				else curr_frame = max_frame-1;
				animation_end = true;
			}
			frame_count = 0;
			frame_change = true;
		}
	}
}

package jp.OzekiHideto.ReflektEngine.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import jp.OzekiHideto.ReflektEngine.Engine;

public class Bitmap {
	public int w,h;
	public int [] pixels;
	BufferedImage image;
	public Bitmap(String s){
		try {
			image = ImageIO.read(new File(s));
			w = image.getWidth();
			h = image.getHeight();
			pixels = image.getRGB(0, 0, w, h, null, 0, w);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Bitmap(Polygon p){
		int minx = Engine.min(p.x);
		int miny = Engine.min(p.y);
		int maxx = Engine.max(p.x);
		int maxy = Engine.max(p.y);
		w = maxx-minx;
		h = maxy-miny;
		if(w > 320*2)w = 320*2;
		if(h > 240*2)h = 240*2;
		this.pixels = new int[w*h];
		Screen screen = new Screen(w, h);
		screen.line(p.x[0]-minx, p.y[0]-miny, p.x[1]-minx, p.y[1]-miny, 0xffffff);
		screen.line(p.x[2]-minx, p.y[2]-miny, p.x[1]-minx, p.y[1]-miny, 0xffffff);
		screen.line(p.x[2]-minx, p.y[2]-miny, p.x[0]-minx, p.y[0]-miny, 0xffffff);
		screen.draw(pixels);
	}
	public Bitmap(BufferedImage image){
			w = image.getWidth();
			h = image.getHeight();
			pixels = image.getRGB(0, 0, w, h, null, 0, w);
	}
	public void Shader(){
		
	}
}

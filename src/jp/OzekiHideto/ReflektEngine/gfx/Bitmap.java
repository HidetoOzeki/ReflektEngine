package jp.OzekiHideto.ReflektEngine.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
	public void Shader(){
		
	}
}

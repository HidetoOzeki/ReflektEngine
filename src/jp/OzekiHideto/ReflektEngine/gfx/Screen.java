package jp.OzekiHideto.ReflektEngine.gfx;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import jp.OzekiHideto.ReflektEngine.Physics.Body;
import jp.OzekiHideto.ReflektEngine.Physics.Box;
import jp.OzekiHideto.ReflektEngine.Physics.PhysicsObject;
import jp.OzekiHideto.ReflektEngine.Physics.vec2;

public class Screen {
	public int w;
	public int h;
	public int xoffset, yoffset;
	int[] pixels;
	public static final int CAMERA_INSTANT = 0;
	public static final int CAMERA_CHASE = 1;
	public int CAMERAMODE = CAMERA_INSTANT;
	public int FONTSCALE = 1;
	int color = 0;

	public Screen(int width, int height) {
		w = width;
		h = height;
		pixels = new int[w * h];
		xoffset = 0;
		yoffset = 0;
	}

	public void clear(int col) {
		for (int i = 0; i < w * h; i++) {
			pixels[i] = col;
		}
	}
	public void render(int x,int y,Bitmap img){
		render(x,y,img,1);
	}
	public void render(int x,int y,Bitmap img,int ignorecolor){
		render(x,y,img,ignorecolor,1);
	}
	public void render(int x, int y, Bitmap img,int ignorecolor,int scale) {
		x -= xoffset;
		y -= yoffset;
		for (int i = 0; i < img.w*scale; i++) {
			for (int j = 0; j < img.h*scale; j++) {
				int c = img.pixels[i/scale + j/scale * img.w];
				if(c!=ignorecolor){
				putpixel(x + i, y + j, c);
				}
			}
		}
	}
	public void Font(String s,int x,int y,int col){
		if(col==0xffffff){
			col = 0xffffff-1;
		}
		BufferedImage fonts = new BufferedImage(7*s.length(), 9, BufferedImage.TYPE_INT_RGB);
		Graphics fg = fonts.getGraphics();
		fg.fillRect(0, 0, fonts.getWidth(), fonts.getHeight());
		fg.setColor(new Color(col));
		fg.drawString(s, 0, 9);
		Bitmap f = new Bitmap(fonts);
		render(x,y,f,-1,FONTSCALE);
	}

	public void draw(PhysicsObject po) {
		vec2 v0 = po.getVector()[0];
		vec2 v1 = po.getVector()[1];
		line((int)v0.getX(), (int)v0.getY(), (int)v1.getX(), (int)v1.getY(), color);
		//rect((int)v0.getX() - 1, (int)v0.getY() - 1, 3, 3, 0xffff00);
		//rect((int)v1.getX() - 1, (int)v1.getY() - 1, 3, 3, 0xffff00);
	}
	public void draw(Box box){
		line(box.x, box.y, box.x+box.xs, box.y, color);
		line(box.x, box.y, box.x, box.y+box.ys, color);
		line(box.x+box.xs, box.y, box.x+box.xs, box.y+box.ys, color);
		line(box.x, box.y+box.ys, box.x+box.xs, box.y+box.ys, color);
	}
	public void setColor(int col){
		color =col;
	}
	public int getColor(){
		return color;
	}

	public void draw(Body b) {
		vec2 np = vec2.incvec(b.getPos(), b.getVel());
		rect((int) b.getPos().getX()-1, (int) b.getPos().getY()-1, 2, 2, color);
		line((int) b.getPos().getX(), (int) b.getPos().getY(),(int)np.getX(),(int)np.getY(), color);
	}

	public int blend(int c, int c1, double ratio) {
		double ratioH = 1.0 - ratio;
		int r1 = (c & 0xff0000) >> 16;
		int g1 = (c & 0xff00) >> 8;
		int b1 = (c & 0xff);

		int r2 = (c1 & 0xff0000) >> 16;
		int g2 = (c1 & 0xff00) >> 8;
		int b2 = (c1 & 0xff);

		int r = (int) ((r1 * ratio) + (r2 * ratioH));
		int g = (int) ((g1 * ratio) + (g2 * ratioH));
		int b = (int) ((b1 * ratio) + (b2 * ratioH));
		return r << 16 | g << 8 | b;
	}

	public void rect(int x, int y, int sx, int sy, int col) {
		x -= xoffset;
		y -= yoffset;
		for (int i = x; i < x + sx; i++) {
			for (int j = y; j < y + sy; j++) {
				putpixel(i, j, col);
			}
		}
	}

	public void line(int x, int y, int x1, int y1, int col) {
		x -= xoffset;
		y -= yoffset;
		x1 -= xoffset;
		y1 -= yoffset;
		int px = (x - x1);
		int py = (y - y1);
		int length = (int) Math.sqrt((px * px) + (py * py));
		for (int i = 0; i < length; i++) {
			int dx = (px * i) / length;
			int dy = (py * i) / length;
			putpixel(x - dx, y - dy, col);
		}
	}

	public void edge(int[] x, int[] y, int col) {
		int len = x.length;
		for (int i = 0; i < len - 1; i++) {
			line(x[i], y[i], x[i + 1], y[i + 1], col);
		}
	}

	public void putpixel(int x, int y, int col) {
		if (x > 0 && x < w) {
			if (y > 0 && y < h) {
				pixels[x + y * w] = col;
			}
		}
	}

	public int pixel(int x, int y) {
		if (x > 0 && x < w) {
			if (y > 0 && y < h) {
				return pixels[x + y * w];
			}
		}
		return 0;
	}

	public void draw(int[] pix) {
		for (int i = 0; i < pixels.length; i++) {
			pix[i] = pixels[i];
		}
	}

	public int[] getRaster() {
		return pixels;
	}

	public void setOffset(int x, int y) {
		if (CAMERAMODE == CAMERA_INSTANT) {
			this.xoffset = x;
			this.yoffset = y;
		}
		if (CAMERAMODE == CAMERA_CHASE) {
			int px = xoffset - x;
			int py = yoffset - y;
			int length = (int) Math.sqrt((px * px) + (py * py));
			xoffset -= px * length;
			yoffset -= py * length;
		}
	}
}

package jp.OzekiHideto.ReflektEngine.gfx;

import java.awt.Color;
import java.awt.Point;

public class Screen {
	public int w;
	public int h;
	public int xoffset, yoffset;
	int[] pixels;
	public int[] bright;
	public static final int CAMERA_INSTANT = 0;
	public static final int CAMERA_CHASE = 1;
	public int CAMERAMODE = CAMERA_INSTANT;

	public Screen(int width, int height) {
		w = width;
		h = height;
		pixels = new int[w * h];
		bright = new int[w * h];
		xoffset = 0;
		yoffset = 0;
	}
	public void clear(int col) {
		for (int i = 0; i < w * h; i++) {
			pixels[i] = col;
			bright[i] = 0;
		}
	}

	public void render(int x, int y, Bitmap img) {
		x -= xoffset;
		y -= yoffset;
		for (int i = 0; i < img.w; i++) {
			for (int j = 0; j < img.h; j++) {
				putpixel(x + i, y + j, img.pixels[i + j * img.w]);
			}
		}
	}

	public void light(int x, int y, int s, int col) {
		for (int i = (int) x - s; i < x + s; i++) {
			for (int j = (int) y - s; j < y + s; j++) {
				int dx = x - i;
				int dy = y - j;
				int dist = (int) Math.sqrt(dx * dx + dy * dy);
				if (dist < s) {
					int c = (256 - dist) * (256 / s) << col;
					int li = lights(i - xoffset, j - yoffset);
					int co = (c + li);
					putlight(i - xoffset, j - yoffset, co);
				}
			}
		}
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
			putpixel(x - dx,y - dy, col);
		}
	}

	public void edge(int[] x, int[] y, int col) {
		int len = x.length;
		for(int i = 0;i < len-1;i++){
			line(x[i],y[i],x[i+1],y[i+1],col);
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

	public void putlight(int x, int y, int col) {
		if (x > 0 && x < w) {
			if (y > 0 && y < h) {
				bright[x + y * w] = col;
			}
		}
	}

	public int lights(int x, int y) {
		if (x > 0 && x < w) {
			if (y > 0 && y < h) {
				return bright[x + y * w];
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

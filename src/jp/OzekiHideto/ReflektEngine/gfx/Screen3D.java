package jp.OzekiHideto.ReflektEngine.gfx;

public class Screen3D {
	public int w, h;
	int[] pixels;
	public double rot;
	public double xp, yp, zp;
	double fov;

	public Screen3D(int width, int height) {
		w = width;
		h = height;
		fov = w;
		pixels = new int[w * h];
	}

	public int[] getRaster() {
		return pixels;
	}

	public void point(int[] x, int[] y) {

		for (int i = 0; i < x.length; i++) {
			putpixel(x[i], y[i], 0xff0000);
		}
	}

	public void edge(int[] x, int[] y) {
		line(x[0], y[0], x[1], y[1]);
		line(x[1], y[1], x[2], y[2]);
		line(x[2], y[2], x[0], y[0]);
	}

	public void line(int x, int y, int x1, int y1) {
		int px = x - x1;
		int py = y - y1;
		int length = (int) Math.sqrt((px * px) + (py * py));
		for (int i = 0; i < length; i++) {
			int dx = (px * i) / length;
			int dy = (py * i) / length;
			putpixel(x - dx, y - dy, 0xffffff);
		}
	}

	public void fill(int[] x, int[] y) {
		edge(x, y);
	}

	public void point3d(double x, double y, double z) {
		double x3d = x - xp;
		double y3d = y - yp;
		double z3d = z - zp;
		double scale = fov / (z3d);
		if (scale < 0)return;
		double x2d = (x3d * scale) + w / 2;
		double y2d = (y3d * scale) + h / 2;
		putpixel((int) x2d, (int) y2d, 0xffffff);
	}

	public void clear() {
		for (int i = 0; i < w * h; i++) {
			pixels[i] = 0;
		}
	}

	public void putpixel(int x, int y, int col) {
		if (x > 0 && x < w) {
			if (y > 0 && y < h) {
				pixels[x + y * w] = col;
			}
		}
	}

	public int getpixel(int x, int y) {
		if (x > 0 && x < w) {
			if (y > 0 && y < h) {
				return pixels[x + y * w];
			}
		}
		return 0xffffff;
	}
}

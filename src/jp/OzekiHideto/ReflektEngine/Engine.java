package jp.OzekiHideto.ReflektEngine;

import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import jp.OzekiHideto.ReflektEngine.gfx.Screen;

public class Engine implements Runnable{
	public int width = 320;
	public int height = 240;
	public int mx = 0;
	public int my = 0;
	public int oldmx = 0;
	public int oldmy = 0;
	public boolean initialized = false;
	public int scale;
	public JFrame window;
	BufferStrategy str;
	BufferedImage image;
	public int[] pixels;
	public Engine(int w,int h,int s,String t){
		width = w;
		height = h;
		scale = s;
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
		window = new JFrame(t);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(width*scale, height*scale);
		window.setVisible(true);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.createBufferStrategy(2);
		str = window.getBufferStrategy();
		this.run();
	}
	public void draw(){
		
	}
	public void init(){
		
	}
	void render(){
		Graphics g = str.getDrawGraphics();
		draw();
		g.drawImage(image, 0, 0,width*scale,height*scale, null);
		g.dispose();
		str.show();
	}
	public void update(){
		
	}
	void tick(){
		oldmx = mx;
		oldmy = my;
		mx = (int) ((MouseInfo.getPointerInfo().getLocation().getX())/scale)-window.getX()/scale;
		my = (int) ((MouseInfo.getPointerInfo().getLocation().getY())/scale)-window.getY()/scale;
		update();
	}
	@Override
	public void run() {
		double last = System.nanoTime();
		double delta = 0;
		double ns = 1000000000.0/60.0;
		int fps = 0;
		int tps = 0;
		long timer = System.currentTimeMillis();
		init();
		while(true){
			double now = System.nanoTime();
			delta+=(now-last)/ns;
			last = now;
			if(delta >= 1){
				delta--;
				tick();
				tps++;
			}else {
				render();
				fps++;
			}
			if(System.currentTimeMillis()-timer >= 1000){
				timer+=1000;
				System.out.println("fps : "+fps+" tps : "+tps);
				fps = 0;
				tps = 0;
			}
		}
	}
}

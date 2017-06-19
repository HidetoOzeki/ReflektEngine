package jp.OzekiHideto.ReflektEngine.gfx;

import java.util.ArrayList;

public class Shader {
	int [] map;
	int w,h;
	ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	ArrayList<Integer> color = new ArrayList<Integer>();
	public Shader(int width,int height,int col){
		w = width;
		h = height;
		map = new int[w*h];
		for(int i = 0;i < map.length;i++){
			map[i] = 0;
		}
		doshade();
	}
	public void add(Vertex v,int col){
		vertices.add(v);
		color.add(col);
	}
	public void doshade(){
		for(int i = 0;i < vertices.size();i++){
			int x = vertices.get(i).getX();
			int y = vertices.get(i).getY();
			map[x+y*w] = color.get(i);
		}
	}
	public void BitmapShader(Bitmap m,Screen screen,double rat){
		for(int i = 0;i < m.pixels.length;i++){
			m.pixels[i] = screen.blend(m.pixels[i], map[i], rat);
		}
	}
	public int[] getShader(){
		return map;
	}
}

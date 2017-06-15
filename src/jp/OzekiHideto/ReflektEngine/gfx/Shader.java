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
		for(int maps:map){
			map[maps] = col;
		}
		doshade();
	}
	public void doshade(){
		for(int i = 0;i < vertices.size();i++){
			int x = vertices.get(i).getX();
			int y = vertices.get(i).getY();
			map[x+y*w] = color.get(i);
		}
	}
	public int[] getShader(){
		return map;
	}
}

package jp.OzekiHideto.ReflektEngine.Physics;

public class Box {
	public int x,y,xs,ys;
	public Box(int x,int y,int xs,int ys){
		this.x = x;
		this.y = y;
		this.xs = xs-x;
		this.ys = ys-y;
	}
}

package jp.OzekiHideto.ReflektEngine.Physics;

public class vec2 {
	public double x,y;
	public vec2(){
		this(0,0);
	}
	public vec2(double x,double y){
		this.x = x;
		this.y = y;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public static vec2 incvec(vec2 v1,vec2 v2){
		vec2 res = new vec2(v1.x+v2.x,v1.y+v2.y);
		return res;
	}
	
}

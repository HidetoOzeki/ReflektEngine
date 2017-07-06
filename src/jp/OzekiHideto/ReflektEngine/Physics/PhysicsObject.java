package jp.OzekiHideto.ReflektEngine.Physics;

public class PhysicsObject {
	vec2[] vector;
	int x[];
	int y[];
	int num = 0;
	public PhysicsObject(){
		
	}
	public void initvec(){
		vector = new vec2[num];
	}
	public vec2[] getVector() {
		return vector;
	}
	public void setVector(vec2[] vector) {
		this.vector = vector;
	}
	public int[] getX(){
		return x;
	}
	public int[] getY(){
		return y;
	}
	public void set(int x[],int y[]){
		this.x = x;
		this.y = y;
	}
	
}

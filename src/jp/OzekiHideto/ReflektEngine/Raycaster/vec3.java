package jp.OzekiHideto.ReflektEngine.Raycaster;

public class vec3 {
	public double x,y,z;
	public vec3(){
		this(0,0,0);
	}
	public vec3(double x,double y,double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public static vec3 increase(vec3 v1,vec3 v2){
		vec3 res = new vec3(v1.x+v2.x,v1.y+v2.y,v1.z+v2.z);
		return res;
	}
}

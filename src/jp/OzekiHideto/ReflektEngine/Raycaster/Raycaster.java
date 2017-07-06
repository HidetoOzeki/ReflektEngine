package jp.OzekiHideto.ReflektEngine.Raycaster;

public class Raycaster {
	public vec3 pos = new vec3();
	double rot = 3.14/2;
	int[] zbuf = new int[320];
	public Raycaster(){
		
	}
	public void raycast(){
		for(int i = 0;i < 320;i++){
			double angle = rot+(i-(320/2))/100;
		}
	}
}

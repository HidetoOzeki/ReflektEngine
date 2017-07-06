package jp.OzekiHideto.ReflektEngine.Physics;

public class Body {
	double grav = 0;
	boolean decrease = false;
	boolean landed = false;
	vec2 spawnpos = new vec2();
	vec2 pos = new vec2();
	vec2 vel = new vec2();
	public Body() {
		vel.x = 0;
		vel.y = 0;
		spawnpos = new vec2(100,100);
	}

	public void tick() {
		if(pos.getY() > 450)vel.y=-1;
		vel.y += grav;
		pos.x += vel.x;
		pos.y += vel.y;
		if (decrease) {
			//vel.x*=0.9;
			//vel.y*=0.9;
		}
	}

	public vec2 getPos() {
		return pos;
	}

	public void setPos(vec2 pos) {
		this.pos = pos;
		spawnpos = pos;
	}

	public vec2 getVel() {
		return vel;
	}

	public void setVel(vec2 vel) {
		this.vel = vel;
	}
}

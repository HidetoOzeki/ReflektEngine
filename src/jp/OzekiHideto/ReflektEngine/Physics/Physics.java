package jp.OzekiHideto.ReflektEngine.Physics;

import java.util.ArrayList;

public class Physics {
	public double gravity;
	public ArrayList<Body> bods = new ArrayList<Body>();
	ArrayList<PhysicsObject> obj = new ArrayList<PhysicsObject>();

	public Physics() {

	}

	public void tick() {
		for (int i = 0; i < bods.size(); i++) {
			bods.get(i).grav = gravity;
			bods.get(i).decrease=false;
			bods.get(i).landed= false;
			for (int j = 0; j < obj.size(); j++) {
				PhysicsObject e = obj.get(j);
				Body b = bods.get(i);
				boolean col = false;
				//makeit method
				double dist = PhysicsMath.getDistance(b.pos, vec2.incvec(b.pos, b.vel));
				for (int o = 0; o < dist; o++) {
					double dx = b.pos.getX() + ((b.getVel().getX()/dist) * o);
					double dy = b.pos.getY() + ((b.getVel().getY()/dist) * o);
					vec2 bb = new vec2(dx, dy);
					if (PhysicsMath.collide(bb, e)) {
						col = true;
						break;
					}
				}
				//
				if (col) {
					bods.get(i).landed= true;
					bods.get(i).decrease=true;
					double rangle = PhysicsMath.getReflectAngle(e.getVector()[0], e.getVector()[1]);
					double angle = rangle+(rangle-PhysicsMath.getAngle(b));
					double spd = PhysicsMath.getDistance(b.pos, vec2.incvec(b.pos, b.vel));
					double dx = Math.sin(angle) * spd;
					double dy = Math.cos(angle) * 3;
					vec2 ve = new vec2(dx, dy-gravity);
					b.setVel(ve);
				}
			}
			bods.get(i).tick();
		}
	}

	public void add(Body b) {
		bods.add(b);
	}

	public void add(PhysicsObject o) {
		obj.add(o);
	}

}

package jp.OzekiHideto.ReflektEngine.Physics;

public class PhysicsMath {
	public PhysicsMath() {
	}

	public static vec2 getStep(vec2 v1, vec2 v2) {
		double dist = Math.sqrt(v1.getX() * v2.getX() + v1.getY() * v2.getY());
		double stepx = (v1.getX() - v2.getX()) / dist;
		double stepy = (v1.getY() - v2.getY()) / dist;
		vec2 step = new vec2(stepx, stepy);
		return step;
	}

	public static double getDistance(vec2 v1, vec2 v2) {
		double dx = v1.getX() - v2.getX();
		double dy = v1.getY() - v2.getY();
		double dist = Math.sqrt(dx * dx + dy * dy);
		return dist;
	}

	public static vec2 getCenter(vec2 v1, vec2 v2) {
		double dist = Math.sqrt(v1.getX() * v2.getX() + v1.getY() * v2.getY());
		double stepx = (v1.getX() - v2.getX()) / dist;
		double stepy = (v1.getY() - v2.getY()) / dist;
		double sx = stepx * dist / 2;
		double sy = stepy * dist / 2;
		vec2 step = new vec2(v1.getX() - sx, v1.getY() - sy);
		return step;
	}

	public static double getAngle(vec2 v1, vec2 v2) {
		double dx = v1.x - v2.x;
		double dy = v1.y - v2.y;
		double ang = Math.atan2(dx, dy);
		return ang;
	}

	public static vec2[] getEveryStep(PhysicsObject o) {
		int length = (int) getDistance(o.vector[0], o.vector[1]);
		double x1 = o.getVector()[0].getX()-o.getVector()[1].getX();
		double y1 = o.getVector()[0].getY()-o.getVector()[1].getY();
		vec2[] v = new vec2[length];
		for (int i = 0; i < length; i++) {
			double dx = o.getVector()[0].x+(x1 * -i)/length;
			double dy = o.getVector()[0].y+(y1 * -i)/length;
			v[i] = new vec2(dx, dy);
		}
		return v;
	}

	public static boolean collide(vec2 pos, PhysicsObject po) {
		boolean col = false;
		vec2[] steps = getEveryStep(po);
		for(int i = 0;i < steps.length;i++){
		double dist = getDistance(pos, steps[i]);
		if (dist < 1) {
			col = true;
			break;
		}
		}
		return col;
	}

	public static double getAngle(Body b) {
		// incorrect
		return getAngle(b.pos, vec2.incvec(b.pos, b.vel));
	}

	public static double getReflectAngle(vec2 v1, vec2 v2) {
		return getAngle(v1, v2) + 3.14 / 2;
	}
}

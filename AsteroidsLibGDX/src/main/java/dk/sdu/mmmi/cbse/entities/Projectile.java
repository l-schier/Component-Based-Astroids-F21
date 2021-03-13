package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;

public class Projectile extends SpaceObject {
    private float constSpeed;

    public Projectile(float rad, float x, float y) {
        constSpeed = 400;
        radians = rad;
        
        this.x = x;
        this.y = y;

        shapex = new float[2];
		shapey = new float[2];
    }

    public void setShape() {
        shapex[0] = x + MathUtils.cos(radians) * 8;
		shapey[0] = y + MathUtils.sin(radians) * 8;

        shapex[1] = x + MathUtils.cos(radians + 3.1415f) * 8;
		shapey[1] = y + MathUtils.sin(radians + 3.1415f) * 8;
		
    }

    public void update(float dt) {
        dx += MathUtils.cos(radians) * constSpeed;
		dy += MathUtils.sin(radians) * constSpeed;
		float vec = (float) Math.sqrt(dx * dx + dy * dy);
        
        // set speed
		dx = (dx / vec) * constSpeed;
        dy = (dy / vec) * constSpeed;

        // set position
		x += dx * dt;
		y += dy * dt;
        
        setShape();

        wrap();
    }

    public void draw(ShapeRenderer sr) {
		
		sr.setColor(1, 1, 1, 1);
		
		sr.begin(ShapeType.Line);
		
		for(int i = 0, j = shapex.length - 1;
			i < shapex.length;
			j = i++) {
			
			sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
			
		}
		
		sr.end();
		
	}
}

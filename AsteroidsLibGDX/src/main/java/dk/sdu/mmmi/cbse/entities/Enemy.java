package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;

import dk.sdu.mmmi.cbse.main.Game;

public class Enemy extends SpaceObject {

    private boolean left;
	private boolean right;

	
    private float constSpeed;
    private float acceleration;
    
    public Enemy() {
        
		x = (float) Math.random() * Game.WIDTH;
		y = (float) Math.random() * Game.HEIGHT;
		
		speed = 150;
        acceleration = 150;
		
		shapex = new float[4];
		shapey = new float[4];
		
		radians = 3.1415f / 2;
		rotationSpeed = 8;
    }

    private void setShape() {
		shapex[0] = x + MathUtils.cos(radians) * 8;
		shapey[0] = y + MathUtils.sin(radians) * 8;
		
		shapex[1] = x + MathUtils.cos(radians - 4 * 3.1415f / 5) * 8;
        shapey[1] = y + MathUtils.sin(radians - 4 * 3.1145f / 5) * 8;

        shapex[2] = x - MathUtils.cos(radians + 3.1415f) * 5;
		shapey[2] = y - MathUtils.sin(radians + 3.1415f) * 5;
		
		shapex[3] = x + MathUtils.cos(radians + 4 * 3.1415f / 5) * 8;
		shapey[3] = y + MathUtils.sin(radians + 4 * 3.1415f / 5) * 8;
    }

    private void setMovement() {
        double randTurn = Math.random();
        if (randTurn >= 0.5) {
            left = true;
        } else {
            right = true;
        }


    }

    public void update(float dt) {
		setMovement();
		// turning
		if(left) {
			radians += rotationSpeed * dt;
		}
		else if(right) {
			radians -= rotationSpeed * dt;
		}
        
        // accelerate
		dx += MathUtils.cos(radians) * acceleration * dt;
		dy += MathUtils.sin(radians) * acceleration * dt;
        
        float vec = (float) Math.sqrt(dx * dx + dy * dy);
        
        // set speed
		dx = (dx / vec) * speed;
		dy = (dy / vec) * speed;
		
		
		// set position
		x += dx * dt;
		y += dy * dt;
		
		// set shape
		setShape();
		
		// screen wrap
        wrap();

        left = false;
        right = false;
		
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
    
    public Projectile shoot() {
        return new Projectile(radians, x, y);
    }
    
}

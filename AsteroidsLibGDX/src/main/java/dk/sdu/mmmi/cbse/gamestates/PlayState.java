package dk.sdu.mmmi.cbse.gamestates;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import dk.sdu.mmmi.cbse.entities.Enemy;
import dk.sdu.mmmi.cbse.entities.Player;
import dk.sdu.mmmi.cbse.entities.Projectile;
import dk.sdu.mmmi.cbse.managers.GameKeys;
import dk.sdu.mmmi.cbse.managers.GameStateManager;

public class PlayState extends GameState {
	
	private ShapeRenderer sr;
	
	private Player player;
	private Enemy enemy;
	private HashSet<Projectile> projectiles;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		projectiles = new HashSet<>();
		
		sr = new ShapeRenderer();
		
		player = new Player();
		enemy = new Enemy();
		
	}
	
	public void update(float dt) {
		
		handleInput();
		
		player.update(dt);
		enemy.update(dt);
		if (!projectiles.isEmpty()) {
			for (Projectile projectile : projectiles) {
				projectile.update(dt);
			}
		}
		
		if (Math.random() > 0.994) {
			projectiles.add(enemy.shoot());
		}
		
	}
	
	public void draw() {
		player.draw(sr);
		enemy.draw(sr);
		if (!projectiles.isEmpty()) {
			for (Projectile projectile : projectiles) {
				projectile.draw(sr);
			}
		}
	}
	
	public void handleInput() {
		player.setLeft(GameKeys.isDown(GameKeys.LEFT));
		player.setRight(GameKeys.isDown(GameKeys.RIGHT));
		player.setUp(GameKeys.isDown(GameKeys.UP));
	}
	
	public void dispose() {
		/**
		 * unimplemented
		 */
	}
	
}

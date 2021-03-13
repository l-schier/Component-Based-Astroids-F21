package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class AsteroidPlugin implements IGamePluginService{

    private Entity asteroid;

    public AsteroidPlugin() {
        /**
         * TODO
         */
    }
    @Override
    public void start(GameData gameData, World world) {
        asteroid = createAsteroid(gameData);
        world.addEntity(asteroid);
    }

    private Entity createAsteroid(GameData gameData) {
        float deacceleration = 0;
        float acceleration = 2000;
        float maxSpeed = (float) Math.random() * 400;
        float rotationSpeed = 5;
        float x = (float) Math.random() * gameData.getDisplayWidth();
        float y = (float) Math.random() * gameData.getDisplayHeight();
        float radians = (float) Math.random() * 2 * 3.1415f;
        int life = 100;
        float expiration = 1000;
        
        Entity asteroidRock = new Asteroid();
        asteroidRock.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        asteroidRock.add(new PositionPart(x, y, radians));
        asteroidRock.add(new LifePart(life, expiration));
        
        return asteroidRock;
    }
    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(asteroid);
    }

    
    
}

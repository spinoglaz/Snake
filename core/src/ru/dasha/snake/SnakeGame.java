package ru.dasha.snake;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import ru.dasha.snake.components.*;
import ru.dasha.snake.systems.*;

public class SnakeGame extends ApplicationAdapter {
	SpriteBatch batch;
	ShapeRenderer shapeRenderer;
	World world;
	Texture appleTexture;
	Texture pointTexture;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		appleTexture = new Texture("apple.png");
		pointTexture = new Texture("point.png");
		WorldConfiguration setup = new WorldConfigurationBuilder()
                .with(new InputSystem())
                .with(new TurnSystem())
				.with(new MovementSystem())
                .with(new CollisionSystem())
                .with(new RenderSystem(batch, shapeRenderer))
				.build();

		world = new World(setup);

        createApple(3, 6);
        createSnake(0, 0);
    }

    private void createApple(int x, int y) {
        int apple = world.create();
        PositionComponent positionComponent = world.edit(apple).create(PositionComponent.class);
        positionComponent.x = x;
        positionComponent.y = y;
        ColliderComponent colliderComponent = world.edit(apple).create(ColliderComponent.class);
        colliderComponent.sizeX = 1;
        colliderComponent.sizeY = 1;
        world.edit(apple).create(TextureComponent.class).texture = appleTexture;
    }

    private void createSnake(int x, int y) {
        int snake = world.create();
        world.edit(snake).create(SnakeComponent.class);
        world.edit(snake).create(TurnComponent.class);
        PositionComponent positionComponent = world.edit(snake).create(PositionComponent.class);
        positionComponent.x = x;
        positionComponent.y = y;
        MovementComponent movementComponent = world.edit(snake).create(MovementComponent.class);
        movementComponent.speed = 4;
        movementComponent.direction = Direction.UP;
        ColliderComponent colliderComponent = world.edit(snake).create(ColliderComponent.class);
        colliderComponent.sizeX = 1;
        colliderComponent.sizeY = 1;
        world.edit(snake).create(TextureComponent.class).texture = pointTexture;
    }

    @Override
	public void render () {
	    world.setDelta(Gdx.graphics.getDeltaTime());
		world.process();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}

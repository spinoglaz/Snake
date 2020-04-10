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
import ru.dasha.snake.systems.InputSystem;
import ru.dasha.snake.systems.MovementSystem;
import ru.dasha.snake.systems.RenderSystem;
import ru.dasha.snake.systems.TurnSystem;

public class SnakeGame extends ApplicationAdapter {
	SpriteBatch batch;
	ShapeRenderer shapeRenderer;
	World world;
	Texture appleTexture;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		appleTexture = new Texture("apple.png");
		WorldConfiguration setup = new WorldConfigurationBuilder()
                .with(new InputSystem())
                .with(new TurnSystem())
				.with(new MovementSystem())
                .with(new RenderSystem(batch, shapeRenderer))
				.build();

		world = new World(setup);

        createApple(3, 6);
        createApple(-4, -2);
        createApple(2, -7);
        createSnake(0, 0);
    }

    private void createApple(int x, int y) {
        int apple = world.create();
        PositionComponent positionComponent = world.edit(apple).create(PositionComponent.class);
        positionComponent.x = x;
        positionComponent.y = y;
        world.edit(apple).create(TextureComponent.class).texture = appleTexture;
    }

    private void createSnake(int x, int y) {
        int snake = world.create();
        world.edit(snake).create(SnakeComponent.class);
        world.edit(snake).create(TurnComponent.class);
        PositionComponent positionComponent = world.edit(snake).create(PositionComponent.class);
        positionComponent.x = x;
        positionComponent.y = y;
        world.edit(snake).create(TextureComponent.class).texture = appleTexture;
        MovementComponent movementComponent = world.edit(snake).create(MovementComponent.class);
        movementComponent.speed = 4;
        movementComponent.direction = Direction.UP;
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

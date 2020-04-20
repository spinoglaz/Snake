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

import java.util.LinkedList;

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
                .with(new TailSystem())
                .with(new CollisionSystem())
                .with(new HitWallSystem())
                .with(new AppleEatSystem())
                .with(new AppleDestroySystem())
                .with(new AppleCreatingSystem())
                .with(new RenderSystem(batch, shapeRenderer))
                .with(new RemoveTailSystem())
                .with(new CleanUpSystem())
                .with(new RemoveMovedSystem())
				.build();

		world = new World(setup);

		createHorizontalWall(-10, -10, 10);
		createHorizontalWall(-10, 10, 10);
		createVerticalWall(-11, -10, 10);
		createVerticalWall(11, -10, 10);
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
        world.edit(apple).create(AppleComponent.class);
        world.edit(apple).create(TextureComponent.class).texture = appleTexture;
    }

    private void createSnake(int x, int y) {
        int snake = world.create();
        world.edit(snake).create(SnakeComponent.class);
        world.edit(snake).create(TurnComponent.class);
        TailComponent tailComponent = world.edit(snake).create(TailComponent.class);
        tailComponent.growth = 3;
        tailComponent.tail = new LinkedList<>();
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

    private void createHorizontalWall(int x, int y, int size) {
        for (int i = x; i <= size ; i++) {
            int wall = world.create();
            world.edit(wall).create(WallComponent.class);
            PositionComponent positionComponent = world.edit(wall).create(PositionComponent.class);
            positionComponent.x = i;
            positionComponent.y = y;
            world.edit(wall).create(TextureComponent.class).texture = pointTexture;
            ColliderComponent colliderComponent = world.edit(wall).create(ColliderComponent.class);
            colliderComponent.sizeX = 1;
            colliderComponent.sizeY = 1;
        }
    }

    private void createVerticalWall(int x, int y, int size) {
        for (int i = y; i <= size ; i++) {
            int wall = world.create();
            world.edit(wall).create(WallComponent.class);
            PositionComponent positionComponent = world.edit(wall).create(PositionComponent.class);
            positionComponent.x = x;
            positionComponent.y = i;
            ColliderComponent colliderComponent = world.edit(wall).create(ColliderComponent.class);
            colliderComponent.sizeX = 1;
            colliderComponent.sizeY = 1;
            world.edit(wall).create(TextureComponent.class).texture = pointTexture;
        }
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

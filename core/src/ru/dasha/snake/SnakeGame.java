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
    Texture pointTexture;

    @Override
    public void create () {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        pointTexture = new Texture("point.png");
        WorldConfiguration setup = new WorldConfigurationBuilder()
                .with(new InputSystem())
                .with(new RestartSystem())
                .with(new NewGameSystem())
                .with(new TurnSystem())
                .with(new MovementSystem())
                .with(new TailSystem())
                .with(new CollisionSystem())
                .with(new HitWallSystem())
                .with(new AppleEatSystem())
                .with(new TailGrowthSystem())
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
        int newGame = world.create();
        world.edit(newGame).create(NewGameComponent.class);
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

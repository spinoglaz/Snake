package ru.dasha.snake.systems;

import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Texture;
import ru.dasha.snake.components.*;

import java.util.LinkedList;

@All(NewGameComponent.class)
public class NewGameSystem extends IteratingSystem {
    private Texture pointTexture = new Texture("point.png");
    private Texture appleTexture = new Texture("apple.png");

    @Override
    protected void process(int entityId) {
        createSnake(0,0);
        createApple((int)(Math.random() * 20 - 10), (int)(Math.random() * 20 - 10));
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
}

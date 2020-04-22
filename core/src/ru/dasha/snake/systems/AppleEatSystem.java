package ru.dasha.snake.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import ru.dasha.snake.components.AppleComponent;
import ru.dasha.snake.components.AppleEatComponent;
import ru.dasha.snake.components.CollisionComponent;
import ru.dasha.snake.components.SnakeComponent;

@All({CollisionComponent.class})
public class AppleEatSystem extends IteratingSystem {
    private ComponentMapper<CollisionComponent> mCollision;
    private ComponentMapper<AppleComponent> mApple;
    private ComponentMapper<SnakeComponent> mSnake;

    @Override
    protected void process(int entityId) {
        CollisionComponent collision = mCollision.get(entityId);
        if (mApple.has(collision.entity1) && mSnake.has(collision.entity2)) {
            eatApple(collision.entity1, collision.entity2);
        }
        else if (mApple.has(collision.entity2) && mSnake.has(collision.entity1)) {
            eatApple(collision.entity2, collision.entity1);
        }
    }

    private void eatApple(int apple, int snake) {
        int appleEat = world.create();
        AppleEatComponent appleEatComponent = world.edit(appleEat).create(AppleEatComponent.class);
        appleEatComponent.appleEntity = apple;
        appleEatComponent.snakeEntity = snake;
    }
}

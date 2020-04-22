package ru.dasha.snake.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import ru.dasha.snake.components.CollisionComponent;
import ru.dasha.snake.components.GameOverComponent;
import ru.dasha.snake.components.SnakeComponent;
import ru.dasha.snake.components.WallComponent;

@All(CollisionComponent.class)
public class HitWallSystem extends IteratingSystem {
    protected ComponentMapper<SnakeComponent> mSnake;
    protected ComponentMapper<WallComponent> mWall;
    protected ComponentMapper<CollisionComponent> mCollision;

    @Override
    protected void process(int entityId) {
        CollisionComponent collision = mCollision.get(entityId);
        if(mSnake.has(collision.entity1) && mWall.has(collision.entity2)) {
            world.delete(collision.entity1);
            int gameOver = world.create();
            world.edit(gameOver).create(GameOverComponent.class);
        }
        else if(mSnake.has(collision.entity2) && mWall.has(collision.entity1)) {
            world.delete(collision.entity2);
            int gameOver = world.create();
            world.edit(gameOver).create(GameOverComponent.class);
        }
    }
}

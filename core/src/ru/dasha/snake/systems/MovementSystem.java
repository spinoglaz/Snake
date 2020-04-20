package ru.dasha.snake.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import ru.dasha.snake.components.MovedComponent;
import ru.dasha.snake.components.MovementComponent;
import ru.dasha.snake.components.PositionComponent;

@All({MovementComponent.class, PositionComponent.class})
public class MovementSystem extends IteratingSystem {
    private ComponentMapper<PositionComponent> mPosition;
    private ComponentMapper<MovementComponent> mMovement;

    @Override
    protected void process(int entityId) {
        PositionComponent position = mPosition.get(entityId);
        MovementComponent movement = mMovement.get(entityId);
        movement.progress += movement.speed * world.delta;
        if (movement.progress >= 1) {
            MovedComponent movedComponent = world.edit(entityId).create(MovedComponent.class);
            movedComponent.x = position.x;
            movedComponent.y = position.y;
            switch (movement.direction) {
                case UP:
                    position.y += 1;
                    break;
                case DOWN:
                    position.y -= 1;
                    break;
                case LEFT:
                    position.x -=1;
                    break;
                case RIGHT:
                    position.x += 1;
                    break;
            }
            movement.progress -= 1;
        }
    }
}

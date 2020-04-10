package ru.dasha.snake.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import ru.dasha.snake.components.MovementComponent;
import ru.dasha.snake.components.TurnComponent;

@All({TurnComponent.class, MovementComponent.class})
public class TurnSystem extends IteratingSystem {
    private ComponentMapper<TurnComponent> mTurn;
    private ComponentMapper<MovementComponent> mMovement;

    @Override
    protected void process(int entityId) {
        TurnComponent turnComponent = mTurn.get(entityId);
        MovementComponent movementComponent = mMovement.get(entityId);
        if(turnComponent.direction != null) {
            movementComponent.direction = turnComponent.direction;
        }
    }
}

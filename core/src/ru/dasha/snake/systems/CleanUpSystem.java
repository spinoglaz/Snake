package ru.dasha.snake.systems;

import com.artemis.annotations.One;
import com.artemis.systems.IteratingSystem;
import ru.dasha.snake.components.AppleEatComponent;
import ru.dasha.snake.components.CollisionComponent;
import ru.dasha.snake.components.NewGameComponent;

@One({AppleEatComponent.class, NewGameComponent.class, CollisionComponent.class})
public class CleanUpSystem extends IteratingSystem {
    @Override
    protected void process(int entityId) {
        world.delete(entityId);
    }
}

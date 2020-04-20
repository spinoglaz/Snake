package ru.dasha.snake.systems;

import com.artemis.annotations.One;
import com.artemis.systems.IteratingSystem;
import ru.dasha.snake.components.AppleEatComponent;
import ru.dasha.snake.components.MovedComponent;

@One({AppleEatComponent.class})
public class CleanUpSystem extends IteratingSystem {
    @Override
    protected void process(int entityId) {
        world.delete(entityId);
    }
}

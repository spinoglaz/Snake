package ru.dasha.snake.systems;

import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import ru.dasha.snake.components.AppleEatComponent;

@All(AppleEatComponent.class)
public class CleanUpSystem extends IteratingSystem {
    @Override
    protected void process(int entityId) {
        world.delete(entityId);
    }
}

package ru.dasha.snake.systems;

import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import ru.dasha.snake.components.MovedComponent;

@All(MovedComponent.class)
public class RemoveMovedSystem extends IteratingSystem {
    @Override
    protected void process(int entityId) {
        world.edit(entityId).remove(MovedComponent.class);
    }
}

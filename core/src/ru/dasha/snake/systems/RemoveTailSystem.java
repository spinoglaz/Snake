package ru.dasha.snake.systems;

import com.artemis.BaseEntitySystem;
import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import ru.dasha.snake.components.TailComponent;

@All(TailComponent.class)
public class RemoveTailSystem extends BaseEntitySystem {
    protected ComponentMapper<TailComponent> mTail;

    @Override
    protected void processSystem() {
    }

    @Override
    protected void removed(int entityId) {
      TailComponent tail = mTail.get(entityId);
        for (int segment : tail.tail) {
            world.delete(segment);
        }
        tail.tail.clear();
    }
}

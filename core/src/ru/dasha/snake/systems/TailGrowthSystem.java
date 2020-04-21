package ru.dasha.snake.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import ru.dasha.snake.components.AppleEatComponent;
import ru.dasha.snake.components.TailComponent;

@All(AppleEatComponent.class)
public class TailGrowthSystem extends IteratingSystem {
    protected ComponentMapper<TailComponent> mTail;
    protected ComponentMapper<AppleEatComponent> mAppleEat;

    @Override
    protected void process(int entityId) {
        AppleEatComponent appleEatComponent = mAppleEat.get(entityId);
        TailComponent tailComponent = mTail.get(appleEatComponent.snakeEntity);
        tailComponent.growth += 1;
    }
}

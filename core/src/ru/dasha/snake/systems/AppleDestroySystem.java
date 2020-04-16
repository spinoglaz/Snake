package ru.dasha.snake.systems;

import com.artemis.BaseEntitySystem;
import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import ru.dasha.snake.components.AppleComponent;
import ru.dasha.snake.components.AppleEatComponent;

@All({AppleEatComponent.class})
public class AppleDestroySystem extends IteratingSystem {
    private ComponentMapper<AppleEatComponent> mAppleEat;

    @Override
    protected void process(int entityId) {
        AppleEatComponent appleEatComponent = mAppleEat.get(entityId);
        world.delete(appleEatComponent.appleEntity);
    }


}

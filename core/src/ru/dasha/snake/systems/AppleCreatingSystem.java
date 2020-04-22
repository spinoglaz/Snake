package ru.dasha.snake.systems;

import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Texture;
import ru.dasha.snake.components.*;

@All(AppleEatComponent.class)
public class AppleCreatingSystem extends IteratingSystem {
    private Texture texture = new Texture("apple.png");

    @Override
    protected void process(int entityId) {
        int apple = world.create();
        PositionComponent positionComponent = world.edit(apple).create(PositionComponent.class);
        positionComponent.x = (int)(Math.random() * 20 - 10);
        positionComponent.y =(int)(Math.random() * 20 - 10);
        ColliderComponent colliderComponent = world.edit(apple).create(ColliderComponent.class);
        colliderComponent.sizeX = 1;
        colliderComponent.sizeY = 1;
        world.edit(apple).create(AppleComponent.class);
        world.edit(apple).create(TextureComponent.class).texture = texture;
    }

}

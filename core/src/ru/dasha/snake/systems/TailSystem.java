package ru.dasha.snake.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Texture;
import ru.dasha.snake.components.MovedComponent;
import ru.dasha.snake.components.PositionComponent;
import ru.dasha.snake.components.TailComponent;
import ru.dasha.snake.components.TextureComponent;

@All({TailComponent.class, MovedComponent.class})
public class TailSystem extends IteratingSystem {
    protected ComponentMapper<TailComponent> mTailComponent;
    protected ComponentMapper<MovedComponent> mMoved;
    protected ComponentMapper<PositionComponent> mPosition;
    private Texture texture = new Texture("point.png");

    @Override
    protected void process(int entityId) {
        TailComponent tailComponent = mTailComponent.get(entityId);
        MovedComponent movedComponent = mMoved.get(entityId);
        if(tailComponent.growth > 0) {
            int segment = createSegment(movedComponent.x, movedComponent.y);
            tailComponent.tail.add(segment);
            tailComponent.growth--;
        }
        else if(!tailComponent.tail.isEmpty()){
           int segment = tailComponent.tail.removeFirst();
           PositionComponent positionComponent = mPosition.get(segment);
           positionComponent.x = movedComponent.x;
           positionComponent.y = movedComponent.y;
           tailComponent.tail.add(segment);
        }
    }

    private int createSegment(int x, int y) {
        int segment = world.create();
        PositionComponent positionComponent = world.edit(segment).create(PositionComponent.class);
        positionComponent.x = x;
        positionComponent.y = y;
        world.edit(segment).create(TextureComponent.class).texture = texture;
        return segment;
    }
}

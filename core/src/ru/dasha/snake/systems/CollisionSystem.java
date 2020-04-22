package ru.dasha.snake.systems;

import com.artemis.BaseEntitySystem;
import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.utils.IntBag;
import ru.dasha.snake.components.ColliderComponent;
import ru.dasha.snake.components.CollisionComponent;
import ru.dasha.snake.components.PositionComponent;

@All({PositionComponent.class, ColliderComponent.class})
public class CollisionSystem extends BaseEntitySystem {
    private ComponentMapper<PositionComponent> mPosition;
    private ComponentMapper<ColliderComponent> mCollider;

    @Override
    protected void processSystem() {
        IntBag actives = subscription.getEntities();
        int[] ids = actives.getData();
        for (int i = 0, s = actives.size(); s > i; i++) {
            for (int j = i + 1; s > j; j++) {
                int entity1 = ids[i];
                int entity2 = ids[j];
                PositionComponent position1 = mPosition.get(entity1);
                ColliderComponent collider1 = mCollider.get(entity1);
                PositionComponent position2 = mPosition.get(entity2);
                ColliderComponent collider2 = mCollider.get(entity2);
                if(position1.x + collider1.sizeX > position2.x &&
                        position1.x < position2.x + collider2.sizeX &&
                        position1.y + collider1.sizeY > position2.y &&
                        position1.y < position2.y + collider2.sizeY){
                    int collisionEntity = world.create();
                    CollisionComponent collision = world.edit(collisionEntity).create(CollisionComponent.class);
                    collision.entity1 = entity1;
                    collision.entity2 = entity2;
                }
            }
        }
    }
}

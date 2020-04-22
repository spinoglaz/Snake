package ru.dasha.snake.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import ru.dasha.snake.components.Direction;
import ru.dasha.snake.components.SnakeComponent;
import ru.dasha.snake.components.TurnComponent;

@All({SnakeComponent.class, TurnComponent.class})
public class InputSystem extends IteratingSystem {
    private ComponentMapper <TurnComponent> mTurnComponent;

    @Override
    protected void process(int entityId) {

        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            mTurnComponent.get(entityId).direction = Direction.UP;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            mTurnComponent.get(entityId).direction = Direction.DOWN;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            mTurnComponent.get(entityId).direction = Direction.LEFT;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            mTurnComponent.get(entityId).direction = Direction.RIGHT;
        }
    }

}

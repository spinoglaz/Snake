package ru.dasha.snake.systems;

import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import ru.dasha.snake.components.GameOverComponent;
import ru.dasha.snake.components.NewGameComponent;

@All(GameOverComponent.class)
public class RestartSystem extends IteratingSystem {

    @Override
    protected void process(int entityId) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            int newGame = world.create();
            world.edit(newGame).create(NewGameComponent.class);
            world.delete(entityId);
        }
    }
}

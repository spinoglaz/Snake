package ru.dasha.snake.systems;

import com.artemis.BaseEntitySystem;
import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import ru.dasha.snake.components.PositionComponent;
import ru.dasha.snake.components.TextureComponent;

@All({TextureComponent.class, PositionComponent.class})
public class RenderSystem extends BaseEntitySystem {
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private int cellSize;
    protected ComponentMapper<TextureComponent> mTextureComponent;
    protected ComponentMapper<PositionComponent> mPositionComponent;

    public RenderSystem(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        this.batch = batch;
        this.shapeRenderer = shapeRenderer;
        cellSize = 20;
    }

    @Override
    protected void processSystem() {
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.gl.glLineWidth(2);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0,0,1,1);
        for (int x = 0; x < Gdx.graphics.getWidth(); x+=cellSize) {
            shapeRenderer.line(x, 0, x, Gdx.graphics.getHeight());
        }
        for (int y = 0; y < Gdx.graphics.getHeight(); y+=cellSize) {
            shapeRenderer.line(0, y, Gdx.graphics.getWidth(), y);
        }
        shapeRenderer.end();

        batch.begin();
        IntBag actives = subscription.getEntities();
        int[] ids = actives.getData();
        for (int i = 0, s = actives.size(); s > i; i++) {
            int id = ids[i];
            Texture texture = mTextureComponent.get(id).texture;
            int positionX = mPositionComponent.get(id).x * cellSize + Gdx.graphics.getWidth() / 2;
            int positionY = mPositionComponent.get(id).y * cellSize + Gdx.graphics.getHeight() / 2;
            positionX += cellSize / 2 - texture.getWidth() / 2;
            positionY += cellSize / 2 - texture.getHeight() / 2;
            batch.draw(texture, positionX, positionY);
        }
        batch.end();
    }
}

package com.gamepad;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by gebruiker on 28-11-13.
 */
public class TestActor extends Actor{

    private ShapeRenderer renderer;

    @Override
    public void act(float delta) {
        super.act(delta);
        renderer = new ShapeRenderer();
    }

    public void draw (SpriteBatch batch, float parentAlpha) {
        batch.end();

        renderer.setProjectionMatrix(batch.getProjectionMatrix());
        renderer.setTransformMatrix(batch.getTransformMatrix());
        renderer.translate(getX(), getY(), 0);

        renderer.begin(ShapeRenderer.ShapeType.Point);
        renderer.rect(0, 0, 100, 100);
        renderer.end();

        batch.begin();
    }
}

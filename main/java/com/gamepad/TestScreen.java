package com.gamepad;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by gebruiker on 28-11-13.
 */
public class TestScreen implements Screen {

    private Stage stage;
    private TestActor testActor;

    public TestScreen() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        testActor = new TestActor();
        stage.addActor(testActor);
    }

    public void resize(int width, int height) {
        stage.setViewport(800, 600, false);
        stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override public void dispose() {
        stage.dispose();
    }
    @Override public void show() {}
    @Override public void hide() {}
    @Override public void pause() {}
    @Override public void resume() {}
}

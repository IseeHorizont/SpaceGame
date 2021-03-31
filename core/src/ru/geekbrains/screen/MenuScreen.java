package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private Texture img;
    private Vector2 pos;
    private Vector2 v;
    private Vector2 vClick;
    private Vector2 vAng;
    float speed = 2.5f;
    private float angle;

    @Override
    public void show() {
        super.show();
        img = new Texture("earth.jpg");
        pos = new Vector2();
        v = new Vector2();
        vClick = new Vector2();
        vAng = new Vector2();
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        vClick.add(screenX, (Gdx.graphics.getHeight() - screenY));
        v.add(vClick);
        vAng.add(screenX, 0);
        vClick.nor();
        vAng.nor();
        angle = (float) Math.acos(vClick.dot(vAng));
        return false;
    }

    private void update (float delta) {
        while (v.x > pos.x) {
            pos.x += speed * Math.cos(angle);
            pos.y += speed * Math.sin(angle);
            break;
        }
    }

    private void draw () {
        Gdx.gl.glClearColor(0, 0.6f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, pos.x, pos.y, 300, 300);
        batch.end();
    }


}

package ru.geekbrains.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;
import ru.geekbrains.pool.BulletPool;

public class Ship extends Sprite {

    protected Rect worldBounds;
    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;
    protected Vector2 bulletV;
    protected int damage;
    protected float bulletHeight;

    protected Vector2 v;
    protected Vector2 v0;

    protected float reloadTimer;
    protected float reloadInterval;
    protected Sound sound;

    protected int hp;

    public Ship(TextureRegion region, int rows, int cols, int frame) {
        super(region, rows, cols, frame);
        bulletV = new Vector2();
        v = new Vector2();
        v0 = new Vector2();
    }

    public Ship() {
        bulletV = new Vector2();
        v = new Vector2();
        v0 = new Vector2();
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        reloadTimer += delta;
        if (reloadTimer > reloadInterval) {
            reloadTimer = 0f;
            shoot();
        }
    }

    private void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, this.pos, bulletV, worldBounds, damage, bulletHeight);
        sound.play();
    }
}

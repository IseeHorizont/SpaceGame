package ru.geekbrains.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.math.Rect;
import ru.geekbrains.math.Rnd;
import ru.geekbrains.pool.EnemyPool;
import ru.geekbrains.sprite.EnemyShip;

public class EnemyEmitter {

    private static final float GENERETE_INTERVAL = 4f;

    private static final float ENEMY_SMALL_HEIGHT = 0.1f;
    private static final float ENEMY_SMALL_BULLET_HEIGHT = 0.01f;
    private static final float ENEMY_SMALL_BULLET_VY = -0.3f;
    private static final int ENEMY_SMALL_BULLET_DAMAGE = 1;
    private static final float ENEMY_SMALL_RELOAD_INTERVAL = 3f;
    private static final int ENEMY_SMALL_HP = 1;

    private static final float ENEMY_MEDIUM_HEIGHT = 0.15f;
    private static final float ENEMY_MEDIUM_BULLET_HEIGHT = 0.02f;
    private static final float ENEMY_MEDIUM_BULLET_VY = -0.25f;
    private static final int ENEMY_MEDIUM_BULLET_DAMAGE = 5;
    private static final float ENEMY_MEDIUM_RELOAD_INTERVAL = 4f;
    private static final int ENEMY_MEDIUM_HP = 5;

    private static final float ENEMY_BIG_HEIGHT = 0.2f;
    private static final float ENEMY_BIG_BULLET_HEIGHT = 0.04f;
    private static final float ENEMY_BIG_BULLET_VY = -0.3f;
    private static final int ENEMY_BIG_BULLET_DAMAGE = 10;
    private static final float ENEMY_BIG_RELOAD_INTERVAL = 1f;
    private static final int ENEMY_BIG_HP = 10;

    private Rect worldBounds;
    private EnemyPool enemyPool;
    private float generateTimer;
    private TextureRegion[] enemySmallRegions;
    private TextureRegion[] enemyMediumRegions;
    private TextureRegion[] enemyBigRegions;
    private TextureRegion bulletRegion;

    private Vector2 enemySmallV;
    private Vector2 enemyMediumV;
    private Vector2 enemyBigV;

    public EnemyEmitter(Rect worldBounds, EnemyPool enemyPool, TextureAtlas atlas) {
        this.worldBounds = worldBounds;
        this.enemyPool = enemyPool;
        enemySmallV = new Vector2(0, -0.2f);
        enemyMediumV = new Vector2(0, -0.03f);
        enemyBigV = new Vector2(0, -0.05f);
        bulletRegion = atlas.findRegion("bulletEnemy");
        enemySmallRegions = Regions.split(atlas.findRegion("enemy0"), 1, 2, 2);
        enemyMediumRegions = Regions.split(atlas.findRegion("enemy1"), 1, 2, 2);
        enemyBigRegions = Regions.split(atlas.findRegion("enemy2"), 1, 2, 2);
    }

    public void generate(float delta) {
        generateTimer += delta;
        if (generateTimer >= GENERETE_INTERVAL) {
            generateTimer = 0;
            EnemyShip enemyShip = enemyPool.obtain();
            float type = (float)Math.random();
            if (type < 0.5f) {
                enemyShip.set(enemySmallRegions, enemySmallV, bulletRegion, ENEMY_SMALL_BULLET_HEIGHT,
                        ENEMY_SMALL_BULLET_VY, ENEMY_SMALL_BULLET_DAMAGE, ENEMY_SMALL_RELOAD_INTERVAL,
                        ENEMY_SMALL_HEIGHT, ENEMY_SMALL_HP);
            } else if (type < 0.8f) {
                //TODO medium ship
                enemyShip.set(enemyMediumRegions, enemyMediumV, bulletRegion, ENEMY_MEDIUM_BULLET_HEIGHT,
                        ENEMY_MEDIUM_BULLET_VY, ENEMY_MEDIUM_BULLET_DAMAGE, ENEMY_MEDIUM_RELOAD_INTERVAL,
                        ENEMY_MEDIUM_HEIGHT, ENEMY_MEDIUM_HP);

            } else {
                //TODO big ship
                enemyShip.set(enemyBigRegions, enemyBigV, bulletRegion, ENEMY_BIG_BULLET_HEIGHT,
                        ENEMY_BIG_BULLET_VY, ENEMY_BIG_BULLET_DAMAGE, ENEMY_BIG_RELOAD_INTERVAL,
                        ENEMY_BIG_HEIGHT, ENEMY_BIG_HP);
            }

            enemyShip.pos.x = Rnd.nextFloat(
                    worldBounds.getLeft() + enemyShip.getHalfWidth(),
                    worldBounds.getRight() - enemyShip.getHalfWidth());
            enemyShip.setBottom(worldBounds.getTop());
        }
    }
}

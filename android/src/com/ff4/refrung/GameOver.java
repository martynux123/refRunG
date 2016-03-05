package com.ff4.refrung;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class GameOver implements Screen {
	
	private SpriteBatch batch;
	private ShapeRenderer shape;
	private Texture gameOver;
	private GameRunner runner;
	private BitmapFont scoreDisplay;	
	private Preferences prefs;
	private int index;
	public GameOver(GameRunner runner){
		this.runner = runner;
		
		index = MathUtils.random(0,1);
		
		switch(index){
		case 0: 
			gameOver = GameRunner.assets.get("gameover.png");
		break;
		
		case 1:
			gameOver = GameRunner.assets.get("gameover1.png");
		}
		
		
		scoreDisplay = GameRunner.font;
				
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1f);
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		
		prefs = Gdx.app.getPreferences("Stats");
	}
	

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		batch.draw(gameOver, 0, 0 , Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		scoreDisplay.draw(batch, "Best: " + prefs.getInteger("Highscore") , Gdx.graphics.getWidth()/2+20,
				Gdx.graphics.getHeight()-380);
		scoreDisplay.draw(batch, "Score: " + prefs.getInteger("Score") 
		, Gdx.graphics.getWidth()/8, Gdx.graphics.getHeight()-380);
		batch.end();
		
		
		if(Gdx.input.justTouched()){
			runner.setScreen(new GameMenu(runner));
		}
		
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	
}

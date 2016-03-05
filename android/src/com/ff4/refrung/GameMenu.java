package com.ff4.refrung;

import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameMenu implements Screen{
	
	private Texture background;
	private SpriteBatch batch;
	private GameRunner runner;
	private boolean isLoaded = false;
	private Timer timer;
	private BitmapFont taptoPlay;
	
	public GameMenu(GameRunner runner){
		this.runner = runner;
		timer = new Timer("GameMenu");
		
		//Loading...
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				isLoaded = true;
			}
		}, 2000);
		//
		
		taptoPlay = GameRunner.font;
	}

	@Override
	public void show() {
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		batch = new SpriteBatch();
		background = GameRunner.assets.get("badlogic.jpg");
	}

	@Override
	public void render(float delta) {
		
		batch.begin();
		batch.draw((Texture) GameRunner.assets.get("Loading.png"), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();
	
		//Drawing background
		if(isLoaded){
			batch.begin();
			batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			taptoPlay.draw(batch, "Tap to play", Gdx.graphics.getWidth()/7, Gdx.graphics.getHeight()/4);
			batch.end();
		
		//Changing screen
		if(Gdx.input.justTouched()){
			runner.setScreen(new GameSc(runner));
		}
		
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}
	
	public void resume() {
	}

	@Override
	public void hide() {
		timer.cancel();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
	
	

}

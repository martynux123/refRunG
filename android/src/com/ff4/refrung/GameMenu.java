package com.ff4.refrung;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameMenu implements Screen{
	
	private Texture background;
	private SpriteBatch batch;
	private GameRunner runner;
	
	public GameMenu(GameRunner runner){
		this.runner = runner;
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		background = GameRunner.assets.get("badlogic.jpg");
	}

	@Override
	public void render(float delta) {
		//Drawing background
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		
		//Changing screen
		if(Gdx.input.justTouched()){
			runner.setScreen(new GameSc(runner));
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
	
	

}

package com.ff4.refrung;

import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class GameMenu implements Screen{
	
	private Texture background;
	private SpriteBatch batch;
	private GameRunner runner;
	private boolean isLoaded = false;
	private Timer timer;
	private Preferences prefs;
	private BitmapFont taptoPlay;
	private Rectangle infoRectangle;
	private Rectangle playButton;
	private ShapeRenderer shape;
	private Music music;
	
	public GameMenu(GameRunner runner){
		this.runner = runner;
		music = GameRunner.assets.get("menuMusic.mp3");
		music.play();
		timer = new Timer("GameMenu");
		infoRectangle = new Rectangle(50, Gdx.graphics.getHeight()/3, 200,200);
		playButton = new Rectangle(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/3, 300,300);
		shape = new ShapeRenderer();
		prefs = Gdx.app.getPreferences("Stats");
		//Loading...
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				isLoaded = true;
			}
		}, 1000);
		//
		
		taptoPlay = GameRunner.font;
	}

	@Override
	public void show() {
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		batch = new SpriteBatch();
		background = GameRunner.assets.get("menu.png");
		
		
		
	}

	@Override
	public void render(float delta) {
		
		batch.begin();
		batch.draw((Texture) GameRunner.assets.get("Loading.png"), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();
	
	
		int touchX = Gdx.input.getX();
		int touchY = Gdx.input.getY() + ((Gdx.graphics.getHeight() / 2 - Gdx.input.getY()) * 2);
		//Drawing background
		if(isLoaded){
			batch.begin();
			batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			batch.end();
		
		
			
		//Changing screen
			System.out.println(prefs.getBoolean("FirstTIme"));
		if(Gdx.input.justTouched()&& playButton.contains(touchX, touchY)){
			if(prefs.getBoolean("FirstTIme")==false){
				runner.setScreen(new HelpScreen(runner));	
				
			}
			if(prefs.getBoolean("FirstTIme")==true){				
				runner.setScreen(new GameSc(runner));
				music.stop();
			}
		}
			
		if(Gdx.input.justTouched() &&infoRectangle.contains(touchX, touchY)){
			runner.setScreen(new HelpScreen(runner));
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

package com.ff4.refrung;

import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class TerroristTutorial implements Screen {
	
	private int TerroristAnimIndex;
	private GameRunner runner;
	private SpriteBatch batch;
	private ShapeRenderer shape;
	private Texture txt;
	private Preferences prefs;
	private Texture refugee[] = new Texture[7];
	private Texture refugeeOther[] = new Texture[7];
	
	public TerroristTutorial(GameRunner runner) {
		this.runner = runner;
		batch = new SpriteBatch();
		prefs = Gdx.app.getPreferences("Stats");
		prefs.putBoolean("FirstTIme", true);
		prefs.flush();
		
		shape = new ShapeRenderer();
		txt = GameRunner.assets.get("terroristScreen.png");
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
					
					@Override
					public void run() {
						TerroristAnimIndex++;
						if(TerroristAnimIndex > 6)
							TerroristAnimIndex = 0;
						
					}
				}, 100, 100);



		refugee[0] = GameRunner.assets.get("Terrorist/Terrorist1.1.png");
		refugee[1] = GameRunner.assets.get("Terrorist/Terrorist1.2.png");
		refugee[2] = GameRunner.assets.get("Terrorist/Terrorist1.3.png");
		refugee[3] = GameRunner.assets.get("Terrorist/Terrorist1.4.png");
		refugee[4] = GameRunner.assets.get("Terrorist/Terrorist1.1.png");
		refugee[5] = GameRunner.assets.get("Terrorist/Terrorist1.2.png");
		refugee[6] = GameRunner.assets.get("Terrorist/Terrorist1.4.png");
		
		refugeeOther[0] = GameRunner.assets.get("Terrorist/Terrorist2.1.png");
		refugeeOther[1] = GameRunner.assets.get("Terrorist/Terrorist2.2.png");
		refugeeOther[2] = GameRunner.assets.get("Terrorist/Terrorist2.3.png");
		refugeeOther[3] = GameRunner.assets.get("Terrorist/Terrorist2.4.png");
		refugeeOther[4] = GameRunner.assets.get("Terrorist/Terrorist2.1.png");
		refugeeOther[5] = GameRunner.assets.get("Terrorist/Terrorist2.2.png");
		refugeeOther[6] = GameRunner.assets.get("Terrorist/Terrorist2.4.png");
		
		
	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(txt, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(refugee[TerroristAnimIndex], Gdx.graphics.getWidth()*0.10f, Gdx.graphics.getHeight()*0.62f, 300,400);
		batch.draw(refugeeOther[TerroristAnimIndex], Gdx.graphics.getWidth()*0.67f, Gdx.graphics.getHeight()*0.62f, 300,400);
		batch.end();
		
		if(Gdx.input.justTouched()){
			runner.setScreen(new GameMenu(runner));
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
		shape.dispose();
		
	}

}

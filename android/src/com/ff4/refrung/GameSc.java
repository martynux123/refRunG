package com.ff4.refrung;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class GameSc implements Screen {
	
	private boolean debugMode = false;
	public GameRunner runner;
	public SpriteBatch batch;
	public ShapeRenderer shape;
	private Preferences prefs;
	private int Score;
	private BitmapFont scoreFont;

	// REFUGEES
	private ArrayList<NorwayRefugee> NorwayRefs;
	private ArrayList<LithuanianRefugee> LithuanianRefs;
	private ArrayList<GermanRefugee> GermanRefs;
	private ArrayList<Terrorist> Terrorists;

	
	private NorwayPost NorPost;
	private GermanPost GerPost;
	private LithuanianPost LTPost;
	
	private int touchX;
	private int touchY;

	public GameSc(GameRunner runner) {
		this.runner = runner;
		batch = new SpriteBatch();
		shape = new ShapeRenderer();

		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1f);

		scoreFont = GameRunner.font;

		// Refugees List
		Terrorists = new ArrayList<Terrorist>();
		NorwayRefs = new ArrayList<NorwayRefugee>();
		LithuanianRefs = new ArrayList<LithuanianRefugee>();
		GermanRefs = new ArrayList<GermanRefugee>();

		// Posts
		
		LTPost = new LithuanianPost();
		GerPost = new GermanPost();
		NorPost = new NorwayPost();
		
		prefs = Gdx.app.getPreferences("Stats");
		

		terroristThread();
		refuggeThread();

	}

	private float speed = LithuanianRefugee.DEFAULT_SPEED;
	private void refuggeThread() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (runner.getScreen() == GameSc.this) {
					long randTime = MathUtils.random(1000, 2500);
					
					try {
						Thread.sleep(randTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					speed+=0.3f;
					int randRefugee = MathUtils.random(0, 2);
					float randX = MathUtils.random(0, Gdx.graphics.getWidth() - LithuanianRefugee.SIZE);
					

						if(randRefugee == 0){
							spawnLtRefugee(randX, Gdx.graphics.getHeight(),
									speed);
						}
						if(randRefugee == 1){						
							spawnNorwegianRefugee(randX, Gdx.graphics.getHeight(),
									speed);
						}
						if(randRefugee== 2){
							spawnGermanRefugee(randX, Gdx.graphics.getHeight(),
									speed);						
						}


				}
				Thread.currentThread().interrupt();
			}
		}, "RefugeeThread").start();
	}

	private float terroristSpeed = Terrorist.DEFAULT_SPEED;
	private void terroristThread() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (runner.getScreen() == GameSc.this) {
					long randTime = MathUtils.random(1000, 4000);

					try {
						Thread.sleep(randTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					terroristSpeed+=0.4;

					float randX = MathUtils.random(0, Gdx.graphics
							.getWidth() /* TODO: handle uniform size */);

					spawnTerrorist(randX, Gdx.graphics.getHeight(),
							terroristSpeed);

				}
				Thread.currentThread().interrupt();
			}
		}, "Terrorist thread").start();
	}

	public void spawnTerrorist(float x, float y, float speed) {
		Terrorists.add(new Terrorist(x, y, speed));
	}

	public void spawnLtRefugee(float x, float y, float speed) {
	 	LithuanianRefs.add(new LithuanianRefugee(x, y, speed));

	}

	public void spawnNorwegianRefugee(float x, float y, float speed) {
		NorwayRefs.add(new NorwayRefugee(x, y, speed));

	}
	public void spawnGermanRefugee(float x, float y, float speed) {
		GermanRefs.add(new GermanRefugee(x, y, speed));

	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		prefs.putInteger("Score", Score);
		prefs.flush();
		
		if(prefs.getInteger("Highscore")<prefs.getInteger("Score")){
			prefs.putInteger("Highscore", Score);
		}
		
		touchX = Gdx.input.getX();
		touchY = Gdx.input.getY() + ((Gdx.graphics.getHeight() / 2 - Gdx.input.getY()) * 2);
		batch.begin();
		scoreFont.draw(batch, " " + prefs.getInteger("Score"), Gdx.graphics.getWidth() / 2 - (String.valueOf(Score).length() * 34),
				Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight() * 0.0462f);
		batch.end();

		NorPost.render(batch, shape);
		LTPost.render(batch, shape);
		GerPost.render(batch, shape);
		
		// DOING EVERYTHING FOR TERRORIST
		for (int i = 0; i < Terrorists.size(); i++) {
			Terrorists.get(i).render(batch, shape);
		}
		for (int i = 0; i < Terrorists.size(); i++) {
			if (Terrorists.get(i).y < Gdx.graphics.getHeight()*0.131f) {
				runner.setScreen(new GameOver(runner));
			}
		}
		for (int i = 0; i < Terrorists.size(); i++) {
			if (Gdx.input.justTouched() && Terrorists.get(i).rect.contains(touchX, touchY)) {
				Terrorists.get(i).explode();
				//Explosion radius
				Rectangle rect = new Rectangle(Terrorists.get(i).x - 200, Terrorists.get(i).y - 200, 600, 600);
				
				//Exploding
				for (int j = 0; j < LithuanianRefs.size(); j++) {
					if(LithuanianRefs.get(i).rect.overlaps(rect)){
						runner.setScreen(new GameOver(runner));
					}
				}
				Score++;
			}
		}
		for (int i = 0; i < Terrorists.size(); i++) {
			if(Terrorists.get(i).isDead)
				Terrorists.remove(i);
		}
		

		// DOING EVERYTHING FOR GERMAN
		for (int i = 0; i < GermanRefs.size(); i++) {
			GermanRefs.get(i).render(batch, shape);
		}

		for (int i = 0; i < GermanRefs.size(); i++) {
			if (GermanRefs.get(i).y < 0) {
				GermanRefs.remove(i);
			}
		}
		for(int i = 0; i<GermanRefs.size(); i++){
			if(GermanRefs.get(i).rect.overlaps(GerPost.rect)){
				GermanRefs.remove(i);
				Score++;
			}
		}
		for(int i =0; i<GermanRefs.size(); i++){
			if(GermanRefs.get(i).rect.overlaps(NorPost.rect)||GermanRefs.get(i).rect.overlaps(LTPost.rect)){
				runner.setScreen(new GameOver(runner));
			}			
		}
		
		
		
		// DOING EVERYTHING FOR NORWAY
		for (int i = 0; i < NorwayRefs.size(); i++) {
			NorwayRefs.get(i).render(batch, shape);
		}
		for (int i = 0; i < NorwayRefs.size(); i++) {
			if (NorwayRefs.get(i).y < 0) {
				NorwayRefs.remove(i);
			}
		}
		for(int i=0; i<NorwayRefs.size(); i++){
			if(NorwayRefs.get(i).rect.overlaps(LTPost.rect) || NorwayRefs.get(i).rect.overlaps(GerPost.rect)){
				runner.setScreen(new GameOver(runner));
			}
		}
		for(int i=0; i<NorwayRefs.size(); i++){
			if(NorwayRefs.get(i).rect.overlaps(NorPost.rect)){
				NorwayRefs.remove(i);
				Score++;
			}
		}

		// DOING EVERYTHING FOR NORWAY
		for (int i = 0; i < LithuanianRefs.size(); i++) {
			LithuanianRefs.get(i).render(batch, shape);
		}
		for (int i = 0; i < LithuanianRefs.size(); i++) {
			if (LithuanianRefs.get(i).y < 0) {
				LithuanianRefs.remove(i);
			}
		}
		for(int i =0; i<LithuanianRefs.size(); i++){
			if(LithuanianRefs.get(i).rect.overlaps(NorPost.rect)||LithuanianRefs.get(i).rect.overlaps(GerPost.rect)){
			runner.setScreen(new GameOver(runner));
			}			
		}
		for(int i = 0; i<LithuanianRefs.size(); i++){
			if(LithuanianRefs.get(i).rect.overlaps(LTPost.rect)){
				LithuanianRefs.remove(i);
				Score++;
			}
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

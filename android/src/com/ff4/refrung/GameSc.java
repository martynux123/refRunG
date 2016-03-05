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
	private ArrayList<Terrorist> Terrorist;

	// POSTS
	private Rectangle LithuanianPost;
	private Rectangle NorwayPost;
	private Rectangle GermanPost;
	private int touchX;
	private int touchY;

	public GameSc(GameRunner runner) {
		this.runner = runner;
		batch = new SpriteBatch();
		shape = new ShapeRenderer();

		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1f);

		scoreFont = GameRunner.font;

		// Refugees List
		Terrorist = new ArrayList<Terrorist>();
		NorwayRefs = new ArrayList<NorwayRefugee>();
		LithuanianRefs = new ArrayList<LithuanianRefugee>();
		GermanRefs = new ArrayList<GermanRefugee>();

		// Posts
		LithuanianPost = new Rectangle(10, 10, Gdx.graphics.getWidth()*0.231f,Gdx.graphics.getHeight()*0.1262f);
		
		NorwayPost = new Rectangle(380, 10
				,Gdx.graphics.getWidth()*0.231f,Gdx.graphics.getHeight()*0.1262f);
		GermanPost = new Rectangle(780,10,
				Gdx.graphics.getWidth()*0.231f,Gdx.graphics.getHeight()*0.1262f);

		prefs = Gdx.app.getPreferences("Stats");
		

		terroristThread();
		refuggeThread();

	}

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
					int randRefugee = MathUtils.random(0, 2);
					float randX = MathUtils.random(0, Gdx.graphics.getWidth() - LithuanianRefugee.SIZE);
					

						if(randRefugee == 0){
							spawnLtRefugee(randX, Gdx.graphics.getHeight(),
									LithuanianRefugee.DEFAULT_SPEED);
						}
						if(randRefugee == 1){						
							spawnNorwegianRefugee(randX, Gdx.graphics.getHeight(),
									NorwayRefugee.DEFAULT_SPEED);
						}
						if(randRefugee== 2){
							spawnGermanRefugee(randX, Gdx.graphics.getHeight(),
									LithuanianRefugee.DEFAULT_SPEED);						
						}


				}
				Thread.currentThread().interrupt();
			}
		}, "RefugeeThread").start();
	}

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

					float randX = MathUtils.random(0, Gdx.graphics
							.getWidth() /* TODO: handle uniform size */);

					spawnTerrorist(randX, Gdx.graphics.getHeight(),
							Gdx.graphics.getHeight()*0.0050f /* TODO: handle uniform speed */);

				}
				Thread.currentThread().interrupt();
			}
		}, "Terrorist thread").start();
	}

	public void spawnTerrorist(float x, float y, float speed) {
		Terrorist.add(new Terrorist(x, y, speed));
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


		// DOING EVERYTHING FOR TERRORIST
		for (int i = 0; i < Terrorist.size(); i++) {
			Terrorist.get(i).render(batch, shape);
		}
		for (int i = 0; i < Terrorist.size(); i++) {
			if (Terrorist.get(i).y < Gdx.graphics.getHeight()*0.131f) {
				runner.setScreen(new GameOver(runner));
			}
		}
		for (int i = 0; i < Terrorist.size(); i++) {
			if (Gdx.input.justTouched() && Terrorist.get(i).rect.contains(touchX, touchY)) {
				Terrorist.remove(i);
				Score++;
			}
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
			if(GermanRefs.get(i).rect.overlaps(GermanPost)){
				GermanRefs.remove(i);
				Score++;
			}
		}
		for(int i =0; i<GermanRefs.size(); i++){
			if(GermanRefs.get(i).rect.overlaps(NorwayPost)||GermanRefs.get(i).rect.overlaps(LithuanianPost)){
//				runner.setScreen(new GameOver(runner));
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
			if(NorwayRefs.get(i).rect.overlaps(LithuanianPost) || NorwayRefs.get(i).rect.overlaps(GermanPost)){
//				runner.setScreen(new GameOver(runner));
			}
		}
		for(int i=0; i<NorwayRefs.size(); i++){
			if(NorwayRefs.get(i).rect.overlaps(NorwayPost)){
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
			if(LithuanianRefs.get(i).rect.overlaps(NorwayPost)||LithuanianRefs.get(i).rect.overlaps(GermanPost)){
//				runner.setScreen(new GameOver(runner));
			}			
		}
		for(int i = 0; i<LithuanianRefs.size(); i++){
			if(LithuanianRefs.get(i).rect.overlaps(LithuanianPost)){
				LithuanianRefs.remove(i);
				Score++;
			}
		}

	

	// Out of bounds

	shape.setAutoShapeType(true);
	shape.begin(ShapeType.Filled);
	shape.setColor(Color.BLACK);
	shape.rect(10, 10, Gdx.graphics.getWidth()*0.231f,Gdx.graphics.getHeight()*0.1262f);
	shape.setColor(Color.GREEN);
	shape.rect(380, 10
			,Gdx.graphics.getWidth()*0.231f,Gdx.graphics.getHeight()*0.1262f);
	shape.setColor(Color.BLUE);
	shape.rect(780,10,
			Gdx.graphics.getWidth()*0.231f,Gdx.graphics.getHeight()*0.1262f);
	if(debugMode){
		shape.set(ShapeType.Line);
		shape.setColor(Color.GREEN);
		shape.rect(10, 10, Gdx.graphics.getWidth()*0.231f,Gdx.graphics.getHeight()*0.1262f);
		shape.setColor(Color.WHITE);
		shape.rect(380, 10
				,Gdx.graphics.getWidth()*0.231f,Gdx.graphics.getHeight()*0.1262f);
		shape.setColor(Color.YELLOW);
		shape.rect(780,10,
				Gdx.graphics.getWidth()*0.231f,Gdx.graphics.getHeight()*0.1262f);
		
	}
	
	shape.end();

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

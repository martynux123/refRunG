package com.ff4.refrung;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class GameSc implements Screen {
	
	private boolean debugMode = false;
	public GameRunner runner;
	private Music music;
	public SpriteBatch batch;
	public ShapeRenderer shape;
	private Preferences prefs;
	private Texture txt;
	private int Score;
	private boolean addOnce=false;
	private BitmapFont scoreFont;
	private Sound gameOverSound;
	private Sound heartMinus;
	private Texture[] hearts = new Texture[3];
	private Sound sound;
	
	// REFUGEES
	private ArrayList<NorwayRefugee> NorwayRefs;
	private ArrayList<LithuanianRefugee> LithuanianRefs;
	private ArrayList<GermanRefugee> GermanRefs;
	private ArrayList<Terrorist> Terrorists;
	private Sound soundScore;
	public int lives = 3;
	
	private NorwayPost NorPost;
	private GermanPost GerPost;
	private LithuanianPost LTPost;
	
	private int touchX;
	private int touchY;

	public GameSc(GameRunner runner) {
		this.runner = runner;
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		
		sound = GameRunner.assets.get("Audio/pain.wav");
		gameOverSound = GameRunner.assets.get("gameOVer.wav");
		heartMinus = GameRunner.assets.get("heart.wav");
		soundScore = GameRunner.assets.get("scoreSound.wav");
		
		//Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1f);
		
		txt = GameRunner.assets.get("background.jpg");
		
		hearts[0] = GameRunner.assets.get("hearts/1.png");
		hearts[1] = GameRunner.assets.get("hearts/2.png");
		hearts[2] = GameRunner.assets.get("hearts/3.png");
		
		scoreFont = GameRunner.gameScore;

		music = GameRunner.assets.get("mainMusic.mp3");
		music.setLooping(true);
		music.play();
		
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
	private int minDelayRef = 1300;
	
	private void refuggeThread() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (runner.getScreen() == GameSc.this) {
					long randTime = MathUtils.random(minDelayRef, minDelayRef + 900);
					if(minDelayRef > 800)
						minDelayRef-=20;
					
					
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

	private int minDelay = 2000;
	private float terroristSpeed = 6;
	private void terroristThread() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (runner.getScreen() == GameSc.this) { 
					long randTime = MathUtils.random(minDelay, minDelay + 1500);
					if(minDelay > 1000)
						minDelay-=10;
					
					try {
						Thread.sleep(randTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					terroristSpeed+=0.6f;

					float randX = MathUtils.random(0, Gdx.graphics
							.getWidth() /* TODO: handle uniform size */);

					spawnTerrorist(randX, Gdx.graphics.getHeight(),
							/*terroristSpeed*/speed);

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
	public void addScore(){
		addOnce=false;
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			
			@Override
			public void run() {
				addOnce = true;
			}
		}, 100);
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		NorPost.close();
		GerPost.close();
		LTPost.close();
		
		
		if(lives <= 0){
			runner.setScreen(new GameOver(runner));
			gameOverSound.play();
			music.stop();
			
		}
			
		
		prefs.putInteger("Score", Score);
		prefs.flush();
		
		if(prefs.getInteger("Highscore")<prefs.getInteger("Score")){
			prefs.putInteger("Highscore", Score);
		}
		
		touchX = Gdx.input.getX();
		touchY = Gdx.input.getY() + ((Gdx.graphics.getHeight() / 2 - Gdx.input.getY()) * 2);
		batch.begin();
		batch.draw(txt, 0,0 , Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		scoreFont.draw(batch, " " + prefs.getInteger("Score"), Gdx.graphics.getWidth() / 2 - (String.valueOf(Score).length() * 60),
				Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight() * 0.0462f);
		batch.end();

		
		// DOING EVERYTHING FOR TERRORIST
		for (int i = 0; i < Terrorists.size(); i++) {
			Terrorists.get(i).render(batch, shape);
		}
		for (int i = 0; i < Terrorists.size(); i++) {
			if (Terrorists.get(i).y < Gdx.graphics.getHeight()*0.131f) {
				lives--;
				heartMinus.play();
			}
		}
		for (int i = 0; i < Terrorists.size(); i++) {
			if (Gdx.input.justTouched() && Terrorists.get(i).rect.contains(touchX, touchY)) {
				Terrorists.get(i).explode();
				Terrorists.get(i).boomSound();
				//Explosion radius
				Rectangle rect = new Rectangle(Terrorists.get(i).x - 200, Terrorists.get(i).y - 200, 600, 600);
				
				//Exploding
				for (int j = 0; j < LithuanianRefs.size(); j++) {
					if(LithuanianRefs.get(i).rect.overlaps(rect)){
						lives--;
						heartMinus.play();
						LithuanianRefs.remove(i);
						
						sound.play();
						
					}
				}
				for (int j = 0; j < NorwayRefs.size(); j++) {
					if(NorwayRefs.get(i).rect.overlaps(rect)){
						lives--;
						heartMinus.play();
						NorwayRefs.remove(i);
						sound.play();
					}
				}
				for (int j = 0; j < GermanRefs.size(); j++) {
					if(GermanRefs.get(i).rect.overlaps(rect)){
						lives--;
						heartMinus.play();
						GermanRefs.remove(i);
						sound.play();
					}
				}
				//Score++;
			}
		}
		for (int i = 0; i < Terrorists.size(); i++) {
			if(Terrorists.get(i).isDead)
				Terrorists.remove(i);
		}
		

		// DOING EVERYTHING FOR GERMAN
		//rendering
		for (int i = 0; i < GermanRefs.size(); i++) {
			GermanRefs.get(i).render(batch, shape);
		}
		//Removing
		for (int i = 0; i < GermanRefs.size(); i++) {
			if (GermanRefs.get(i).y < -50) {
				GermanRefs.remove(i);
			}
		}
		//Adding score
		for(int i = 0; i<GermanRefs.size(); i++){
			if(GermanRefs.get(i).rect.overlaps(GerPost.rect)){
				if(GermanRefs.get(i).shouldAddScore){
					Score++;
					soundScore.play();
					GermanRefs.get(i).shouldAddScore = false;
				}
				GerPost.open();
			}
		}
		//Removing a live when in wront passport station
		for(int i =0; i<GermanRefs.size(); i++){
			if(GermanRefs.get(i).rect.overlaps(NorPost.rect)||GermanRefs.get(i).rect.overlaps(LTPost.rect)){
				if(!GermanRefs.get(i).rect.overlaps(GerPost.rect)){
					lives--;
					heartMinus.play();
					GermanRefs.remove(i);
				}
			}			
		}
		
		
		
		// DOING EVERYTHING FOR NORWAY
		for (int i = 0; i < NorwayRefs.size(); i++) {
			NorwayRefs.get(i).render(batch, shape);
		}
		for (int i = 0; i < NorwayRefs.size(); i++) {
			if (NorwayRefs.get(i).y < -50) {
				NorwayRefs.remove(i);
			}
		}
		for(int i=0; i<NorwayRefs.size(); i++){
			if(NorwayRefs.get(i).rect.overlaps(LTPost.rect) || NorwayRefs.get(i).rect.overlaps(GerPost.rect)){
				if(!NorwayRefs.get(i).rect.overlaps(NorPost.rect)){
					lives--;
					heartMinus.play();
					NorwayRefs.remove(i);
				}
			}
		}
		for(int i=0; i<NorwayRefs.size(); i++){
			if(NorwayRefs.get(i).rect.overlaps(NorPost.rect)){
				if(NorwayRefs.get(i).shouldAddScore){
					Score++;
					soundScore.play();
					NorwayRefs.get(i).shouldAddScore = false;
				}
				NorPost.open();
				
			}
		}

		// DOING EVERYTHING FOR NORWAY
		for (int i = 0; i < LithuanianRefs.size(); i++) {
			LithuanianRefs.get(i).render(batch, shape);
		}
		for (int i = 0; i < LithuanianRefs.size(); i++) {
			if (LithuanianRefs.get(i).y < -50) {
				LithuanianRefs.remove(i);
			}
		}
		for(int i =0; i<LithuanianRefs.size(); i++){
			if(LithuanianRefs.get(i).rect.overlaps(NorPost.rect)||LithuanianRefs.get(i).rect.overlaps(GerPost.rect)){
				if(!LithuanianRefs.get(i).rect.overlaps(LTPost.rect)){
					lives--;
					heartMinus.play();
					LithuanianRefs.remove(i);
				}
			}			
		}
		for(int i = 0; i<LithuanianRefs.size(); i++){
			if(LithuanianRefs.get(i).rect.overlaps(LTPost.rect)){
				if(LithuanianRefs.get(i).shouldAddScore){
					Score++;
					soundScore.play();
					LithuanianRefs.get(i).shouldAddScore = false;
				}
				LTPost.open();
			}
		}
		
		NorPost.render(batch, shape);
		LTPost.render(batch, shape);
		GerPost.render(batch, shape);
		
		if(lives > 0){
			batch.begin();
			batch.draw(hearts[lives-1], Gdx.graphics.getWidth()*0.65f, Gdx.graphics.getHeight()*0.9f, 350, 200);
			batch.end();
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
		scoreFont.dispose();
	}
}

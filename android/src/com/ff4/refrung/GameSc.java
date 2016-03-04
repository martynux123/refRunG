package com.ff4.refrung;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class GameSc implements Screen{
	
	public GameRunner runner;
	public SpriteBatch batch;
	public ShapeRenderer shape;
	private ArrayList<NorwayRefugee> NorwayRefs;
	private ArrayList<LithuanianRefugee> LithuanianRefs;
	private ArrayList<GermanRefugee> GermanRefs;
	private ArrayList<Terrorist> Terrorist;
	
	public GameSc(GameRunner runner){
		this.runner = runner;
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		refuggeThread();
		terroristThread();
		
		Terrorist = new ArrayList<Terrorist>();
		NorwayRefs = new ArrayList<NorwayRefugee>();
		LithuanianRefs = new ArrayList<LithuanianRefugee>();
		GermanRefs = new ArrayList<GermanRefugee>();
		
	}
	
	private void refuggeThread(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					long randTime = MathUtils.random(1000, 2500);
					int randRefugee = MathUtils.random(0, 3);
					
					try {
						Thread.sleep(randTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					float randX = MathUtils.random(0, Gdx.graphics.getWidth() /*TODO: handle uniform size*/);
					
					switch (randRefugee) {
					case 0:
						spawnLtRefugee(randX, Gdx.graphics.getHeight(), 10 /*TODO: handle uniform speed*/);
						break;
					case 1:
						spawnNorwegianRefugee(randX, Gdx.graphics.getHeight(), 10 /*TODO: handle uniform speed*/);
						break;
					case 2:
						spawnGermanRefugee(randX, Gdx.graphics.getHeight(), 10 /*TODO: handle uniform speed*/);
						break;
					}
					
					
					
				}
			}
		});
	}
	private void terroristThread(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					long randTime = MathUtils.random(4000, 1000);
					
					try {
						Thread.sleep(randTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					float randX = MathUtils.random(0, Gdx.graphics.getWidth() /*TODO: handle uniform size*/);
					
					spawnTerrorist(randX, Gdx.graphics.getHeight(), 10 /*TODO: handle uniform speed*/);
					
					
					
				}
			}
		});
	}
	
	public void spawnTerrorist(float x, float y, float speed){
		
	}
	public void spawnLtRefugee(float x, float y, float speed){
		
	}
	public void spawnNorwegianRefugee(float x, float y, float speed){
		
	}
	public void spawnGermanRefugee(float x, float y, float speed){
		
	}

	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		
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
	}
}

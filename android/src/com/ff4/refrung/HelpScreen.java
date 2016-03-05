package com.ff4.refrung;

import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import android.R.anim;



public class HelpScreen implements Screen{

	private ShapeRenderer shape;
	private SpriteBatch batch;
	private GameRunner runner;
	private BitmapFont font;
	private Timer timer;
	private int NanimIndex;
	private int LTanimIndex;
	private int GeranimIndex;
	private Texture[] LTRef = new Texture[13];
	private Texture[] NRef = new Texture[7];
	private Texture[] GerRef = new Texture[10];
	private Texture txt;
	
	public HelpScreen(GameRunner runner){
		shape = new ShapeRenderer();
		batch = new SpriteBatch();
		txt = GameRunner.assets.get("helperScreen.png");
		this.runner = runner;
		font = GameRunner.smallFont;
		timer = new Timer("Tutorial anim");
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				NanimIndex++;
				if(NanimIndex > 6)
					NanimIndex = 0;
				LTanimIndex++;
				if(LTanimIndex > 6)
					LTanimIndex = 0;
				
				if(GeranimIndex > 6)
					GeranimIndex = 0;
				GeranimIndex++;
				
			}
		}, 100, 100);
		
		LTRef[0] = GameRunner.assets.get("Lithuanian/Lithuanian1.1.png");
		LTRef[1] = GameRunner.assets.get("Lithuanian/Lithuanian1.2.png");
		LTRef[2] = GameRunner.assets.get("Lithuanian/Lithuanian1.3.png");
		LTRef[3] = GameRunner.assets.get("Lithuanian/Lithuanian1.1.png");
		LTRef[4] = GameRunner.assets.get("Lithuanian/Lithuanian1.2.png");
		LTRef[5] = GameRunner.assets.get("Lithuanian/Lithuanian1.3.png");
		LTRef[6] = GameRunner.assets.get("Lithuanian/Lithuanian1.1.png");
		LTRef[7] = GameRunner.assets.get("Lithuanian/Lithuanian1.2.png");
		LTRef[8] = GameRunner.assets.get("Lithuanian/Lithuanian1.3.png");
		LTRef[9] = GameRunner.assets.get("Lithuanian/Lithuanian1.1.png");
		LTRef[10] = GameRunner.assets.get("Lithuanian/Lithuanian1.2.png");
		LTRef[11] = GameRunner.assets.get("Lithuanian/Lithuanian1.3.png");
		LTRef[12] = GameRunner.assets.get("Lithuanian/Lithuanian1.4.png");
		
		NRef[0] = GameRunner.assets.get("Norway/Norway1.1.png");
		NRef[1] = GameRunner.assets.get("Norway/Norway1.2.png");
		NRef[2] = GameRunner.assets.get("Norway/Norway1.3.png");
		NRef[3] = GameRunner.assets.get("Norway/Norway1.1.png");
		NRef[4] = GameRunner.assets.get("Norway/Norway1.2.png");
		NRef[5] = GameRunner.assets.get("Norway/Norway1.3.png");
		NRef[6] = GameRunner.assets.get("Norway/Norway1.4.png");
		
		GerRef[0] = GameRunner.assets.get("German/German1.1.png");
		GerRef[1] = GameRunner.assets.get("German/German1.2.png");
		GerRef[2] = GameRunner.assets.get("German/German1.3.png");
		GerRef[3] = GameRunner.assets.get("German/German1.1.png");
		GerRef[4] = GameRunner.assets.get("German/German1.2.png");
		GerRef[5] = GameRunner.assets.get("German/German1.3.png");
		GerRef[6] = GameRunner.assets.get("German/German1.1.png");
		GerRef[7] = GameRunner.assets.get("German/German1.2.png");
		GerRef[8] = GameRunner.assets.get("German/German1.3.png");
		GerRef[9] = GameRunner.assets.get("German/German1.4.png");
	}
	
	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		shape.setAutoShapeType(true);
		shape.begin(ShapeType.Filled);
		shape.setColor(Color.GOLD);
		shape.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		shape.end();
		
		batch.begin();
		batch.draw(txt, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		font.draw(batch, "Norwegian refugee", Gdx.graphics.getWidth()*0.1f, Gdx.graphics.getHeight()*0.9f);
		batch.draw(NRef[NanimIndex], Gdx.graphics.getWidth() * 0.4f, Gdx.graphics.getHeight()*0.65f, NorwayRefugee.SIZE*1.2f, Gdx.graphics.getHeight()*0.17f);
		font.draw(batch, "German refugee", Gdx.graphics.getWidth()*0.15f, Gdx.graphics.getHeight()*0.6f);
		batch.draw(GerRef[GeranimIndex], Gdx.graphics.getWidth() * 0.4f, Gdx.graphics.getHeight()*0.35f, LithuanianRefugee.SIZE*1.2f, Gdx.graphics.getHeight()*0.17f);
		font.draw(batch, "Lithuanian refugee", Gdx.graphics.getWidth()*0.1f, Gdx.graphics.getHeight()*0.3f);
		batch.draw(LTRef[LTanimIndex], Gdx.graphics.getWidth() * 0.4f, Gdx.graphics.getHeight()*0.05f, LithuanianRefugee.SIZE*1.2f, Gdx.graphics.getHeight()*0.17f);
		batch.end();
		
		if(Gdx.input.justTouched())
			runner.setScreen(new TerroristTutorial(runner));
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
		timer.cancel();
	}

	@Override
	public void dispose() {
	}

}

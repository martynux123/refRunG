package com.ff4.refrung;

import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import android.os.Handler;

public class GameRunner extends Game{
	public static AssetManager assets;
	public static BitmapFont font;
	private SpriteBatch batch;
	
	@Override
	
	
	public void create() {
		
		GameRunner.assets = new AssetManager();
		//Terrorist
		GameRunner.assets.load("Terrorist/Terrorist1.1.png", Texture.class);
		GameRunner.assets.load("Terrorist/Terrorist1.2.png", Texture.class);
		GameRunner.assets.load("Terrorist/Terrorist1.3.png", Texture.class);
		GameRunner.assets.load("Terrorist/Terrorist1.4.png", Texture.class);
		
		//German
		GameRunner.assets.load("German/German1.1.png", Texture.class);
		GameRunner.assets.load("German/German1.2.png", Texture.class);
		GameRunner.assets.load("German/German1.3.png", Texture.class);
		GameRunner.assets.load("German/German1.4.png", Texture.class);
		
		//Lithuanian
		GameRunner.assets.load("Lithuanian/Lithuanian1.1.png", Texture.class);
		GameRunner.assets.load("Lithuanian/Lithuanian1.2.png", Texture.class);
		GameRunner.assets.load("Lithuanian/Lithuanian1.3.png", Texture.class);
		GameRunner.assets.load("Lithuanian/Lithuanian1.4.png", Texture.class);
		
		GameRunner.assets.load("Loading.png", Texture.class);
		
		

		assets.load("gameOver.png", Texture.class);

		GameRunner.assets.load("badlogic.jpg", Texture.class);

		
		GameRunner.assets.finishLoading();
				
		
		
		FreeTypeFontGenerator fontgen = new FreeTypeFontGenerator(Gdx.files.internal("Font.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = (int) (Gdx.graphics.getHeight()*0.092);
		font = fontgen.generateFont(parameter);
		
		setScreen(new GameMenu(this));
		
		
		
	}
}

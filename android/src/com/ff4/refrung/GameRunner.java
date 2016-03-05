package com.ff4.refrung;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class GameRunner extends Game{
	public static AssetManager assets;
	public static BitmapFont font;

	@Override
	public void create() {
		
		
		
		assets = new AssetManager();
		
		assets.load("Terrorist/Terrorist1.1.png", Texture.class);
		assets.load("Terrorist/Terrorist1.2.png", Texture.class);
		assets.load("Terrorist/Terrorist1.3.png", Texture.class);
		assets.load("Terrorist/Terrorist1.4.png", Texture.class);
		
		assets.load("German/German1.1.png", Texture.class);
		assets.load("German/German1.2.png", Texture.class);
		assets.load("German/German1.3.png", Texture.class);
		assets.load("German/German1.4.png", Texture.class);
		
		assets.load("gameOver.png", Texture.class);
		
		
		assets.load("badlogic.jpg", Texture.class);
		
		assets.finishLoading();
		
		FreeTypeFontGenerator fontgen = new FreeTypeFontGenerator(Gdx.files.internal("Font.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = (int) (Gdx.graphics.getHeight()*0.092);
		font = fontgen.generateFont(parameter);
		
		setScreen(new GameMenu(this));
		
		
		
	}
	
	
	

}

package com.ff4.refrung;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
 
public class GameRunner extends Game{
	public static AssetManager assets;
	public static BitmapFont font;
	private SpriteBatch batch;
	public static ParticleEffect particles;
	public static BitmapFont smallFont;
	
	@Override
	public void create() {
		
		GameRunner.assets = new AssetManager();
		
		GameRunner.assets.load("terroristScreen.png", Texture.class);
		
		//Terrorist
		GameRunner.assets.load("Terrorist/Terrorist1.1.png", Texture.class);
		GameRunner.assets.load("Terrorist/Terrorist1.2.png", Texture.class);
		GameRunner.assets.load("Terrorist/Terrorist1.3.png", Texture.class);
		GameRunner.assets.load("Terrorist/Terrorist1.4.png", Texture.class);
	
		GameRunner.assets.load("credits.png", Texture.class);
		
		//German
		GameRunner.assets.load("German/German1.1.png", Texture.class);
		GameRunner.assets.load("German/German1.2.png", Texture.class);
		GameRunner.assets.load("German/German1.3.png", Texture.class);
		GameRunner.assets.load("German/German1.4.png", Texture.class);
		
		//Norway
		GameRunner.assets.load("Norway/Norway1.1.png", Texture.class);
		GameRunner.assets.load("Norway/Norway1.2.png", Texture.class);
		GameRunner.assets.load("Norway/Norway1.3.png", Texture.class);
		GameRunner.assets.load("Norway/Norway1.4.png", Texture.class);
		GameRunner.assets.load("helperScreen.png", Texture.class);
		
		//Lithuanian
		GameRunner.assets.load("Lithuanian/Lithuanian1.1.png", Texture.class);
		GameRunner.assets.load("Lithuanian/Lithuanian1.2.png", Texture.class);
		GameRunner.assets.load("Lithuanian/Lithuanian1.3.png", Texture.class);
		GameRunner.assets.load("Lithuanian/Lithuanian1.4.png", Texture.class);
		
		GameRunner.assets.load("GermanPassport.png", Texture.class);
		GameRunner.assets.load("LithuanianPassport.png", Texture.class);
		GameRunner.assets.load("NorwayPassport.png", Texture.class);
		
		GameRunner.assets.load("Loading.png", Texture.class);
		
		

		assets.load("gameOver.png", Texture.class);

		GameRunner.assets.load("menu.png", Texture.class);
		
		
		GameRunner.assets.finishLoading();
				
		
		
		FreeTypeFontGenerator fontgen = new FreeTypeFontGenerator(Gdx.files.internal("Font.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = (int) (Gdx.graphics.getHeight()*0.092);
		font = fontgen.generateFont(parameter);
		
		FreeTypeFontParameter parametersmall = new FreeTypeFontParameter();
		parametersmall.size = (int) (Gdx.graphics.getHeight()*0.052);
		smallFont = fontgen.generateFont(parametersmall);
		
		particles = new ParticleEffect();
		particles.load(Gdx.files.internal("tryout"), Gdx.files.internal(""));
		particles.scaleEffect(2f);
		
		
	    setScreen(new GameMenu(this));
		//setScreen(new HelpScreen(this));
		
		
		
	}
}

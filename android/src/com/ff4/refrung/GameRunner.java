package com.ff4.refrung;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class GameRunner extends Game{
	public static AssetManager assets;

	@Override
	public void create() {
		
		
		
		assets = new AssetManager();
		
		assets.load("badlogic.jpg", Texture.class);
		
		assets.finishLoading();
		setScreen(new GameSc(this));
		
	}
	
	
	

}

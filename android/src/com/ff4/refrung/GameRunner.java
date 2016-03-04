package com.ff4.refrung;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;

public class GameRunner extends Game{
	public static AssetManager assets;

	@Override
	public void create() {
		
		assets = new AssetManager();
		
		setScreen(new GameSc(this));
		
	}
	
	
	

}

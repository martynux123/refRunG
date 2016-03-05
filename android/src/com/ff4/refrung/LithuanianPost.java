package com.ff4.refrung;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class LithuanianPost {
	
	public Rectangle rect;
	private boolean debugMode = false;
	private Texture txt;
	
	public LithuanianPost(){
		rect = new Rectangle(10, 10, Gdx.graphics.getWidth()*0.231f,Gdx.graphics.getHeight()*0.1262f);
		txt = GameRunner.assets.get("LithuanianPassport.png");
	
	}
	public void render(SpriteBatch batch, ShapeRenderer shape){
		
		batch.begin();
		batch.draw(txt,10, 10, Gdx.graphics.getWidth()*0.3f,Gdx.graphics.getHeight()*0.135f);
		batch.end();
		
		
		if(debugMode){
		shape.setAutoShapeType(true);
		shape.begin(ShapeType.Line);
		shape.setColor(Color.BLACK);
		shape.rect(10, 10, Gdx.graphics.getWidth()*0.231f,Gdx.graphics.getHeight()*0.1262f);
		shape.end();
		}
		
	}

}

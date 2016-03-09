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
		rect = new Rectangle(0, -20, Gdx.graphics.getWidth()*0.38f,Gdx.graphics.getHeight()*0.15f);
		close();
	}
	
	public void open(){
		txt = GameRunner.assets.get("Borders/pasienis1open.png");
	}
	public void close(){
		txt = GameRunner.assets.get("Borders/pasienis1closed.png");
	}
	
	public void render(SpriteBatch batch, ShapeRenderer shape){
		
		batch.begin();
		batch.draw(txt,0, -20, Gdx.graphics.getWidth()*0.38f,Gdx.graphics.getHeight()*0.24f);
		batch.end();
		
		
		if(debugMode){
		shape.setAutoShapeType(true);
		shape.begin(ShapeType.Line);
		shape.setColor(Color.BLACK);
		shape.rect(rect.x,rect.y,
				rect.width,rect.height);
		shape.end();
		}
		
	}

}

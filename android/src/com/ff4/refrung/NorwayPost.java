package com.ff4.refrung;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class NorwayPost {

	private boolean debugMode = false;
	public Rectangle rect;
	private Texture txt;
	
	
	public NorwayPost(){
		rect = new Rectangle(370, -20
				,Gdx.graphics.getWidth()*0.27f,Gdx.graphics.getHeight()*0.16f);

		close();
	}
	public void open(){
		txt = GameRunner.assets.get("Borders/pasienis3open.png");
	}
	public void close(){
		txt = GameRunner.assets.get("Borders/pasienis3closed.png");
	}
	
	public void render(SpriteBatch batch, ShapeRenderer shape){
		
		batch.begin();
		batch.draw(txt,350, -20
				,Gdx.graphics.getWidth()*0.38f,Gdx.graphics.getHeight()*0.24f);
		batch.end();
		
		
		
		if(debugMode){
		shape.setAutoShapeType(true);
		shape.begin(ShapeType.Line);           
		shape.setColor(Color.GREEN);
		shape.rect(rect.x,rect.y,
				rect.width,rect.height);
		shape.end();
	}
	}
	
}

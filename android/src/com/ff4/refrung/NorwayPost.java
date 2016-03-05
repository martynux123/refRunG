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
		rect = new Rectangle(380, 10
				,Gdx.graphics.getWidth()*0.231f,Gdx.graphics.getHeight()*0.1262f);

		txt = GameRunner.assets.get("NorwayPassport.png");
	}
	
	public void render(SpriteBatch batch, ShapeRenderer shape){
		
		batch.begin();
		batch.draw(txt,380, 10
				,Gdx.graphics.getWidth()*0.3f,Gdx.graphics.getHeight()*0.135f);
		batch.end();
		
		
		if(debugMode){
		shape.setAutoShapeType(true);
		shape.begin(ShapeType.Line);           
		shape.setColor(Color.GREEN);
		shape.rect(380, 10
				,Gdx.graphics.getWidth()*0.231f,Gdx.graphics.getHeight()*0.1262f);
		shape.end();
	}
	}
	
}

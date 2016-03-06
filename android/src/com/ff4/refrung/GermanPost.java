package com.ff4.refrung;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class GermanPost {
	
	private boolean debugMode = false;
	public Rectangle rect;
	private Texture txt;
	
	public GermanPost(){
		rect = new Rectangle(710,10,
				Gdx.graphics.getWidth()*0.231f,Gdx.graphics.getHeight()*0.1262f);
		txt = GameRunner.assets.get("GermanPassport.png");
	}
	public void render(SpriteBatch batch, ShapeRenderer shape){
		
		batch.begin();
		batch.draw(txt,690,10,
				Gdx.graphics.getWidth()*0.32f,Gdx.graphics.getHeight()*0.2f);
		batch.end();
		
		
		if(debugMode){
		shape.setAutoShapeType(true);
		shape.begin(ShapeType.Line);
		shape.setColor(Color.BLUE);
		shape.rect(780,10,
				Gdx.graphics.getWidth()*0.231f,Gdx.graphics.getHeight()*0.1262f);
		shape.end();
		}
	}
}

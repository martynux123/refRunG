package com.ff4.refrung;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;



public class LithuanianRefugee {
	
	public float x;
	public float y;
	private float speed;
	private Texture[] refugee = new Texture[6];
	private int index;
	private int tickcount;
	
	public static final int DEFAULT_SPEED = 8;
	public static final int SIZE = 150;
	public Rectangle rect;
	private boolean debugMode = false;
	
	public LithuanianRefugee(float x, float y, float speed){
		this.x = x;
		this.y = y;
		this.speed = speed;
		rect = new Rectangle(x,y,SIZE,SIZE);
		
		
		refugee[0] = GameRunner.assets.get("badlogic.jpg");
		//refugee[0] = GameRunner.assets.get("LithuanianRef1.1");
		//refugee[1] = GameRunner.assets.get("LithuanianRef1.2");
		//refugee[2] = GameRunner.assets.get("LithuanianRef1.3");
		//refugee[3] = GameRunner.assets.get("LithuanianRef1.4");
		//refugee[4] = GameRunner.assets.get("LithuanianRef1.5");
		//refugee[5] = GameRunner.assets.get("LithuanianRef1.6");
		
	}
	public void render(SpriteBatch batch, ShapeRenderer shape){
		x-=Gdx.input.getAccelerometerX();
		y-=speed;
		
		if(tickcount>8){
			tickcount = 0;
			index++;
		}
		if(index>7){
			index = 0;
		}
		
		tickcount++;
		batch.begin();
		batch.draw(refugee[0], x, y, SIZE, SIZE);
		batch.end();
		
		if(debugMode){
			shape.setAutoShapeType(true);
			shape.setColor(Color.RED);
			shape.begin(ShapeType.Line);
			shape.rect(x, y, SIZE, SIZE);
			shape.end();
		}
		
	}

}

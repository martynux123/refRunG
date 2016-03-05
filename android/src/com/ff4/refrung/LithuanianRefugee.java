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
	private Texture[] refugee = new Texture[13];
	private int index;
	private int tickcount;
	
	public static final int DEFAULT_SPEED = (int) (Gdx.graphics.getHeight()*0.0040f);
	public static final int SIZE = (int) (Gdx.graphics.getWidth()*0.185f);
	
	public Rectangle rect;
	private boolean debugMode = true;
	
	public LithuanianRefugee(float x, float y, float speed){
		this.x = x;
		this.y = y;
		this.speed = speed;
		rect = new Rectangle(x,y,SIZE,SIZE);
		
		
		refugee[0] = GameRunner.assets.get("Lithuanian/Lithuanian1.1.png");
		refugee[1] = GameRunner.assets.get("Lithuanian/Lithuanian1.2.png");
		refugee[2] = GameRunner.assets.get("Lithuanian/Lithuanian1.3.png");
		refugee[3] = GameRunner.assets.get("Lithuanian/Lithuanian1.1.png");
		refugee[4] = GameRunner.assets.get("Lithuanian/Lithuanian1.2.png");
		refugee[5] = GameRunner.assets.get("Lithuanian/Lithuanian1.3.png");
		refugee[6] = GameRunner.assets.get("Lithuanian/Lithuanian1.1.png");
		refugee[7] = GameRunner.assets.get("Lithuanian/Lithuanian1.2.png");
		refugee[8] = GameRunner.assets.get("Lithuanian/Lithuanian1.3.png");
		refugee[9] = GameRunner.assets.get("Lithuanian/Lithuanian1.1.png");
		refugee[10] = GameRunner.assets.get("Lithuanian/Lithuanian1.2.png");
		refugee[11] = GameRunner.assets.get("Lithuanian/Lithuanian1.3.png");
		refugee[12] = GameRunner.assets.get("Lithuanian/Lithuanian1.4.png");
	}
	
	public void render(SpriteBatch batch, ShapeRenderer shape){
		
		rect.setPosition(x, y);
		
		x-=Gdx.input.getAccelerometerX()*5;
		y-=speed;
		
		if(tickcount>6){
			tickcount = 0;
			index++;
		}
		if(index>12){
			index = 0;
		}
		if(x<0){
			x=0;
		}
		if(x>Gdx.graphics.getWidth()-SIZE){
			x = Gdx.graphics.getWidth()-SIZE;
		}
		
		tickcount++;
		batch.begin();
		batch.draw(refugee[index], x, y, SIZE, Gdx.graphics.getHeight()*0.126f);
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

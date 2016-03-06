package com.ff4.refrung;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;



public class Terrorist {
	
	public float x;
	public float y;
	public float speed;
	private Texture[] refugee = new Texture[7];
	private int index;
	private int tickcount;
	
	public static final int DEFAULT_SPEED = 8;
	public static final int SIZE = 200;
	public Rectangle rect;
	private boolean debugMode = false;
	public boolean isExploding = false;
	private Texture[] explosion = new Texture[5];
	private int tickCount = 0;
	private int explodeIndex = 0;
	public boolean isDead = false;
	private Sound sound;
	
	public Terrorist(float x, float y, float speed){
		sound = GameRunner.assets.get("blast.wav");
		
		this.x = x;
		this.y = y;
		this.speed = speed;
		rect = new Rectangle(x,y,SIZE,SIZE);
		
		refugee[0] = GameRunner.assets.get("Terrorist/Terrorist1.1.png");
		refugee[1] = GameRunner.assets.get("Terrorist/Terrorist1.2.png");
		refugee[2] = GameRunner.assets.get("Terrorist/Terrorist1.3.png");
		refugee[3] = GameRunner.assets.get("Terrorist/Terrorist1.4.png");
		refugee[4] = GameRunner.assets.get("Terrorist/Terrorist1.1.png");
		refugee[5] = GameRunner.assets.get("Terrorist/Terrorist1.2.png");
		refugee[6] = GameRunner.assets.get("Terrorist/Terrorist1.4.png");
		
		explosion[0] = GameRunner.assets.get("explosion/1.png");
		explosion[1] = GameRunner.assets.get("explosion/2.png");
		explosion[2] = GameRunner.assets.get("explosion/3.png");
		explosion[3] = GameRunner.assets.get("explosion/4.png");
		explosion[4] = GameRunner.assets.get("explosion/5.png");
		
	}
	public void boomSound(){
		sound.play(1f);	
	}
	public void render(SpriteBatch batch, ShapeRenderer shape){
		
		rect.setPosition(x, y);
		
		x+=Gdx.input.getAccelerometerX()*MathUtils.random(5, 8);
		y-=speed;
		
		if(tickcount>6){
			tickcount = 0;
			index++;
		}
		if(index>6){
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
		
		if(debugMode){
			shape.setAutoShapeType(true);
			shape.setColor(Color.RED);
			shape.begin(ShapeType.Line);
			shape.rect(x, y, SIZE, SIZE);
			shape.end();
		}
		System.out.println();
		if(isExploding){ 
			batch.draw(explosion[explodeIndex], x - 200, y - 200, 600, 600);
			
			if(tickCount > 2){
				tickCount = 0;
				explodeIndex++;
			}
			
			if(explodeIndex > 4){
				isDead = true;
				isExploding = false;
			}
			
			tickCount++;
		}
		batch.end();
		
	}
	
	public void explode(){
		isExploding = true;
	}

}
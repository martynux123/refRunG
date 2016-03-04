package com.ff4.refrung;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LithuanianRefugee {
	
	private float x;
	private float y;
	private float speed;
	private Texture[] refugee = new Texture[6];
	private int index;
	private int tickcount;
	public static final int SIZE = 150;
	
	public LithuanianRefugee(float x, float y, float speed){
		this.x = x;
		this.y = y;
		this.speed = speed;
		
	}
	public void render(SpriteBatch batch){
		if(tickcount>8){
			tickcount = 0;
			index++;
		}
		if(index>7){
			index = 0;
		}
		
		tickcount++;
		batch.begin();
		batch.draw(refugee[index], x, y, SIZE, SIZE);
		batch.end();
		
	}
	public void tick(){
		
	}

	
}

package com.ff4.refrung;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class Credits {

	private boolean debugMode = false;
	private Texture txt;
	private SpriteBatch batch;
	private Rectangle rect;
	private ShapeRenderer shape;
	private GameRunner runner;
	public Credits(GameRunner runner){
		this.runner= runner;
		
		txt = new Texture(Gdx.files.internal("credits.png"));
		
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1f);
		batch = new SpriteBatch();
		rect = new Rectangle(0, 0, Gdx.graphics.getWidth()*0.277f, Gdx.graphics.getHeight()*0.126f);
		shape = new ShapeRenderer();
	}
	public void render(){
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
	int	touchX = Gdx.input.getX();
	int	touchY = Gdx.input.getY() + ((Gdx.graphics.getHeight() / 2 - Gdx.input.getY()) * 2);
		
	if(rect.contains(touchX, touchY)){
		runner.setScreen(new GameMenu(runner));
	}
		batch.begin();
		batch.draw(txt, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();
		
		if(debugMode){
			shape.setAutoShapeType(true);
			shape.begin(ShapeType.Line);
			shape.setColor(Color.RED);
			shape.rect(0, 0, Gdx.graphics.getWidth()*0.277f, Gdx.graphics.getHeight()*0.126f);
			shape.end();
			}
		
	}
	
	
}

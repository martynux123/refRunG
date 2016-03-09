package com.ff4.refrung;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;



public class NorwayRefugee {
	
	public float x;
	public float y;
	private float speed;
	private Texture[] refugee = new Texture[7];
	private int index;
	private int tickcount;
	private Preferences prefs;
	public boolean shouldAddScore = true;
	public static final int DEFAULT_SPEED = (int) (Gdx.graphics.getHeight()*0.0040f);
	public static final int SIZE = (int) (Gdx.graphics.getWidth()*0.185f);

	private ParticleEffect particles;
	public Rectangle rect;
	private boolean debugMode = false;
	
	
	public NorwayRefugee(float x, float y, float speed){
		this.x = x;
		this.y = y;
		this.speed = speed;
		particles = new ParticleEffect(GameRunner.particles);
		rect = new Rectangle(x,y,SIZE-100,SIZE-100);
		
		refugee[0] = GameRunner.assets.get("Norway/Norway1.1.png");
		refugee[1] = GameRunner.assets.get("Norway/Norway1.2.png");
		refugee[2] = GameRunner.assets.get("Norway/Norway1.3.png");
		refugee[3] = GameRunner.assets.get("Norway/Norway1.1.png");
		refugee[4] = GameRunner.assets.get("Norway/Norway1.2.png");
		refugee[5] = GameRunner.assets.get("Norway/Norway1.3.png");
		refugee[6] = GameRunner.assets.get("Norway/Norway1.4.png");
		
	}
	public void die(){
		
		prefs = Gdx.app.getPreferences("Stats");
		prefs.putInteger("Deaths", prefs.getInteger("Deaths")+1);
		
	}
	public void render(SpriteBatch batch, ShapeRenderer shape){
		
		if(shouldAddScore)
			x-=Gdx.input.getAccelerometerX()*MathUtils.random(5, 8);
		
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
		
		rect.setPosition(x, y);
		
		tickcount++;
		batch.begin();
		if(Gdx.input.getAccelerometerX() > 3){
			particles.setPosition(x + SIZE/2, y + SIZE/2);
			particles.update(Gdx.graphics.getDeltaTime());
//			particles.draw(batch);
		}
		batch.draw(refugee[index], x, y, SIZE, Gdx.graphics.getHeight()*0.126f);
		batch.end();
		
		if(debugMode){
			shape.setAutoShapeType(true);
			shape.setColor(Color.RED);
			shape.begin(ShapeType.Line);
			shape.rect(x+100,y,SIZE-150,SIZE-100);
			shape.end();
		}
		
	}

}
package com.me.starassault;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class WorldRenderer {
	private static final float CAMERA_WIDTH = 10f;
	private static final float CAMERA_HEIGHT = 7f;
	
	private World world;
	private OrthographicCamera cam;
	
	/** for debug rendering **/
	ShapeRenderer debugRenderer = new ShapeRenderer();
	
	private Texture bobTexture;
	private Texture blockTexture;

	private SpriteBatch spriteBatch;
	private boolean debug = false;
	private	int widthInPixels;
	private int heightInPixels;
	private float ppuX;
	private float ppuY;
	public void setSize(int w, int h){
		this.widthInPixels = w;
		this.heightInPixels = h;
		ppuX = (float)widthInPixels / CAMERA_WIDTH;
		ppuY = (float)heightInPixels / CAMERA_HEIGHT;
	}
	
	public WorldRenderer(World world, boolean debug){
		this.world = world;
		this.cam = new OrthographicCamera(10, 7);
		this.cam.position.set(5, 3.5f, 0);
		this.cam.update();
		this.debug = debug;
		spriteBatch = new SpriteBatch();
		loadTextures();
	}
	
	private void loadTextures() {
		bobTexture = new Texture(Gdx.files.internal("data/bob.jpg"));
		blockTexture = new Texture(Gdx.files.internal("data/cube.jpg"));
	}

	public void render(){
		spriteBatch.begin();
		drawBlocks();
		drawBob();
		spriteBatch.end();
		if(debug)
			drawDebug();
	}
	public void drawBlocks(){
		for (Block block : world.getBlocks()) {
			spriteBatch.draw(
					blockTexture,
					block.getPosition().x * ppuX,
					block.getPosition().y * ppuY,
					Block.SIZE * ppuX,
					Block.SIZE * ppuY);
		}
	}
	
	public void drawBob(){
		Bob bob = world.getBob();
		spriteBatch.draw(
				bobTexture,
				bob.getPosition().x * ppuX,
				bob.getPosition().y * ppuY,
				Bob.SIZE * ppuX,
				Bob.SIZE * ppuY);
	}
	
	public void drawDebug(){
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Line);
		for(Block block : world.getBlocks()){
			Rectangle rect = block.getBounds();
			float x1 = block.getPosition().x + rect.x;
			float y1 = block.getPosition().y + rect.y;
			debugRenderer.setColor(new Color(1, 0, 0, 1));
			debugRenderer.rect(x1, y1, rect.width, rect.height);
		}
		
		Bob bob = world.getBob();
		Rectangle rect = bob.getBounds();
		float x1 = bob.getPosition().x + rect.x;
		float y1 = bob.getPosition().y + rect.y;
		debugRenderer.setColor(new Color(0, 1, 0, 1));
		debugRenderer.rect(x1, y1, rect.width, rect.height);
		debugRenderer.end();
	}
}

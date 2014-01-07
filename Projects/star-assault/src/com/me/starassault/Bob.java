package com.me.starassault;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;

public class Bob {

	public enum State{
		IDLE,
		WALKING,
		JUMPING,
		DYING
	}
	
	public static final float SPEED = 4f; //unit per second
	static final float JUMP_VELOCITY = 1f;
	static final float SIZE = 0.5f; //half a unit
	
	Vector2 position = new Vector2();
	Vector2 acceleration = new Vector2();
	Vector2 velocity = new Vector2();
	Rectangle bounds = new Rectangle();
	State state = State.IDLE;
	boolean facingLeft = true;
	
	public Bob(Vector2 position){
		this.position = position;
		this.bounds.height = SIZE;
		this.bounds.width = SIZE;
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	public Vector2 getVelocity(){
		return velocity;
	}
	
	public Vector2 getAcceleration(){
		return acceleration;
	}
	
	public Vector2 getPosition(){
		return position;
	}
	
	public void setState(State state){
		this.state = state;
	}
	
	public void setFacing(boolean isLeft){
		this.facingLeft = isLeft;
	}
	
	public void update(float delta){
		position.add(velocity.cpy().scl(delta));
	}
}

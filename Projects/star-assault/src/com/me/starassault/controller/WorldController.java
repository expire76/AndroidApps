package com.me.starassault.controller;

import java.util.HashMap;
import java.util.Map;

import com.me.starassault.Bob;
import com.me.starassault.Bob.State;
import com.me.starassault.World;

public class WorldController {
	enum Keys {
		LEFT, RIGHT, JUMP, FIRE
	}

	private World world;
	private Bob bob;

	static Map<Keys, Boolean> keys = new HashMap<Keys, Boolean>();
	static {
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.JUMP, false);
		keys.put(Keys.FIRE, false);
	};

	public WorldController(World world) {
		this.world = world;
		this.bob = world.getBob();
	}

	public void leftPressed() {
		keys.get(keys.put(Keys.LEFT, true));
	}

	public void rightPressed() {
		keys.get(keys.put(Keys.RIGHT, true));
	}

	public void jumpPressed() {
		keys.get(keys.put(Keys.JUMP, true));
	}

	public void firePressed() {
		keys.get(keys.put(Keys.FIRE, true));
	}

	public void leftReleased() {
		keys.get(keys.put(Keys.LEFT, false));
	}

	public void rightReleased() {
		keys.get(keys.put(Keys.RIGHT, false));
	}

	public void jumpReleased() {
		keys.get(keys.put(Keys.JUMP, false));
	}

	public void fireReleased() {
		keys.get(keys.put(Keys.FIRE, false));
	}
	
    public void update(float delta) {
        processInput();
        bob.update(delta);
    }

	private void processInput() {
		if(keys.get(Keys.LEFT)){
			bob.setFacing(true);
			bob.setState(State.WALKING);
			bob.getVelocity().x = - Bob.SPEED;
		}
		if(keys.get(Keys.RIGHT)){
			bob.setFacing(false);
			bob.setState(State.WALKING);
			bob.getVelocity().x = Bob.SPEED;
		}
		if((keys.get(Keys.RIGHT) && keys.get(Keys.LEFT)) ||
				(!keys.get(Keys.RIGHT) && !keys.get(Keys.LEFT))){
			bob.setState(State.IDLE);
			bob.getAcceleration().x = 0;
			bob.getVelocity().x = 0;
		}
		
	}

}

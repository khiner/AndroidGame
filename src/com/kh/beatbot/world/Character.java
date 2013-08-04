package com.kh.beatbot.world;

import com.kh.beatbot.Position;
import com.kh.beatbot.ui.view.page.Page;

public abstract class Character {
	Position position;
	Position velocity = new Position(0, 0);
	float size;
	
	static float parentWidth = Page.mainPage.width,
			parentHeight = Page.mainPage.height;
	boolean touched = true;

	public void applyGravity(float gravity) {
		if (touched)
			return;
		if (position.y + size >= parentHeight || position.y - size <= 0) {
			velocity.y = - velocity.y;
		}
		if (position.x + size >= parentWidth || position.x - size <= 0){
			velocity.x = - velocity.x;
		}
		velocity.y += gravity;
	}

	public void applyDrag(float drag) {
		velocity.x *= drag;
	}
	
	public void update() {
		if (touched)
			return;
		position.set(clipX(position.x + velocity.x), clipY(position.y + velocity.y));
	}
	
	public void moveTo(float x, float y) {
		long deltaTime = (System.currentTimeMillis() - position.timeInMillis) / 4;
		float newX = clipX(x), newY = clipY(y);
		velocity.set((newX - position.x) / deltaTime, (newY - position.y) / deltaTime);
		position.set(newX, newY);
	}

	public void drop() {
		touched = false;
	}

	public void pickUp() {
		touched = true;
	}

	public abstract void draw();

	private float clipX(float x) {
		return x < (parentWidth - size) ? (x > size ? x : size)
				: (parentWidth - size);
	}

	private float clipY(float y) {
		return y < (parentHeight - size) ? (y > size ? y : size)
				: (parentHeight - size);
	}
}

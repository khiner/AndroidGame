package com.kh.beatbot.character;

import com.kh.beatbot.Position;
import com.kh.beatbot.ui.color.Colors;
import com.kh.beatbot.ui.view.View;
import com.kh.beatbot.ui.view.page.Page;

public class Character {
	Position position;
	Position velocity = new Position(0, 0);
	float size;
	static float parentWidth = Page.mainPage.width,
			parentHeight = Page.mainPage.height;
	boolean touched = true;

	public Character(float size) {
		this(size, new Position(parentWidth / 2, parentHeight / 2));
	}

	public Character(float size, Position position) {
		this.size = size;
		this.position = position;
	}

	public void applyGravity(float gravity) {
		if (touched)
			return;
		velocity.y += gravity;
		if (position.y + size >= parentHeight) {
			stop();
		} else {
			setPositionBounded(position.x + velocity.x, position.y + velocity.y);
		}
	}

	private void setPositionBounded(float x, float y) {
		float newX = x < (Page.mainPage.width - size) ? (x > size ? x : size)
				: (Page.mainPage.width - size);
		float newY = y < (Page.mainPage.height - size) ? (y > size ? y : size)
				: (Page.mainPage.height - size);
		setPosition(newX, newY);
	}

	private void setPosition(float x, float y) {
		position.set(x, y);
	}

	public void moveTo(float x, float y) {
		long deltaTime = (System.currentTimeMillis() - position.timeInMillis) / 4;
		velocity.set((x - position.x) / deltaTime, (y - position.y) / deltaTime);
		setPositionBounded(x, y);
	}
	
	public void drop() {
		touched = false;
	}

	public void pickUp() {
		touched = true;
		stop();
	}

	public void draw() {
		View.drawPoint(size, Colors.VOLUME, position.x, position.y);
	}

	private void stop() {
		velocity.y = 0;
	}
}

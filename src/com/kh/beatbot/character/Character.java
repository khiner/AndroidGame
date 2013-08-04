package com.kh.beatbot.character;

import com.kh.beatbot.Position;
import com.kh.beatbot.ui.color.Colors;
import com.kh.beatbot.ui.view.View;
import com.kh.beatbot.ui.view.page.Page;

public class Character {
	Position position;
	Position velocity = new Position(0, 0);
	float size, parentHeight = Page.mainPage.height;
	
	public Character(float size) {
		this(size, new Position(0, 0));
	}

	public Character(float size, Position position) {
		this.size = size;
		this.position = position;
	}

	public void applyGravity(float gravity) {
		velocity.y *= gravity;
		if (position.y + size >= parentHeight) {
			velocity.y = 0;
		}
		position.add(velocity);
	}

	public void setPosition(float x, float y) {
		velocity.x = x - position.x;
		position.x = x;
		position.y = y;
	}

	public void drop() {
		velocity.y = 1;
	}
	
	public void draw() {
		View.drawPoint(size, Colors.VOLUME, position.x, position.y);
	}
}

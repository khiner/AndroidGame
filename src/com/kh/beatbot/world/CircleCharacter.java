package com.kh.beatbot.world;

import com.kh.beatbot.Position;
import com.kh.beatbot.ui.color.Colors;
import com.kh.beatbot.ui.view.View;

public class CircleCharacter extends Character {
	
	public CircleCharacter(float size) {
		this(size, new Position(parentWidth / 2, parentHeight / 2));
	}

	public CircleCharacter(float size, Position position) {
		this.size = size;
		this.position = position;
	}
	
	public void draw() {
		View.drawPoint(size, Colors.VOLUME, position.x, position.y);
	}
}

package com.kh.beatbot.ui.view.page;

import com.kh.beatbot.listener.OnReleaseListener;
import com.kh.beatbot.ui.RoundedRectIcon;
import com.kh.beatbot.ui.color.Colors;
import com.kh.beatbot.ui.view.control.Button;
import com.kh.beatbot.ui.view.control.ImageButton;
import com.kh.beatbot.world.Character;
import com.kh.beatbot.world.CircleCharacter;
import com.kh.beatbot.world.Environment;

public class MainPage extends Page {
	ImageButton startButton;
	float colorTransitionRate = 30;
	float[] currColor = new float[] { 1, 1, 1, 1 };
	int frameCount = 0;
	
	Character character;
	
	@Override
	public void update() {

	}

	@Override
	public void createChildren() {
		startButton = new ImageButton();
		startButton.setText("Start");
		startButton.setBgIcon(new RoundedRectIcon(null, Colors.labelBgColorSet,
				Colors.labelStrokeColorSet));
		
		startButton.setOnReleaseListener(new OnReleaseListener() {
			@Override
			public void onRelease(Button button) {
				removeChild(button);

			}
		});
		addChild(startButton);
	}

	@Override
	public void layoutChildren() {
		character = new CircleCharacter(height / 10);
		Environment.addCharacter(character);
		startButton.layout(this, width / 2 - width / 8, height / 2 - height
				/ 16, width / 4, height / 8);
	}

	@Override
	public void draw() {
		currColor[1] = currColor[2] = (frameCount++ % colorTransitionRate)
				/ colorTransitionRate;
		setBackgroundColor(currColor);
		Environment.tick();
	}

	@Override
	public void handleActionMove(int id, float x, float y) {
		character.moveTo(x, y);
	}
	
	@Override
	public void handleActionUp(int id, float x, float y) {
		character.drop();
	}
	
	@Override
	public void handleActionDown(int id, float x, float y) {
		character.pickUp();
	}
}

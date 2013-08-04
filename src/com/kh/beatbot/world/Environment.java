package com.kh.beatbot.world;

import java.util.ArrayList;
import java.util.List;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

import com.kh.beatbot.Position;

public class Environment implements SensorEventListener {
	private static List<Character> characters = new ArrayList<Character>();
	private static Position gravity = new Position(0, 0.9f);
	private static float drag = 0.9f;
	
	public static void addCharacter(Character character) {
		characters.add(character);
	}

	public static void tick() {
		for (Character character : characters) {
			character.applyGravity(gravity);
			character.applyDrag(drag);
			character.update();
			character.draw();
		}
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		float axisX = event.values[0];
		float axisY = event.values[1];
		//float axisZ = event.values[2];
		
		gravity.x = axisY;
		gravity.y = axisX;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}
}

package com.kh.beatbot.world;

import java.util.ArrayList;
import java.util.List;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

public class Environment implements SensorEventListener {
	private static List<Character> characters = new ArrayList<Character>();
	private static float gravity = 1.1f, drag = 0.9f;
	
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
		float axisZ = event.values[2];

		Log.i(
			"Beatbot",
			"Axis X: " + axisX + "; Axis Y: " + axisY + "; Axis Z: " + axisZ
		);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}
}

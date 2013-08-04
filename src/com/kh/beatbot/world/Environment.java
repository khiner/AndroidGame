package com.kh.beatbot.world;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

public class Environment implements SensorEventListener {
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

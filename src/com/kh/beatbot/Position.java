package com.kh.beatbot;

public class Position {

	public float x, y;
	public long timeInMillis;
	
	public Position(float x, float y) {
		set(x, y);
	}

	public void set(float x, float y) {
		this.x = x;
		this.y = y;
		timeInMillis = System.currentTimeMillis();
	}
	
	public void add(Position other) {
		this.x += other.x;
		this.y += other.y;
	}
}

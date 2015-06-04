package com.example.romotecontroller;


public class DriveMessage extends Message {

	int leftSpeed, rightSpeed;

	public DriveMessage(int leftSpeed, int rightSpeed) {
		this.leftSpeed = leftSpeed;
		this.rightSpeed = rightSpeed;
	}


}

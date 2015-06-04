package com.example.romotecontroller;

import java.io.IOException;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Handles all sending and parsing of message with the robot. This class
 * inmplements runnable so it can be started in a new thread.
 * 
 * @author peter
 * 
 */
public class Communicator extends AsyncTask<Void, Integer, Void> {

	private Message m;

	public Communicator(Message m) {
		this.m = m;
	}

	@Override
	protected Void doInBackground(Void... params) {
		try {
			CommStreams.out.writeObject(m);
		} catch (IOException e) {
			Log.e("send error", "ERROR " + e.getCause() + " " + e.getMessage());
			for (StackTraceElement ste : e.getStackTrace()) {
				Log.e("trace", ste.toString());
			}
		}
		return null;
	}

	@Override
	public void onPostExecute(Void result) {
	}

}

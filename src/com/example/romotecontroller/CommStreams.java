package com.example.romotecontroller;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observer;

import android.os.AsyncTask;
import android.util.Log;

public class CommStreams extends AsyncTask<Void, Void, Void> {

	private static ServerSocket socket;
	private static Socket conn;
	public static ObjectOutputStream out;
	private Observer o;

	public CommStreams(Observer o) {
		this.o = o;
	}

	@Override
	protected Void doInBackground(Void... params) {
		try {
			socket = new ServerSocket(1234);
			conn = socket.accept();
			out = new ObjectOutputStream(conn.getOutputStream());
		} catch (IOException e) {
			Log.e("comm error", e.getMessage());
			Log.e("comm error", e.getCause().toString());
		}
		return null;
	}

	@Override
	public void onPostExecute(Void result) {
		o.update(null, null);
	}
}

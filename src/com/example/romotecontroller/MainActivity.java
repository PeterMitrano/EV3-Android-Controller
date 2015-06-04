package com.example.romotecontroller;

import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnSeekBarChangeListener, Observer, OnClickListener {

	private SeekBar leftPowerBar, rightPowerBar;
	private Button terminate;
	private boolean send;
	private int leftSpeed = 0, rightSpeed = 0;
	private LinearLayout background;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		terminate = (Button) findViewById(R.id.terminate);
		background = (LinearLayout) findViewById(R.id.background);
		leftPowerBar = (SeekBar) findViewById(R.id.leftPowerBar);
		rightPowerBar = (SeekBar) findViewById(R.id.rightPowerBar);

		terminate.setOnClickListener(this);

		leftPowerBar.setProgress(50);
		rightPowerBar.setProgress(50);

		leftPowerBar.setOnSeekBarChangeListener(this);
		rightPowerBar.setOnSeekBarChangeListener(this);

		background.setBackgroundColor(getResources().getColor(R.color.red));
		new CommStreams(this).execute();
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		if (seekBar == leftPowerBar) {
			leftSpeed = 2 * progress - 100;
		} else if (seekBar == rightPowerBar) {
			rightSpeed = 2 * progress - 100;
		}
		if (send) {
			new Communicator(new DriveMessage(leftSpeed, rightSpeed)).execute();
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		new Communicator(new DriveMessage(0, 0)).execute();
		leftPowerBar.setProgress(50);
		rightPowerBar.setProgress(50);
	}

	@Override
	public void update(Observable observable, Object data) {
		background.setBackgroundColor(getResources().getColor(R.color.green));
		Toast.makeText(this, "Connected!", Toast.LENGTH_SHORT).show();
		send = true;
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == terminate.getId()) {
			new Communicator(new TerminateMessage()).execute();
		}

	}

}

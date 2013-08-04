package com.kh.beatbot.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.kh.beatbot.GeneralUtils;
import com.kh.beatbot.R;
import com.kh.beatbot.ui.color.Colors;
import com.kh.beatbot.ui.view.View;
import com.kh.beatbot.ui.view.group.GLSurfaceViewGroup;
import com.kh.beatbot.ui.view.group.ViewPager;
import com.kh.beatbot.ui.view.page.MainPage;
import com.kh.beatbot.ui.view.page.Page;
import com.kh.beatbot.world.Environment;

public class MainActivity extends Activity {
	private static ViewPager activityPager;

	public static MainActivity mainActivity;
	public static final int MAIN_PAGE_NUM = 0;

	public static final int EXIT_DIALOG_ID = 0;

	public SensorManager sensorMgr;
	public Sensor sensorAccel;
	public static Environment environment;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GeneralUtils.initAndroidSettings(this);
		mainActivity = this;
		View.font = Typeface.createFromAsset(getAssets(),
				"REDRING-1969-v03.ttf");
		Colors.initColors(this);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT);
		View.root = new GLSurfaceViewGroup(this);
		View.root.setLayoutParams(lp);

		LinearLayout layout = new LinearLayout(this);
		layout.addView(View.root);
		setContentView(layout, lp);

		Page.mainPage = new MainPage();

		activityPager = new ViewPager();
		activityPager.addPage(Page.mainPage);
		activityPager.setPage(0);

		sensorMgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sensorAccel = sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		environment = new Environment();

		((GLSurfaceViewGroup) View.root).setBBRenderer(activityPager);
	}

	@Override
	public void onBackPressed() {
		if (activityPager.getCurrPageNum() == MAIN_PAGE_NUM) {
			showDialog(EXIT_DIALOG_ID);
		}
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		switch (id) {
		case EXIT_DIALOG_ID:
			break;
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		switch (id) {
		case EXIT_DIALOG_ID:
			builder.setIcon(android.R.drawable.ic_dialog_alert)
					.setTitle("Closing " + getString(R.string.app_name))
					.setMessage(
							"Are you sure you want to exit "
									+ getString(R.string.app_name) + "?")
					.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									finish();
								}
							}).setNegativeButton("No", null);
			break;
		}
		return builder.create();
	}

	@Override
	public void onPause() {
		View.root.onPause();
		sensorMgr.unregisterListener(environment);
		super.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
		View.root.onResume();
		sensorMgr.registerListener(
			environment,
			sensorAccel,
			SensorManager.SENSOR_DELAY_NORMAL
		);
	}
}

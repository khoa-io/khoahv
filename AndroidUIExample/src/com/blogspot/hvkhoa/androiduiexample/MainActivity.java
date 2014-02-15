package com.blogspot.hvkhoa.androiduiexample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.blogspot.hvkhoa.androiduiexample.MainActivity.MESSAGE";

	//private Button btnTap, btnDone;
	private EditText editTextName, editTextReminder;
	private String message;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//btnTap = (Button) findViewById(R.id.btnTap);
		//btnDone = (Button) findViewById(R.id.btnDone);
		editTextName = (EditText) findViewById(R.id.editTextName);
		editTextReminder = (EditText) findViewById(R.id.editTextReminder);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/** Called when the user clicks the Done button */
	public void sendMessage(View view) {
		// Do something in response to button
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		String message = editTextName.getText().toString() + "\n"
				+ editTextReminder.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		// show a toast message
		Context context = getApplicationContext();
		CharSequence text = message;
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		// start DisplayMessageActivity
		startActivity(intent);

	}

}

package com.example.esercitazione;

import com.example.esercitazione.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Secondo_articolo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_secondo_articolo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_secondo_articolo, menu);
		return true;
	}

}

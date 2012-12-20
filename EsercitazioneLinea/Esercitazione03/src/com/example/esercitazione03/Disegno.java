package com.example.esercitazione03;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

/**
 * 
 * @author Laudenzi Alessandro 0162020
 *
 */

public class Disegno extends Activity {

	//QUI DEVO METTERCI TUTTA IL 'DisegnoTest' PIU' IL PEZZO DELLA CHAT
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new DisegnoTest(this)); //Mi accede al layout della classe DisegnoTest
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.activity_disegno, menu);
		return false;
		}

}

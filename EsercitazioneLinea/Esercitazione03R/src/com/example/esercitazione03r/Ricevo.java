package com.example.esercitazione03r;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 * 
 * @author Laudenzi Alessandro 0162020
 *
 */

public class Ricevo extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new RicevoTest(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_ricevo, menu);
        return true;
    }
}

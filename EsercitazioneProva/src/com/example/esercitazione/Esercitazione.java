package com.example.esercitazione;

import com.example.esercitazione.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

/**
 * 
 * Per l'esercizio si era partiti con l'idea di fare un'array con più valori, contenti titolo e data, da riempire manualmente; dove a seconda della selezione 
 * un ciclo for analizzava l'array e restituiva il corrispondente articolo. Purtroppo, causa dificoltà (e disperazione) e comunque per consegnare qualcosa che 
 * comunque "svolge" ciò che chiede l'esercizio, si è ripiegato su un'altro metodo meno elegante utilizzando due (o più) bottoni che aprono la seconda activity 
 * con l'articolo selezionato.
 * 
 * @author Laudenzi Alessandro 0162020
 *
 */

  /** Activity Principale */

public class Esercitazione extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_esercitazione);
		
		//Bottone che permette di visualizzare la seconda Activity,nel nostro caso, il primo articolo
		Button Articolo_1 = (Button) findViewById(R.id.button1);
		Articolo_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				/**Andiamo in modo esplicito alla seconda Activity
				Quindi in questo caso l' Intent sarà caratterizzato da informazioni relative all’operazione da
				eseguire e da un meccanismo per l’identificazione del tipo di dati su cui la stessa opererà.*/
				
				Intent primo = new Intent(Esercitazione.this,Primo_articolo.class);
				startActivity(primo);
				
			}
		});
		
		//Bottone che permette di visualizzare la terza Activity,nel nostro caso, il secondo articolo
		Button Articolo_2 = (Button) findViewById(R.id.button2);
		Articolo_2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent secondo = new Intent(Esercitazione.this,Secondo_articolo.class);
				startActivity(secondo);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_esercitazione, menu);
		return true;
	}

}

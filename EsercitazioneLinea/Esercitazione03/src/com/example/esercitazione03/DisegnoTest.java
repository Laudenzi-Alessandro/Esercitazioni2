package com.example.esercitazione03;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class DisegnoTest extends View {
	
	int x = 50;
	int y = 50;
	Path linea = new Path(); 
	Paint punto = new Paint();
	boolean selected = false;
	
	String riga = "";
	XMPPConnection connection ;
	
	//Editor di testo VEDERE
	public DisegnoTest(Context context) {
		// TODO Auto-generated constructor stub
		super(context);
		punto.setColor(Color.BLACK); //Come in MyView del prj DragImageView qui imposto i parametri dell'oggetto, 
		                            //l'immagine o nel nostro caso il colore del tratto
		punto.setStrokeWidth(3);
		punto.setStyle(Paint.Style.STROKE);
	}
	
	@Override
	 //Il metodo onDraw è responsabile del disegno del componente
	//L'oggetto Canvas effettua l'operazione di disegno VEDERE
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		//punto.setStrokeWidth(3);             //Definisco lo spessore della linea
		//punto.setStyle(Paint.Style.STROKE); //Imposta lo stile della pittura, ciò che andrà disegnato (riga, area da punto a punto...)	           
		canvas.drawPath(linea, punto);       //Disegna il tratto continuo
	}	
	
	@Override
	//Si implementa questo metodo per gestire eventi di "tocco" dello schermo
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		int eventaction = event.getAction();
		//Le coordinate del mio tocco sullo schermo
		int touchx = (int) event.getX();  //Restituisce la coordinata X di questo evento 
		int touchy = (int) event.getY(); //Restituisce la coordinata Y di questo evento
		
		switch(eventaction){
		
		//E' iniziato il tocco, la mozione contiene la posizione iniziale di partenza 
		case MotionEvent.ACTION_DOWN:
			linea.moveTo(touchx, touchy); //Si definisce dove parte la linea dal click in input
			selected = true;
			
			riga = "TraccioIO;" + touchx + "&" + touchy;
			invia(); 
			
			break; //Si esce dal blocco se la condizione è vera
			
		//Tutti i cambiamenti avvenuti tra ACTION_DOWN e ACTION_UP 
		case MotionEvent.ACTION_MOVE:
			x = touchx;
			y = touchy;
			linea.lineTo(x, y);
			invalidate();
			
			riga = "MuovoIO;" + touchx + "&" + touchy;
			invia(); 
			
			break;
			
		//L'azione che sto compiendo è finita, si tiene conto della posizione finale ed eventuali punti intermedi
		case MotionEvent.ACTION_UP:
		selected = false;
		
		riga = "LascioIO;" + touchx + "&" + touchy;
		invia(); 
		
		break;
		}
		
		return true;
	} //FINO A QUI FUNZIONA E DISEGNA
	
	//PARTE DELLA CHAT CHE GIUSTAMENTE NON FUNZIONA
	public void connect() {
		Runnable runnable = new Runnable() {
			//Creazione del Thread 
			public void run() {
				// TODO Auto-generated method stub
				
				ConnectionConfiguration config= new ConnectionConfiguration("ppl.eln.uniroma2.it",5222);  // Connessione al server dove andremo ad inviare 
				                                                                                    // i nostri messaggi i quali 
			                                                                                       // verranno visualizzati da tutti gli utenti connessi.
				                                                                                  // Nelle parentesi tonde ci sono rispettivamente 
			                                                                                     // nome del server e la porta.
				config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled); // Per le nostre prove si disabilita la sicurezza
				connection = new XMPPConnection(config); // Creiamo la connessione
				//Corpo del Thread
				try {
					connection.connect(); // Ci connettiamo alla chat
					connection.login("laudenzi","qwerty"); 
				} catch (XMPPException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		
		new Thread(runnable).start();
	}
	
	// Questo blocco di codice rappresenta il metodo contenente le istruzioni utili ad inviare la "riga" tracciata 
	public void invia() {
		Runnable runnable =  new Runnable() {
			
			public void run() {
				
			Log.d("XMPPChat", "Traccio: " + riga);
			Message msg = new Message(); 
			msg.setTo("all@broadcast.ppl.eln.uniroma2.it");
			msg.setBody(riga);
			connection.sendPacket(msg); 
				
			}
		};
		
		Thread nuovo = new Thread(runnable);
		nuovo.start();
		
	} 


}
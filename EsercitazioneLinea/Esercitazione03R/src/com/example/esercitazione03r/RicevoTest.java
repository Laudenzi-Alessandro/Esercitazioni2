package com.example.esercitazione03r;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.view.View;

import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import android.util.Log;

//PARTE DEL RECEIVER CHE GIUSTAMENTE NON SO SE FUNZIONA
public class RicevoTest extends View {
	
	int x = 50;
	int y = 50;
	boolean selected = false;
	Path linea = new Path();
	Paint punto = new Paint();
	Handler handler; //Per registrare i punti touch
	
	String riga = "";
	String[] testo;
	private XMPPConnection connection;
	
	public RicevoTest(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		punto.setColor(Color.BLACK);
		handler = new Handler();
		connect();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		punto.setStrokeWidth(3);
		punto.setStyle(Paint.Style.STROKE);
		canvas.drawPath(linea, punto);
	}
	
	public void connect() {
		Runnable runnable = new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				
				ConnectionConfiguration config = new ConnectionConfiguration("ppl.eln.uniroma2.it",5222);
				config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled); 
				connection = new XMPPConnection(config); 
				
				try {
					connection.connect();
					connection.login("laudenzi", "qwerty");
					connection.addPacketListener(new PacketListener() {
						
						public void processPacket(Packet arg0) {
							// TODO Auto-generated method stub
							
							Message msg = new Message();
							//Prendo gli elementi del messaggio
							final String to = msg.getTo(); 
							String body = msg.getBody();
							//Elimino ciò che c'è dopo la @
							final String[] from = msg.getFrom().split("@");
							
							Log.d("XMPPChat","Hai ricevuto un messaggio: " + from + " " + to + " " + body);
							testo = body.split("&");
							Log.d("XMP", "Testo=" + testo[0] + " Testo1= " + testo[1] + " Testo2= " + testo[2]);
							
							x = Integer.parseInt(testo[1]);
							y = Integer.parseInt(testo[2]);
							
							handler.post(new Runnable() {
								
								public void run() {
									// TODO Auto-generated method stub
									
									// Analizzo i vari casi, a seconda da ciò che ho ricevuto, ACTION_DOWN, ACTION_MOVE o ACTION_UP 
									if(testo[0].equals("TraccioIO")){
										linea.moveTo((int)x, (int)y);
										Log.d("XMP","TraccioIO" + x);
										
									}else if(testo[0].equals("MuovoIO")){
										linea.lineTo((int)x,(int)y);
										
									}else if(testo[0].equals("LascioIO")){
										
									};
									invalidate();
									
								}
							});
						}
				
			}, new MessageTypeFilter(Message.Type.normal));
					
		}catch (XMPPException e1) {			
			// TODO Auto-generated catch block			
			e1.printStackTrace();		}
		}
	};
	
	new Thread(runnable).start();
	
	}

             public void invia() {
            	 Runnable runnable =  new Runnable() {
            		 
            		 public void run() {
            			 Log.d("XMPPChat","Traccio: "+ riga);
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
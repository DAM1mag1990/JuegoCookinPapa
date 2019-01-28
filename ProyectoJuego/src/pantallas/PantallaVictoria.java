package pantallas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

import base.PanelJuego;
import base.Pantalla;


public class PantallaVictoria implements Pantalla {
	Color colorLetra = Color.YELLOW;
	PanelJuego paneljuego;
	Image fondo = null;
	Image fondoescalado = null;
	private DecimalFormat formatoDecimal;
	private Thread HiloMusical;

	public PantallaVictoria(PanelJuego paneljuego) {
		System.out.println("Entro en pantalla victoria");
		this.paneljuego = paneljuego;
		inicializarPantalla();
		redimensionarPantalla();
		formatoDecimal = new DecimalFormat("#.##");
	}

	@Override
	public void inicializarPantalla() {
		
		// Set the blank cursor to the JFrame.
		paneljuego.setCursor(null);
	
		try {
			fondo = ImageIO.read(new File("Imagenes/PantallaVictoria.png"));
			ejecutarFrame();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	public void renderizarPantalla(Graphics g) {
	
	}

	@Override
	public void ejecutarFrame() {
		paneljuego.repaint();
		try {
			Thread.sleep(25);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			colorLetra = colorLetra == Color.YELLOW ? Color.GREEN : Color.YELLOW;

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void moverRaton(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pulsarRaton(MouseEvent e) {
		paneljuego.setPantallaActual(new PantallaInicial(paneljuego));

	}


	@Override
	public void pintarPantalla(Graphics g) {
		g.drawImage(fondoescalado, 0, 0, null);
		g.setColor(Color.CYAN);
		g.setFont(new Font("MiLetra", Font.ITALIC, 50));
		g.drawString("Felicidades Cocinas Mucho mejor que PaPá!! ", paneljuego.getWidth() / 2 - 450,
				paneljuego.getHeight() / 2 - 230);
		g.setColor(Color.RED);
		g.setFont(PantallaJuego.fuenteTiempo);

		//g.drawString(formatoDecimal.format((PantallaJuego.tiempoDeJuego) / 1000000000) + " segundos.",
			//	paneljuego.getWidth() / 2 - 60, paneljuego.getHeight() / 2 - 70);
		g.setColor(colorLetra);
		g.setFont(new Font("MiLetra", Font.BOLD, 35));
		g.drawString("Volver a Jugar", paneljuego.getWidth() / 2 - 110, paneljuego.getHeight() / 2 + 100);		
	}


	public void redimensionarPantalla() {
		fondoescalado = fondo.getScaledInstance(paneljuego.getWidth(), paneljuego.getHeight(), Image.SCALE_SMOOTH);
		
	}

	@Override
	public void redimensionarPantalla(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}



}

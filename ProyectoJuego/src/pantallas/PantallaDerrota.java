package pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

import base.PanelJuego;
import base.Pantalla;


public class PantallaDerrota implements Pantalla {
	private DecimalFormat formatoDecimal;
	Color colorLetra = Color.YELLOW;
	Thread HiloMusical = null;
	PanelJuego panelJuego;
	Image fondo = null;
	Image fondoescalado = null;

	public PantallaDerrota(PanelJuego panelJuego) {
		this.panelJuego = panelJuego;
		formatoDecimal = new DecimalFormat("#.##");
		inicializarPantalla();
		redimensionarPantalla();
	}

	@Override
	public void inicializarPantalla() {
		panelJuego.setCursor(null);
		try {
			fondo = ImageIO.read(new File("Imagenes/PantallaDerrota.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public void renderizarPantalla(Graphics g) {
	
	}

	@Override
	public void ejecutarFrame() {
		panelJuego.repaint();
		try {
			Thread.sleep(25);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			colorLetra = colorLetra == Color.BLUE ? Color.BLACK : Color.BLUE;

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
		panelJuego.setPantallaActual(new PantallaInicial(panelJuego));

	}

	public void redimensionarPantalla() {
		fondoescalado = fondo.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(), Image.SCALE_SMOOTH);

	}

	

	@Override
	public void pintarPantalla(Graphics g) {
		g.drawImage(fondoescalado, 0, 0, null);

		g.setColor(Color.BLACK);
		g.setFont(new Font("MiLetra", Font.BOLD, 35));
		g.drawString("Ohhhhh!! Parece que PaPá...      ", 75,
				panelJuego.getHeight() / 2 - 200);
		g.drawString(" pedirá la comida por telefono", 75,
				panelJuego.getHeight() / 2 - 100);
		g.setColor(Color.BLACK);
		g.setFont(new Font("MiLetra", Font.BOLD, 24));
	

		g.setColor(colorLetra);
		g.setFont(new Font("MiLetra", Font.BOLD, 35));
		g.drawString("Volver a Jugar", panelJuego.getWidth() / 2 - 110, panelJuego.getHeight() / 2 + 100);
		
	}

	@Override
	public void redimensionarPantalla(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
}

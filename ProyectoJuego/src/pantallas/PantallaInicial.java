package pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import base.PanelJuego;
import base.Pantalla;

public class PantallaInicial implements Pantalla {

	public static PanelJuego panelJuego;
	
	BufferedImage imagenOriginalInicial;
	Image imagenReescaladaInicial;
	Font fuenteInicial;
	//Inicio pantalla
	Color colorLetras = Color.YELLOW;
	int contadorColorFrames = 0;
	static final int CAMBIO_COLOR_INICIO = 10;
	
	public PantallaInicial(PanelJuego panelJuego) {
		this.panelJuego = panelJuego;
		panelJuego.setSize( (int) panelJuego.getWidth(), (int) panelJuego.getHeight());
		panelJuego.setLocation( (int) panelJuego.getWidth() - panelJuego.getWidth(), 0 );
	}
	
	
	@Override
	public void inicializarPantalla() {
		try {
			imagenOriginalInicial = ImageIO.read(new File("Imagenes/Presentacion.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		fuenteInicial = new Font("Arial", Font.BOLD, 45); 
	}

	@Override
	public void pintarPantalla(Graphics g) {
		g.drawImage(imagenReescaladaInicial, 0,0, null);
		g.setColor(colorLetras);
		g.setFont(fuenteInicial);
		g.drawString("!Presiona el ratón y cocina para Papá!",panelJuego.getWidth()/2-400,panelJuego.getHeight()-200);
		
	}

	@Override
	public void ejecutarFrame() {
		contadorColorFrames++;
		if(contadorColorFrames % CAMBIO_COLOR_INICIO == 0) {
			
			if(colorLetras.equals(Color.YELLOW)) {
				colorLetras = Color.RED;
			}else {
				colorLetras = Color.YELLOW;
			}
		}

	}

	@Override
	public void moverRaton(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pulsarRaton(MouseEvent e) {
		PantallaJuego pantallaJuego = new PantallaJuego(panelJuego);
		pantallaJuego.inicializarPantalla();
		panelJuego.setPantallaActual(pantallaJuego);
		
		
		
		
		

	}
	public void redimensionarPantalla() {
		imagenReescaladaInicial = imagenOriginalInicial.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(), Image.SCALE_SMOOTH);
		
	}
	@Override
	public void redimensionarPantalla(ComponentEvent e) {
		imagenReescaladaInicial = imagenOriginalInicial.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(), Image.SCALE_SMOOTH);
	

	}
	

}

package pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import base.PanelJuego;
import base.Pantalla;
import base.Sprite;

public class PantallaJuego implements Pantalla {
	private static final int ANCHO_SARTEN = 65;
	private static final int ANCHO_INGREDIENTE = 300;
	private static final int ALTO_INGREDIENTE = 100;
	private static final int VELOCIDAD_INGREDIENTE = 10;
	private static final Color COLOR_PUNTUACION = Color.WHITE;

	PanelJuego panelJuego;

	// Array que almacena todos los cuadrados que se moverán por pantalla.
	String[] imagenesRutas = { "Imagenes/baseBurguer.png" + "", "Imagenes/Hamburguesa.png", "Imagenes/Lomo.png",
			"Imagenes/panSuperior.png", "Imagenes/Aceptar.png", "Imagenes/Bacon.png", "Imagenes/Huevo.png",
			"Imagenes/queso.png", "Imagenes/Lechuga.png", "Imagenes/Ketchup.png" };
	ArrayList<String> burguerJuga;
	ArrayList<String> BurguerEjem;
	ArrayList<String> BurguerEjem2;
	ArrayList<String> BurguerEjem3;
	ArrayList<String> BurguerEjem4;
	ArrayList<String> BurguerEjem5;
	ArrayList<String> BurguerEjem6;
	ArrayList<Sprite> ingredientes;
	ArrayList<Boolean> correctas;
	Sprite[] botones = new Sprite[10];
	Sprite ejemplo;
	int contadorIngre = 0;
	BufferedImage imagenOriginal;
	Image imagenReescalada;
	boolean flag = false;
	Sprite raton;
	Sprite ingrediente;
	int c = 0;
	int contadorFallos = 3;
	// Variables para el contador de tiempo
	Image vida = null, vidaEscalado = null;
	double tiempoInicial;
	public static double tiempoDeJuego;
	private DecimalFormat formatoDecimal; // Formatea la salida.
	public static Font fuenteTiempo;
	boolean correcto = false;

	public PantallaJuego(PanelJuego panelJuego) {

	

		this.panelJuego = panelJuego;
		tiempoDeJuego = 0;
		tiempoInicial = System.nanoTime();
		panelJuego.setCursor(panelJuego.getToolkit().createCustomCursor(
	            new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),
	            "null"));

	}

	@Override
	public void inicializarPantalla() {
		ingredientes = new ArrayList<Sprite>();
		burguerJuga = new ArrayList<String>();
		correctas = new ArrayList<>();
		BurguerEjem = new ArrayList<>();
		BurguerEjem.add("Imagenes/baseBurguer.png" + "");
		BurguerEjem.add("Imagenes/Hamburguesa.png");
		BurguerEjem.add("Imagenes/panSuperior.png");

		BurguerEjem2 = new ArrayList<>();
		BurguerEjem2.add("Imagenes/baseBurguer.png" + "");
		BurguerEjem2.add("Imagenes/queso.png");
		BurguerEjem2.add("Imagenes/Bacon.png");
		BurguerEjem2.add("Imagenes/Huevo.png");
		BurguerEjem2.add("Imagenes/panSuperior.png");

		BurguerEjem3 = new ArrayList<>();
		BurguerEjem3.add("Imagenes/baseBurguer.png" + "");
		BurguerEjem3.add("Imagenes/Hamburguesa.png");
		BurguerEjem3.add("Imagenes/queso.png");
		BurguerEjem3.add("Imagenes/Bacon.png");
		BurguerEjem3.add("Imagenes/panSuperior.png");

		BurguerEjem4 = new ArrayList<>();
		BurguerEjem4.add("Imagenes/baseBurguer.png" + "");
		BurguerEjem4.add("Imagenes/Ketchup.png");
		BurguerEjem4.add("Imagenes/Hamburguesa.png");
		BurguerEjem4.add("Imagenes/Bacon.png");
		BurguerEjem4.add("Imagenes/baseBurguer.png" + "");
		BurguerEjem4.add("Imagenes/Hamburguesa.png");
		BurguerEjem4.add("Imagenes/queso.png");
		BurguerEjem4.add("Imagenes/Huevo.png");
		BurguerEjem4.add("Imagenes/panSuperior.png");
		
		BurguerEjem5=new ArrayList<>();
		BurguerEjem5.add("Imagenes/baseBurguer.png" + "");
		BurguerEjem5.add("Imagenes/Lechuga.png");
		BurguerEjem5.add("Imagenes/Lomo.png");
		BurguerEjem5.add("Imagenes/queso.png");
		BurguerEjem5.add("Imagenes/panSuperior.png");
		
		BurguerEjem6=new ArrayList<>();
		BurguerEjem6.add("Imagenes/baseBurguer.png" + "");
		BurguerEjem6.add("Imagenes/Hamburguesa.png");
		BurguerEjem6.add("Imagenes/Ketchup.png");
		BurguerEjem6.add("Imagenes/baseBurguer.png" + "");
		BurguerEjem6
		.add("Imagenes/Huevo.png");
		BurguerEjem6.add("Imagenes/panSuperior.png");

		;

		try {
			imagenOriginal = ImageIO.read(new File("Imagenes/FondoPantalla.jpg"));
			vida = ImageIO.read(new File("Imagenes/vida.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		ejemplo = new Sprite(800, 900, 50, -100, "Imagenes/BurguerEjem.png");
		raton = new Sprite(ANCHO_SARTEN, ANCHO_SARTEN,-10,-10, "Imagenes/Cursor.png");

		fuenteTiempo = new Font("Arial", Font.BOLD, 20);

		tiempoInicial = System.nanoTime()+50E9;
		tiempoDeJuego = 0;
		formatoDecimal = new DecimalFormat("#.##");
		reescalarImagen();
		Sprite aux;
		for (int i = 0; i < botones.length; i++) {
			switch (i) {
			
			case 0:
				 aux = new Sprite(150, 50, panelJuego.getWidth() - 300, panelJuego.getHeight() / 2, 0, 0,
						imagenesRutas[i]);
				botones[i] = aux;
				break;
			case 1:
				aux = new Sprite(150, 50, panelJuego.getWidth() - 450, panelJuego.getHeight() / 2, 0, 0,
						imagenesRutas[i]);
				botones[i] = aux;
				break;
			case 2:
				 aux = new Sprite(150, 50, panelJuego.getWidth() - 300, panelJuego.getHeight() / 3, 0, 0,
						imagenesRutas[i]);
				botones[i] = aux;
				break;
			case 3:
				aux = new Sprite(150, 50, panelJuego.getWidth() - 450, panelJuego.getHeight() / 3, 0, 0,
						imagenesRutas[i]);
				botones[i] = aux;
				break;
			case 4:
				 aux = new Sprite(150, 50, panelJuego.getWidth() - 450, panelJuego.getHeight() / 4 - 50, 0, 0,
						imagenesRutas[i]);
				botones[i] = aux;
				break;
			case 5:
				aux = new Sprite(150, 80, panelJuego.getWidth() - 300, panelJuego.getHeight() / 2 + 100, 0, 0,
						imagenesRutas[i]);
				botones[i] = aux;
				break;
			case 6:
				aux = new Sprite(150, 80, panelJuego.getWidth() - 450, panelJuego.getHeight() / 2 + 100, 0, 0,
						imagenesRutas[i]);
				botones[i] = aux;
				break;
			case 7:
				 aux = new Sprite(150, 80, panelJuego.getWidth() - 300, panelJuego.getHeight() / 2 + 200, 0, 0,
						imagenesRutas[i]);
				botones[i] = aux;
				break;
			case 8:
				 aux = new Sprite(150, 80, panelJuego.getWidth() - 450, panelJuego.getHeight() / 2 + 200, 0, 0,
						imagenesRutas[i]);
				botones[i] = aux;
				break;
			case 9:
				 aux = new Sprite(170, 90, panelJuego.getWidth() - 300, panelJuego.getHeight() / 4 - 50, 0, 0,
						imagenesRutas[i]);
				botones[i] = aux;
				break;
				
			default:
				break;
			}
			
			
		}
	}

	@Override
	public void pintarPantalla(Graphics g) {
		rellenarFondo(g);
		// Pintamos los cuadrados:
		for (Sprite cuadrado : ingredientes) {
			cuadrado.pintarSpriteEnMundo(g);
		}
		for (Sprite cuadrado : botones) {
			cuadrado.pintarSpriteEnMundo(g);
		}
		if (ingrediente != null) {
			ingrediente.pintarSpriteEnMundo(g);
		}
		ejemplo.pintarSpriteEnMundo(g);
		raton.pintarSpriteEnMundo(g);
		pintarTiempo(g);
		pintarVidas(g);

	}
	private void pintarVidas(Graphics g) {
		switch (contadorFallos) {

		case 1:
			g.drawImage(vidaEscalado, panelJuego.getWidth() - 70, 20, null);
			break;
		case 2:
			g.drawImage(vidaEscalado, panelJuego.getWidth() - 70, 20, null);
			g.drawImage(vidaEscalado, panelJuego.getWidth() - 130, 20, null);
			break;
		case 3:
			g.drawImage(vidaEscalado, panelJuego.getWidth() - 70, 20, null);
			g.drawImage(vidaEscalado, panelJuego.getWidth() - 130, 20, null);
			g.drawImage(vidaEscalado, panelJuego.getWidth() - 190, 20, null);
			break;

		default:
			break;
		}
	}
	private void pintarTiempo(Graphics g) {

		Font f = g.getFont();
		Color c = g.getColor();

		g.setColor(COLOR_PUNTUACION);
		g.setFont(fuenteTiempo);
		actualizarTiempo();
		g.drawString(formatoDecimal.format(tiempoDeJuego / 1000000000d), 25, 25);
		
		g.setColor(c);
		g.setFont(f);
	}

	/**
	 * Método que actualiza el tiempo de juego transcurrido.
	 */
	private void actualizarTiempo() {
		tiempoDeJuego = tiempoInicial - System.nanoTime();
		System.out.println(tiempoDeJuego);
		if(tiempoDeJuego<= 2.0000000E7) {
			panelJuego.setPantallaActual(new PantallaDerrota(panelJuego));
			System.out.println("Entro");
		}
	}

	/**
	 * Método que se utiliza para rellenar el fondo del JPanel.
	 * 
	 * @param g
	 *            Es el gráficos sobre el que vamos a pintar el fondo.
	 */
	private void rellenarFondo(Graphics g) {
		// Pintar la imagen de fondo reescalada:
		g.drawImage(imagenReescalada, 0, 0, null);
	}

	/**
	 * Método para mover todos los Sprites del juego.
	 */
	private void moverSprites() {

		if (ingrediente != null) {
			ingrediente.moverSprite();
			if (ingrediente.getPosY() + ingrediente.getAlto() >= panelJuego.getHeight()) {
				ingrediente.setVelocidadY(0);
			}
		}
		for (int i = 0; i < ingredientes.size(); i++) {
			Sprite aux = ingredientes.get(i);
			if (aux != null) {
				aux.moverSprite();
				if (aux.getPosY() + aux.getAlto() >= panelJuego.getHeight()) {
					aux.setVelocidadY(0);
					aux.setPosY(aux.getPosY());
				}
			}

		}

	}

	private void comprobarHambur() {
		System.out.println(burguerJuga);
		System.out.println(BurguerEjem);
		if (burguerJuga.size() == BurguerEjem.size()) {
			correctas = new ArrayList<>();

			for (int i = 0; i < BurguerEjem.size(); i++) {
				if (BurguerEjem.get(i).equalsIgnoreCase(burguerJuga.get(i))) {
					correctas.add(i, true);
				} else {

					correctas.add(i, false);
				}

			}
			if (esCorrecto(correctas)) {

				c++;
				burguerJuga.clear();
				ingredientes.clear();
				BurguerEjem.clear();
				switch (c) {
				case 1:
					BurguerEjem.addAll(BurguerEjem2);

					ejemplo = new Sprite(400, 500, 50, 100, "Imagenes/BurguerEjem2.png");
					break;
				case 2:
					BurguerEjem.addAll(BurguerEjem5);
					ejemplo = new Sprite(650, 750, 60, 30, "Imagenes/BurguerEjem5.png");
					break;
				case 3:
					BurguerEjem.addAll(BurguerEjem3);
					ejemplo = new Sprite(400, 500, 50, 100, "Imagenes/BurguerEjem3.png");
					break;
				case 4:
					BurguerEjem.addAll(BurguerEjem6);
					ejemplo = new Sprite(650, 750, 60, 30, "Imagenes/BurguerEjem6.png");
					break;
				case 5:
					BurguerEjem.addAll(BurguerEjem4);
					ejemplo = new Sprite(650, 750, 60, 30, "Imagenes/BurguerEjem4.png");
					break;
					
				case 6:
					panelJuego.setPantallaActual(new PantallaVictoria(panelJuego));

					break;
				default:
					break;
				}

				
			}
			}else {
				
				contadorFallos--;
				
				if(contadorFallos==0) {
					panelJuego.setPantallaActual(new PantallaDerrota(panelJuego));

				}
		}

	}

	public boolean esCorrecto(ArrayList<Boolean> cor) {
		for (boolean b : cor)
			if (!b)
				return false;
		return true;

	}

	private void comprobarColisiones() {

		for (int i = 0; i < ingredientes.size() - 1; i++) {
			for (int j = i + 1; j < ingredientes.size(); j++) {
				if (ingredientes.get(i).colisionan(ingredientes.get(j))) {
					ingredientes.get(j).setVelocidadY(0);

					if (ingredientes.size() > 1) {

						// ingredientes.get(j).setPosY(panelJuego.getHeight()-(ALTO_INGREDIENTE*i));
						ingredientes.get(j).setVelocidadY(0);
						ingredientes.get(j).setPosY(ingredientes.get(j - 1).getPosY() - (ALTO_INGREDIENTE / 2));
					}

				}
			}

		}

		//

	}

	@Override
	public void ejecutarFrame() {
		comprobarColisiones();
		moverSprites();

	}

	@Override
	public void moverRaton(MouseEvent e) {
		raton.setPosX(e.getX() - raton.getAncho() / 2);
		raton.setPosY(e.getY() - raton.getAlto() / 2);

	}

	@Override
	public void pulsarRaton(MouseEvent e) {

		for (int i = 0; i < botones.length; i++) {
			if (raton.colisionan(botones[i])) {
				if (imagenesRutas[i].equalsIgnoreCase("Imagenes/Aceptar.png")) {
					comprobarHambur();

				} else {
					ingrediente = new Sprite(ANCHO_INGREDIENTE, ALTO_INGREDIENTE,
							panelJuego.getWidth() / 2 - ANCHO_INGREDIENTE / 2, -panelJuego.getHeight(), 0,
							VELOCIDAD_INGREDIENTE, imagenesRutas[i]);
					ingredientes.add(ingrediente);
					burguerJuga.add(imagenesRutas[i]);
					contadorIngre++;
					flag = true;
				}

			}
		}
		for (int i = 0; i < ingredientes.size(); i++) {

			if (ingredientes.get(i).colisionan(raton)) {
			
				ingredientes.removeAll(ingredientes);
				ingredientes=new ArrayList<>();
				burguerJuga.clear();
				

			}
		}

	}

	@Override
	public void redimensionarPantalla(ComponentEvent e) {
		reescalarImagen();

	}

	private void reescalarImagen() {
		// Pensar en cada caso particular
		imagenReescalada = imagenOriginal.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(),
				Image.SCALE_SMOOTH);
		vidaEscalado = vida.getScaledInstance(50, 50, Image.SCALE_SMOOTH);

	}
}

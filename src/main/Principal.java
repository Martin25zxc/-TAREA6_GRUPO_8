package main;
import negocio.PersonaNegocio;
import negocioImpl.PersonaNegocioImpl;
import presentacion.controller.PersonasController;
import presentacion.view.VentanaPrincipal;

public class Principal {

	public static void main(String[] args) {
		VentanaPrincipal vista = new VentanaPrincipal();
		PersonaNegocio negocio = new PersonaNegocioImpl();
		PersonasController controlador = new PersonasController(vista, negocio);
		controlador.inicializar();

	}

}

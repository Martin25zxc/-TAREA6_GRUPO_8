package presentacion.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.KeyEvent;

import entidades.Persona;
import negocio.PersonaNegocio;
import negocioImpl.PersonaException;
import presentacion.view.PanelPersonaAgregar;
import presentacion.view.PanelPersonaEliminar;
import presentacion.view.PanelPersonaListar;
import presentacion.view.PanelPersonaModificar;
import presentacion.view.VentanaPrincipal;

public class PersonasController {

	private VentanaPrincipal ventanaPrincipal;
	private PanelPersonaAgregar pnlPersonasAgregar;
	private PanelPersonaEliminar pnlPersonasEliminar;
	private PanelPersonaModificar pnlPersonasModificar;
	private PanelPersonaListar pnlPersonasListar;
	private PersonaNegocio negocioPersona;
	
	public PersonasController(VentanaPrincipal ventanaPrincipal, PersonaNegocio negocioPersona)
	{
		//Guardo todas las instancias que recibo en el constructor
		this.ventanaPrincipal = ventanaPrincipal;
		this.negocioPersona = negocioPersona;
		
		//Instancio los paneles
		this.pnlPersonasAgregar = new PanelPersonaAgregar();
		this.pnlPersonasEliminar = new PanelPersonaEliminar();
		this.pnlPersonasModificar = new PanelPersonaModificar();
		this.pnlPersonasListar = new PanelPersonaListar();
		
		//Enlazo todos los eventos
		
		//Eventos menu del Frame principal llamado Ventana
		this.ventanaPrincipal.getMenuAgregar().addActionListener(a->EventoClickMenu_AbrirPanel(a, pnlPersonasAgregar));
		this.ventanaPrincipal.getMenuEliminar().addActionListener(a->EventoClickMenu_AbrirPanel(a, pnlPersonasEliminar));
		this.ventanaPrincipal.getMenuModificar().addActionListener(a->EventoClickMenu_AbrirPanel(a, pnlPersonasModificar));
		this.ventanaPrincipal.getMenuListar().addActionListener(a->EventoClickMenu_AbrirPanel(a, pnlPersonasListar));
		
		PersonasAgregarInicializar();
		PersonasListarInicializar();
		PersonasModificarInicializar();
		PersonasEliminarInicializar();
	}
	
	public void inicializar()
	{
		this.ventanaPrincipal.setVisible(true);;
	}
	
	private void PersonasAgregarInicializar()
	{
		 JTextField txtNombre = this.pnlPersonasAgregar.getTxtNombre();
	     JTextField textApellido = this.pnlPersonasAgregar.getTxtApellido();
	      
	     txtNombre.addKeyListener(new KeyAdapterCaracteres(txtNombre));
	     textApellido.addKeyListener(new KeyAdapterCaracteres(textApellido));
	      
		 this.pnlPersonasAgregar.getBtnAceptar().addActionListener(a->Agregar());
		 this.pnlPersonasAgregar.getTxtDni().addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent ke) {
					JTextField text = pnlPersonasAgregar.getTxtDni();
					boolean isEditable = ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE);
		 			text.setEditable(isEditable);
		         }
		      });
	}
	
	private void PersonasEliminarInicializar()
	{
		 //List<Persona> lista = negocioPersona.get();

		 //JList<Persona> jListaPersonas = new JList<Persona>();
		 //for(Persona persona : lista)  lista.add(persona);
		 
		// this.pnlPersonasEliminar.setListaPersonas(jListaPersonas);
	}
	
	private void PersonasModificarInicializar()
	{
		 
	}
	
	private void PersonasListarInicializar()
	{
		 
	}
	
	//EventoClickMenu abrir Panel
	public void  EventoClickMenu_AbrirPanel(ActionEvent a, JPanel panel)
	{		
		ventanaPrincipal.getContentPane().removeAll();
		ventanaPrincipal.getContentPane().add(panel);
		ventanaPrincipal.getContentPane().repaint();
		ventanaPrincipal.getContentPane().revalidate();
	}
	
	private void Agregar() {
		try
		{
			String nombre = pnlPersonasAgregar.getTxtNombre().getText();
			String apellido = pnlPersonasAgregar.getTxtApellido().getText();
			String dni = pnlPersonasAgregar.getTxtDni().getText();
			
			Persona personaAAgregar = new Persona(dni, nombre, apellido);
			negocioPersona.post(personaAAgregar);
			JLabel label = new JLabel("El registro se agrego correctamente.");
			JOptionPane.showMessageDialog(null,label);
		}
		catch(PersonaException ex)
		{
			JLabel label = new JLabel(ex.getMessage());
			JOptionPane.showMessageDialog(null,label,"ERROR",JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			JLabel label = new JLabel(ex.toString());
			JOptionPane.showMessageDialog(null,label,"ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private class KeyAdapterCaracteres extends KeyAdapter{
		private JTextField txtAValidar;
		
		public KeyAdapterCaracteres(JTextField txtAValidar)
		{
			this.txtAValidar = txtAValidar;
		}
		
		@Override
		public void keyPressed(KeyEvent ke) {
			JTextField text = txtAValidar;
			char keyChar = ke.getKeyChar();
			int ascii = (int) keyChar;
			
			boolean isEditable =  (( ascii >= 97 && ascii <= 122) || ( ascii >= 65 && ascii <= 90) || keyChar == KeyEvent.VK_BACK_SPACE);
 			text.setEditable(isEditable);
         }
	}
}


package presentacion.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

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
	private List<Persona> personasEnTabla;
	
	public PersonasController(VentanaPrincipal ventanaPrincipal, PersonaNegocio negocioPersona)
	{
		//Guardo todas las instancias que recibo en el constructor
		this.ventanaPrincipal = ventanaPrincipal;
		this.negocioPersona = negocioPersona;
		
		this.pnlPersonasAgregar = new PanelPersonaAgregar();
		this.pnlPersonasEliminar = new PanelPersonaEliminar();
		this.pnlPersonasModificar = new PanelPersonaModificar();
		this.pnlPersonasListar = new PanelPersonaListar();
		
		//Enlazo todos los eventos
		//Eventos menu del Frame principal llamado Ventana
		this.ventanaPrincipal.getMenuAgregar().addActionListener(a->eventoClickMenu_AbrirPanel(a, pnlPersonasAgregar));
		this.ventanaPrincipal.getMenuEliminar().addActionListener(a->{
				refrescarTabla(); 
				eventoClickMenu_AbrirPanel(a, pnlPersonasEliminar);}
		);
		this.ventanaPrincipal.getMenuModificar().addActionListener(a->{
			refrescarTabla(); 
			eventoClickMenu_AbrirPanel(a, pnlPersonasModificar);
		});
		this.ventanaPrincipal.getMenuListar().addActionListener(a->{
			refrescarTabla(); 
			eventoClickMenu_AbrirPanel(a, pnlPersonasListar);
		});
		
		personasAgregarInicializar();
		personasModificarInicializar();
		personasEliminarInicializar();
	}
	
	public void inicializar()
	{
		this.ventanaPrincipal.setVisible(true);;
	}
	
	private void refrescarTabla()
	{
		this.personasEnTabla = (ArrayList<Persona>) negocioPersona.get();
		this.pnlPersonasEliminar.fillModel(this.personasEnTabla);
		this.pnlPersonasListar.fillModel(this.personasEnTabla);
		this.pnlPersonasModificar.fillModel(this.personasEnTabla);
	}
	
	private void personasAgregarInicializar()
	{
		 JTextField txtNombre = this.pnlPersonasAgregar.getTxtNombre();
	     JTextField textApellido = this.pnlPersonasAgregar.getTxtApellido();
	      
	     txtNombre.addKeyListener(new KeyAdapterCaracteres(txtNombre));
	     textApellido.addKeyListener(new KeyAdapterCaracteres(textApellido));
	      
		 this.pnlPersonasAgregar.getBtnAceptar().addActionListener(a->agregar());
		 this.pnlPersonasAgregar.getTxtDni().addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent ke) {
					JTextField text = pnlPersonasAgregar.getTxtDni();
					boolean isEditable = ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE);
		 			text.setEditable(isEditable);
		         }
		      });
	}
	
	private void personasEliminarInicializar()
	{
		this.pnlPersonasEliminar.getBtnEliminar().addActionListener(a->eliminar());
	}
	
	private void personasModificarInicializar()
	{
		this.pnlPersonasModificar.getListaPersonas().addListSelectionListener(a -> seleccionarModificar());
		this.pnlPersonasModificar.getBtnModificar().addActionListener(a -> modificar());
	}
	
	//EventoClickMenu abrir Panel
	public void  eventoClickMenu_AbrirPanel(ActionEvent a, JPanel panel)
	{		
		ventanaPrincipal.getContentPane().removeAll();
		ventanaPrincipal.getContentPane().add(panel);
		ventanaPrincipal.getContentPane().repaint();
		ventanaPrincipal.getContentPane().revalidate();
	}
	
	private void eliminar() {
		try
		{
			int filaSeleccionada = this.pnlPersonasEliminar.getListaPersonas().getSelectedIndex();
			if(filaSeleccionada != -1)
			{
				negocioPersona.delete(this.personasEnTabla.get(filaSeleccionada).dni);
				this.refrescarTabla();
				mostrarMensaje("El registro se elimino correctamente.");
			}
			else
			{
				mostrarMensajeError("No se encuentra seleccionado ningun registro.");
			}
		}
		catch(PersonaException ex)
		{
			mostrarMensajeError(ex.getMessage());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			mostrarMensajeError(ex.toString());
		}
	}
	
	private void seleccionarModificar() {
		try
		{
			int filaSeleccionada = this.pnlPersonasModificar.getListaPersonas().getSelectedIndex();
			if(filaSeleccionada != -1)
			{
				Persona p = this.personasEnTabla.get(filaSeleccionada);
				pnlPersonasModificar.getTxtNombre().setText(p.getNombre());
				pnlPersonasModificar.getTxtApellido().setText(p.getApellido());
				pnlPersonasModificar.getTxtDni().setText(p.getDni());
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			mostrarMensajeError(ex.toString());
		}
	}
	
	private void modificar() {
		try
		{
			String nombre = pnlPersonasModificar.getTxtNombre().getText().trim();
			String apellido = pnlPersonasModificar.getTxtApellido().getText().trim();
			String dni = pnlPersonasModificar.getTxtDni().getText().trim();
			
			Persona personaAAgregar = new Persona(dni, nombre, apellido);
			negocioPersona.update(personaAAgregar);
			
			pnlPersonasModificar.getTxtNombre().setText("");
			pnlPersonasModificar.getTxtApellido().setText("");
			pnlPersonasModificar.getTxtDni().setText("");
			mostrarMensaje("El registro se modifico correctamente.");
			this.pnlPersonasModificar.getListaPersonas().clearSelection();
			this.refrescarTabla();
		}
		catch(PersonaException ex)
		{
			mostrarMensajeError(ex.getMessage());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			mostrarMensajeError(ex.toString());
		}
	}
	
	private void agregar() {
		try
		{
			String nombre = pnlPersonasAgregar.getTxtNombre().getText().trim();
			String apellido = pnlPersonasAgregar.getTxtApellido().getText().trim();
			String dni = pnlPersonasAgregar.getTxtDni().getText().trim();
			
			Persona personaAAgregar = new Persona(dni, nombre, apellido);
			negocioPersona.post(personaAAgregar);
			
			pnlPersonasAgregar.getTxtNombre().setText("");
			pnlPersonasAgregar.getTxtApellido().setText("");
			pnlPersonasAgregar.getTxtDni().setText("");
			mostrarMensaje("El registro se agrego correctamente.");
			
		}
		catch(PersonaException ex)
		{
			mostrarMensajeError(ex.getMessage());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			mostrarMensajeError(ex.toString());
		}
	}
	
	private void mostrarMensaje(String mensaje)
	{
		JLabel label = new JLabel(mensaje);
		JOptionPane.showMessageDialog(null,label);
	}
	
	private void mostrarMensajeError(String mensaje)
	{
		JLabel label = new JLabel(mensaje);
		JOptionPane.showMessageDialog(null,label,"ERROR",JOptionPane.ERROR_MESSAGE);
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


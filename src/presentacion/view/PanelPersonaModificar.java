package presentacion.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entidades.Persona;
import javax.swing.BoxLayout;
import javax.swing.JButton;

public class PanelPersonaModificar extends JPanel {

	private static final long serialVersionUID = 7729882027159064854L;
	private JButton btnModificar;
	private JTextField txtDni;
	private JTextField txtApellido;
	private JTextField txtNombre;
	
	private JList<Persona> listaPersonas;
	protected DefaultListModel<Persona> listModel;
	
	public PanelPersonaModificar() 
	{
		super();
		dibujarControles();;
	}
	
	public void dibujarControles() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30, 0, 0, 17, 0, 0, 0, 0, 30, 0};
		gridBagLayout.rowHeights = new int[]{30, 0, 0, 0, 0, 30, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblModificarUsuarios = new JLabel("Seleccione la persona que desea modificar");
		GridBagConstraints gbc_lblModificarUsuarios = new GridBagConstraints();
		gbc_lblModificarUsuarios.anchor = GridBagConstraints.WEST;
		gbc_lblModificarUsuarios.insets = new Insets(0, 0, 5, 5);
		gbc_lblModificarUsuarios.gridx = 4;
		gbc_lblModificarUsuarios.gridy = 1;
		add(lblModificarUsuarios, gbc_lblModificarUsuarios);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea = new GridBagConstraints();
		gbc_rigidArea.insets = new Insets(0, 0, 5, 5);
		gbc_rigidArea.gridx = 2;
		gbc_rigidArea.gridy = 2;
		add(rigidArea, gbc_rigidArea);
		
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.gridx = 4;
		gbc_list.gridy = 2;
		
		listaPersonas = new JList<Persona>();
		listModel = new DefaultListModel<Persona>();
		listaPersonas.setModel(listModel);
		listaPersonas.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		add(listaPersonas, gbc_list);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_1 = new GridBagConstraints();
		gbc_rigidArea_1.insets = new Insets(0, 0, 5, 5);
		gbc_rigidArea_1.gridx = 6;
		gbc_rigidArea_1.gridy = 2;
		add(rigidArea_1, gbc_rigidArea_1);
		
		JPanel pnlForm = new JPanel();
		GridBagConstraints gbc_pnlForm = new GridBagConstraints();
		gbc_pnlForm.gridheight = 2;
		gbc_pnlForm.insets = new Insets(0, 0, 5, 5);
		gbc_pnlForm.fill = GridBagConstraints.BOTH;
		gbc_pnlForm.gridx = 4;
		gbc_pnlForm.gridy = 3;
		add(pnlForm, gbc_pnlForm);
		pnlForm.setLayout(new BoxLayout(pnlForm, BoxLayout.X_AXIS));
		
		txtNombre = new JTextField();
		pnlForm.add(txtNombre);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		pnlForm.add(rigidArea_2);
		
		txtApellido = new JTextField();
		pnlForm.add(txtApellido);
		
		Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		pnlForm.add(rigidArea_3);
		
		txtDni = new JTextField();
		pnlForm.add(txtDni);
		
		Component rigidArea_4 = Box.createRigidArea(new Dimension(20, 20));
		pnlForm.add(rigidArea_4);
		
		btnModificar = new JButton("Modificar");
		pnlForm.add(btnModificar);
		
	}

	public JList<Persona> getListaPersonas() {
		return listaPersonas;
	}

	public void setListaPersonas(JList<Persona> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}
	
	public void fillModel(List<Persona> personas) {
		listModel.clear();
		personas.stream().forEach(p -> {
			listModel.addElement(p);
		});
	}

	public JButton getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(JButton btnModificar) {
		this.btnModificar = btnModificar;
	}

	public JTextField getTxtDni() {
		return txtDni;
	}

	public void setTxtDni(JTextField txtDni) {
		this.txtDni = txtDni;
	}

	public JTextField getTxtApellido() {
		return txtApellido;
	}

	public void setTxtApellido(JTextField txtApellido) {
		this.txtApellido = txtApellido;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}	
}

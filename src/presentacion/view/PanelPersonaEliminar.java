package presentacion.view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import entidades.Persona;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;

import java.awt.Dimension;
import javax.swing.JLabel;

public class PanelPersonaEliminar extends JPanel{

	private static final long serialVersionUID = -4124896243667700654L;
	
	private JList<Persona> listaPersonas;
	private JButton btnEliminar;
	protected DefaultListModel<Persona> listModel;
	
	public PanelPersonaEliminar() 
	{
		super();
		dibujarControles();
	}
	
	public void dibujarControles() {
		Font fuenteComun = new Font("Segoe UI", Font.PLAIN, 18);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30, 0, 0, 17, 0, 0, 0, 0, 30, 0};
		gridBagLayout.rowHeights = new int[]{30, 0, 0, 0, 30, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblEliminarUsuarios = new JLabel("Eliminar usuarios");
		GridBagConstraints gbc_lblEliminarUsuarios = new GridBagConstraints();
		gbc_lblEliminarUsuarios.insets = new Insets(0, 0, 5, 5);
		gbc_lblEliminarUsuarios.gridx = 4;
		gbc_lblEliminarUsuarios.gridy = 1;
		add(lblEliminarUsuarios, gbc_lblEliminarUsuarios);
		
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
		
		btnEliminar = new JButton("Eliminar");
		
		btnEliminar.setFont(fuenteComun);
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.insets = new Insets(0, 0, 5, 5);
		gbc_btnEliminar.gridx = 4;
		gbc_btnEliminar.gridy = 3;
		add(btnEliminar, gbc_btnEliminar);
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(JButton btnAceptar) {
		this.btnEliminar = btnAceptar;
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
}

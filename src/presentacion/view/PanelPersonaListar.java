package presentacion.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entidades.Persona;

public class PanelPersonaListar extends JPanel {

	private static final long serialVersionUID = 7365682374521075898L;
	private DefaultTableModel modelPersonas;
	private String[] nombreColumnas = {"Nombre", "Apellido","DNI"};
	
	private JTable tablaPersonas;
	
	public PanelPersonaListar() 
	{
		super();
		dibujarControles();
	}
	
	public void dibujarControles() {
		tablaPersonas = new JTable();
		modelPersonas = new DefaultTableModel(null,nombreColumnas);
		tablaPersonas.setModel(modelPersonas);
	
		for(int i = 0; i < nombreColumnas.length; i++) {
			tablaPersonas.getColumnModel().getColumn(i).setPreferredWidth(103);
			tablaPersonas.getColumnModel().getColumn(i).setResizable(false);
		}
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30, 0, 0, 17, 0, 0, 0, 0, 30, 0};
		gridBagLayout.rowHeights = new int[]{30, 0, 0, 0, 30, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.insets = new Insets(0, 0, 5, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 4;
		gbc_table.gridy = 2;
		add(new JScrollPane(tablaPersonas), gbc_table);
	}
	
	
	public void fillModel(List<Persona> personasEnTabla) {
		this.modelPersonas.setRowCount(0); 
		this.modelPersonas.setColumnCount(0);
		this.modelPersonas.setColumnIdentifiers(this.nombreColumnas);
		
		for (Persona p : personasEnTabla)
		{
			String nombre = p.getNombre();
			String dni = p.getDni();
			String apellido = p.getApellido();
			Object[] fila = {nombre, apellido, dni};
			modelPersonas.addRow(fila);
		}
		
	}

}

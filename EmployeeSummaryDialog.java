/*
 * 
 * This is the summary dialog for displaying all Employee details
 * 
 * */

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

public class EmployeeSummaryDialog extends JDialog implements ActionListener {
	// vector with all Employees details
	private Vector<Vector<Object>> allEmployees;
	private JButton back;
	private JTable employeeTable;
	private DefaultTableModel tableModel;
	
	private static final String[] HEADERS_NAMES = {"ID", "PPS Number", "Surname", "First Name", "Gender", "Department", "Salary", "Full Time" };
	private static final int[] COLUMNS_WIDTHS = {15, 100, 120, 120, 50, 120, 80, 80 };
	
	public EmployeeSummaryDialog(Vector<Vector<Object>> allEmployees) {
		setTitle("Employee Summary");
		setModal(true);
		this.allEmployees = allEmployees;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JScrollPane scrollPane = new JScrollPane(summaryPane());
		setContentPane(scrollPane);

		setSize(850, 500);
		setLocation(350, 250);
		setVisible(true);

	}
	// initialise container
	public Container summaryPane() {
		JPanel summaryDialog = new JPanel(new MigLayout());
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		// column center alignment
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		// column left alignment 
		DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
		Vector<String> header = new Vector<String>();
		
		
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		leftRenderer.setHorizontalAlignment(JLabel.LEFT);
		// add headers
		for (int i = 0; i < HEADERS_NAMES.length; i++) {
			header.addElement(HEADERS_NAMES[i]);
		}// end for
		// construnct table and choose table model for each column
		tableModel = new DefaultTableModel(this.allEmployees, header) {
			public Class getColumnClass(int c) {
				switch (c) {
				case 0:
					return Integer.class;
				case 4:
					return Character.class;
				case 6:
					return Double.class;
				case 7:
					return Boolean.class;
				default:
					return String.class;
				}// end switch
			}// end getColumnClass
		};

		employeeTable = new JTable(tableModel);
		// add header names to table
		for (int i = 0; i < employeeTable.getColumnCount(); i++) {
			employeeTable.getColumn(HEADERS_NAMES[i]).setMinWidth(COLUMNS_WIDTHS[i]);
		}// end for
		// set alignments
		employeeTable.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
		employeeTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		employeeTable.getColumnModel().getColumn(6).setCellRenderer(new DecimalFormatRenderer());

		employeeTable.setEnabled(false);
		employeeTable.setPreferredScrollableViewportSize(new Dimension(800, (15 * employeeTable.getRowCount() + 15)));
		employeeTable.setAutoCreateRowSorter(true);
		JScrollPane scrollPane = new JScrollPane(employeeTable);

		buttonPanel.add(back = new JButton("Back"));
		back.addActionListener(this);
		back.setToolTipText("Return to main screen");
		
		summaryDialog.add(buttonPanel,"growx, pushx, wrap");
		summaryDialog.add(scrollPane,"growx, pushx, wrap");
		scrollPane.setBorder(BorderFactory.createTitledBorder("Employee Details"));
		
		return summaryDialog;
	}// end summaryPane

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back){
			dispose();
		}

	}
	// format for salary column
	static class DecimalFormatRenderer extends DefaultTableCellRenderer {
		 private static final DecimalFormat format = new DecimalFormat(
		 "\u20ac ###,###,##0.00" );

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {

			Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			 JLabel label = (JLabel) c;
			 label.setHorizontalAlignment(JLabel.RIGHT);
			 // format salary column
			value = format.format((Number) value);

			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		}// end getTableCellRendererComponent
	}// DefaultTableCellRenderer
}// end class EmployeeSummaryDialog

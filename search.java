import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;


public abstract class search extends JDialog implements ActionListener{
	
	public EmployeeDetails parent;
	public JButton search;
	public JButton cancel;
	public JTextField searchField;
	
	public search(EmployeeDetails parent, String title, String label)
	{
		setTitle(title);
		this.parent = parent;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JScrollPane scrollPane = new JScrollPane(searchPane());
		setContentPane(scrollPane);

		getRootPane().setDefaultButton(search);
		
		setSize(500, 190);
		setLocation(350, 250);
		setVisible(true);
	}
	
	public Container searchPane()
	{
		JPanel searchPanel = new JPanel(new GridLayout(3,1));
		JPanel textPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JLabel searchLabel;

		searchPanel.add(new JLabel("Search by Surname"));
	
		textPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		textPanel.add(searchLabel = new JLabel("Enter Surname:"));
		searchLabel.setFont(this.parent.font1);
		textPanel.add(searchField = new JTextField(20));
		searchField.setFont(this.parent.font1);
		searchField.setDocument(new JTextFieldLimit(20));

		buttonPanel.add(search = new JButton("Search"));
		search.addActionListener(this);
		search.requestFocus();
		
		buttonPanel.add(cancel = new JButton("Cancel"));
		cancel.addActionListener(this);
		
		searchPanel.add(textPanel);
		searchPanel.add(buttonPanel);
		
		return searchPane();
		
	}
	
	public abstract String dialogTitle();
	
	public abstract void searchFun();
	
	public void actionPreformed(ActionEvent e)
	{
		if(e.getSource() == search)
		{
			searchFun();
		}
		if(e.getSource() == cancel)
		{
			dispose();
		}
	}

	
	

}

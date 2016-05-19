package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;

import domen.Poslanik;
import gui.models.PoslanikTableModel;

import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;

public class PoslanikGUI extends JFrame {

	private JPanel contentPane;
	private JPanel jpnlButton;
	private JPanel jpnlStatus;
	private JButton btnGetMembers;
	private JButton btnFillTable;
	private JButton btnUpdateMembers;
	private JScrollPane scrollPane;
	private JTable table;
	private JScrollPane scpnStatus;
	private JTextArea txtStatus;

	

	/**
	 * Create the frame.
	 */
	public PoslanikGUI() {
		setTitle("Parlament members");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getJpnlButton(), BorderLayout.EAST);
		contentPane.add(getJpnlStatus(), BorderLayout.SOUTH);
		contentPane.add(getScrollPane(), BorderLayout.CENTER);
	}

	private JPanel getJpnlButton() {
		if (jpnlButton == null) {
			jpnlButton = new JPanel();
			jpnlButton.setPreferredSize(new Dimension(160, 10));
			jpnlButton.setLayout(null);
			jpnlButton.add(getBtnGetMembers());
			jpnlButton.add(getBtnFillTable());
			jpnlButton.add(getBtnUpdateMembers());
		}
		return jpnlButton;
	}
	private JPanel getJpnlStatus() {
		if (jpnlStatus == null) {
			jpnlStatus = new JPanel();
			jpnlStatus.setBorder(new TitledBorder(null, "STATUS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			jpnlStatus.setPreferredSize(new Dimension(10, 80));
			jpnlStatus.setLayout(new BorderLayout(0, 0));
			jpnlStatus.add(getScpnStatus(), BorderLayout.CENTER);
		}
		return jpnlStatus;
	}
	private JButton getBtnGetMembers() {
		if (btnGetMembers == null) {
			btnGetMembers = new JButton("GET members");
			btnGetMembers.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUIKontroler.serializujGETRequest();
					txtStatus.append("Poslanici uspesno preuzeti sa servisa.\n");
				}
			});
			btnGetMembers.setBounds(10, 11, 140, 23);
		}
		return btnGetMembers;
	}
	private JButton getBtnFillTable() {
		if (btnFillTable == null) {
			btnFillTable = new JButton("Fill table");
			btnFillTable.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					List<Poslanik> poslanici = GUIKontroler.vratiListuPoslanika(); 
					PoslanikTableModel tbm = (PoslanikTableModel) table.getModel();
					tbm.osveziTabelu(poslanici);
					txtStatus.append("Tabela popunjena podacima.\n");
				}
			});
			btnFillTable.setBounds(10, 45, 140, 23);
		}
		return btnFillTable;
	}
	private JButton getBtnUpdateMembers() {
		if (btnUpdateMembers == null) {
			btnUpdateMembers = new JButton("Update members");
			btnUpdateMembers.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					PoslanikTableModel tbm = (PoslanikTableModel) table.getModel();
					List<Poslanik> poslanici = tbm.vratiListu();
					GUIKontroler.serijalizujPromenu(poslanici);
					txtStatus.append("Izmenjeni podaci su sacuvani.\n");
				}
			});
			btnUpdateMembers.setBounds(10, 79, 140, 23);
		}
		return btnUpdateMembers;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			PoslanikTableModel tbm = new PoslanikTableModel();
			table.setModel(tbm);
		}
		return table;
	}
	private JScrollPane getScpnStatus() {
		if (scpnStatus == null) {
			scpnStatus = new JScrollPane();
			scpnStatus.setViewportView(getTxtStatus());
		}
		return scpnStatus;
	}
	private JTextArea getTxtStatus() {
		if (txtStatus == null) {
			txtStatus = new JTextArea();
			txtStatus.setEditable(false);
		}
		return txtStatus;
	}
}

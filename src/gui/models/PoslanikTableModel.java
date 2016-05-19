package gui.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import domen.Poslanik;

public class PoslanikTableModel extends AbstractTableModel{


	private static final long serialVersionUID = 1L;
	private List<Poslanik> poslanici;
	private static final String[] kolone = {"ID", "Name", "Last name", "Birth date"};
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
	
	public PoslanikTableModel() {
		poslanici = new LinkedList<>();
	}
	
	public PoslanikTableModel(List<Poslanik> poslanici) {
		if (poslanici == null) {
			this.poslanici = new LinkedList<>();
		}
		this.poslanici = poslanici;
	}
	
	@Override
	public int getRowCount() {
		return poslanici.size();
	}

	@Override
	public int getColumnCount() {
		return kolone.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return kolone[column];
	}

	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Poslanik p = poslanici.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return p.getId();
		case 1:
			return p.getIme();
		case 2:
			return p.getPrezime();
		case 3:
			if (p.getDatumRodjenja() != null) {
				return sdf.format(p.getDatumRodjenja());
			}
			return "";
//			return p.getDatumRodjenja(); zasto vraca los format
		default:
			return "Error";
		}
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return false;
		}
		return true;
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Poslanik p = poslanici.get(rowIndex);
		switch (columnIndex) {
		case 1:
			if (aValue.toString() == null || aValue.toString().equals("")) {
				JOptionPane.showMessageDialog(null, "Polje sa imenom mora biti popunjeno.\nPolje neizmenjeno.", "Greska", JOptionPane.ERROR_MESSAGE);
				break;
			}
			p.setIme(aValue.toString());
			break;
		case 2:
			if (aValue.toString() == null || aValue.toString().equals("")) {
				JOptionPane.showMessageDialog(null, "Polje sa prezimenom mora biti popunjeno.\nPolje neizmenjeno.", "Greska", JOptionPane.ERROR_MESSAGE);
				break;
			}
			p.setPrezime(aValue.toString());
			break;
		case 3:
			Date d;
			try {
				d = sdf.parse(aValue.toString());
				p.setDatumRodjenja(d);
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Datum mora biti u odgovarajucem formatu.\nPolje neizmenjeno.", "Greska", JOptionPane.ERROR_MESSAGE);
				break;
			}			
			break;
		}
	}
	
	public void osveziTabelu(List<Poslanik> lista){
		this.poslanici = lista;
		fireTableDataChanged();
	}

	public List<Poslanik> vratiListu(){
		return this.poslanici;
	}
	
}

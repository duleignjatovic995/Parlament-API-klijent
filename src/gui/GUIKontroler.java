package gui;

import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;


import domen.Poslanik;
import util.ParlamentAPIKomunikacija;


public class GUIKontroler {

	private static PoslanikGUI glavniProzor;
	private static ParlamentAPIKomunikacija api;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					api = new ParlamentAPIKomunikacija();
					glavniProzor = new PoslanikGUI();
					glavniProzor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void serializujGETRequest(){
		try {
			api.serijalizujGET();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<Poslanik> vratiListuPoslanika(){
		List<Poslanik> p = null;
		try {
			p = api.vratiPoslanike();
		} catch (FileNotFoundException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
	
	public static void serijalizujPromenu(List<Poslanik> poslanici){
		try {
			api.serijalizujListu(poslanici);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
}

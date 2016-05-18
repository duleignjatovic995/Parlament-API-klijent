package gui;

import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import domen.Poslanik;
import util.ParlamentAPIKomunikacija;


public class GUIKontroler {

	private static PoslanikGUI glavniProzor;
	
	public static void main(String[] args) {
		ParlamentAPIKomunikacija kurac = new ParlamentAPIKomunikacija();
		try {
			List<Poslanik> govna = kurac.vratiPoslanike();
			for (int i = 0; i < govna.size(); i++) {
				System.out.println(govna.get(i));
			}
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

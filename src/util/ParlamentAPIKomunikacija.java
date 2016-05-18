package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import domen.Poslanik;

public class ParlamentAPIKomunikacija {

	public static final String url = "http://147.91.128.71:9090/parlament/api/members";
	public static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
	
	public List<Poslanik> vratiPoslanike() throws IOException, ParseException{
		String rezultat = sendGET(url);
		Gson gson = new GsonBuilder().create();
		JsonArray poslanici = gson.fromJson(rezultat, Poslanik.class);
		List<Poslanik> listaPoslanika = new LinkedList<>();
		for (int i = 0; i < poslanici.size(); i++) {
			JsonObject objekat = (JsonObject) poslanici.get(i);
			Poslanik p = new Poslanik();
			p.setId(objekat.get("id").getAsInt());
			p.setIme(objekat.get("name").getAsString());
			p.setPrezime(objekat.get("lastName").getAsString());
			if (objekat.get("birthDate") != null) {
				p.setDatumRodjenja(sdf.parse(objekat.get("birthDate").getAsString()));
			}
			listaPoslanika.add(p);
		}
		return listaPoslanika;
	}
	
	private String sendGET(String url) throws IOException{
		URL obj = new URL(url);
		HttpURLConnection konekcija = (HttpURLConnection) obj.openConnection();
		konekcija.setRequestMethod("GET");
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(konekcija.getInputStream()));
		boolean endOfStream = false;
		String rezultat = "";
		
		while (!endOfStream) {
			String s = reader.readLine();
			if (s != null) {
				rezultat += s;
			} else {
				endOfStream = true;
			}
		}
		reader.close();
		return rezultat;
	}
}

package com.fdmgroup.demojdbc.presentation;

import java.util.Collections;
import java.util.List;

import com.fdmgroup.demojdbc.data.NbaPlayersJdbcDAO;
import com.fdmgroup.demojdbc.model.NbaPlayer;

public class Client {

	public static void main(String[] args) {
		NbaPlayersJdbcDAO dao = new NbaPlayersJdbcDAO();
		List<NbaPlayer> players = dao.read();
		
		System.out.println(header());
		
		for (NbaPlayer temp: players) {
			System.out.println(display(temp));
		}
		
		
//		System.out.println("||||||||||\n "
//				+ "curl neal is ");
//		NbaPlayer curlneal=dao.read(1005);
//		System.out.println(header());
//		System.out.println(display(curlneal));
		
		
		
//		boolean iscurlynealdeleted = dao.delete(1005);
//		
//		if (iscurlynealdeleted) {
//			System.out.println("||||||||||\n "
//					+ "delete curly neal ");
//			players = dao.read();
//			System.out.println(header());
//			
//			for (NbaPlayer temp: players) {
//				System.out.println(display(temp));
//			}
//		}
		
		
		/*
		 * NbaPlayer waiyathamdani = new NbaPlayer();
		 * waiyathamdani.setFirstName("Waiyat"); waiyathamdani.setLastName("Hamdani");
		 * waiyathamdani.setCareerPoints(99999); dao.create(waiyathamdani);
		 * System.out.println("Waiyat hamdani is added to db ....");
		 * 
		 * players = dao.read(); System.out.println(header());
		 * 
		 * for (NbaPlayer temp: players) { System.out.println(display(temp)); }
		 */
		
		
		NbaPlayer persistedWaiyat = dao.read(1007);
		persistedWaiyat.setFirstName("waiyat");
		persistedWaiyat.setLastName("hamdani");
		persistedWaiyat.setCareerPoints(90000);
		dao.update(persistedWaiyat);
		
		players = dao.read();
		
		System.out.println(header());
		
		for (NbaPlayer temp: players) {
			System.out.println(display(temp));
		}
		
}
	
	private static String header() {
		
		int lineLength = 45;
		StringBuilder underLine = new StringBuilder();
		for (int i=0; i<lineLength; i++) {
			underLine.append("-");
		}
		
		return String.format("%-8s%-14s%-13s%-10s%n%45s", "ID", "FIRST_NAME", "LAST_NAME", "CAREER_PTS", underLine.toString());
	}
	
	private static String display(NbaPlayer player) {
		
		return String.format("%-8d%-14s%-13s%,10d", player.getId(), player.getFirstName(), player.getLastName(), player.getCareerPoints());
	}
}

package com.fdmgroup.demojdbc.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fdmgroup.demojdbc.model.NbaPlayer;
import com.fdmgroup.jdbcdemo.service.PropertiesManager;

public class NbaPlayersJdbcDAO implements Crudable<NbaPlayer, Integer>{
	private static final PropertiesManager DB_PROPERTIES;
	private static final String URL;
	private static final String USERNAME;
	private static final String PASSWORD;
	private static final String READALL;
	static {
		DB_PROPERTIES = new PropertiesManager("config/database.properties");
		URL = DB_PROPERTIES.get("url");
		USERNAME = DB_PROPERTIES.get("uname");
		PASSWORD = DB_PROPERTIES.get("pwd");
		READALL = DB_PROPERTIES.get("readall");
	}

	
	public NbaPlayersJdbcDAO() {
		super();
		try {
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean create(NbaPlayer player) {
		boolean isCreated =false;
		try (Connection conn = DriverManager.getConnection( //getting the connection
					URL
					,USERNAME  // for sys since is common name you need to do "username as role" as well
					, PASSWORD);){
			CallableStatement cs = conn.prepareCall("{ call create_nba_player(?,?,?)}");
			cs.setString(1,player.getFirstName());
			cs.setString(2,player.getLastName());
			cs.setInt(3,player.getCareerPoints());
			
			int affectedRows=cs.executeUpdate();
			if(affectedRows ==1) {
				isCreated = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isCreated;
	}

	@Override
	public NbaPlayer read(Integer id) {
		NbaPlayer player = null;
		try(Connection conn = DriverManager.getConnection( //getting the connection
				URL
				,USERNAME  // for sys since is common name you need to do "username as role" as well
				, PASSWORD);){
			Statement stmt = conn.createStatement(); // creating statement 
			String query="Select ID,FIRST_NAME,LAST_NAME,CAREER_POINTS from NBA_PLAYERS WHERE ID ="+id;   //query
			ResultSet rs =stmt.executeQuery(query);			// result set and execute query
			while (rs.next()) {
				player = new NbaPlayer();
				player.setId(rs.getInt(1));
				player.setFirstName(rs.getString("FIRST_NAME"));
				player.setLastName(rs.getString("LAST_NAME"));
				player.setCareerPoints(rs.getInt("CAREER_POINTS"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return player;
	}

	@Override
	public boolean update(NbaPlayer player) {
		boolean isCreated=false;
		try (Connection conn = DriverManager.getConnection( //getting the connection
				URL
				,USERNAME  // for sys since is common name you need to do "username as role" as well
				, PASSWORD);){
			
			CallableStatement cs = conn.prepareCall("{ call update_nba_player(?,?,?,?)}");
			cs.setString(1, player.getFirstName());
			cs.setString(2, player.getLastName());
			cs.setInt(3, player.getCareerPoints());
			cs.setInt(4, player.getId());
			int affectedRows=cs.executeUpdate();
			if(affectedRows ==1) {
				isCreated = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isCreated;
	}

	@Override
	public boolean delete(Integer id) {
		boolean deleted= false;
		
		try(Connection conn = DriverManager.getConnection( //getting the connection
				URL
				,USERNAME  // for sys since is common name you need to do "username as role" as well
				, PASSWORD);){
		
		String query="delete from NBA_PLAYERS WHERE ID = ?";   //query
		PreparedStatement pstmt = conn.prepareCall(query);
		pstmt.setInt(1, id);
		int affectedrow =pstmt.executeUpdate();			// result set and execute query
		if (affectedrow == 1) {
			deleted = true;
		}
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return deleted;
		
	}

	@Override
	public List<NbaPlayer> read() {
		List<NbaPlayer> players = new ArrayList<>();
		
		//Database going to maintain connection
		try (Connection conn = DriverManager.getConnection( //getting the connection
				URL
				,USERNAME  // for sys since is common name you need to do "username as role" as well
				, PASSWORD);){
			
			Statement stmt = conn.createStatement(); // creating statement 
			String query=READALL;   //query
			ResultSet rs =stmt.executeQuery(query);			// rsult set and execute querry
			while (rs.next()) {
				NbaPlayer player = new NbaPlayer();
				player.setId(rs.getInt(1));
				player.setFirstName(rs.getString("FIRST_NAME"));
				player.setLastName(rs.getString("LAST_NAME"));
				player.setCareerPoints(rs.getInt("CAREER_POINTS"));
				players.add(player);
			}
			
			//Database end
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return players;
	}
	
}

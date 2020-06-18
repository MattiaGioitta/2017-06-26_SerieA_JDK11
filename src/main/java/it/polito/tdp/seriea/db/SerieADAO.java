package it.polito.tdp.seriea.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.seriea.model.Adiacenza;
import it.polito.tdp.seriea.model.Match;
import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.Team;

public class SerieADAO {

	public List<Season> listSeasons() {
		String sql = "SELECT season, description FROM seasons";
		List<Season> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Season(res.getInt("season"), res.getString("description")));
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void listTeams(Map<String, Team> idMap) {
		String sql = "SELECT team FROM teams";
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				idMap.put(res.getString("team"),new Team(res.getString("team")));
			}

			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}

	public List<Adiacenza> getAdiacenze(Map<String, Team> idMap) {
		final String sql = "SELECT t1.team AS s1,t2.team AS s2,COUNT(m.match_id) AS peso " + 
				"FROM matches AS m, teams AS t1,teams AS t2 " + 
				"WHERE (m.HomeTeam=t1.team " + 
				"AND m.AwayTeam=t2.team) OR " + 
				"(m.HomeTeam=t2.team AND m.AwayTeam=t1.team) " + 
				"GROUP BY t1.team,t2.team";
		List<Adiacenza> lista = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Adiacenza a = new Adiacenza(idMap.get(res.getString("s1")),idMap.get(res.getString("s2")),res.getInt("peso"));
				lista.add(a);
			}

			conn.close();
			return lista;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
			
		}
		
	}

	public List<Match> partitePerStagione(Season stagione, Map<String, Team> idMap) {
		final String sql = "SELECT * " + 
				"FROM matches AS m " + 
				"WHERE m.Season=? " + 
				"ORDER BY m.Date ASC";
		List<Match> lista = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, stagione.getSeason());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				int id = res.getInt("match_id");
			    Season season = stagione;
				String div = res.getString("Div");
				LocalDate date = res.getDate("Date").toLocalDate();
				Team homeTeam = idMap.get(res.getString("HomeTeam"));
				Team awayTeam = idMap.get(res.getString("AwayTeam"));
				int fthg = res.getInt("FTHG"); // full time home goals
				int ftag = res.getInt("FTAG"); // full time away goals
				String ftr = res.getString("FTR");
				
				lista.add(new Match(id,season,div,date,homeTeam,awayTeam,fthg,ftag,ftr));
			}

			conn.close();
			return lista;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
			
		}
	
	}

}


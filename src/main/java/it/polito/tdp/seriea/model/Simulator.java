package it.polito.tdp.seriea.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

import it.polito.tdp.seriea.db.SerieADAO;

public class Simulator {
	
	
	//coda degli eventi
	private PriorityQueue<Event> queue;
	
	private int T = 1000;
	private int P = 10;
	private List<Match> partite;
	private Map<String,Team> idMap;
	private SerieADAO dao;
	private List<Team> teams;
	
	
	public void init(Season stagione) {
		this.dao = new SerieADAO();
		this.teams = new ArrayList<>();
		this.idMap = new HashMap<>();
		this.dao.listTeams(idMap);
		this.partite = this.dao.partitePerStagione(stagione,idMap);
		this.queue = new PriorityQueue<>();
		for(Match m : this.partite) {
			if(!this.teams.contains(m.getAwayTeam())) {
				this.teams.add(m.getAwayTeam());
			}
			if(!this.teams.contains(m.getHomeTeam())) {
				this.teams.add(m.getHomeTeam());
			}
			Event e = new Event(m.getDate(),m);
			this.queue.add(e);
		}
	}
	
	public void run() {
		while(!this.queue.isEmpty()) {
			Event e = this.queue.poll();
			processEvent(e);
		}
	}

	private void processEvent(Event e) {
		this.calcolaRisultato(e.getMatch());
		if(e.getMatch().getFtr().compareTo("H")==0) {
			int scarto = e.getMatch().getFthg()-e.getMatch().getFtag();
			int p = scarto*this.P;
			double percentuale = p/100.0;
			int tifosiCambiano = (int) (e.getMatch().getAwayTeam().getNumTifosi()*percentuale);
			e.getMatch().getAwayTeam().setNumTifosi(e.getMatch().getAwayTeam().getNumTifosi()-tifosiCambiano);
			e.getMatch().getHomeTeam().setNumTifosi(e.getMatch().getHomeTeam().getNumTifosi()+tifosiCambiano);
			e.getMatch().getHomeTeam().increasePunti(3);
		}
		else if(e.getMatch().getFtr().compareTo("A")==0) {
			int scarto = e.getMatch().getFtag()-e.getMatch().getFthg();
			int p = scarto*this.P;
			double percentuale = p/100.0;
			int tifosiCambiano = (int) (e.getMatch().getHomeTeam().getNumTifosi()*percentuale);
			e.getMatch().getAwayTeam().setNumTifosi(e.getMatch().getHomeTeam().getNumTifosi()-tifosiCambiano);
			e.getMatch().getHomeTeam().setNumTifosi(e.getMatch().getAwayTeam().getNumTifosi()+tifosiCambiano);
			e.getMatch().getAwayTeam().increasePunti(3);
		}
		else
		{
			e.getMatch().getAwayTeam().increasePunti(1);
			e.getMatch().getHomeTeam().increasePunti(1);
		}
		
	}
	

	private void calcolaRisultato(Match match) {
		Random r = new Random();
		double random = r.nextDouble();
		int TA = match.getHomeTeam().getNumTifosi();
		int TB = match.getAwayTeam().getNumTifosi();
		if(TA<TB) {
			if(random>(1-TA/TB)) {
				match.setFthg(match.getFthg()-1);
			}
		}
		else {
			if(random>(1-TB/TA)) {
				match.setFtag(match.getFtag()-1);
			}
		}
		
		if(match.getFthg()>match.getFtag()) {
			match.setFtr("H");
		}
		else if(match.getFtag()==match.getFthg()) {
			match.setFtr("D");
		}
		else
		{
			match.setFtr("A");
		}
	
		
	}

	public Collection<Team> teams() {
		// TODO Auto-generated method stub
		return this.teams;
	}

	
}

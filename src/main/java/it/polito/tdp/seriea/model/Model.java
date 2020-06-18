package it.polito.tdp.seriea.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.seriea.db.SerieADAO;

public class Model {

	private Graph<Team,DefaultWeightedEdge> graph;
	private SerieADAO dao;
	private Map<String,Team> idMap;
	private Simulator sim;
	
	public Model() {
		this.dao = new SerieADAO();
		this.idMap = new HashMap<>();
	}
	
	
	public void createGraph() {
		this.graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.dao.listTeams(this.idMap);
		List<Adiacenza> adiacenze = this.dao.getAdiacenze(this.idMap);
		for(Adiacenza a : adiacenze) {
			if(!this.graph.containsVertex(a.getT1())) {
				this.graph.addVertex(a.getT1());
			}
			if(!this.graph.containsVertex(a.getT2())) {
				this.graph.addVertex(a.getT2());
			}
			if(this.graph.getEdge(a.getT1(), a.getT2()) == null) {
				Graphs.addEdgeWithVertices(this.graph, a.getT1(), a.getT2(),a.getPeso());
			}
		}
		
	}


	public Integer nVertici() {
		return this.graph.vertexSet().size();
	}


	public Integer nArchi() {
		return this.graph.edgeSet().size();
	}


	public List<Team> getSquadre() {
		List<Team> l = new ArrayList<>();
		for(Team t : this.graph.vertexSet()) {
			l.add(t);
		}
		Collections.sort(l);
		return l;
	}


	public List<Vicino> getVicini(Team scelto) {
		List<Vicino> l = new ArrayList<>();
		for(Team t : Graphs.neighborListOf(this.graph, scelto)) {
			l.add(new Vicino(t,this.graph.getEdgeWeight(this.graph.getEdge(scelto, t))));
		}
		Collections.sort(l);
		return l;
	}


	public List<Season> getSeasons() {
	   
		return this.dao.listSeasons();
	}


	public void simula(Season scelta) {
		this.sim = new Simulator();
		this.sim.init(scelta);
		this.sim.run();
		
	}


	public Collection<Team> getTeams() {
		// TODO Auto-generated method stub
		return this.sim.teams();
	}

}

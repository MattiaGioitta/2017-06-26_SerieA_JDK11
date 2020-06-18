package it.polito.tdp.seriea.model;

public class Vicino implements Comparable<Vicino>{
	
	private Team t;
	private double peso;
	/**
	 * @param t
	 * @param peso
	 */
	public Vicino(Team t, double peso) {
		super();
		this.t = t;
		this.peso = peso;
	}
	@Override
	public String toString() {
		return t.toString() + " num partite: " + (int)peso ;
	}
	@Override
	public int compareTo(Vicino o) {
		// TODO Auto-generated method stub
		return (int) (o.peso-this.peso);
	}
	
	

}

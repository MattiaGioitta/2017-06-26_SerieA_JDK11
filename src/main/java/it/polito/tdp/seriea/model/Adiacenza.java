package it.polito.tdp.seriea.model;

public class Adiacenza {
	
	private Team t1;
	private Team t2;
	private double peso;
	/**
	 * @param t1
	 * @param t2
	 * @param peso
	 */
	public Adiacenza(Team t1, Team t2, double peso) {
		super();
		this.t1 = t1;
		this.t2 = t2;
		this.peso = peso;
	}
	/**
	 * @return the t1
	 */
	public Team getT1() {
		return t1;
	}
	/**
	 * @param t1 the t1 to set
	 */
	public void setT1(Team t1) {
		this.t1 = t1;
	}
	/**
	 * @return the t2
	 */
	public Team getT2() {
		return t2;
	}
	/**
	 * @param t2 the t2 to set
	 */
	public void setT2(Team t2) {
		this.t2 = t2;
	}
	/**
	 * @return the peso
	 */
	public double getPeso() {
		return peso;
	}
	/**
	 * @param peso the peso to set
	 */
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	

}

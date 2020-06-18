package it.polito.tdp.seriea.model;

import java.time.LocalDate;


public class Event implements Comparable<Event>{
	
	private LocalDate time;
	private Match match;
	/**
	 * @param time
	 * @param match
	 */
	public Event(LocalDate time, Match match) {
		super();
		this.time = time;
		this.match = match;
	}
	/**
	 * @return the time
	 */
	public LocalDate getTime() {
		return time;
	}
	/**
	 * @return the match
	 */
	public Match getMatch() {
		return match;
	}
	@Override
	public int compareTo(Event o) {
		// TODO Auto-generated method stub
		return this.time.compareTo(o.getTime());
	}
	
	

}

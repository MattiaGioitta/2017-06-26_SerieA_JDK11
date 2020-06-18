package it.polito.tdp.seriea.model;

public class Team implements Comparable<Team>{

	private String team;
	private int numTifosi;
	private int punti;

	public Team(String team) {
		super();
		this.team = team;
		this.numTifosi = 1000;
		this.punti = 0;
	}

	/**
	 * @return the punti
	 */
	public int getPunti() {
		return punti;
	}

	/**
	 * @param punti the punti to set
	 */
	public void setPunti(int punti) {
		this.punti = punti;
	}

	/**
	 * @return the numTifosi
	 */
	public int getNumTifosi() {
		return numTifosi;
	}

	/**
	 * @param numTifosi the numTifosi to set
	 */
	public void setNumTifosi(int numTifosi) {
		this.numTifosi = numTifosi;
	}

	/**
	 * @return the team
	 */
	public String getTeam() {
		return team;
	}

	/**
	 * @param team
	 * the team to set
	 */
	public void setTeam(String team) {
		this.team = team;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return team;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((team == null) ? 0 : team.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		return true;
	}

	@Override
	public int compareTo(Team o) {
		// TODO Auto-generated method stub
		return this.team.compareTo(o.getTeam());
	}

	public void increasePunti(int i) {
		this.punti = this.punti+i;
		
	}

}

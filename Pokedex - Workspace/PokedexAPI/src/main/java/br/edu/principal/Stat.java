package br.edu.principal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder("base_stat")
public class Stat {
	private int base_stat;
	private int effort;
	private StatDetails stat;
	public int getBase_stat() {
		return base_stat;
	}
	public void setBase_stat(int base_stat) {
		this.base_stat = base_stat;
	}
	public int getEffort() {
		return effort;
	}
	public void setEffort(int effort) {
		this.effort = effort;
	}
	public StatDetails getStat() {
		return stat;
	}
	public void setStat(StatDetails stat) {
		this.stat = stat;
	}
	
}

package com.sports.core.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="players_count")
public class PlayersCount {
	
	
	@Id
	@Column(name="count_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int count_id;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="game_id")
	private Games games;
	private int players_count;
	public int getCount_id() {
		return count_id;
	}
	public void setCount_id(int count_id) {
		this.count_id = count_id;
	}
	public Games getGames() {
		return games;
	}
	public void setGames(Games games) {
		this.games = games;
	}
	public int getPlayers_count() {
		return players_count;
	}
	public void setPlayers_count(int players_count) {
		this.players_count = players_count;
	}
	
	

}

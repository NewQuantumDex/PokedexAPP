package br.edu.principal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)

public class Move {
			private MoveDetails move;

			public MoveDetails getMove() {
				return move;
			}

			public void setMove(MoveDetails move) {
				this.move = move;
			}
			
}

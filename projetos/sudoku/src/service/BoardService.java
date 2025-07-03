package service;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import sudoku.Board;
import sudoku.Cell;
import sudoku.GameStatusEnum;

public class BoardService {
	private final static int BOARD_LIMIT = 9;
	
	private final Board board;

	public BoardService(final Map<String, String> gameConfig) {
		super();
		this.board = new Board(initBoard(gameConfig));
	}
	
	private List<List<Cell>> initBoard(final Map<String, String> gameConfig){
		List<List<Cell>> cells = new ArrayList<>();
        for (int i = 0; i < BOARD_LIMIT; i++) {
        	cells.add(new ArrayList<>());
            for (int j = 0; j < BOARD_LIMIT; j++) {
                var positionConfig = gameConfig.get("%s,%s".formatted(i, j));
                var expected = Integer.parseInt(positionConfig.split(",")[0]);
                var fixed = Boolean.parseBoolean(positionConfig.split(",")[1]);
                var currentSpace = new Cell(expected, fixed);
                cells.get(i).add(currentSpace);
            }
        }

        System.out.println("O jogo está pronto para começar");
        return cells;
	}
	
	public List<List<Cell>> getCells(){
		return this.board.getCells();
	}
	public void reset() {
		this.board.reset();
	}
	public boolean hasErrors() {
		return this.board.hasErrors();
	}
	public GameStatusEnum getStatus() {
		return this.board.getStatus();
	}
	public boolean isGameFinished() {
		return this.board.isGameFinished();
	}
	
	
}

package sudoku;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Board {

	private final List<List<Cell>> cells;
	
	public Board(final List<List<Cell>> cells) {
		this.cells = cells;
	}

	public List<List<Cell>> getCells() {
		return cells;
	}
	
	public GameStatusEnum getStatus() {
		if(cells.stream().flatMap(Collection::stream).noneMatch(s -> !s.isFixed() && Objects.nonNull(s.getAtualValue()) )) {
			return GameStatusEnum.NON_STARTED;
		}
		return cells.stream().flatMap(Collection::stream).anyMatch(s ->  !s.isFixed() && Objects.isNull(s.getAtualValue())) ? GameStatusEnum.INCOMPLETE : GameStatusEnum.COMPLETE;
		
	}
	
	public boolean hasErrors() {
		if (getStatus() == GameStatusEnum.NON_STARTED) return false;
		return cells.stream().flatMap(Collection::stream).anyMatch(s -> Objects.nonNull(s.getAtualValue()) && !s.getAtualValue().equals(s.getExpectedValue()));
	}
	
	public boolean changeValue(final int col, final int row, Integer newValue) {
		var cell = this.cells.get(col).get(row);
		if (cell.isFixed()) {
			return false;
		}
		cell.setAtualValue(newValue);
		return true;
	}
	
	public boolean clearValue(final int col, final int row) {
		var cell = this.cells.get(col).get(row);
		if (cell.isFixed()) {
			return false;
		}
		cell.clear();
		return true;
	}
	
	public void reset() {
		this.cells.stream().flatMap(Collection::stream).forEach(Cell::clear);
	}
	
	public boolean isGameFinished() {
		return !hasErrors() && getStatus().equals(GameStatusEnum.COMPLETE);
	}
}

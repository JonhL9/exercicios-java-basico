package sudoku;

public class Cell {
	private final boolean fixed;
	private final int expectedValue;
	private Integer atualValue;
	
	public Cell(int expectedValue, boolean fixed) {
		this.expectedValue = expectedValue;
		this.fixed = fixed;
		if (fixed) {
			this.atualValue = expectedValue;
		}
		else {
			this.atualValue = null;
		}
	}

	public Integer getAtualValue() {
		return atualValue;
	}

	public void setAtualValue(Integer atualValue) {
		if (this.fixed) return;
		this.atualValue = atualValue;
	}
	
	public boolean isCorrect() {
		return this.atualValue == this.expectedValue;
	}

	public boolean isFixed() {
		return fixed;
	}

	public int getExpectedValue() {
		return expectedValue;
	}
		
	public void clear() {
		if (!this.fixed) {
			this.setAtualValue(null);
		}
	}
}

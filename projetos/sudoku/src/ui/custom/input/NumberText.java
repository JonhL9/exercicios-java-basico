package ui.custom.input;

import java.awt.Dimension;
import java.awt.Font;
import static java.awt.Font.PLAIN;
import static service.EventEnum.CLEAR_SPACE;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import service.EventEnum;
import service.EventListener;
import sudoku.Cell;

public class NumberText extends JTextField implements EventListener{
	
	private final Cell cell;
	
	public NumberText(final Cell cell) {
        this.cell = cell;
        var dimension = new Dimension(50, 50);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setVisible(true);
        this.setFont(new Font("Arial", PLAIN, 20));
        this.setHorizontalAlignment(CENTER);
        this.setDocument(new NumberTextLimit());
        this.setEnabled(!this.cell.isFixed());
        if (this.cell.isFixed()){
            this.setText(this.cell.getAtualValue().toString());
        }
        this.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(final DocumentEvent e) {
                changeSpace();
            }

            @Override
            public void removeUpdate(final DocumentEvent e) {
                changeSpace();
            }

            @Override
            public void changedUpdate(final DocumentEvent e) {
                changeSpace();
            }

            private void changeSpace(){
                if (getText().isEmpty()){
                    cell.clear();
                    return;
                }
                cell.setAtualValue(Integer.parseInt(getText()));
            }

        });
    }

    @Override
    public void update(final EventEnum eventType) {
        if (eventType.equals(CLEAR_SPACE) && (this.isEnabled())){
            this.setText("");
        }
    }
}

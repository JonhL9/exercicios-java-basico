package ui.custom.input;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import static java.awt.Font.PLAIN;
import static service.EventEnum.CLEAR_SPACE;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


import service.EventEnum;
import service.EventListener;
import sudoku.Cell;

public class NumberText extends JTextField implements EventListener{
	
	private final Cell cell;
	private final Border defaultBorder;
	private boolean sketckMode = false;
	
	public NumberText(final Cell cell) {
        this.cell = cell;
        this.defaultBorder = this.getBorder();
        
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
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                setBackground(new Color(230, 240, 255));
            }

            @Override
            public void focusLost(FocusEvent e) {
                setBorder(defaultBorder);
                if(sketckMode) {
                	setBackground(new Color(255, 230, 0));
                }
                else {
                	setBackground(Color.WHITE);
                }
                
            }
			
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e) && !cell.isFixed()) {
                	if(sketckMode) {
                		setBackground(Color.WHITE);
                	}
                	else {
                		setBackground(new Color(255, 230, 0));
                	}
                    sketckMode = !sketckMode;
                }
            }
        });
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
                setBackground(new Color(14, 126, 130, 50));
                
            }

        });
    }

    @Override
    public void update(final EventEnum eventType) {
        if (eventType.equals(CLEAR_SPACE) && (this.isEnabled())){
            this.setText("");
            this.setBackground(new Color(255, 255, 255, 255));
            this.sketckMode = false;
        }
    }
}

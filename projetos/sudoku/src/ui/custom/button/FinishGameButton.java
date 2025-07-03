package ui.custom.button;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public class FinishGameButton extends JButton{

	public FinishGameButton(final ActionListener actionListener) {
		this.setText("Concluir jogo");
		this.addActionListener(actionListener);
		
	}
}

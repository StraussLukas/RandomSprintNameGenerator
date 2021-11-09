package stage1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SprintName implements ActionListener{

	private String name;
	private int index;
	private int votes;
	private static int voteCount = 0;
	private static int yCord = 180;
	private static JPanel panel;
	private JTextField outputName;
	private JLabel voteLable = new JLabel();
	private JButton voteBtn;

	
	public JTextField getOutputName() {
		return outputName;
	}

	public void setOutputName(JTextField outputName) {
		this.outputName = outputName;
	}

	public JButton getVoteBtn() {
		return voteBtn;
	}

	public void setVoteBtn(JButton voteBtn) {
		this.voteBtn = voteBtn;
	}




	public static int getVoteCount() {
		return voteCount;
	}

	public static void setVoteCount(int voteCount) {
		SprintName.voteCount = voteCount;
	}

	public SprintName(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public void displaySprintName() {
		outputName = new JTextField(name);
		outputName.setBounds(20, yCord, 200, 20);
		outputName.setEditable(false);
		panel.add(outputName);
		yCord += 30;
	}
	
	public void clearNameOutput() {
		panel.remove(outputName);
	}

	public void displayVoteButton() {
		voteBtn = new JButton("Vote");
		voteBtn.setBounds( 230, yCord, 100, 20 );
		voteBtn.addActionListener(this);
		panel.add(voteBtn);
		yCord += 30;
	}

	public void clearVoteBtn() {
		if(voteBtn == null) return;
		panel.remove(voteBtn);
	}

	public static JPanel getPanel() {
		return panel;
	}

	public static void setPanel(JPanel panel) {
		SprintName.panel = panel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public static int getyCord() {
		return yCord;
	}

	public static void setyCord(int yCord) {
		SprintName.yCord = yCord;
	}

	public int getVotes() {
		return votes;
	}
	
	public void voteUp() {
		votes++;
		voteCount--;
		displayVotes();
	}
	
	public void displayVotes() {
		voteLable.setText("");
		voteLable.setText("Votes: " + votes);
		voteLable.setBounds( 340, voteBtn.getY(), 200, 20 );
		panel.add(voteLable);
		panel.updateUI();
	}
	
	void clearVotes() {
		votes = 0;
		voteLable.setText("");
		panel.remove(voteLable);
		panel.updateUI();
	}

	public void clearHighlighting() {	
		outputName.setBackground(Color.WHITE);

	}

	public void markNameWithMostVotes() {
		RunnerSprintNameGenerator.markNameWithMostVotes();
	}

	public void markName( Color bg ) {
		outputName.setBackground(bg);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getSource().equals(voteBtn)) {
			voteUp();
			markNameWithMostVotes();
			panel.updateUI();
		}
	}

}

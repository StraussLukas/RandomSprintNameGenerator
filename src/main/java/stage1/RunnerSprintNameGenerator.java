package stage1;


import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class RunnerSprintNameGenerator extends JFrame implements ActionListener{

	private static JPanel panel = new JPanel();
	private static JButton b1, btnStartVoting, btnRevote;
	private static JLabel l1, l2, statusLabel, headlineLabel;
	private static JTextField t1, t2;
	private RandomSprintNameGenerator generator = new RandomSprintNameGenerator();
	private static List<SprintName> sprintNames;

	public static JLabel getStatusLabel() {
		return statusLabel;
	}

	public static void setStatusLabel(JLabel statusLabel) {
		RunnerSprintNameGenerator.statusLabel = statusLabel;
	}
	
	public static JTextField getT1() {
		return t1;
	}

	public static void setT1(JTextField t1) {
		RunnerSprintNameGenerator.t1 = t1;
	}

	public static JTextField getT2() {
		return t2;
	}

	public static void setT2(JTextField t2) {
		RunnerSprintNameGenerator.t2 = t2;
	}

	public static List<SprintName> getSprintNames() {
		return sprintNames;
	}

	public static void setSprintNames(List<SprintName> sprintNames) {
		RunnerSprintNameGenerator.sprintNames = sprintNames;
	}

	private static int voteCount = 0;

	public static JButton getBtnStartVoting() {
		return btnStartVoting;
	}

	public static void setBtnStartVoting(JButton btnStartVoting) {
		RunnerSprintNameGenerator.btnStartVoting = btnStartVoting;
	}

	public static JButton getBtnRevote() {
		return btnRevote;
	}

	public static void setBtnRevote(JButton btnRevote) {
		RunnerSprintNameGenerator.btnRevote = btnRevote;
	}


	
	public RunnerSprintNameGenerator() {
		panel.setLayout(null);
		SprintName.setPanel(panel);
		headlineLabel = new JLabel("DCB Sprintname Generator");
		headlineLabel.setBounds(20, 20, 420, 20);
		panel.add(headlineLabel);
		l1 = new JLabel("Initial Letter: ");
		l1.setBounds( 20, 60, 200, 20 );
		panel.add(l1);
		t1 = new JTextField();
		t1.setBounds(240, 60, 200, 20);
		panel.add(t1);
		l2 = new JLabel("Number of random names: ");
		l2.setBounds( 20, 100, 200, 20 );
		panel.add(l2);
		t2 = new JTextField();
		t2.setBounds(240, 100, 200, 20);
		panel.add(t2);
		b1 = new JButton("Generate random names!");
		b1.addActionListener(this);
		b1.setBounds( 20, 140, 420, 20 );
		panel.add(b1);
		statusLabel = new JLabel();
		statusLabel.setBounds(20, 180, 420, 20);
		panel.add(statusLabel);
		this.add(panel);
	}

	public static void createVoteButtons() {
		if(btnStartVoting == null) 
			createStartVotingButton();
		if(btnRevote == null) 
			createRevoteButton();
	}

	private static void createRevoteButton() {
		btnRevote = new JButton("Vote again");
		btnRevote.setBounds( 20, (SprintName.getyCord() + 20), 420, 20 );
		panel.add(btnRevote);
	}

	private static void createStartVotingButton() {
		btnStartVoting = new JButton("Start voting");
		btnStartVoting.setBounds( 20, (SprintName.getyCord() + 20), 420, 20 );
		panel.add(btnStartVoting);
	}

	public static void displaySprintNames(List<SprintName> names) {
		for (int i = 0; i < names.size(); i++)
			names.get(i).displaySprintName();
		if( btnStartVoting == null || btnRevote == null )
			createVoteButtons();
		panel.updateUI();
	}

	public static boolean checkIfInputCorrect() {
		if( t1.getText().isEmpty() || 
				t2.getText().isEmpty() ||
				t1.getText().trim().length() != 1 || 
				Integer.valueOf(t2.getText().trim()) < 1 || 
				Integer.valueOf(t2.getText().trim()) > 10  ) {
			return false;
		}
		else if (t1.getText().trim().toUpperCase().contains("A") || 
				t1.getText().trim().toUpperCase().contains("B") ||
				t1.getText().trim().toUpperCase().contains("C") ||
				t1.getText().trim().toUpperCase().contains("D") ||
				t1.getText().trim().toUpperCase().contains("E") ||
				t1.getText().trim().toUpperCase().contains("F") ||
				t1.getText().trim().toUpperCase().contains("G") ||
				t1.getText().trim().toUpperCase().contains("H") ||
				t1.getText().trim().toUpperCase().contains("I") ||
				t1.getText().trim().toUpperCase().contains("J") ||
				t1.getText().trim().toUpperCase().contains("K") ||
				t1.getText().trim().toUpperCase().contains("L") ||
				t1.getText().trim().toUpperCase().contains("M") ||
				t1.getText().trim().toUpperCase().contains("N") ||
				t1.getText().trim().toUpperCase().contains("O") ||
				t1.getText().trim().toUpperCase().contains("P") ||
				t1.getText().trim().toUpperCase().contains("Q") ||
				t1.getText().trim().toUpperCase().contains("R") ||
				t1.getText().trim().toUpperCase().contains("S") ||
				t1.getText().trim().toUpperCase().contains("T") ||
				t1.getText().trim().toUpperCase().contains("U") ||
				t1.getText().trim().toUpperCase().contains("V") ||
				t1.getText().trim().toUpperCase().contains("W") ||
				t1.getText().trim().toUpperCase().contains("X") ||
				t1.getText().trim().toUpperCase().contains("y") ||
				t1.getText().trim().toUpperCase().contains("z") ) {
			return true;
			
		} else return false;
	}

	public static void clearAll() {
		statusLabel.setText("");
		if(!(sprintNames == null)) {	
			for (SprintName sprintName : sprintNames) {
				sprintName.clearNameOutput();
				sprintName.clearVoteBtn();
				sprintName.clearVotes();
				sprintName.clearHighlighting();
			}
			for(int i = 0; i < sprintNames.size(); i++  )
				sprintNames.remove(i);
			sprintNames.clear();
			SprintName.setyCord(180);
		}
		panel.updateUI();
	}

	public static void main(String[] args) throws IOException {
		RunnerSprintNameGenerator app = new RunnerSprintNameGenerator();
		app.setTitle("Random-Sprintname-Generator");
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(500, 600);
		app.setVisible(true);
	}

	public static void markNameWithMostVotes() {
		List<SprintName> namesWithMostVotes = getNamesWithMostVotes();	
		if( namesWithMostVotes.size() > 1 ) {
			markNamesDraw(namesWithMostVotes);
			if(SprintName.getVoteCount() == 0) {
				showDialogRevoteNeeded(namesWithMostVotes);	
				startRevote(namesWithMostVotes);
			}
			panel.updateUI();

		} else {
			markWinnerName(namesWithMostVotes);
			if(SprintName.getVoteCount() == 0) {
				showDialogWinnerName(namesWithMostVotes);
				for (SprintName name : sprintNames) {
					name.clearVoteBtn();
				}
			}
			panel.updateUI();
		}
	}

	private static void markWinnerName(List<SprintName> namesWithMostVotes) {
		for (SprintName name : namesWithMostVotes) {
			name.markName(Color.GREEN);
		}
	}

	private static void showDialogWinnerName(List<SprintName> namesWithMostVotes) {
		JOptionPane.showMessageDialog(panel,
				"\"" + namesWithMostVotes.get(0).getName() + "\" is your new sprintname!",
				"Popup",
				JOptionPane.PLAIN_MESSAGE);
	}

	private static void showDialogRevoteNeeded(List<SprintName> namesWithMostVotes) {
		JOptionPane.showMessageDialog(panel,
				"You need to revote between " + getNamesWithMostVotesString(namesWithMostVotes) + "!",
				"Popup",
				JOptionPane.PLAIN_MESSAGE);
	}

	public static void startRevote(List<SprintName> namesWithMostVotes) {
		clearAll();
		displaySprintNames(namesWithMostVotes);
		startVoting(namesWithMostVotes);
		sprintNames = namesWithMostVotes;
	}

	public static void markNamesDraw(List<SprintName> namesWithMostVotes) {
		for (SprintName name : namesWithMostVotes) {
			name.markName(Color.YELLOW);
		}
	}

	public static StringBuilder getNamesWithMostVotesString(List<SprintName> namesWithMostVotes) {
		StringBuilder names = new StringBuilder();
		names.append("\"" + namesWithMostVotes.get(0).getName() + "\"");
		for(int i = 1; i < namesWithMostVotes.size(); i++) {
			names.append(" and \"" + namesWithMostVotes.get(i).getName() + "\"");
		}
		return names;
	}

	public static List<SprintName> getNamesWithMostVotes() {
		List<SprintName> namesWithMostVotes = new ArrayList<SprintName>();
		for (SprintName name : sprintNames) {
			name.clearHighlighting();
			if( name.getVotes() == getHighestVotes() )
				namesWithMostVotes.add(name);
		}
		return namesWithMostVotes;
	}

	public static int getHighestVotes() {
		int[] votes = new int[sprintNames.size()];
		for (int i = 0; i < votes.length; i++)
			votes[i] = sprintNames.get(i).getVotes();
		int max = votes[0];
		for( int i = 0; i < votes.length; i++ ){
			if(max < votes[i] && votes[i] != 0)
				max = votes[i];
		}
		return max;
	}

	public static void setNumberofVoters() {
		Icon icon = new ImageIcon();
		Object[] possibilities = {1,2,3,4,5,6,7,8,9,10};
		voteCount = (Integer)JOptionPane.showInputDialog( 
				panel,
				"Please state the number of voters!",
				"Popup",
				JOptionPane.PLAIN_MESSAGE,
				icon, 
				possibilities,
				1);
		SprintName.setVoteCount(voteCount);
	}

	public static void startVoting(List<SprintName> names) {
		SprintName.setyCord(180);
		if( voteCount == 0 )
			setNumberofVoters();
		else
			SprintName.setVoteCount(voteCount);
		for (SprintName name : names) {
			name.clearVotes();
			name.clearHighlighting();
			name.clearVoteBtn();
			name.displayVoteButton();
		}
		panel.updateUI();
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if( e.getSource().equals(b1)) {
			try {
				clearAll();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

			if( !checkIfInputCorrect() ) {
				statusLabel.setText("Please check your entry and try again!");
				panel.updateUI();
			} else {
				try {
					sprintNames = generator.getRandomSprintNames( t1.getText().trim(), Integer.valueOf(t2.getText().trim()) );
					displaySprintNames(sprintNames);
					voteCount = 0;
					if( btnStartVoting != null || btnRevote != null ) {
						btnRevote.addActionListener(this);
						btnStartVoting.addActionListener(this);
						btnStartVoting.setVisible(true);
						btnRevote.setVisible(false);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		if( e.getSource().equals(btnStartVoting)) {
			startVoting(sprintNames);
			btnStartVoting.setVisible(false);
			btnRevote.setVisible(true);
		}
		if( e.getSource().equals(btnRevote)) {
			startVoting(sprintNames);
		}
	}
}


package stage1;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.junit.Test;


public class RunnerSprintNameGeneratorTest {

	public void setTestSprintNames(){
		List<SprintName> sprintNames = new ArrayList<SprintName>();
		SprintName name1 = new SprintName("accident", 0);
		sprintNames.add(name1);
		SprintName name2 = new SprintName("alien", 1);
		sprintNames.add(name2);
		SprintName name3 = new SprintName("arthur", 2);
		sprintNames.add(name3);
		SprintName name4 = new SprintName("architect", 3);
		sprintNames.add(name4);
		SprintName name5 = new SprintName("august", 4);
		sprintNames.add(name5);
		RunnerSprintNameGenerator.setSprintNames(sprintNames);
		JPanel panel = new JPanel();
		SprintName.setPanel(panel);

	}

	@Test
	public void test_createVoteButtons_checkIfVoteButtonsWereCreated() {
		RunnerSprintNameGenerator.createVoteButtons();
		assertNotNull(RunnerSprintNameGenerator.getBtnStartVoting());
		assertNotNull(RunnerSprintNameGenerator.getBtnRevote());
	}

	@Test
	public void test_displaySprintNames_checkIfNamesWereDisplayedInCorrectOrder() {
		setTestSprintNames();
		RunnerSprintNameGenerator.displaySprintNames(RunnerSprintNameGenerator.getSprintNames());
		assertEquals(RunnerSprintNameGenerator.getSprintNames().get(0).getOutputName().getText(), "accident");
		assertEquals(RunnerSprintNameGenerator.getSprintNames().get(1).getOutputName().getText(), "alien");
		assertEquals(RunnerSprintNameGenerator.getSprintNames().get(2).getOutputName().getText(), "arthur");
		assertEquals(RunnerSprintNameGenerator.getSprintNames().get(3).getOutputName().getText(), "architect");
		assertEquals(RunnerSprintNameGenerator.getSprintNames().get(4).getOutputName().getText(), "august");
	}

	@Test
	public void test_checkIfInputCorrect_checkIfAllCasesWereCaught() {
		JTextField t1 = new JTextField();
		JTextField t2 = new JTextField();
		RunnerSprintNameGenerator.setT1(t1);
		RunnerSprintNameGenerator.setT2(t2);

		t1.setText("");
		t2.setText("");
		assertFalse(RunnerSprintNameGenerator.checkIfInputCorrect());
		t1.setText("De");
		t2.setText("2");
		assertFalse(RunnerSprintNameGenerator.checkIfInputCorrect());
		t1.setText("d");
		t2.setText("13");
		assertFalse(RunnerSprintNameGenerator.checkIfInputCorrect());
		t1.setText("!");
		t2.setText("3");
		assertFalse(RunnerSprintNameGenerator.checkIfInputCorrect());
		t1.setText("D");
		t2.setText("-1");
		assertFalse(RunnerSprintNameGenerator.checkIfInputCorrect());
		t1.setText(":");
		t2.setText("4");
		assertFalse(RunnerSprintNameGenerator.checkIfInputCorrect());
		t1.setText("D");
		t2.setText("7");
		assertTrue(RunnerSprintNameGenerator.checkIfInputCorrect());

	}

	@Test
	public void test_clearAll_checkIfEverythingIsCleared() {
		setTestSprintNames();
		for (SprintName sprintName : RunnerSprintNameGenerator.getSprintNames()) {
			sprintName.displaySprintName();
			sprintName.displayVoteButton();
		}
		JLabel lable = new JLabel("Test");
		RunnerSprintNameGenerator.setStatusLabel(lable);
		RunnerSprintNameGenerator.clearAll();
		assertEquals("", RunnerSprintNameGenerator.getStatusLabel().getText().trim());
		assertTrue( RunnerSprintNameGenerator.getSprintNames().isEmpty() );
		assertEquals(0, SprintName.getVoteCount());
	}

	@Test
	public void test_markNameWithMostVotes_checkIfCorrectNameIsHighlightedGreenIfThereIsAWinner() {
		setTestSprintNames();
		for (SprintName sprintName : RunnerSprintNameGenerator.getSprintNames()) {
			sprintName.displaySprintName();
			sprintName.displayVoteButton();
		}
		SprintName.setVoteCount(3);
		RunnerSprintNameGenerator.getSprintNames().get(1).voteUp();
		RunnerSprintNameGenerator.getSprintNames().get(1).voteUp();
		RunnerSprintNameGenerator.getSprintNames().get(2).voteUp();
		RunnerSprintNameGenerator.markNameWithMostVotes();
		assertEquals(Color.GREEN, RunnerSprintNameGenerator.getSprintNames().get(1).getOutputName().getBackground());
	}

	@Test
	public void test_markNameWithMostVotes_checkIfRevotingStartsIfThereIsADraw() {
		setTestSprintNames();
		for (SprintName sprintName : RunnerSprintNameGenerator.getSprintNames()) {
			sprintName.displaySprintName();
			sprintName.displayVoteButton();
		}
		SprintName.setVoteCount(4);
		RunnerSprintNameGenerator.getSprintNames().get(1).voteUp();
		RunnerSprintNameGenerator.getSprintNames().get(1).voteUp();
		RunnerSprintNameGenerator.getSprintNames().get(2).voteUp();
		RunnerSprintNameGenerator.getSprintNames().get(2).voteUp();
		RunnerSprintNameGenerator.markNameWithMostVotes();
		assertTrue(SprintName.getVoteCount() > 0);
		for (SprintName sprintName : RunnerSprintNameGenerator.getSprintNames()) {
			assertNotNull(sprintName.getOutputName());
			assertNotNull(sprintName.getVoteBtn());
		}

	}

	@Test
	public void test_getNamesWithMostVotes_checkIfListContainsTheRightNameIfThereIsAWinner() {
		setTestSprintNames();
		for (SprintName sprintName : RunnerSprintNameGenerator.getSprintNames()) {
			sprintName.displaySprintName();
			sprintName.displayVoteButton();
		}
		SprintName.setVoteCount(5);
		RunnerSprintNameGenerator.getSprintNames().get(1).voteUp();
		RunnerSprintNameGenerator.getSprintNames().get(1).voteUp();
		RunnerSprintNameGenerator.getSprintNames().get(1).voteUp();
		RunnerSprintNameGenerator.getSprintNames().get(2).voteUp();
		RunnerSprintNameGenerator.getSprintNames().get(2).voteUp();
		assertEquals(RunnerSprintNameGenerator.getSprintNames().get(1), RunnerSprintNameGenerator.getNamesWithMostVotes().get(0));
	}

	@Test
	public void test_getNamesWithMostVotes_checkIfListContainsTheRightNamesIfThereIsADraw() {
		setTestSprintNames();
		for (SprintName sprintName : RunnerSprintNameGenerator.getSprintNames()) {
			sprintName.displaySprintName();
			sprintName.displayVoteButton();
		}
		SprintName.setVoteCount(4);
		RunnerSprintNameGenerator.getSprintNames().get(1).voteUp();
		RunnerSprintNameGenerator.getSprintNames().get(1).voteUp();
		RunnerSprintNameGenerator.getSprintNames().get(2).voteUp();
		RunnerSprintNameGenerator.getSprintNames().get(2).voteUp();
		assertEquals(RunnerSprintNameGenerator.getSprintNames().get(1), RunnerSprintNameGenerator.getNamesWithMostVotes().get(0));
		assertEquals(RunnerSprintNameGenerator.getSprintNames().get(2), RunnerSprintNameGenerator.getNamesWithMostVotes().get(1));
	}

	@Test
	public void test_getHighestVotes_checkIfHighestVotesIsCorrect() {
		setTestSprintNames();
		for (SprintName sprintName : RunnerSprintNameGenerator.getSprintNames()) {
			sprintName.displaySprintName();
			sprintName.displayVoteButton();
		}
		SprintName.setVoteCount(5);
		RunnerSprintNameGenerator.getSprintNames().get(1).voteUp();
		RunnerSprintNameGenerator.getSprintNames().get(1).voteUp();
		RunnerSprintNameGenerator.getSprintNames().get(2).voteUp();
		RunnerSprintNameGenerator.getSprintNames().get(2).voteUp();
		RunnerSprintNameGenerator.getSprintNames().get(2).voteUp();
		assertEquals(3, RunnerSprintNameGenerator.getHighestVotes());
	}


	@Test
	public void test_getHighestVotes_checkIfHighestVotesIsCorrectEvenIfMoreNamesHasTheSameVotes() {
		setTestSprintNames();
		for (SprintName sprintName : RunnerSprintNameGenerator.getSprintNames()) {
			sprintName.displaySprintName();
			sprintName.displayVoteButton();
		}
		SprintName.setVoteCount(6);
		RunnerSprintNameGenerator.getSprintNames().get(1).voteUp();
		RunnerSprintNameGenerator.getSprintNames().get(1).voteUp();
		RunnerSprintNameGenerator.getSprintNames().get(1).voteUp();
		RunnerSprintNameGenerator.getSprintNames().get(2).voteUp();
		RunnerSprintNameGenerator.getSprintNames().get(2).voteUp();
		RunnerSprintNameGenerator.getSprintNames().get(2).voteUp();
		assertEquals(3, RunnerSprintNameGenerator.getHighestVotes());
	}

	@Test
	public void test_startRevote_checkIfRevoteStartsWithRemainingNames() {
		setTestSprintNames();
		for (SprintName sprintName : RunnerSprintNameGenerator.getSprintNames()) {
			sprintName.displaySprintName();
			sprintName.displayVoteButton();
		}
		SprintName.setVoteCount(6);
		RunnerSprintNameGenerator.getSprintNames().get(1).voteUp();
		RunnerSprintNameGenerator.getSprintNames().get(1).voteUp();
		RunnerSprintNameGenerator.getSprintNames().get(1).voteUp();
		RunnerSprintNameGenerator.getSprintNames().get(2).voteUp();
		RunnerSprintNameGenerator.getSprintNames().get(2).voteUp();
		RunnerSprintNameGenerator.getSprintNames().get(2).voteUp();
		RunnerSprintNameGenerator.startRevote(RunnerSprintNameGenerator.getNamesWithMostVotes());
		assertEquals(1, RunnerSprintNameGenerator.getSprintNames().get(0).getIndex());
		assertEquals(2, RunnerSprintNameGenerator.getSprintNames().get(1).getIndex());
	}
	
	@Test
	public void test_markNamesDraw_checkIfNamesWereHighlightedYellow() {
		setTestSprintNames();
		for (SprintName sprintName : RunnerSprintNameGenerator.getSprintNames()) {
			sprintName.displaySprintName();
			sprintName.displayVoteButton();
		}
		SprintName.setVoteCount(4);
		RunnerSprintNameGenerator.getSprintNames().get(1).voteUp();
		RunnerSprintNameGenerator.getSprintNames().get(1).voteUp();
		RunnerSprintNameGenerator.getSprintNames().get(2).voteUp();
		RunnerSprintNameGenerator.getSprintNames().get(2).voteUp();
		RunnerSprintNameGenerator.markNamesDraw(RunnerSprintNameGenerator.getNamesWithMostVotes());
		assertEquals(Color.YELLOW, RunnerSprintNameGenerator.getNamesWithMostVotes().get(0).getOutputName().getBackground());
		assertEquals(Color.YELLOW, RunnerSprintNameGenerator.getNamesWithMostVotes().get(1).getOutputName().getBackground());
	
	}
}


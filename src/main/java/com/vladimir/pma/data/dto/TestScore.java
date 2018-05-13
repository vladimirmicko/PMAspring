package com.vladimir.pma.data.dto;

import java.util.ArrayList;
import java.util.List;


public class TestScore {

	private List<StimulusResult> scoreList;
	private int testStartTime;

	public TestScore() {
		this.scoreList = new ArrayList<StimulusResult>();
	}

	public TestScore(List<StimulusResult> scoreList) {
		this.scoreList = scoreList;
	}

	public void addStimulusResult(StimulusResult stimulusResult) {
		this.scoreList.add(stimulusResult);
	}

	public void removeStimulusResult(StimulusResult stimulusResult) {
		this.scoreList.remove(stimulusResult);
	}

	public List<StimulusResult> getScoreList() {
		return scoreList;
	}

	public void setScoreList(List<StimulusResult> scoreList) {
		this.scoreList = scoreList;
	}

	public int getTestStartTime() {
		return testStartTime;
	}

	public void setTestStartTime(int testStartTime) {
		this.testStartTime = testStartTime;
	}

}

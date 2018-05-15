package com.vladimir.pma.data.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class StimulusResult implements Serializable {

	private static final long serialVersionUID = -7382483200397458920L;
	
    private int testId;
    private int stimulusNo;
    private long primeStimShowTime;
    private long testStimShowTime;
    private int answer;
    private long answerTime;


    public StimulusResult() {
    }


    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getStimulusNo() {
        return stimulusNo;
    }

    public void setStimulusNo(int stimulusNo) {
        this.stimulusNo = stimulusNo;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public long getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(long answerTime) {
        this.answerTime = answerTime;
    }

    public long getPrimeStimShowTime() {
        return primeStimShowTime;
    }

    public void setPrimeStimShowTime(long primeStimShowTime) {
        this.primeStimShowTime = primeStimShowTime;
    }

    public long getTestStimShowTime() {
        return testStimShowTime;
    }

    public void setTestStimShowTime(long testStimShowTime) {
        this.testStimShowTime = testStimShowTime;
    }

    @JsonIgnore
    public long getAnswerDuration() {
        return answerTime-testStimShowTime;
    }

}


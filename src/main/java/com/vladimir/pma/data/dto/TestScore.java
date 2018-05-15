package com.vladimir.pma.data.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TestScore implements Serializable {

	private static final long serialVersionUID = -830415288783418966L;
	
    private List<StimulusResult> stimulusResultList;
    private long testStartTime;


    public TestScore() {
        this.stimulusResultList = new ArrayList();
    }

    public TestScore(List<StimulusResult> stimulusResultList) {
        this.stimulusResultList = stimulusResultList;
    }

    public void addStimulusResult(StimulusResult stimulusResult){
        this.stimulusResultList.add(stimulusResult);
    }

    public void removeStimulusResult(StimulusResult stimulusResult){
        this.stimulusResultList.remove(stimulusResult);
    }

    public List<StimulusResult> getStimulusResultList() {
        return stimulusResultList;
    }

    public void setStimulusResultList(List<StimulusResult> stimulusResultList) {
        this.stimulusResultList = stimulusResultList;
    }

    public long getTestStartTime() {
        return testStartTime;
    }

    public void setTestStartTime(long testStartTime) {
        this.testStartTime = testStartTime;
    }



}

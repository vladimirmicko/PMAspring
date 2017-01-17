package com.vladimir.pma.data.dto;

import java.util.List;

public class TestScore {

    List<String> scoreList;

    public TestScore() {
    }

    public TestScore(List<String> scoreList) {
        this.scoreList = scoreList;
    }

    public List<String> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<String> scoreList) {
        this.scoreList = scoreList;
    }
}

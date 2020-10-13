package seedu.revised.card.quiz;

public class Result {
    private double score = 0;
    private double maxScore;
    private String description;

    public Result(float score, int maxScore) {
        this.score = score;
        this.maxScore = maxScore;
        this.description = "Fail";

    }

    public double getScore() {
        return score;
    }

    public double getMaxScore() {
        return maxScore;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public String getDescription() {
        return description;
    }


    public void updateResult(double score) {
        this.score = score;
        if (this.score >= this.maxScore / 2) {
            this.description = "Pass";
        }
        if (this.score == this.maxScore) {
            this.description = "Excellent";
        }
    }

    public String toString() {
        return this.score + "/" + this.maxScore + " -- " + this.description;
    }
}

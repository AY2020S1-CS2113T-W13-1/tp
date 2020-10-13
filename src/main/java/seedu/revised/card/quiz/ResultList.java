package seedu.revised.card.quiz;


import java.util.List;

public class ResultList {
    private List<Result> resultList;

    public ResultList(List<Result> resultList) {
        this.resultList = resultList;

    }

    public void add(Result result) {
        this.resultList.add(result);
    }

    public List<Result> getList() {
        return this.resultList;
    }

}

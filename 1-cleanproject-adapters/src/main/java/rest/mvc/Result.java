package rest.mvc;

public class Result {
    private String viewData;
    private int viewStatus;

    public Result(String viewData,int viewStatus ){
        this.viewData=viewData;
        this.viewStatus=viewStatus;
    }

    public String getViewData() {
        return viewData;
    }

    public int getViewStatus() {
        return viewStatus;
    }
}

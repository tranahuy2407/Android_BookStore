package stu.edu.vn.tahbookstore.model;

import java.util.List;

public class SachBanChayModel {
    boolean success;
    String message;
    List<SachBanChay> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SachBanChay> getResult() {
        return result;
    }

    public void setResult(List<SachBanChay> result) {
        this.result = result;
    }
}

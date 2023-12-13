package stu.edu.vn.tahbookstore.model;

import java.util.List;

public class SachMoiModel {
    boolean success;
    String message;
    List<SachMoi> result;

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

    public List<SachMoi> getResult() {
        return result;
    }

    public void setResult(List<SachMoi> result) {
        this.result = result;
    }
}

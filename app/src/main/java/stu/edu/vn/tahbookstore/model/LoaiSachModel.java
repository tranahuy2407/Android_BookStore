package stu.edu.vn.tahbookstore.model;

import java.util.List;

public class LoaiSachModel {
    boolean success;
    String message;
    List<LoaiSach> result;

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

    public List<LoaiSach> getResult() {
        return result;
    }

    public void setResult(List<LoaiSach> result) {
        this.result = result;
    }
}

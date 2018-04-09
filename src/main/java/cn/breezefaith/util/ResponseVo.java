package cn.breezefaith.util;

public class ResponseVo {
    private boolean success;
    private int errorCode;
    private Object data;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseVo{" +
                "success=" + success +
                ", errorCode=" + errorCode +
                ", data='" + data + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

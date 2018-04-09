package cn.breezefaith.constant;

public interface Cons {
    Integer TOKEN_TIMEOUT_SECONDS=1800;

    public interface Login {
        int USERNAME_OR_PASSWORD_WRONG_CODE=1001;
        String USERNAME_OR_PASSWORD_WRONG_MESSAGE="用户名或密码错误";
        int LOGIN_SUCCESS_CODE=0;
        String LOGIN_SUCCESS_MESSAGE="登录成功";
        int NOT_LOGGED_CODE=1002;
        String NOT_LOGGED_MESSAGE="未登录";
    }

    public interface Register {
        int REGISTER_FAILED_CODE=2001;
        String REGISTER_FAILED_MESSAGE="注册失败";
        int REGISTER_SUCCESS_CODE=0;
        String REGISTER_SUCCESS_MESSAGE="注册成功";
    }

    public interface Request {

        int REQUEST_FAILED_CODE=3001;
        String REQUEST_FAILED_MESSAGE="请求错误";
        int REQUEST_SUCCESS_CODE=0;
        String REQUEST_SUCCESS_MESSAGE="请求成功";
    }
}

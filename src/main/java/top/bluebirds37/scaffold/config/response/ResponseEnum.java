package top.bluebirds37.scaffold.config.response;

public enum ResponseEnum {

    /**
     *
     */
    OK(10000,"成功"),
    UNAUTHORIZED(30001,"未认证"),
    FORBIDDEN(30002,"未认证"),
    FAIL(50000,"失败");

    public final int status;
    public final String message;

    ResponseEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }

}

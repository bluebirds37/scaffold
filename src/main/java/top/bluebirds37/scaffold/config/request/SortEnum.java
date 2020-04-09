package top.bluebirds37.scaffold.config.request;


/**
 * @version 1.0
 * @Author mwh
 * @Date 2020/2/14 15:20
 */
public enum SortEnum {

    /**
     *
     */
    ASC("ASC"),
    DESC("DESC");

    public final String value;

    SortEnum(String value) {
        this.value = value;
    }
}

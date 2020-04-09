package top.bluebirds37.scaffold.config.properties;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author mwh
 * description    项目配置
 * createDate     2019/4/11 10:26
 * updateRemark
 * version        1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "top.bluebirds37")
public class SystemProperties {

    /**
     * 文件下载前缀
     */
    public String downloadPrefix;

    /**
     * 文件文件夹路径
     */
    public String fileStoreDir;

    public String fileStoreDirPath;

    /**
     * 文件解压后的路径
     */
    public String zipOutDir;

}

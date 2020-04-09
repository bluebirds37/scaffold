package top.bluebirds37.scaffold.config.jpa;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;

import java.io.Serializable;

/**
 * @version 1.0
 * @Author mwh
 * @Date 2020/1/21 10:11
 */
public class SnowIdentityGenerator extends IdentityGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor s, Object obj) {
        return SnowFlake.nextId();
    }
}

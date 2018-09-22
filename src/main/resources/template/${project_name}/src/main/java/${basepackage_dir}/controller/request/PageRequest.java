package ${basepackage}.controller.request;

import lombok.Data;
import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;

@Data
public class PageRequest implements Serializable {
    private static final long serialVersionUID = -7022848697987418010L;
    private Integer page = 1;
    private Integer pageSize = 10;

    public Integer getOffset() {
        return (page - 1) * pageSize;
    }

    public Integer getLimit() {
        return pageSize;
    }

    public RowBounds toRowBounds() {
        return new RowBounds(getOffset(), getLimit());
    }
}

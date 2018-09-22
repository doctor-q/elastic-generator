package ${basepackage}.controller.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import ${basepackage}.controller.request.PageRequest;

@Data
public class PageResponse<T> implements Serializable {
    private Integer total;
    private Integer page;
    private Integer pageSize;
    private List<T> list;

    public PageResponse() {

    }

    public PageResponse(Integer total, List<T> list) {
        this.total = total;
        this.list = list;
    }

    public PageResponse(PageRequest pageRequest, Integer total, List<T> list) {
        this.page = pageRequest.getPage();
        this.pageSize = pageRequest.getPageSize();
        this.total = total;
        this.list = list;
    }
}
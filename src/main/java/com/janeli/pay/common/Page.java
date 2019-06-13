package com.janeli.pay.common;

import java.util.List;

/**
 * Created by Administrator on 2018/11/23 0023.
 */
public class Page<T> {
    private long total;

    private List<T> content;

    public Page() {
    }

    public Page(List<T> content) {
        if (content == null) {
            return;
        }
        this.content = content;
        this.total = content.size();
    }

    public Page(long total, List<T> content) {
        this.total = total;
        this.content = content;
    }

    public long getTotal() {
        return total;
    }

    public Page<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    public List<T> getContent() {
        return content;
    }

    public Page<T> setContent(List<T> content) {
        this.content = content;
        return this;
    }
}

package com.mercadolibre.stock.model.dto;

import java.util.ArrayList;
import java.util.List;

public class PaginationWrapperDTO<T> {
    private long totalItems;
    private long totalPages;
    private long currentPage;
    private List<T> items = new ArrayList<>();

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}

package com.bloomberg.clustereddatawarehouse.utils;

import com.bloomberg.clustereddatawarehouse.exceptions.PaginationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public abstract class Util {
    public static Pageable fromPaginationRequest(int pageNumber, int pageSize) {
        if (pageNumber < 1) {
            throw new PaginationException("Inavalid page number");
        } else if (pageSize < 1) {
            throw new PaginationException("Invalid page size");
        }
        return PageRequest.of(pageNumber - 1, pageSize);
    }
}

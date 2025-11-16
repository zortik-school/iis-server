package me.zort.iis.server.iisserver.http.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;

import java.util.function.Function;

@Getter
public class PageResponse<T> {
    private final Iterable<T> items;
    private final PageInfo page;

    @Getter
    @AllArgsConstructor
    public static class PageInfo {
        private final int index;
        private final int size;
        private final int totalPages;

    }

    public PageResponse(Iterable<T> items, int pageIndex, int pageSize, int totalPages) {
        this.items = items;
        this.page = new PageInfo(pageIndex, pageSize, totalPages);
    }

    /**
     * Create a PageResponse from a Spring Data Page, mapping the content using the provided mapper function.
     *
     * @param page The Spring Data Page to convert.
     * @param mapper Function to map from D to T.
     * @return A PageResponse containing the mapped items and pagination info.
     * @param <T> The type of items in the PageResponse.
     * @param <D> The type of items in the original Page.
     */
    public static @NotNull <T, D> PageResponse<T> fromPage(Page<D> page, Function<D, T> mapper) {
        Iterable<T> mappedItems = page.map(mapper).toList();

        return new PageResponse<>(mappedItems, page.getNumber(), page.getSize(), page.getTotalPages());
    }
}

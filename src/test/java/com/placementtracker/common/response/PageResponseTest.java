package com.placementtracker.common.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PageResponseTest {

    @Test
    @DisplayName("Should correctly map Spring Data Page to PageResponse")
    void shouldMapSpringPageToPageResponse() {
        List<String> items = List.of("Java", "Spring", "MySQL");
        Page<String> springPage = new PageImpl<>(items, PageRequest.of(0, 10), 30);

        PageResponse<String> pageResponse = PageResponse.from(springPage);

        assertThat(pageResponse.getContent()).containsExactly("Java", "Spring", "MySQL");
        assertThat(pageResponse.getPageNumber()).isEqualTo(0);
        assertThat(pageResponse.getPageSize()).isEqualTo(10);
        assertThat(pageResponse.getTotalElements()).isEqualTo(30L);
        assertThat(pageResponse.getTotalPages()).isEqualTo(3);
        assertThat(pageResponse.isLast()).isFalse();
    }
}

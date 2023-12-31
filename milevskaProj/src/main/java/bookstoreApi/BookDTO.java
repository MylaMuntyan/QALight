package bookstoreApi;

import lombok.*;

    @Builder
    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor

    public class BookDTO{
        String isbn;
        String title;
        String subTitle;
        String author;
        String publish_date;
        String publisher;
        Integer pages;
        String description;
        String website;
    }


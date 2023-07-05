package com.github.albertloubet.libraryfx.repository;

import com.github.albertloubet.libraryfx.exception.RepositoryException;
import com.github.albertloubet.libraryfx.foundation.RepositoryFoundation;
import com.github.albertloubet.libraryfx.model.entity.Book;
import com.github.albertloubet.libraryfx.model.filter.BookFilter;
import org.apache.commons.lang3.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookRepository extends RepositoryFoundation {

    public List<Book> findAllByFilters(BookFilter bookFilter) {
        var books = new ArrayList<Book>();
        var query = new StringBuilder("SELECT * FROM book b");

        if (StringUtils.isNotEmpty(bookFilter.name())) {
            query.append(" WHERE des_name LIKE '%")
                    .append(bookFilter.name())
                    .append("%'");
        }

        if (StringUtils.isNotEmpty(bookFilter.code())) {
            whereOrAnd(query);

            query.append("des_code = '")
                    .append(bookFilter.code())
                    .append("'");
        }

        if (Objects.nonNull(bookFilter.volume())) {
            whereOrAnd(query);

            query.append("num_volume = ")
                    .append(bookFilter.volume());
        }

        query.append(";");

        try (var conn = getConnection();
             var st = conn.createStatement();
             var rs = st.executeQuery(query.toString())) {

            while (rs.next()) {
                var book = Book.builder()
                        .code(rs.getString("des_code"))
                        .name(rs.getString("des_name"))
                        .quantity(rs.getInt("num_quantity"))
                        .volume(rs.getInt("num_quantity"))
                        .build();
                book.setId(rs.getLong("idt_book"));
                book.setCreatedAt(rs.getTimestamp("dat_created_at").toLocalDateTime());
                book.setUpdatedAt(rs.getTimestamp("dat_updated_at").toLocalDateTime());

                books.add(book);
            }

            return books;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }
}

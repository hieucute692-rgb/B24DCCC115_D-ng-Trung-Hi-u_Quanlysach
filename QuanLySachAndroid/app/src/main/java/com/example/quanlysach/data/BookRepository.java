package com.example.quanlysach.data;

import java.util.ArrayList;
import com.example.quanlysach.model.Book;

/** Dữ liệu lưu trong bộ nhớ đúng theo yêu cầu bài tập (chưa dùng cơ sở dữ liệu). */
public final class BookRepository {
    private static final ArrayList<Book> BOOKS = new ArrayList<>();

    static {
        BOOKS.add(new Book("Lập trình Android cơ bản", "Nguyễn Văn A", "Công nghệ", "Đang đọc", 4f,
                "Sách dành cho người mới bắt đầu"));
        BOOKS.add(new Book("Đắc nhân tâm", "Dale Carnegie", "Kỹ năng sống", "Đã đọc", 5f, ""));
        BOOKS.add(new Book("Dế Mèn phiêu lưu ký", "Tô Hoài", "Văn học", "Chưa đọc", 0f, ""));
    }

    private BookRepository() { }

    public static ArrayList<Book> getBooks() { return BOOKS; }
}

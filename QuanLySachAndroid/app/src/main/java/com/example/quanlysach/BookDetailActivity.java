package com.example.quanlysach;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import com.example.quanlysach.data.BookRepository;
import com.example.quanlysach.model.Book;

public class BookDetailActivity extends Activity {
    public static final String EXTRA_BOOK_INDEX = "book_index";
    private int bookIndex;
    private ArrayList<Book> books;
    private TextView txtName;
    private TextView txtAuthorCategory;
    private TextView txtStatus;
    private TextView txtRatingValue;
    private TextView txtNotes;
    private RatingBar ratingBar;
    private Button btnMarkRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        books = BookRepository.getBooks();
        bookIndex = getIntent().getIntExtra(EXTRA_BOOK_INDEX, -1);
        if (bookIndex < 0 || bookIndex >= books.size()) {
            Toast.makeText(this, "Không tìm thấy sách.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        txtName = findViewById(R.id.txtDetailName);
        txtAuthorCategory = findViewById(R.id.txtDetailAuthorCategory);
        txtStatus = findViewById(R.id.txtDetailStatus);
        txtRatingValue = findViewById(R.id.txtDetailRatingValue);
        txtNotes = findViewById(R.id.txtDetailNotes);
        ratingBar = findViewById(R.id.ratingBarDetail);
        btnMarkRead = findViewById(R.id.btnMarkRead);
        Button btnBack = findViewById(R.id.btnDetailBack);
        Button btnDelete = findViewById(R.id.btnDeleteBook);

        btnMarkRead.setOnClickListener(v -> markAsRead());
        btnBack.setOnClickListener(v -> finish());
        btnDelete.setOnClickListener(v -> confirmDelete());
        showBook();
    }

    private void showBook() {
        if (bookIndex < 0 || bookIndex >= books.size()) return;
        Book book = books.get(bookIndex);
        txtName.setText(book.getName());
        txtAuthorCategory.setText(book.getAuthor() + "  ·  " + book.getCategory());
        txtStatus.setText(book.getStatus());
        ratingBar.setRating(book.getRating());
        txtRatingValue.setText(String.format(java.util.Locale.getDefault(), "%.1f / 5 sao", book.getRating()));
        txtNotes.setText(book.getNotes().isEmpty() ? "Chưa có ghi chú." : book.getNotes());

        boolean alreadyRead = "Đã đọc".equals(book.getStatus());
        btnMarkRead.setEnabled(!alreadyRead);
        btnMarkRead.setAlpha(alreadyRead ? 0.65f : 1f);
        btnMarkRead.setText(alreadyRead ? "Đã đọc" : "Đánh dấu đã đọc");
    }

    private void markAsRead() {
        if (bookIndex < 0 || bookIndex >= books.size()) return;
        books.get(bookIndex).setStatus("Đã đọc");
        showBook();
        Toast.makeText(this, "Đã cập nhật trạng thái sách.", Toast.LENGTH_SHORT).show();
    }

    private void confirmDelete() {
        new AlertDialog.Builder(this)
                .setTitle("Xóa sách")
                .setMessage("Bạn có chắc muốn xóa cuốn sách này không?")
                .setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss())
                .setPositiveButton("Xóa", (dialog, which) -> {
                    if (bookIndex >= 0 && bookIndex < books.size()) {
                        books.remove(bookIndex);
                        Toast.makeText(this, "Đã xóa sách.", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                })
                .show();
    }
}

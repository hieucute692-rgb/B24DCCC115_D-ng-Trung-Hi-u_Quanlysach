package com.example.quanlysach;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import com.example.quanlysach.data.BookRepository;
import com.example.quanlysach.model.Book;

public class MainActivity extends Activity {
    private TextView txtTotal;
    private TextView txtRead;
    private TextView txtUnread;
    private ListView listBooks;
    private BookAdapter adapter;
    private ArrayList<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTotal = findViewById(R.id.txtTotal);
        txtRead = findViewById(R.id.txtRead);
        txtUnread = findViewById(R.id.txtUnread);
        listBooks = findViewById(R.id.listBooks);
        Button btnAddBook = findViewById(R.id.btnAddBook);

        books = BookRepository.getBooks();
        adapter = new BookAdapter();
        listBooks.setAdapter(adapter);
        listBooks.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, BookDetailActivity.class);
            intent.putExtra(BookDetailActivity.EXTRA_BOOK_INDEX, position);
            startActivity(intent);
        });
        btnAddBook.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AddBookActivity.class)));

        updateSummary();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
            updateSummary();
        }
    }

    private void updateSummary() {
        int total = books.size();
        int read = 0;
        for (Book book : books) {
            if ("Đã đọc".equals(book.getStatus())) read++;
        }
        txtTotal.setText(String.valueOf(total));
        txtRead.setText(String.valueOf(read));
        txtUnread.setText(String.valueOf(total - read));
    }

    private class BookAdapter extends BaseAdapter {
        @Override public int getCount() { return books.size(); }
        @Override public Object getItem(int position) { return books.get(position); }
        @Override public long getItemId(int position) { return position; }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            if (row == null) {
                row = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_book, parent, false);
            }
            Book book = books.get(position);
            TextView name = row.findViewById(R.id.txtBookName);
            TextView author = row.findViewById(R.id.txtBookAuthor);
            TextView category = row.findViewById(R.id.txtBookCategory);
            TextView status = row.findViewById(R.id.txtBookStatus);
            TextView rating = row.findViewById(R.id.txtBookRating);

            name.setText(book.getName());
            author.setText("Tác giả: " + book.getAuthor());
            category.setText(book.getCategory());
            status.setText(book.getStatus());
            rating.setText(formatStars(book.getRating()));

            int statusColor = "Đã đọc".equals(book.getStatus()) ? 0xFF2E7D32
                    : ("Đang đọc".equals(book.getStatus()) ? 0xFFEF6C00 : 0xFF757575);
            status.setTextColor(statusColor);
            return row;
        }
    }

    private String formatStars(float value) {
        int fullStars = Math.round(value);
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < 5; i++) stars.append(i < fullStars ? "★" : "☆");
        return stars.toString();
    }
}

package com.example.quanlysach;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlysach.data.BookRepository;
import com.example.quanlysach.model.Book;

public class AddBookActivity extends Activity {
    private EditText edtName;
    private EditText edtAuthor;
    private EditText edtNotes;
    private Spinner spnCategory;
    private RadioGroup radioStatus;
    private RatingBar ratingBar;
    private TextView txtError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        edtName = findViewById(R.id.edtBookName);
        edtAuthor = findViewById(R.id.edtBookAuthor);
        edtNotes = findViewById(R.id.edtBookNotes);
        spnCategory = findViewById(R.id.spnCategory);
        radioStatus = findViewById(R.id.radioStatus);
        ratingBar = findViewById(R.id.ratingBarAdd);
        txtError = findViewById(R.id.txtAddError);
        Button btnSave = findViewById(R.id.btnSaveBook);
        Button btnCancel = findViewById(R.id.btnCancelAdd);

        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(
                this, R.array.book_categories, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCategory.setAdapter(categoryAdapter);
        radioStatus.check(R.id.rbUnread);

        btnSave.setOnClickListener(v -> saveBook());
        btnCancel.setOnClickListener(v -> finish());
    }

    private void saveBook() {
        String name = edtName.getText().toString().trim();
        String author = edtAuthor.getText().toString().trim();

        if (name.isEmpty() || author.isEmpty()) {
            txtError.setText("Tên sách và tác giả không được để trống.");
            txtError.setVisibility(View.VISIBLE);
            return;
        }

        int checkedId = radioStatus.getCheckedRadioButtonId();
        RadioButton selected = findViewById(checkedId);
        String status = selected == null ? "Chưa đọc" : selected.getText().toString();
        String category = spnCategory.getSelectedItem() == null ? "Khác" : spnCategory.getSelectedItem().toString();
        String notes = edtNotes.getText().toString().trim();
        float rating = ratingBar.getRating();

        BookRepository.getBooks().add(new Book(name, author, category, status, rating, notes));
        Toast.makeText(this, "Đã lưu sách.", Toast.LENGTH_SHORT).show();
        finish();
    }
}

package soon.domain.one.take_notes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class NoteEditorActivity extends AppCompatActivity {

    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        // access the edit text
        final EditText editText = findViewById(R.id.editText);

        // get the extra
        Intent intent = getIntent();
        // -1 to denote some error
        noteId = intent.getIntExtra("noteId", -1);

        // no error
        if (noteId != -1) {
            // display the note
            editText.setText(MainActivity.notes.get(noteId));
        } else {
            // when we add new note
            MainActivity.notes.add("");
            noteId = MainActivity.notes.size() - 1;
        }

        // to save when changes occur
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // this is the place we want to to our save stuff
                // lets update the array
                MainActivity.notes.set(noteId, String.valueOf(charSequence));
                MainActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("soon.domain.one.take_notes", Context.MODE_PRIVATE);
                // we'll use Set
                HashSet<String> set = new HashSet<>(MainActivity.notes);
                // set wont maintain the order
                sharedPreferences.edit().putStringSet("notes", set).apply();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}

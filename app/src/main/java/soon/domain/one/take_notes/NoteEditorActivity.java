package soon.domain.one.take_notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class NoteEditorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        // access the edit text
        EditText editText = findViewById(R.id.editText);

        // get the extra
        Intent intent = getIntent();
        // -1 to denote some error
        int noteId = intent.getIntExtra("noteId", -1);

        // no error
        if (noteId != -1) {
            // display the note
            editText.setText(MainActivity.notes.get(noteId));
        }


    }
}

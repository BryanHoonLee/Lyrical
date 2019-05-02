package hoonstudio.com.tutory

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class NewUserActivity : AppCompatActivity(){

    private lateinit var editUserView: EditText

    public override fun onCreate(savedInstance: Bundle?){
        super.onCreate(savedInstance)
        setContentView(R.layout.activity_new_user)
        editUserView = findViewById(R.id.edit_user)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if(TextUtils.isEmpty(editUserView.text)){
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else{
                val word = editUserView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "NewUserActivity.REPLY"
    }
}
package example.com.materialsample.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.com.materialsample.R;

//http://www.hermosaprogramacion.com/2016/02/textinputlayout-en-android-material-design/
public class TextInputLayoutActivity extends AppCompatActivity
        implements View.OnFocusChangeListener {

    @BindView(R.id.input1_layout)
    TextInputLayout input1Layout;
    @BindView(R.id.input1_text)
    TextInputEditText input1Text;

    @BindView(R.id.input2_layout)
    TextInputLayout input2Layout;
    @BindView(R.id.input2_text)
    TextInputEditText input2Text;

    @BindView(R.id.input3_layout)
    TextInputLayout input3Layout;
    @BindView(R.id.input3_text)
    TextInputEditText input3Text;

    public static Intent getStartIntent(@NonNull Context from) {
        Intent intent = new Intent(from, TextInputLayoutActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        ButterKnife.bind(this);

        configureUI();
    }

    private void configureUI() {
        // put the error below Edit text
        input1Layout.setErrorEnabled(true);
        input2Layout.setErrorEnabled(true);
        input3Layout.setErrorEnabled(true);

        // configure validation on lost focus
        input1Text.setOnFocusChangeListener(this);
        input2Text.setOnFocusChangeListener(this);
        input3Text.setOnFocusChangeListener(this);
    }

    @OnClick(R.id.button_validate)
    void onValidateClick() {
        isDataValid();
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if (!hasFocus) {
            switch (view.getId()) {
                case R.id.input1_text:
                    validateName();
                    break;

                case R.id.input2_text:
                    validatePhone();
                    break;

                case R.id.input3_text:
                    validateMail();
                    break;
            }
        }
    }

    private boolean isDataValid() {
        final String name = input1Text.getText().toString();
        final String phone = input2Text.getText().toString();
        final String mail = input3Text.getText().toString();

        boolean isValid = true;

        if (!isNameValid(name)) {
            input1Layout.setError("Invalid name");
            isValid = false;
        } else {
            input1Layout.setError(null);
        }

        if (!isPhoneValid(phone)) {
            input2Layout.setError("Invalid phone");
            isValid = false;
        } else {
            input2Layout.setError(null);
        }

        if (!isMailValid(mail)) {
            input3Layout.setError("Invalid mail");
            isValid = false;
        } else {
            input3Layout.setError(null);
        }

        return isValid;
    }

    private boolean validateName() {
        boolean isValid = true;

        final String name = input1Text.getText().toString();
        if (!isNameValid(name)) {
            input1Layout.setError("Invalid name");
            isValid = false;
        } else {
            input1Layout.setError(null);
        }
        return isValid;
    }

    private boolean isNameValid(String nombre) {
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        return patron.matcher(nombre).matches() || nombre.length() > 30;
    }

    private boolean validatePhone() {
        boolean isValid = true;

        final String phone = input2Text.getText().toString();
        if (!isPhoneValid(phone)) {
            input2Layout.setError("Invalid phone");
            isValid = false;
        } else {
            input2Layout.setError(null);
        }
        return isValid;
    }

    private boolean isPhoneValid(String telefono) {
        return Patterns.PHONE.matcher(telefono).matches();
    }

    private boolean validateMail() {
        boolean isValid = true;

        final String mail = input3Text.getText().toString();
        if (!isMailValid(mail)) {
            input3Layout.setError("Invalid mail");
            isValid = false;
        } else {
            input3Layout.setError(null);
        }
        return isValid;
    }

    private boolean isMailValid(String correo) {
        return Patterns.EMAIL_ADDRESS.matcher(correo).matches();
    }
}

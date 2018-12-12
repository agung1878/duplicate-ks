package id.co.koperasisyariah212.ui.helper;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.widget.AutoCompleteTextView;

public class Field {

    private TextInputLayout textInputLayout;
    private TextInputEditText textInputEditText;
    private AutoCompleteTextView autoCompleteTextView;

    public Field(TextInputLayout textInputLayout, TextInputEditText textInputEditText) {
        this.textInputLayout = textInputLayout;
        this.textInputEditText = textInputEditText;
    }

    public Field(TextInputLayout textInputLayout, AutoCompleteTextView autoCompleteTextView) {
        this.textInputLayout = textInputLayout;
        this.autoCompleteTextView = autoCompleteTextView;
    }

    public TextInputLayout getTextInputLayout() {
        return textInputLayout;
    }

    public void setTextInputLayout(TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
    }

    public TextInputEditText getTextInputEditText() {
        return textInputEditText;
    }

    public void setTextInputEditText(TextInputEditText textInputEditText) {
        this.textInputEditText = textInputEditText;
    }

    public AutoCompleteTextView getAutoCompleteTextView() {
        return autoCompleteTextView;
    }

    public void setAutoCompleteTextView(AutoCompleteTextView autoCompleteTextView) {
        this.autoCompleteTextView = autoCompleteTextView;
    }
}

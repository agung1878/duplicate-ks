package id.co.koperasisyariah212.ui.helper;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;

import java.util.List;

import id.co.koperasisyariah212.R;
import id.co.koperasisyariah212.ui.helper.Field;

public class FragmentHelper extends Fragment {

    public void setTitle(String title){
        getActivity().setTitle(title);
    }

    public void changeFragment(Fragment fragment){
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_home, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }

    public boolean hasEmpty(List<Field> fields){

        boolean error = false;

        for(Field field : fields){
            if(field.getTextInputEditText() != null){
                if(field.getTextInputEditText().getText().toString().isEmpty()){
                    error = true;
                    field.getTextInputLayout().setError("Cannot be empty");
                }
            }
            if(field.getAutoCompleteTextView() != null){
                if(field.getAutoCompleteTextView().getText().toString().isEmpty()){
                    error = true;
                    field.getTextInputLayout().setError("Cannot be empty");
                }
            }

        }

        return error;
    }

    public void resetForm(List<Field> fields){
        for(Field field : fields){
            field.getTextInputLayout().setError(null);
            if(field.getTextInputEditText() != null){
                field.getTextInputEditText().setText(null);
            }
            if(field.getAutoCompleteTextView() != null){
                field.getAutoCompleteTextView().setText(null);
            }
        }

    }

    public class SprinnerTouchListener implements View.OnTouchListener {
        private AutoCompleteTextView spinner;

        public SprinnerTouchListener(AutoCompleteTextView spinner) {
            this.spinner = spinner;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            spinner.showDropDown();
            return false;
        }
    }

    public boolean hasPermission(String permission) {
        return ContextCompat.checkSelfPermission(getActivity(), permission) == PackageManager.PERMISSION_GRANTED;
    }


}

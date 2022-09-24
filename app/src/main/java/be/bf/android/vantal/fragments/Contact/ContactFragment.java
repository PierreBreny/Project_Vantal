package be.bf.android.vantal.fragments.Contact;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import be.bf.android.vantal.R;

public class ContactFragment extends Fragment {

    private Button send_btn;
    private Button back_btn;
    private EditText te_subject;
    private EditText te_message;
    private NavController navController;

    public ContactFragment() {
        // Required empty public constructor
    }

    public static ContactFragment newInstance(String param1, String param2) {
        ContactFragment fragment = new ContactFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        navController = NavHostFragment.findNavController(this);

        back_btn = view.findViewById(R.id.back_btn);
        back_btn.setOnClickListener(this::goBack);

        send_btn = view.findViewById(R.id.send_btn);
        send_btn.setOnClickListener(this::sendEmail);

        return view;
    }

    private void sendEmail(View view) {
        te_subject = view.findViewById(R.id.et_subject);
        te_message = view.findViewById(R.id.et_message);

        String subject = te_subject.getText().toString();
        String message = te_message.getText().toString();
        String mailTo = "pierre@vantal.com";

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, mailTo);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        startActivity(intent);

    }

    private void goBack(View view) {
        navController.navigate(R.id.action_contactFragment_to_accountFragment);
    }
}
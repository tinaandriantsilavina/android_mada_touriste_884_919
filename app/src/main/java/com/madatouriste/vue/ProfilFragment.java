package com.madatouriste.vue;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.madatouriste.R;
import com.madatouriste.modele.CustomResponse;
import com.madatouriste.modele.Token;
import com.madatouriste.modele.User;
import com.madatouriste.service.RetrofitClient;
import com.madatouriste.ApiInterface.UserInterface;
import com.madatouriste.utils.ProgressBuilder;
import com.madatouriste.utils.Utils;
import com.madatouriste.utils.template_json.InfosJson;
import com.madatouriste.utils.template_json.PasswordJson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfilFragment} factory method to
 * create an instance of this fragment.
 */
public class ProfilFragment extends Fragment {
    User user;
    EditText profil_txt_nom, profil_txt_prenom, profil_txt_email ,  profil_txt_password ;
    Button btnModifier, btnMdp;
    ImageView passwordIcon;
    boolean affichePass = false ;
    public ProfilFragment(){
        // require a empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profil, container, false);
        getUser();
        initUser(v);
        initTxt(v);
        initTxtValue(v);
        actionBtnPassword(v);
        actionBtnModifier();
        actionBtnModifierPass();
        return v ;
    }

    public void initUser(View v){
        ((TextView)v.findViewById(R.id.profil_nom)).setText(user.getPrenom()+"   "+ user.getNom());
    }
    public void getUser(){
        if(getActivity() instanceof  MainActivity){
            MainActivity mainActivity = (MainActivity) getActivity();
            String nom = mainActivity.getUser().getNom();
            user = mainActivity.getUser();
            if(user==null){
                try {
                    mainActivity.getUserInfo();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }else{
            Toast.makeText(getActivity(), "Veuillez vous connecter", Toast.LENGTH_SHORT).show();
        }
    }

    public void initTxt(View v){
        profil_txt_nom = (EditText) v.findViewById(R.id.profil_txt_nom);
        profil_txt_prenom = (EditText) v.findViewById(R.id.profil_txt_prenom);
        profil_txt_email = (EditText) v.findViewById(R.id.profil_txt_email);
        profil_txt_password = (EditText) v.findViewById(R.id.profil_txt_password);

        btnModifier = (Button) v.findViewById(R.id.profil_btn_modifier);
        btnMdp = (Button) v.findViewById(R.id.profil_btn_modifier_pass);
        passwordIcon = (ImageView)  v.findViewById(R.id.profil_passwordIcon);
    }

    public void initTxtValue(View v){
        ((TextView)v.findViewById(R.id.profil_nom)).setText(user.getPrenom()+" "+ user.getNom());
        ((EditText)v.findViewById(R.id.profil_txt_nom)).setText(user.getNom());
        ((EditText)v.findViewById(R.id.profil_txt_prenom)).setText(user.getPrenom());
        ((EditText)v.findViewById(R.id.profil_txt_email)).setText(user.getEmail());
    }
    public void actionBtnPassword(View v){
        passwordIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(affichePass){
                    affichePass = false;
                    profil_txt_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.baseline_remove_red_eye_24);
                }else{
                    affichePass = true;
                    profil_txt_password.setInputType( InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.baseline_disabled_visible_24);
                }
                profil_txt_password.setSelection(profil_txt_password.length());
            }
        });
    }

    public void actionBtnModifier(){
        btnModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = String.valueOf(profil_txt_nom.getText()).trim();
                String prenom = String.valueOf(profil_txt_prenom.getText()).trim();
                String email = String.valueOf(profil_txt_email.getText()).trim();
                if(nom.isEmpty() || prenom.isEmpty() || email.isEmpty()){
                    Toast.makeText(getActivity(), "Veuillez bien remplir les formulaire pour information utilisateur", Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        updateInfos(nom, prenom, email);
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "Erreur :"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                User user = new User ( nom, prenom, email, "");
                String nomUser = user.getNom();
            }
        });
    }

    public void actionBtnModifierPass(){
        btnMdp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = String.valueOf(profil_txt_password.getText());
                if(password.isEmpty()){
                    Toast.makeText(getActivity(), "Veuillez bien remplir les formulaire de mot de passe", Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        updatePassword(password);
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "Erreur :"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void updateInfos(String nom, String prenom, String email) throws Exception{
        ProgressBuilder spinner = new ProgressBuilder(getActivity());
        spinner.showProgressDialog();
        String token = Utils.getToken(getActivity());
        UserInterface userInterface = RetrofitClient.getRetrofitInstance().create(UserInterface.class);
        InfosJson input = new InfosJson();
        input.setNom(nom);
        input.setPrenom(prenom);
        input.setEmail(email);
        Call<CustomResponse> call = userInterface.updateInfos(new Token(token).getBearerToken(), input);
        call.enqueue(new Callback<CustomResponse>() {
                         @Override
                         public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                             if (response.body().getStatus() == 200) {
                                 String jsonString = new Gson().toJson(response.body().getDatas());
                                 User user = new Gson().fromJson(jsonString, User.class);
                                 ((MainActivity) getActivity()).setUser(user);
                                 Utils.logger(response);
                                 String str = "Votre modification à ete realiser avec success :\nNom: %s \n Prenom: %s \n EMail: %s";
//                                 String.format(str, nom, prenom, email)
                                 Utils.sendNotif1(getContext(), "Securité\n", "Les informations de votre compte ont été modifié." );
                                 ProfilFragment profil = new ProfilFragment();
                                 profil.setUser(user);
                                 Utils.fragmentNavig(getActivity(), profil);
                             } else {
                                Utils.logger(response);
                                 Toast.makeText(getActivity(), "Erreur "+response.message(), Toast.LENGTH_SHORT).show();
                             }
                             spinner.dismissProgressDialog();
                         }
                         @Override
                         public void onFailure(Call<CustomResponse> call, Throwable t) {
                             Log.e("error_message", "onFailure: " + t.getMessage());
                             Toast.makeText(getActivity(), "Erreur WS: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                             spinner.dismissProgressDialog();
                         }
                     }
        );
    }
    public void updatePassword(String mdp) throws Exception{
        String token = Utils.getToken(getActivity());
        UserInterface userInterface = RetrofitClient.getRetrofitInstance().create(UserInterface.class);
        PasswordJson input = new PasswordJson();
        input.setPassword(mdp);

        Call<CustomResponse> call = userInterface.updatePassword(new Token(token).getBearerToken(), input);
        call.enqueue(new Callback<CustomResponse>() {
                         @Override
                         public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                             if (response.body().getStatus() == 200) {
                                 String jsonString = new Gson().toJson(response.body().getDatas());
                                 User user = new Gson().fromJson(jsonString, User.class);
                                 ((MainActivity) getActivity()).setUser(user);
                                 Utils.logger(response);
                                 String str = "La modification de votre mot de passe  à ete realiser avec success \nNouveau mot de passe: %s ";
//                                 Utils.sendNotif2(getActivity(), "Modification terminé", String.format(str, mdp));

                                 ProfilFragment profil = new ProfilFragment();
                                 profil.setUser(user);
                                 Utils.fragmentNavig(getActivity(), profil);
                             } else {
                                 Utils.logger(response);
                                 Utils.sendNotif2(getActivity(),",kj","lkhjlkj");
                                         Toast.makeText(getActivity(), "Erreur :"+response.message(), Toast.LENGTH_SHORT).show();
                             }
                         }
                         @Override
                         public void onFailure(Call<CustomResponse> call, Throwable t) {
                             Log.e("error_message", "onFailure: " + t.getMessage());
                             Toast.makeText(getActivity(), "Erreur WS:: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
                         }
                     }
        );
    }
}
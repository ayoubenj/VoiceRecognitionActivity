package com.example.voicerecognitionactivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.Voice;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.voicerecognitionactivity.mp3.*;

import org.videolan.libvlc.IVLCVout;
import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import com.bumptech.glide.*;

/**
 * class principale permettant le lancement de l'application
* */
public class VoiceRecognitionActivity extends AppCompatActivity implements IVLCVout.Callback , SearchView.OnQueryTextListener{

    private static final int VOICE_RECOGNITION_REQUEST_CODE = 1001;
    public EditText metTextHint; // champs text permettant la verification de ce qui est fait
    private SearchView searchv; //barre de recherche
    Uri medias = Uri.parse(""); //url media initilaisé
    private ListView mlvTextMatches; //pour liste morceau
    //private Spinner msTextMatches; //
    private Button mbtSpeak; //bouton pour la reconnaissance vocale
    private org.videolan.libvlc.MediaPlayer mediaPlayer = null;
    private LibVLC libvlc = null;
    String s = ""; //variable utile pour la chaine de caractére à envoyé au Analyseur
    String serverip; //ip a saisir du serveur
    public static String comAnalisee;//la commande aprés analyse
    client clien; //le client ice pour se connecter
    String precedant ="";//ancien selectionné
    int precedanti=0;
    static ArrayAdapter adapter ;
    static ArrayList<String> textMatchlist;
    static List<morceau> musicx = null; //liste des morceau
    static boolean playing=false; //variable utile pour l'état du player
    public static Map<String, morceau> morc =null; //map de tous couple <titre,morceau> disponible sur le serveur
    public static Map<String, morceau> morcfiltered; //map filtrés aprés recherche

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_recognition);

        //instanciation
        metTextHint = (EditText) findViewById(R.id.etTextHint);
        searchv = (SearchView) findViewById(R.id.search);//recherche

        mlvTextMatches = (ListView) findViewById(R.id.lvTextMatches);//emplacement liste des musiques
       // msTextMatches = (Spinner) findViewById(R.id.sNoOfMatches);
        mbtSpeak = (Button) findViewById(R.id.btSpeak); //bouton pour la reconnaissance vocale

        //demander l'adresse ip du serveur
        IPSERVER("Entrez l'adresse ip du serveur svp!");

        setupSearchView();


    }

    /**
     * methode pour demander l'adresse ip du serveur
     * */
    public void IPSERVER (String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder (this);
        builder.setTitle (title);

        final EditText txt = new EditText( this);
        txt.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView (txt);

        // @ IP, default pour mon seveur qui doit être allumé
        this.serverip = "192.168.43.63";

        builder.setPositiveButton ("OK", new DialogInterface.OnClickListener () {
            @Override
            public void onClick (DialogInterface dialog, int which) {
                //iniialiser l'adresse ip
                serverip = txt.getText ().toString ();
                //initialiser l'url du media à streamer
                medias = Uri.parse("rtsp://" + serverip + ":8555/");

                // Creer un client ICE
                try {
                    clien = new client (serverip, metTextHint);
                   if(metTextHint.getText().toString().contains("Vous n'êtes pas connectez"))
                       IPSERVER ("Réessayez à nouveau.");

                } catch (Exception e) {
                    //metTextHint.setText("erreursss");
                    IPSERVER ("Introuvable. Réessayez.");
                }
            }
        });



        builder.show();
    }


    /**
     * La reconnaisssance de parole via internet
     * */
    public void speak(View view) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass().getPackage().getName());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Je vous ecoute ! ");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
       /* if (msTextMatches.getSelectedItemPosition() == AdapterView.INVALID_POSITION) {
            Toast.makeText(this, "Please select No. of Matches from Spinner", Toast.LENGTH_LONG).show();
            return;

        }*/

        int noOfMatches = 1;
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, noOfMatches);
        startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);

    }


    /**
     * Method pour remplir la liste des morceau disponile à partir d'une map
     * @return liste rempli
     * @param map une map reçu par le serveur
     * */
    public List<morceau> generermusic (Map<String,morceau> map) {


        List<morceau> lm = new ArrayList<>();
        for (Map.Entry<String, morceau> entry : map.entrySet()) {
                lm.add(entry.getValue());
        }


        return lm;

    }




    @Override
    protected void onResume() {
        super.onResume();

    }

    /**
     * method permettant de faire la lecture d'un morceau en interrogeant le serveur
     * @param name la chaine de caractére envoyé au serveur pour qu'il puisse streamer
     */
    protected void playmusics (String name) {
            //appel de la method implimenter par le serveur
        try{
            this.clien.printer.playmusic(name);
        } catch (Exception e){
            metTextHint.setText("attention");
        }
        //si le param n'est pas stop on crée le player côté client
        if(name!="stop")
            createPlayer (medias);

    }


    /**
     *
     */

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.releasePlayer();
    }

    /**
     * methode pour traiter des actions sans passer par le web service
     * comme augmenter le volume, passer à la musique suivante...
     * @param s est la liste des chaines de caractéres proposé par google voice
     *          pour la reconnaissance vocale.
     */
    void resulter(ArrayList<String> s){
        if (!s.isEmpty()) {
            //Nous allons prendre en compte que la premiére proposition parmis la liste proposée.
            int taille = musicx.size();
            metTextHint.setText(s.get(0));
            //si la chaine est stop,
            // nous allons stopper côté client at ensuite côté serveur
            if(s.get(0).contains("stop")){
                mediaPlayer.stop();
                playmusics("stop");
                showToastMessage("stop");
                playing =false;//le player n'est pas en mode play

            }
            //si la chaine contient le mot suivant, nous passsons au morceau suivant
            //parmis la liste
            else if(s.get(0).toLowerCase().contains("suivant")){
                //Toast.makeText(this, "suivant: " + taille, Toast.LENGTH_SHORT).show();

                mediaPlayer.stop();
                playmusics("stop");
                this.precedanti = this.precedanti+1; //pour passer au suivant
                if(precedanti>=taille)
                    this.precedanti = 0;

                playmusics("ecouter de " + musicx.get(this.precedanti).name);

            }
            //passer au précédent
            else if(s.get(0).toLowerCase().contains("précédent")){
                //Toast.makeText(this, "precedant: " + mediaPlayer.getPlayerState(), Toast.LENGTH_SHORT).show();

                mediaPlayer.stop();
                playmusics("stop");
                precedanti = precedanti-1;
                if(precedanti<0)
                    this.precedanti = taille-1;
                playmusics("ecouter de " + musicx.get(precedanti).name);

            }
            //pour augmenter le volume
            else if(s.get(0).toLowerCase().contains("augmenter volume")){
                AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                int currentVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
                int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                float percent = 0.2f;
                int seventyVolume = (int) (maxVolume*percent);
                audio.setStreamVolume(AudioManager.STREAM_MUSIC, +currentVolume+seventyVolume, 0);
                Toast.makeText(this, "volume augmenté de  " + currentVolume+" à " + seventyVolume , Toast.LENGTH_SHORT).show();
            }
            //pour baisser le volume
            else if(s.get(0).toLowerCase().contains("baisser volume")){
                AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                int currentVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
                int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                float percent = 0.2f;
                int seventyVolume = (int) (maxVolume*percent);
                audio.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume-seventyVolume, 0);
                Toast.makeText(this, "volume baissé de  " + currentVolume+" à " + seventyVolume , Toast.LENGTH_SHORT).show();

            }

            else{
                showToastMessage("rien");
                createPlayer(medias);
            }




        }

    }
    /************************************************************************************************************************/


    /**
     * methode pour filtrer la liste des morceau selon une recherche donnée
     * @param sx ce qu'on recherche
     * @return map filtré
     */
    Map<String, morceau> fitered (String sx) {

        this.morcfiltered = new HashMap<>();

        this.morcfiltered.clear();


       if(!sx.isEmpty()) {
           //si on écrit le caractére '?' cela veux dire qu'on veux toute la liste
           if(sx.equals("?"))
               this.morcfiltered = this.morc;
            //sinon on filtre selon ce qui est écrit dans la barre de recherche
            for (Map.Entry<String, morceau> entry : this.morc.entrySet()) {
                    if (entry.getValue().name.contains(sx)) {

                        this.morcfiltered.put(entry.getKey(), entry.getValue());
                    }
                }
            }


        return this.morcfiltered;
    }

    /**
     * methode pour configurer la barre de recherche
     */
    private void setupSearchView()
    {
        searchv.setIconifiedByDefault(false);
        searchv.setOnQueryTextListener(this);
        searchv.setSubmitButtonEnabled(true);
        searchv.setQueryHint("Search Here");
    }

    /**
     * methode pour adapter notre listeview selon la liste filtrés
     */
    public void adaptermeth(){

        musicx = generermusic(this.morcfiltered);
        adapter = new ArrayAdapter (this, R.layout.row_music, R.id.Title, musicx) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView TITLE = (TextView) view.findViewById (R.id.Title);
                TextView AUTHOR = (TextView) view.findViewById (R.id.Author);


                TITLE.setText (musicx.get(position).name);
                AUTHOR.setText (musicx.get(position).auteur);

                ImageView img = (ImageView) view.findViewById(R.id.avatar);
                //pour charger les images de chaque morceau
                Glide.with(this.getContext())
                        .load(musicx.get(position).avatar).into(img);

                view.setOnClickListener (new View.OnClickListener() {
                    @Override
                    public void onClick (View view) {

                        Toast.makeText(VoiceRecognitionActivity.this, "clicked : " +  musicx.get(position).name, Toast.LENGTH_LONG).show();

                        if (precedant != musicx.get(position).name) {

                                //stopper le player que si le player est lancé
                                if(playing==true) {
                                    mediaPlayer.stop();
                                    playmusics("stop");
                                    playing =false;
                                }

                            }

                            //lancer un morceau aprés l'avoir séléctionné parmis la liste
                            playmusics("ecouter de " + musicx.get(position).name);

                            precedant = musicx.get(position).name;
                            precedanti = position;
                        }

                });


                return view;
            }
        };

        }


    /***********************************************************************/


    /**
     * method redéfinie pour définir les actions à faire selon la reconnaissance vocale
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //Toast.makeText(this, "res debut: " + this.playing , Toast.LENGTH_SHORT).show();

        //si le resultat est valide
        if (resultCode == RESULT_OK) {

            //Toast.makeText(this, "taille: " +  musicx , Toast.LENGTH_SHORT).show();


            textMatchlist = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            //si le player est en cours on peux
            // passer a la music suivante ou precedante
            // baisser ou augmenter le volume
            if(this.playing==true)

                if(textMatchlist.get(0).contains("suivant") || textMatchlist.get(0).contains("précédent") || textMatchlist.get(0).contains("augmenter volume") || textMatchlist.get(0).contains("baisser volume")) {
                    if(musicx!=null) //si la liste n'est pas vide
                    resulter(textMatchlist);
                }else{
                        s = textMatchlist.toString().replace("[", "").replace("]", "");
                        StringBuilder x = new StringBuilder();
                        x.append("http://"+serverip+":8080/WS3/Analyse/" + s);

                        //192.168.1.46
                        //x.append(s);
                        new HttpRequestTask().execute(x.toString());
                    }


            else{
                //sinon on appel l'analyseur de requêtes via le web service
                s = textMatchlist.toString().replace("[", "").replace("]", "");
                StringBuilder x = new StringBuilder();
                //l'adresse où le service web est déployé
                x.append("http://"+serverip+":8080/WS3/Analyse/" + s);
                new HttpRequestTask().execute(x.toString());
            }


        } else if (resultCode == RecognizerIntent.RESULT_AUDIO_ERROR) {
            showToastMessage("Audio Error");

        } else if ((resultCode == RecognizerIntent.RESULT_CLIENT_ERROR)) {
            showToastMessage("Client Error");

        } else if (resultCode == RecognizerIntent.RESULT_NETWORK_ERROR) {
            showToastMessage("Network Error");
        } else if (resultCode == RecognizerIntent.RESULT_NO_MATCH) {
            showToastMessage("No Match");
        } else if (resultCode == RecognizerIntent.RESULT_SERVER_ERROR) {
            showToastMessage("Server Error");
        }
        super.onActivityResult(requestCode, resultCode, data);


    }

    void showToastMessage(String message) {

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();


    }


    /**
     * methode permettant la création du player
     * @param media
     */
    private void createPlayer(Uri media) {


        this.releasePlayer();

        try {



            // Setup output
            ArrayList<String> option = new ArrayList<String>();
            option.add("--audio-time-stretch"); // audio-time-stretch
            option.add("-vvv"); // la verbosity
            libvlc = new LibVLC(option);

            // Creer le media player
            this.mediaPlayer = new org.videolan.libvlc.MediaPlayer(this.libvlc);
            this.mediaPlayer.setEventListener(mPlayerListener);

            Media med = new Media(libvlc, media);
            this.mediaPlayer.setMedia(med);
            this.mediaPlayer.play();
            this.playing =true;
            Toast.makeText(this, "play music !"  + this.playing, Toast.LENGTH_LONG).show();


            //Toast.makeText(this, "playing  : " + this.playing , Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this, "error pendant la creation du player", Toast.LENGTH_LONG).show();
        }


    }

/*****/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_voice_recognition, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
/******/

    private void releasePlayer() {
        if (libvlc == null)
            return;
        this.mediaPlayer.stop();
        final IVLCVout vout = mediaPlayer.getVLCVout();
        vout.removeCallback(this);
        vout.detachViews();
        this.libvlc.release();
        this.libvlc = null;
        this.playing =false;
    }

    private org.videolan.libvlc.MediaPlayer.EventListener mPlayerListener = new MyPlayerListener(this);

    @Override
    public void onNewLayout(IVLCVout vlcVout, int width, int height, int visibleWidth, int visibleHeight, int sarNum, int sarDen) {
        if(width * height == 0)
            return;
    }

    @Override
    public void onSurfacesCreated(IVLCVout vlcVout) {

    }

    @Override
    public void onSurfacesDestroyed(IVLCVout vlcVout) {

    }

    /**
     * methode redéfinit, appeler la methode de filtre selon la recherche
     * @param query ce qui est recherché
     * @return
     */
    @Override
    public boolean onQueryTextSubmit(String query) {

        //Toast.makeText(this, "taille" + query.length(), Toast.LENGTH_LONG).show();
        morc= clien.printer.display();
        fitered(searchv.getQuery().toString());

        adaptermeth();

        this.mlvTextMatches.setAdapter (adapter);

        this.mlvTextMatches.setTextFilterEnabled(true);

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

       return false;
    }


    /**
     * classe pour le media player
     */
    private static class MyPlayerListener implements org.videolan.libvlc.MediaPlayer.EventListener {
        private WeakReference<VoiceRecognitionActivity> mOwner;

        public MyPlayerListener(VoiceRecognitionActivity owner) {
            mOwner = new WeakReference<VoiceRecognitionActivity>(owner);
        }


        @Override
        public void onEvent(org.videolan.libvlc.MediaPlayer.Event event) {

            VoiceRecognitionActivity player = mOwner.get();

            switch (event.type) {
                case org.videolan.libvlc.MediaPlayer.Event.EndReached:
                    player.releasePlayer();
                    break;
                case org.videolan.libvlc.MediaPlayer.Event.Playing:
                case org.videolan.libvlc.MediaPlayer.Event.Paused:

                case org.videolan.libvlc.MediaPlayer.Event.Stopped:
                default:
                    break;
            }
        }


    }

    /**
     * classe pour la connexion aux web service en mode asynchrone
     */

    class HttpRequestTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String w = "";
            StringBuilder stringBuilder;//= new StringBuilder(200) ;

            stringBuilder = new StringBuilder(strings[0]);
            StringBuffer response = null;
            URL obj = null;
            int responseCode = 0;

            String encodedURL = stringBuilder.toString().replace(" ", "%20").replace("é", "e");
           try {

                obj = new URL(encodedURL);
            } catch (MalformedURLException e) {
                return (e.getMessage() + " 2 ");
            }

            try {

                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                //con.connect();
                con.setReadTimeout(15000);
                con.setConnectTimeout(15000);
                con.setRequestMethod("GET");
                con.setRequestProperty("Accept-Charset", "UTF-8");
                responseCode = con.getResponseCode();

                if (responseCode == 200) {
                    // Reading response from input Stream
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                    String output;
                    response = new StringBuffer();

                    while ((output = in.readLine()) != null) {
                        response.append(output);
                    }
                    in.close();

                    return response.toString();
                    //+ "stringBuilder : " + stringBuilder;
                } else if (s.contains("stop")) {
                    mediaPlayer.stop();
                    playing = false;
                    return "stop";
                } else
                    return " je ne comprend pas ";

            } catch (Exception e) {
                return e.getMessage() + "connexion au serveur glassfish : erreur";
            }

        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            // metTextHint.setText("debut");
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
           // metTextHint.setText(s + "  ====> " +  result.toLowerCase());
            if (result.contains("liste musiques")) {
                searchv.setQuery("?", true);
            }
            if (comAnalisee != "liste musiques") {
                //searchv.setQuery("?", true);

                if (!result.contains("je ne comprend pas") && !result.contains("liste musiques")) {

                    comAnalisee = result.toLowerCase();
                    if (comAnalisee != "stop") {

                        if (playing == true) {
                            mediaPlayer.stop(); //stopper côté client, et pusi côté serveur
                            playmusics("stop");
                            playing = false;
                        }

                    }
                    if (comAnalisee != "liste musiques") {
                        playmusics(comAnalisee);

                        metTextHint.setText(comAnalisee);
                    }

                }

            }
        }
    }

}








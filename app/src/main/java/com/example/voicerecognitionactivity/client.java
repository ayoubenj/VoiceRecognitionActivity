package com.example.voicerecognitionactivity;

import android.widget.EditText;

import com.example.voicerecognitionactivity.mp3.serveRecoPrx;

/**
 * Classe pour la connection du client via ICE
 * */
class client {

    protected com.example.voicerecognitionactivity.mp3.serveRecoPrx printer;
    boolean b;

    public client(String ip, EditText et) {
       // et = (EditText) findViewById(R.id.etTextHint);

        b = false;
        try (com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize()) {
            com.zeroc.Ice.ObjectPrx base = communicator.stringToProxy("SimplePrinter:default -h " + ip + " -p 10000");
            this.printer = serveRecoPrx.checkedCast(base);
            b = true;


            if (printer == null) {
                // throw new Error("Invalid proxy");
                et.setText("nulle");
            }

        } catch (Exception e) {
            et.setText("Vous n'êtes pas connectez");
        }
        if(b==true)
            et.setText("Vous êtes bien connecté au serveur");

    }

}

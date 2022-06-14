package mhd.ghd.usergui;

import android.os.AsyncTask;
import android.view.View;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class UserTCP extends AsyncTask<String,Void,Void> {
    Socket socket;
    DataOutputStream dos;
    PrintWriter pw;

    @Override
    protected Void doInBackground(String... voids) {
        String message = voids[0];
        try {
                socket = new Socket("192.168.1.109", 8000);
                pw = new PrintWriter(socket.getOutputStream());
                pw.write(message);
                pw.flush();
                pw.close();
                socket.close();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return null;
    }





}


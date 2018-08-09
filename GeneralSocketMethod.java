package git.cluster.io.socketclusterandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import git.cluster.io.socketclusterandroid.adapter.MessageAdapter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class FeneralSocketMethod extends AppCompatActivity {

    ArrayList<String> messageList = new ArrayList<>();

    private OkHttpClient client;
    EchoWebSocketListener listener;

    final class EchoWebSocketListener extends WebSocketListener {
        private static final int NORMAL_CLOSURE_STATUS = 4000;
        TextView textView;
        ArrayList<String> mesList;
        MessageAdapter msgAdapter;


        public EchoWebSocketListener() {
        }

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("command", "register");
                jsonObject.put("userId", "9");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            webSocket.send(jsonObject.toString());
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            Log.i(">>inComingMessage", "onMessage: " + text);
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {

        }


        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**launching websocket here*/
        client = new OkHttpClient();
        start();
        /**end here*/


        /*to send message**/
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("command", "register");
            jsonObject.put("from", "9");
            jsonObject.put("id", "45");
            jsonObject.put("message", "Hello response");
            ws.send(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    WebSocket ws;

    private void start() {
        /**method to connect*/
        Request request = new Request.Builder().url("ws://xxx.xxx.x.xx:8090").build();
        /**Parameter here are just views*/
        listener = new EchoWebSocketListener();
        /*use this ws object to send message on button click or anywhere*/
        ws = client.newWebSocket(request, listener);
        /**end here*/
        //client.dispatcher().executorService().shutdown();
    }
}

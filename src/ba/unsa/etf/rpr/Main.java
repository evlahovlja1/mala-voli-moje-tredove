package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicReference;

public class Main extends Application {
    AtomicReference<String> textFromRest = new AtomicReference<>("");

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");

        StackPane stackPane = new StackPane();
        Button lbl = new Button("Programming is fun");
        stackPane.getChildren().add(lbl);
        //lbl.setOnAction(actionEvent -> System.exit(0));
        //Platform.setImplicitExit(false);

        primaryStage.setScene(new Scene(stackPane, 300, 275));
        primaryStage.show();

        Task<String> getFromServer = new Task<String>() {
            @Override
            protected String call() throws Exception {
                String ret = "";
                try {
                    URL url = new URL("http://localhost:8080/greet?name=Elvir");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Accept", "application/json");

                    if (conn.getResponseCode() != 200) {
                        throw new RuntimeException("HTTP GET Failed: " + conn.getResponseMessage());
                    }

                    InputStreamReader in = new InputStreamReader(conn.getInputStream());
                    BufferedReader br = new BufferedReader(in);
                    String output;

                    while ((output = br.readLine()) != null) {
                        ret += output;
                    }

                    conn.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return ret;
            }
        };

        getFromServer.setOnSucceeded(e -> {
            lbl.setText(getFromServer.getValue());
        });


        Thread t = new Thread(getFromServer);
        t.setDaemon(true);
        t.start();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}

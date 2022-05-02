/**
 * Objective: Create a JAVAFX project that plays the anthem and displays the flag of 7 different nations.
 *Input and Output: The user can select any of the 7 countries (Denmark, Germany, China, India, Norway, UK,  US) to view its flag and listen to its anthem.
 * The user is also able to pause and play the audio.
 * Created by:Lizbeth Trujillo
 * Date: April 3, 2022
 * Version: IntelliJ
 */

package com.example.flaganthem;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FlagAnthem extends Application {
    private final static int NUMBER_OF_NATIONS = 7;
    private final static String URLBase =
            "https://liveexample.pearsoncmg.com/common";
    private int currentIndex = 0;

    @Override
    public void start(Stage primaryStage){

        Image[] images = new Image[NUMBER_OF_NATIONS];
        MediaPlayer[] mp = new MediaPlayer[NUMBER_OF_NATIONS];

        for(int i = 0; i< NUMBER_OF_NATIONS; i++){
            images[i] = new Image(URLBase + "/image/flag" + i + ".gif");
            mp[i] = new MediaPlayer(new Media(
                    URLBase + "/audio/anthem/anthem" + i + ".mp3"));
        }

        Button btPlayPause = new Button("||");
        btPlayPause.setOnAction(e -> {
            if(btPlayPause.getText().equals(">")){
                btPlayPause.setText("||");
                mp[currentIndex].play();
            }
            else{
                btPlayPause.setText(">");
                mp[currentIndex].pause();
            }
        });

        ImageView imageView = new ImageView(images[currentIndex]);
        ComboBox<String> cboNation = new ComboBox<>();
        ObservableList<String> items = FXCollections.observableArrayList
                ("Denmark", "Germany", "China", "India", "Norway", "UK", "US");
        cboNation.getItems().addAll(items);
        cboNation.setValue(items.get(0));
        cboNation.setOnAction(e -> {
            mp[currentIndex].stop();
            currentIndex = items.indexOf(cboNation.getValue());
            imageView.setImage(images[currentIndex]);
            mp[currentIndex].play();
            btPlayPause.setText("||");

            PathTransition pt = new PathTransition(Duration.millis(10000),
                    new Line(100, 200, 100, 0), imageView);
            pt.setCycleCount(5);
            pt.play();

        });

        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(btPlayPause,
                new Label("Select a nation: "), cboNation);
        hBox.setAlignment(Pos.CENTER);


        BorderPane pane = new BorderPane();
        pane.setCenter(imageView);
        pane.setBottom(hBox);

        Scene scene = new Scene(pane, 350, 270);
        primaryStage.setTitle("FlagAnthem");
        primaryStage.setScene(scene);
        primaryStage.show();
        mp[currentIndex].play();
    }
}

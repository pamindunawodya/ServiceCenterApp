package lk.ijse.akautoservice.controller;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;

public class ReceptionistLoginFormController {
    public AnchorPane replogincontext;
    public ImageView resceptionistLoadingImg;
    public AnchorPane mainStageLoadContext;

    public void btnloginOnAction(ActionEvent actionEvent) throws IOException, InterruptedException {
      mainStageLoadContext.setVisible(true);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(3), e -> {
            Stage oldstage = (Stage)replogincontext.getScene().getWindow();
            oldstage.close();
            Stage primarystage = new Stage();
            try {
                primarystage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ReceptionistMainForm.fxml"))));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            primarystage.show();

        }));

        timeline.play();


    }

}

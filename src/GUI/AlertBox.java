package GUI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by andrei on 11/20/2016.
 */
public class AlertBox {

    public static void display(String title, String message)
    {
        Stage alertWindow = new Stage();

        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle(title);
        alertWindow.setMinWidth(500);
        alertWindow.setMinHeight(200);

        Label messageLabel = new Label();
        messageLabel.setPadding(new Insets(10,10,10,10));
        messageLabel.setText(message);
        messageLabel.setFont(new Font("Arial",22));


        VBox layout = new VBox(10);
        layout.getChildren().addAll(messageLabel);

        Scene scene = new Scene(layout);
        alertWindow.setScene(scene);
        alertWindow.showAndWait();
    }
}

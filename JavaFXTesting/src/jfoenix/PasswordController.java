package jfoenix;

import static javafx.util.Duration.millis;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class PasswordController implements Initializable {

	@FXML
	public JFXPasswordField passTxt;

	@FXML
	private Text invalidTxt;

	private Timeline t;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		invalidTxt.setOpacity(0);
		KeyValue val0 = new KeyValue(invalidTxt.opacityProperty(), 0, Interpolator.EASE_BOTH);
		KeyValue val1 = new KeyValue(invalidTxt.opacityProperty(), 1, Interpolator.EASE_BOTH);
		KeyFrame k1 = new KeyFrame(millis(0), val0);
		KeyFrame k2 = new KeyFrame(millis(10), val1);
		KeyFrame k3 = new KeyFrame(millis(800), val0);
		t = new Timeline(k1, k2, k3);
	}

	public void invalidAnim() {
		t.play();
	}
}

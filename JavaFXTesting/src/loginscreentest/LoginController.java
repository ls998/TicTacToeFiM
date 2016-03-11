package loginscreentest;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import static javafx.util.Duration.*;

public class LoginController implements Initializable {

	public interface LoginControllerListener {
		public void onSubmit(String user, String pass);
	}

	private LoginControllerListener listener;

	@FXML
	private TextField userfield;

	@FXML
	private PasswordField passwordfield;

	@FXML
	private Button btnLogin;

	@FXML
	private Label incorrectLbl;

	private Timeline t;

	public LoginController() {
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		KeyValue val0 = new KeyValue(incorrectLbl.opacityProperty(), 0, Interpolator.EASE_BOTH);
		KeyValue val1 = new KeyValue(incorrectLbl.opacityProperty(), 1, Interpolator.EASE_BOTH);
		KeyFrame k1 = new KeyFrame(millis(0), val0);
		KeyFrame k2 = new KeyFrame(millis(10), val1);
		KeyFrame k3 = new KeyFrame(millis(800), val0);
		t = new Timeline(k1, k2, k3);
	}

	public void setListener(LoginControllerListener listener) {
		this.listener = listener;
	}

	public void incorrectAnimation() {
		t.stop();
		t.play();
	}

	@FXML
	private void usernameChanged(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			passwordfield.requestFocus();
		}
	}

	@FXML
	private void passwordChanged(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			btnLogin.arm();
			PauseTransition pause = new PauseTransition(seconds(0.2));
			pause.setOnFinished(x -> {
				btnLogin.disarm();
				btnLogin.fire();
			});
			pause.play();
		}
	}

	@FXML
	private void btnAction(ActionEvent e) {
		listener.onSubmit(userfield.getText(), passwordfield.getText());
	}
}

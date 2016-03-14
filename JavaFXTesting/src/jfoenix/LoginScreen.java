package jfoenix;

import java.io.IOException;
import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class LoginScreen extends Scene {
	public interface LoginScreenListener {
		public void onLogin(String user, String pass);

		public void onUsername(String user);

		public void onClose();
	}

	private LoginScreenListener listener;

	public final MainController controller;

	// storyboard stuff
	private int n = 4;
	private int currentScreen;
	private Parent[] content;
	private String[][] lblMessage;
	private ArrayList<EventHandler<ActionEvent>> leftHandlers;
	private ArrayList<EventHandler<ActionEvent>> rightHandlers;
	public final UsernameController userCtrl;
	public final PasswordController passCtrl;

	public LoginScreen() throws IOException {
		// FML!!!!!!!!!!! this is why i use c++
		super(new Pane());
		FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
		setRoot(loader.load());

		controller = loader.getController();

		// load storyboard
		content = new Parent[n];

		FXMLLoader tmp;

		content[0] = FXMLLoader.load(getClass().getResource("start.fxml"));
		tmp = new FXMLLoader(getClass().getResource("user.fxml"));
		content[1] = (Parent) tmp.load();
		userCtrl = tmp.getController();
		tmp = new FXMLLoader(getClass().getResource("pass.fxml"));
		content[2] = (Parent) tmp.load();
		passCtrl = tmp.getController();
		content[3] = FXMLLoader.load(getClass().getResource("end.fxml"));

		lblMessage = new String[2][n];
		lblMessage[0] = new String[] { "< Exit", "< Back", "< Back", "< Back" };
		lblMessage[1] = new String[] { "Next >", "Next >", "Finish >", "Exit >" };

		EventHandler<ActionEvent> rightHnd = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				transitionNext();
			}
		};

		EventHandler<ActionEvent> leftHnd = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				transitionBack();
			}
		};

		EventHandler<ActionEvent> exitHnd = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (listener != null)
					listener.onClose();
			}
		};

		EventHandler<ActionEvent> loginHnd = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (listener != null)
					listener.onLogin(userCtrl.userTxt.getText(), passCtrl.passTxt.getText());

			}
		};

		EventHandler<ActionEvent> userHnd = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (listener != null)
					listener.onUsername(userCtrl.userTxt.getText());

			}
		};

		leftHandlers = new ArrayList<>();
		leftHandlers.add(exitHnd);
		leftHandlers.add(leftHnd);
		leftHandlers.add(leftHnd);
		leftHandlers.add(leftHnd);

		rightHandlers = new ArrayList<>();
		rightHandlers.add(rightHnd);
		rightHandlers.add(userHnd);
		rightHandlers.add(loginHnd);
		rightHandlers.add(exitHnd);

		EventHandler<KeyEvent> enterNext = new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					controller.rightBtn.fire();
				}
			}
		};

		userCtrl.userTxt.setOnKeyPressed(enterNext);
		passCtrl.passTxt.setOnKeyPressed(enterNext);

		setScreen(0);
	}

	public void transitionNext() {
		setScreen(currentScreen);
		try {
			controller.slider.isAnimatingProperty().addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					if (newValue == false) {
						setScreen((currentScreen + 1) % n);
						controller.slider.isAnimatingProperty().removeListener(this);
					}
				}
			});
			controller.slider.transition(-1);
		} catch (OperationNotSupportedException e) {
			e.printStackTrace();
		}
	}

	public void transitionBack() {
		setScreen(currentScreen);
		try {
			controller.slider.isAnimatingProperty().addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					if (newValue == false) {
						setScreen((currentScreen - 1 + n) % n);
						controller.slider.isAnimatingProperty().removeListener(this);
					}
				}
			});
			controller.slider.transition(1);
		} catch (OperationNotSupportedException e) {
			e.printStackTrace();
		}
	}

	public void setScreen(int idx) {
		controller.leftBtn.setText(lblMessage[0][idx]);
		controller.rightBtn.setText(lblMessage[1][idx]);
		controller.leftBtn.setOnAction(leftHandlers.get(idx));
		controller.rightBtn.setOnAction(rightHandlers.get(idx));
		controller.slider.setCurrentContent(content[idx]);
		controller.slider.setLeftContent(content[(idx - 1 + n) % n]);
		controller.slider.setRightContent(content[(idx + 1) % n]);
		currentScreen = idx;
	}

	public void setListener(LoginScreenListener listener) {
		this.listener = listener;
	}
}

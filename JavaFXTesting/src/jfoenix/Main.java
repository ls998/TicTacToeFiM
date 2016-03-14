package jfoenix;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import jfoenix.LoginScreen.LoginScreenListener;
import loginscreentest.CharacterMgr;
import loginscreentest.PasswordMgr;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			LoginScreen scene = new LoginScreen();
			scene.setListener(new LoginScreenListener() {

				@Override
				public void onLogin(String user, String pass) {
					int id = CharacterMgr.getUser(user);
					long token = PasswordMgr.getToken(id, pass);
					if (token == -1) {
						scene.passCtrl.invalidAnim();
						return;
					}
					scene.transitionNext();
				}

				@Override
				public void onClose() {
					scene.controller.loadingProperty.set(true);
					KeyFrame k = new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							scene.controller.loadingProperty.set(false);
							System.exit(0);
						}
					});
					Timeline t = new Timeline(k);
					t.play();
				}

				@Override
				public void onUsername(String user) {
					int id = CharacterMgr.getUser(user);
					if (id == -1) {
						scene.userCtrl.invalidAnim();
						return;
					}
					scene.transitionNext();
				}
			});
			primaryStage.setScene(scene);

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		launch(args);
	}
}

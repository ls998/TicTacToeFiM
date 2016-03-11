package loginscreentest;

import java.io.IOException;

import insidefx.undecorator.Undecorator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.initStyle(StageStyle.UNDECORATED);
			FXMLLoader myloader = new FXMLLoader(getClass().getResource("login.fxml"));

			Parent root = myloader.load();

			Undecorator undecorator = new Undecorator(primaryStage, (Region) root);
			undecorator.getStylesheets().add("skin/undecorator.css");

			LoginController controller1 = myloader.getController();
			controller1.setListener(new LoginController.LoginControllerListener() {
				@Override
				public void onSubmit(String user, String pass) {
					long token = PasswordMgr.getToken(CharacterMgr.getUser(user), pass);
					if (token == -1) {
						controller1.incorrectAnimation();
					} else {
						javafx.application.Platform.runLater(new Runnable() {
							public void run() {
								FXMLLoader myloader1 = new FXMLLoader(getClass().getResource("welcome.fxml"));
								try {
									Parent root2 = myloader1.load();

									Undecorator undecorator2 = new Undecorator(primaryStage, (Region) root2);
									undecorator2.getStylesheets().add("loginscreentest/derp.css");
									undecorator2.getStylesheets().add("skin/undecorator.css");
//									undecorator2.setFadeInTransition();
									
									Scene scene2 = new Scene(undecorator2);
									scene2.getStylesheets()
											.add(getClass().getResource("application.css").toExternalForm());
									primaryStage.setScene(scene2);
									scene2.setFill(Color.TRANSPARENT);
									WelcomeController controller2 = myloader1.getController();
									controller2.set(user, CharacterMgr.getImage(CharacterMgr.getUser(user)));

								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});

					}
				}
			});

			Scene scene = new Scene(undecorator);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			  scene.setFill(Color.TRANSPARENT);
		        primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}

package loginscreentest;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WelcomeController {

	@FXML
	private Label welcomeLbl;

	@FXML
	private ImageView picImg;

	public void set(String name, URL pic) {

		welcomeLbl.setText("Welcome, " + name);
		try {
			picImg.setImage(new Image(pic.openStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

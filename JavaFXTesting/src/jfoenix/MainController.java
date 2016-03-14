package jfoenix;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import sparklecode.ScreenSlider;

public class MainController implements Initializable {

	@FXML
	private JFXProgressBar progbar;

	@FXML
	public JFXButton leftBtn;
	@FXML
	public JFXButton rightBtn;

	@FXML
	public ScreenSlider slider;

	@FXML
	private Pane screen;

	public final BooleanProperty loadingProperty;

	public MainController() {
		loadingProperty = new SimpleBooleanProperty();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		progbar.prefWidthProperty().bind(slider.widthProperty().add(10));
		leftBtn.disableProperty().bind(slider.isAnimatingProperty().or(loadingProperty));
		rightBtn.disableProperty().bind(slider.isAnimatingProperty().or(loadingProperty));

//		 screen.visibleProperty().bind(slider.isAnimatingProperty().or(loadingProperty));
		slider.isAnimatingProperty().or(loadingProperty).addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				System.out.println(newValue);
			}

		});
		loadingProperty.set(false);
	}

	// public void setLeftBtnText(String text) {
	// leftBtn.setText(text);
	// }
	//
	// public BooleanProperty rightBtnDisableProperty() {
	// return rightBtn.disableProperty();
	// }
	//
	// public BooleanProperty leftBtnDisableProperty() {
	// return leftBtn.disableProperty();
	// }
	//
	// public void setLeftBtnActionEvent(EventHandler<ActionEvent> handler) {
	// leftBtn.setOnAction(handler);
	// }
	//
	// public void setRightBtnText(String text) {
	// rightBtn.setText(text);
	// }
	//
	// public void setRightBtnActionEvent(EventHandler<ActionEvent> handler) {
	// rightBtn.setOnAction(handler);
	// }
}

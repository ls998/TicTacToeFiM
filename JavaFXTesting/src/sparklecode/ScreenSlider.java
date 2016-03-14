package sparklecode;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.OperationNotSupportedException;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class ScreenSlider extends StackPane implements Initializable {
	public Duration animationTime = Duration.millis(200);

	@FXML
	private AnchorPane leftPane;
	@FXML
	private AnchorPane currentPane;
	@FXML
	private AnchorPane rightPane;

	private BooleanProperty animating;

	public ScreenSlider() throws IOException {
		animating = new SimpleBooleanProperty();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("slider.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		fxmlLoader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setAsLeft(leftPane);
		setAsRight(rightPane);
		setAsCurrent(currentPane);
	}

	public void setRightContent(Parent content) {
		setContent(rightPane, content);
	}

	public void setCurrentContent(Parent content) {
		setContent(currentPane, content);
	}

	public void setLeftContent(Parent content) {
		setContent(leftPane, content);
	}

	public ReadOnlyBooleanProperty isAnimatingProperty() {
		return ReadOnlyBooleanProperty.readOnlyBooleanProperty(animating);
	}

	public boolean isAnimating() {
		return animating.get();
	}

	// / -1 = left
	// // 1 = right
	public void transition(int direction) throws OperationNotSupportedException {
		if (animating.get())
			throw new OperationNotSupportedException("Already animating");

		if (direction == -1)
			rightPane.setVisible(true);
		else
			leftPane.setVisible(true);

		double width = currentPane.getWidth();

		KeyValue current = new KeyValue(currentPane.translateXProperty(), width * direction);

		EventHandler<ActionEvent> onFin = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (direction == -1) {
					AnchorPane tmp = currentPane;
					setAsCurrent(rightPane);
					setAsRight(leftPane);
					setAsLeft(tmp);
					animating.set(false);
				} else {
					AnchorPane tmp = currentPane;
					setAsCurrent(leftPane);
					setAsLeft(rightPane);
					setAsRight(tmp);
					animating.set(false);
				}
			}

		};

		KeyFrame end = new KeyFrame(animationTime, onFin, current);

		animating.set(true);
		new Timeline(end).play();
	}

	private void setContent(AnchorPane pane, Parent content) {
		pane.getChildren().clear();
		pane.getChildren().add(content);
		AnchorPane.setTopAnchor(content, 0.0);
		AnchorPane.setBottomAnchor(content, 0.0);
		AnchorPane.setLeftAnchor(content, 0.0);
		AnchorPane.setRightAnchor(content, 0.0);
	}

	private void setAsLeft(AnchorPane pane) {
		pane.setVisible(false);
		pane.translateXProperty().unbind();
		pane.translateXProperty().bind(currentPane.widthProperty().multiply(-1).add(currentPane.translateXProperty()));
		leftPane = pane;
	}

	private void setAsRight(AnchorPane pane) {
		pane.setVisible(false);
		pane.translateXProperty().unbind();
		pane.translateXProperty().bind(currentPane.widthProperty().add(currentPane.translateXProperty()));
		rightPane = pane;
	}

	private void setAsCurrent(AnchorPane pane) {
		pane.setVisible(true);
		pane.translateXProperty().unbind();
		pane.setTranslateX(0);
		currentPane = pane;
	}
}

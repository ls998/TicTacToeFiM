package friendshipframework.ui;

public interface IScreen {
	public interface ScreenClosedObserver {
		public void onClosed(IScreen source);
	}

	public void setCloseListener(ScreenClosedObserver listener);

	public void open();

	public void close();
}

package epam.rest.utenkov.bo;

public class Widgets {
	private String[] widgetPosition;

	private String[] widgetSize;

	private String widgetId;

	public String[] getWidgetPosition() {
		return widgetPosition;
	}

	public void setWidgetPosition(String[] widgetPosition) {
		this.widgetPosition = widgetPosition;
	}

	public String[] getWidgetSize() {
		return widgetSize;
	}

	public void setWidgetSize(String[] widgetSize) {
		this.widgetSize = widgetSize;
	}

	public String getWidgetId() {
		return widgetId;
	}

	public void setWidgetId(String widgetId) {
		this.widgetId = widgetId;
	}

	@Override
	public String toString() {
		return "Widgets [widgetPosition = " + widgetPosition + ", widgetSize = " + widgetSize + ", widgetId = "
		        + widgetId + "]";
	}

}

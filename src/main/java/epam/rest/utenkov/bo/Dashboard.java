package epam.rest.utenkov.bo;

public class Dashboard {
	private String name;
	private String owner;
	private boolean share;
	private Widgets [] widgets;
	
	public Dashboard(String name, String owner, boolean share, Widgets[] widgets) {
		super();
		this.name = name;
		this.owner = owner;
		this.share = share;
		this.widgets = widgets;
	}

	public Widgets[] getWidgets() {
		return widgets;
	}

	public void setWidgets(Widgets[] widgets) {
		this.widgets = widgets;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String discription) {
		this.owner = discription;
	}

	public boolean isShare() {
		return share;
	}

	public void setShare(boolean share) {
		this.share = share;
	}
	
	
	
}

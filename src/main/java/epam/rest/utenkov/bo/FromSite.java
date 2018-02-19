package epam.rest.utenkov.bo;

public class FromSite {
	    private String id;
	    private Widgets[] widgets;
	    private String share;
	    private String name;
	    private String owner;
	    public String getId ()
	    {
	        return id;
	    }

	    public void setId (String id)
	    {
	        this.id = id;
	    }

	    public Widgets[] getWidgets ()
	    {
	        return widgets;
	    }

	    public void setWidgets (Widgets[] widgets)
	    {
	        this.widgets = widgets;
	    }

	    public String getShare ()
	    {
	        return share;
	    }

	    public void setShare (String share)
	    {
	        this.share = share;
	    }

	    public String getName ()
	    {
	        return name;
	    }

	    public void setName (String name)
	    {
	        this.name = name;
	    }

	    public String getOwner ()
	    {
	        return owner;
	    }

	    public void setOwner (String owner)
	    {
	        this.owner = owner;
	    }

	    @Override
	    public String toString()
	    {
	        return "[id = "+id+", widgets = "+widgets+", share = "+share+", name = "+name+", owner = "+owner+"]";
	    }
	
}

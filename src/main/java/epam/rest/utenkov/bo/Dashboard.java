package epam.rest.utenkov.bo;

public class Dashboard {
	
	private String share;
    private String description;
    private String name;
    
    

	public Dashboard(String name, String description, String share) {
		this.share = share;
		this.description = description;
		this.name = name;
	}

	public String getShare (){
        return share;
    }

    public void setShare (String share){
        this.share = share;
    }

    public String getDescription (){
        return description;
    }

    public void setDescription (String description){
        this.description = description;
    }

    public String getName (){
        return name;
    }

    public void setName (String name){
        this.name = name;
    }
    
    @Override
    public String toString() {
    	
    	return "Dushboard name: " + name + "; Description: " + description + "; is share: " + share;
    }
}

package epam.rest.utenkov.bo;

import java.util.HashMap;
import java.util.Map;

public class Launch {
	private String owner;
	private String description;
	private String id;
	private String name;
	private String status;
	private Map<String, String> executions;

	public Launch(String owner, String description, String id, String name, String status, HashMap executions) {
		super();
		this.owner = owner;
		this.description = description;
		this.id = id;
		this.name = name;
		this.status = status;
		this.setExecutions(executions);
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Map<String, String> getExecutions() {
		return executions;
	}

	public void setExecutions(Map<String, String> executions) {
		this.executions = executions;
	}

	@Override
	public String toString() {
		return "Launch [owner = " + owner + ", description = " + description + ", id = " + id + ", name = " + name
		        + ", status = "
		        + status + ",\n executions = " + executions + "]";
	}

}

package bosk.ovelser.ovelse7.ovelse73;

public enum StatusCode {
	HTTP400("400 Bad Request."), HTTP401("401 Unauthorized."), HTTP402("402 Payment Required."), HTTP403("403 Forbidden"), HTTP404("404 Not Found."), HTTP405("405 Method Not Allowed."), HTTP406("406 Not acceptable.");
	private String description;
	private StatusCode(String d){
		description = d;
	}
	public String getError(){
		return description;
	}
	public String toString(){
		return description;
	}
}

package com.trung.request;

public class RequestLine {
    private String method;
    private String endpoint;
    private String version; 
    private boolean isValid;

    public RequestLine(String content) {
        if (content != null && Models.isRequestLineValid(content)) {
            this.isValid = true;

            String[] line_split = content.split(" ");

            this.method = line_split[0];
            this.endpoint = line_split[1];
            this.version = line_split[2];
        }  
    }

	@Override
    public String toString() {
        return String.format(
            "%s %s %s",
            this.method, this.endpoint, this.version
        );
    }

	public String getMethod() {
		return method;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public String getVersion() {
		return version;
	}

    public boolean isValid() {
        return this.isValid;
    }
}
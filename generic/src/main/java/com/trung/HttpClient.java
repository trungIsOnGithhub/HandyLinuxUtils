public class HttpClient {
    private final int PORT;
    private final string HOSTNAME;

    private BufferedReader inBuffer;
    private PrintWriter outWriter;
    private Socket socket;
    private Logger logger;

	public MyHttpClient(String hostName, int portNumber)
                                throws IOException {
        this.PORT = portNumber;
		this.HOST_NAME = hostName;

		this.inBuffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.outWriter = new PrintWriter(socket.getOutputStream(),true);	

        this.socket = new Socket(this.HOST_NAME, this.PORT);
		
		this.logger = new Logger();
	}

	public void getResource(String ObjectName) throws IOException {
		this.logger.info("Making GET request to server");
		out.print(
			String.format(
				"GET /%s HTTP/1.1%sHost: %s%s",
				ObjectName, Util.CRLF, this.HOST_NAME, Util.CRLF
			)
		);
		out.flush();
		this.logger.answer(in);
	}

	public void postData(String[] data) throws IOException {
		this.logger.info("Making POST request to server");
		out.print(
			String.format(
				"POST /simpleForm.html HTTP/1.1%sHost: %s%s%s%s",
				Util.CRLF, this.HOST_NAME, Util.CRLF, Util.CRLF, String.format("{%s,%s}", data[0], data[1])
			)
		);
		out.flush();
		this.logger.answer(in);
	}

	public void malformedRequest(int type) throws IOException {
		switch (type) {
			case 1:
				this.logger.info("Making a malformed request of the i type to server");
				out.print("GET /index.html HTTP/1.1");
				break;
			case 2: 
				this.logger.info("Making a malformed request of the ii type to server");
				out.print("GET  /index.html  HTTP/1.1  " + Util.CRLF);	
				break;
			case 3: 
				this.logger.info("Making a malformed request of the iii type to server");
				out.print("GET /index.html" + Util.CRLF);
				break;
			}
		out.flush();
		this.logger.answer(in);
	}

	public void close() {
		try {
			out.print("DELETE /session HTTP/1.1" + Util.CRLF + "Connection: close" + Util.CRLF);
			out.flush();
			this.in.close();
			this.out.close();
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}    
}
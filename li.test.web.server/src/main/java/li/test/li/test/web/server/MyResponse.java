package li.test.li.test.web.server;

import java.io.IOException;
import java.io.OutputStream;

public class MyResponse {
    private OutputStream output;
    private final String defaultHello = "<h1>Hello, this is a web server of LI</h1>";
    private final String closeMessage = "<h1>Successfully close the server</h1>";

    public MyResponse(OutputStream output) {
        this.output = output;
    }

    public void sendDefaultResponse() {
        String errorMessage = "HTTP/1.1 200 Ok\r\n"
                + "Content-Type: text/html\r\n" + "Content-Length: "
                + defaultHello.length() + "\r\n" + "\r\n" + defaultHello;

        try {
            output.write(errorMessage.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendCloseResponse() {
        String errorMessage = "HTTP/1.1 200 Ok\r\n"
                + "Content-Type: text/html\r\n" + "Content-Length: "
                + closeMessage.length() + "\r\n" + "\r\n" + closeMessage;

        try {
            output.write(errorMessage.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

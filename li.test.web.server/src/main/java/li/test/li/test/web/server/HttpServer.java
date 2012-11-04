package li.test.li.test.web.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpServer {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final int port = 8080;
    private final String address = "127.0.0.1";

    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.await();
    }

    public void await() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port, 1,
                    InetAddress.getByName(address));
            logger.info("the server start...");
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean flag = true;
        while (flag) {
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;

            try {
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                // create MyRequest object and parse
                MyRequest request = new MyRequest(input);
                request.parse();

                if (request.getUri().equals("/close")) {
                    flag = false;
                }

                // create MyResponse object
                MyResponse response = new MyResponse(output);
                if (flag) {
                    response.sendDefaultResponse();
                } else {
                    response.sendCloseResponse();
                    logger.info("the server is closed...");
                }

                // close the socket
                socket.close();
            } catch (Exception e) {

            }
        }
    }
}

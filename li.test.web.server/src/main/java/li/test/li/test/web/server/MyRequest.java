package li.test.li.test.web.server;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyRequest {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private InputStream inputStream;
    private String uri;

    public MyRequest(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void parse() {
        StringBuffer request = new StringBuffer();
        int i;
        byte[] buffer = new byte[2048];
        try {
            i = inputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            i = -1;
        }
        for (int j = 0; j < i; j++) {
            request.append((char) buffer[j]);
        }
        String requestString = request.toString();
        logger.info("the request information:\n" + requestString);
        this.uri = parseUri(requestString);

    }

    private String parseUri(String request) {
        int index1, index2 = -1;
        index1 = request.indexOf(" ");
        if (index1 != -1) {
            index2 = request.indexOf(" ", index1 + 1);
        }
        if (index2 > index1) {
            // use index + 1 to eliminate the space
            String innerUri = request.substring(index1 + 1, index2);
            return innerUri;
        }

        return null;
    }

    public String getUri() {
        return this.uri;
    }
}

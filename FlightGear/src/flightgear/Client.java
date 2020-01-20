package flightgear;

import java.io.IOException;

public interface Client {
    void runClient();
    void writeClient(String data);
    void stopClient() throws IOException;
}

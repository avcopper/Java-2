package lesson6;

import java.io.IOException;

public class ClientLauncher {
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        System.out.println("=== Client started. Connecting to localhost: 1234 ===");

        // В отдельном потоке производим чтение входящего потока и выводим его в консоль
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String message = client.in.readLine();

                        if (message != null) {
                            if (message.equalsIgnoreCase("exit") || message.equalsIgnoreCase("quit")) {
                                System.out.println("\n === Server is disconnected ===");
                                client.close();
                            }

                            client.writeToConsole(message);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();

        // В отдельном потоке производим чтение консоли и отправлем его в исходящий поток на сервер
        new Thread() {
            @Override
            public void run() {
                try {
                    client.sendToServer();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}

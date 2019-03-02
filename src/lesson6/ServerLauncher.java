package lesson6;

import java.io.IOException;

public class ServerLauncher {
    public static void main(String[] args) throws IOException {
        Server server = new Server();

        // В отдельном потоке производим чтение входящего потока и выводим его в консоль
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String message = server.in.readLine();

                        if (message != null) {
                            if (message.equalsIgnoreCase("exit") || message.equalsIgnoreCase("quit"))
                            {
                                System.out.println("\n === lesson6.Client is disconnected ===");
                                Thread.sleep(2000);
                                server.close();
                            }
                            server.writeToConsole(message);
                        }

                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        // В отдельном потоке производим чтение консоли и отправлем его в исходящий поток клиенту
        new Thread() {
            @Override
            public void run() {
                try {
                    server.sendToClient();

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}

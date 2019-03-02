package lesson6;

import java.io.*;
import java.net.*;

class Client {
    private Socket socket;
    BufferedReader in;
    private PrintWriter out;
    private BufferedReader console;

    public Client() throws IOException {
        socket = new Socket("localhost",1234);
        in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(),true);
        console = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Отправляем сообщение на сервер. Закрываем приложение по ключевым словам
     * @throws IOException
     */
    void sendToServer() throws IOException {
        String userMessage;

        while (true) {
            if ((userMessage = console.readLine()) != null) {
                out.println(userMessage);

                if (userMessage.equalsIgnoreCase("exit") || userMessage.equalsIgnoreCase("quit")) {
                    System.out.println("\n === Exiting... ===");
                    close();
                }
            }
        }
    }

    /**
     * Выводим в консоль клиента сообщение сервера
     * @param msg - сообщение
     */
    void writeToConsole(String msg) {
        System.out.println("Server: " + msg);
    }

    /**
     * Закрываем приложение
     * @throws IOException
     */
    void close() throws IOException {
        out.close();
        in.close();
        console.close();
        socket.close();
        System.exit(0);
    }
}

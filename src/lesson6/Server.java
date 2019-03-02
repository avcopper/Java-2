package lesson6;

import java.io.*;
import java.net.*;

class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    BufferedReader in;
    private PrintWriter out;

    public Server()
    {
        try {
            serverSocket = new ServerSocket(1234);
        } catch (IOException e) {
            System.out.println("Can't open port 1234");
            System.exit(1);
        }
        System.out.println("=== Server started. Waiting for a client... ===");

        try {
            socket = serverSocket.accept();
            System.out.println("=== Client connected ===");

            in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
        } catch (IOException e) {
            System.out.println("=== Some problems during accepting client ===");
            System.exit(1);
        }

        System.out.println("=== Wait for messages... === \n");
    }

    /**
     * Отправляем сообщение клиенту. Закрываем приложение по ключевым словам
     * @throws IOException
     */
    void sendToClient() throws IOException, InterruptedException {
        while (true) {
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            String msg = console.readLine();
            out.println(msg);

            if (msg.equalsIgnoreCase("exit") || msg.equalsIgnoreCase("quit")) {
                System.out.println("\n === Exiting... ===");
                Thread.sleep(2000);
                close();
            }
        }
    }

    /**
     * Выводим в консоль сервера сообщение клиента
     * @param msg - сообщение
     */
    void writeToConsole(String msg) {
        System.out.println("lesson6.Client: " + msg);
    }

    /**
     * Закрываем приложение
     * @throws IOException
     */
    void close() throws IOException {
        out.close();
        in.close();
        socket.close();
        serverSocket.close();
        System.exit(0);
    }
}

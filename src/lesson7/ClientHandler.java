package lesson7;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private Server server;
    private Socket clientSocket;
    private Scanner in;
    private PrintWriter out;
    private String name;
    private List<ClientHandler> users;
    private static int clientCount = 0;

    public ClientHandler(Server server, Socket socket) {
        try {
            clientCount++;
            this.server = server;
            this.clientSocket = socket;
            this.in = new Scanner(clientSocket.getInputStream());
            this.out = new PrintWriter(clientSocket.getOutputStream());
            this.users = server.getClients();
        } catch (IOException e) {
            throw new RuntimeException( "Problems during client connection" );
        }
    }

    @Override
    public void run() {
        try {
            server.broadcastMessage("New client in our chat");
            server.broadcastMessage("In our chat = " + clientCount + "clients!");

            while (true) {
                if (in.hasNext()) {
                    String message = in.nextLine();
                    if (message.equalsIgnoreCase("exit") || message.equalsIgnoreCase("quit")) {
                        break;
                    }

                    if (message.startsWith("/")) {
                        for (ClientHandler user : users) {
                            if (message.startsWith("/" + user.getName())) {
                                String[] msg = message.split(" ", 2);
                                user.sendMessage(msg[1]);
                            }
                        }
                    } else {
                        System.out.println(message);
                        server.broadcastMessage(message);
                    }
                }
            }

            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            exit();
        }
    }

    public String getName() {
        return name;
    }

    /**
     * Выход из чата
     */
    private void exit() {
        clientCount--;
        server.broadcastMessage("Client exited. In out chat = " + clientCount + " clients!");
        server.unsubscribe(this);
    }

    /**
     * Аутентификация
     */
    public void authentication() {
        while ( true ) {

            if (in.hasNext()) {
                String message = in.nextLine();

                if (message.startsWith("/auth")) {
                    String[] parts = message.split( "\\s" );
                    String nick = server.getAuthService().getNickByLoginPass(parts[1], parts[2]);

                    if (nick != null ) {

                        if (!server.isNickBusy(nick)) {
                            sendMessage("/authok " + nick);
                            name = nick;
                            server.broadcastMessage(name + " зашел в чат");
                            server.subscribe(this);
                            break;
                        } else {
                            sendMessage("Учетная запись уже используется");
                        }
                    } else {
                        sendMessage("Неверные логин/пароль");
                    }
                }
            }
        }
    }

    /**
     * Отправка сообщения
     * @param msg - сообщение
     */
    public void sendMessage(String msg) {
        try {
            out.println(msg);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

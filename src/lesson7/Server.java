package lesson7;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private List<ClientHandler> clients = new ArrayList<>();;
    private AuthService authService;

    public Server() {
        ServerSocket server = null;
        Socket clientSocket = null;

        try {
            server = new ServerSocket(1234);
            System.out.println("lesson7.Server launched");

            authService = new BaseAuthService();
            authService.start();

            while(true) {
                System.out.println("lesson7.Server is waiting connections");
                clientSocket = server.accept();
                System.out.println("Client connected");

                ClientHandler client = new ClientHandler(this, clientSocket);

                client.authentication();

                clients.add(client);
                new Thread(client).start();
            }
        } catch(IOException e) {
            System.out.println("Ошибка в работе сервера");
            e.printStackTrace();
        } finally {
            if (authService != null ) {
                authService.stop();
            }

            try
            {
                server.close();
                clientSocket.close();
                System.out.println("lesson7.Server finished");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public List<ClientHandler> getClients() {
        return clients;
    }

    public AuthService getAuthService() {
        return authService;
    }

    /**
     * Проверка нет ли в чате клиента с таким ником
     * @param nick - ник
     * @return
     */
    public synchronized boolean isNickBusy(String nick) {
        for (ClientHandler client : clients) {
            if (client.getName().equals(nick)) {
                return true ;
            }
        }
        return false ;
    }

    /**
     * Сообщение всем
     * @param msg - сообщение
     */
    public synchronized void broadcastMessage(String msg) {
        for (ClientHandler client : clients) {
            client.sendMessage(msg);
        }
    }

    /**
     * Вход в чат
     * @param client - клиент
     */
    public synchronized void subscribe(ClientHandler client) {
        clients.add(client);
    }

    /**
     * Выход из чата
     * @param client - клиент
     */
    public synchronized void unsubscribe(ClientHandler client) {
        clients.remove(client);
    }
}

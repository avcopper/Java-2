package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server
{
  private List<ClientHandler> clientHandlers = new ArrayList<>();

  public Server()
  {
    ServerSocket serverSocket = null;
    Socket clientSocket = null;

    try {
      serverSocket = new ServerSocket(8888);
      System.out.println("Server launched");

      while (true) {
        clientSocket = serverSocket.accept();
        ClientHandler clientHandler = new ClientHandler(clientSocket, this);

        clientHandlers.add(clientHandler);
        new Thread(clientHandler).start();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        serverSocket.close();
        clientSocket.close();
        System.out.println("Server finished");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Send message from server to all clients
   * @param msg - message
   */
  public void notificationAllClientWithNewMessage(String msg)
  {
    for (ClientHandler clientHandler : clientHandlers) {
      clientHandler.sendMessage(msg);
    }
  }

  /**
   * Remove client frome chat
   * @param clientHandler - client
   */
  public void removeClient(ClientHandler clientHandler)
  {
    clientHandlers.remove(clientHandler);
  }
}

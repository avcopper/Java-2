package server;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable
{
  private Socket clientSocket;
  private Server server;
  private PrintWriter out;
  private Scanner in;
  private static int clientCount = 0;

  public ClientHandler(Socket clientSocket, Server server)
  {
    try {
      clientCount++;
      this.server = server;
      this.clientSocket = clientSocket;
      this.out = new PrintWriter(clientSocket.getOutputStream());
      this.in = new Scanner(clientSocket.getInputStream());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run()
  {
    try {
      server.notificationAllClientWithNewMessage("=== New client in our chat ===");
      server.notificationAllClientWithNewMessage("Counts of clients in chat:" + clientCount);

      while (true) {
        if (in.hasNext()) {
          String clientMsg = in.nextLine();

          if (clientMsg.equalsIgnoreCase("quit")) {
            break;
          }
          System.out.println(clientMsg);
          server.notificationAllClientWithNewMessage(clientMsg);
        }
      }
      Thread.sleep(1000);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      exitFromChat();
    }
  }

  /**
   * Exit from chat
   */
  private void exitFromChat()
  {
    clientCount--;
    server.notificationAllClientWithNewMessage("=== Client exited ===");
    server.notificationAllClientWithNewMessage("Counts of clients in chat:" + clientCount);
    server.removeClient(this);
  }

  /**
   * Send message
   * @param msg - message
   */
  public void sendMessage(String msg)
  {
    try {
      out.println(msg);
      out.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

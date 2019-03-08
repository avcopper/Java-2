package client;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.*;

public class Client extends JFrame
{
  private final String SERVER_HOST = "localhost";
  private final int SERVER_PORT = 8888;
  private Socket clientSocket;
  private Scanner in;
  private PrintWriter out;
  private JTextField fieldMessage;
  private JTextField fieldName;
  private JTextArea chatArea;

  public Client() throws HeadlessException
  {
    try {
      clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
      in = new Scanner(clientSocket.getInputStream());
      out = new PrintWriter(clientSocket.getOutputStream());
    } catch (Exception e) {
      e.printStackTrace();
    }

    // main window
    setBounds(500, 300, 800, 500);
    setTitle("Chat");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    // chat field
    chatArea = new JTextArea();
    chatArea.setEditable(false);
    chatArea.setLineWrap(true);

    // chat area
    JScrollPane jScrollPane = new JScrollPane(chatArea);
    add(jScrollPane, BorderLayout.CENTER);

    // top panel with client count
    JPanel topPanel = new JPanel();
    topPanel.setBounds(0,0,300,10);
    add(topPanel, BorderLayout.NORTH);

    // count of clients
    JLabel labelCountOfClient = new JLabel("Count of client in chat: ");
    topPanel.add(labelCountOfClient);

    JLabel countsOfClient = new JLabel("");
    topPanel.add(countsOfClient);

    // bottom panel with name, message & button
    JPanel bottomPanel = new JPanel(new BorderLayout());
    add(bottomPanel, BorderLayout.SOUTH);

    // name
    fieldName = new JTextField("Enter your name");
    bottomPanel.add(fieldName, BorderLayout.WEST);

    // message
    fieldMessage = new JTextField("Please input your message");
    bottomPanel.add(fieldMessage, BorderLayout.CENTER);

    // button
    JButton sendButton = new JButton("SEND");
    bottomPanel.add(sendButton, BorderLayout.EAST);

    // action send message
    sendButton.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        String msg = fieldMessage.getText().trim();
        String name = fieldName.getText().trim();

        if (!msg.isEmpty() && !name.isEmpty()) {
          sendMsg();
          fieldMessage.grabFocus();
        }
      }
    });

    //action press enter
    fieldMessage.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        String msg = fieldMessage.getText().trim();
        String name = fieldName.getText().trim();

        if (!msg.isEmpty() && !name.isEmpty()) {
          sendMsg();
          fieldMessage.grabFocus();
        }
      }
    });

    fieldMessage.addFocusListener(new FocusAdapter()
    {
      @Override
      public void focusGained(FocusEvent e)
      {
        fieldMessage.setText("");
      }
    });

    fieldName.addFocusListener(new FocusAdapter()
    {
      @Override
      public void focusGained(FocusEvent e)
      {
        fieldName.setText("");
      }
    });

    // action closing window
    addWindowListener(new WindowAdapter()
    {
      @Override
      public void windowClosing(WindowEvent e)
      {
        super.windowClosing(e);
        String clientName = fieldName.getText();

        if (!clientName.isEmpty() && !clientName.equalsIgnoreCase("Your name")) {
          out.println(clientName + " exited from chat.");
        } else {
          out.println("Anonim client exited from our chat");
        }

        out.println("quit");
        out.flush();
        out.close();
        in.close();

        try {
          clientSocket.close();
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
    });

    // starts new thread
    new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        while (true) {
          if (in.hasNext()) {
            String msg = in.nextLine();
            String clientInChat = "Counts of clients in chat:";
            if (msg.indexOf(clientInChat) == 0) {
              String[] message = msg.split(":", 2);
              countsOfClient.setText(message[1]);
            } else {
              chatArea.append(msg);
              chatArea.append("\n");
            }
          }
        }
      }
    }).start();

    setVisible(true);
  }

  /**
   * Send message from server to clients
   */
  private void sendMsg()
  {
    String name = fieldName.getText().trim();
    String message = (name.equalsIgnoreCase("Enter your name") ? "Anonim" : name) + ": " + fieldMessage.getText().trim();
    out.println(message);
    out.flush();
    fieldMessage.setText("");
  }
}

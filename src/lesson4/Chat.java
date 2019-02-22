package lesson4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chat extends JFrame {
    public Chat () {
        // окно программы
        setTitle ( "Chat" );
        setDefaultCloseOperation ( WindowConstants . EXIT_ON_CLOSE );
        setBounds ( 300 , 300 , 600 , 600 );

        // разделение окна на 2 панели
        JPanel panelTop = new JPanel();
        JPanel panelBottom = new JPanel();
        setLayout(new BorderLayout());
        add(panelTop, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);

        // в верхней панели размещено многострочное текстовое поле только для чтения, помещенное внутрь скрол-панели
        panelTop.setLayout(new BorderLayout());
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panelTop.add(scrollPane);

        // в нижней панели размещено текстовое поле и кнопка отправки сообщения
        panelBottom.setLayout(new GridLayout(2 , 1));

        JTextField textField = new JTextField();
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendMessage(textField, textArea);
            }
        });

        JButton button = new JButton("Send");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendMessage(textField, textArea);
            }
        });

        panelBottom.add(textField);
        panelBottom.add(button);

        setVisible ( true );

        textField.requestFocusInWindow();
    }

    /**
     * Получаем текст из однострочного поля, очищает это поле и отправляем текст в многострочное поле
     * @param textField - однострочное поле
     * @param textArea - многострочное поле
     */
    private void SendMessage(JTextField textField, JTextArea textArea) {
        String text = textArea.getText();
        String newText = text.equals("") ? (textField.getText()) : (text + "\n" +  textField.getText());
        textArea.setText(newText);
        textField.setText("");
    }
}

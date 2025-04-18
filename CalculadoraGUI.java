import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculadoraGUI extends JFrame implements ActionListener {

    private JTextField tela;
    private double num1 = 0, num2 = 0;
    private String operador = "";
    private boolean novoNumero = true;

    public CalculadoraGUI() {
        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centraliza a janela

        // Campo de exibição
        tela = new JTextField();
        tela.setFont(new Font("Arial", Font.PLAIN, 24));
        tela.setEditable(false);
        tela.setHorizontalAlignment(SwingConstants.RIGHT);
        add(tela, BorderLayout.NORTH);

        // Painel de botões
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(5, 4, 5, 5));

        String[] botoes = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C"
        };

        for (String texto : botoes) {
            JButton botao = new JButton(texto);
            botao.setFont(new Font("Arial", Font.BOLD, 20));
            botao.addActionListener(this);
            painel.add(botao);
        }

        add(painel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String botao = e.getActionCommand();

        if (botao.matches("[0-9.]")) {
            if (novoNumero) {
                tela.setText(botao);
                novoNumero = false;
            } else {
                tela.setText(tela.getText() + botao);
            }
        } else if (botao.matches("[+\\-*/]")) {
            num1 = Double.parseDouble(tela.getText());
            operador = botao;
            novoNumero = true;
        } else if (botao.equals("=")) {
            num2 = Double.parseDouble(tela.getText());
            double resultado = 0;

            switch (operador) {
                case "+":
                    resultado = num1 + num2;
                    break;
                case "-":
                    resultado = num1 - num2;
                    break;
                case "*":
                    resultado = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        tela.setText("Erro");
                        return;
                    }
                    resultado = num1 / num2;
                    break;
            }            

            tela.setText(String.valueOf(resultado));
            novoNumero = true;
        } else if (botao.equals("C")) {
            tela.setText("");
            operador = "";
            num1 = num2 = 0;
            novoNumero = true;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalculadoraGUI().setVisible(true);
        });
    }
}

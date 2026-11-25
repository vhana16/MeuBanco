package br.gov.sp.cps.TelaBanco;

import javax.swing.*;

public class MeuBanco extends JFrame {

	private static final long serialVersionUID = 1L;
	JLabel lblBanco, lblSaldo, lblSaldoTitulo, lblDeposito;
	JTextField txtValor;
	JButton btnDepositar, btnExtrato, btnSacar;
	JTextArea txaMensagem;
	double saldo = 1000.00;
	String extrato = "";
	
	public MeuBanco() {
		setTitle("Banco");
		setSize(500, 520);
		setLayout(null);
		setLocationRelativeTo(null);
		
		lblBanco = new JLabel("SISTEMA DE CAIXA ELETRÔNICO");
		lblBanco.setBounds(40, 20, 400, 30);
		add(lblBanco);
		
		lblSaldoTitulo = new JLabel("Saldo Disponível: \n");
		lblSaldoTitulo.setBounds(40, 70, 400, 20);
		add(lblSaldoTitulo);
		
		lblSaldo = new JLabel("R$ " + String.format("%.2f", saldo));
        lblSaldo.setBounds(40, 95, 400, 30);
        add(lblSaldo);
		
		lblDeposito = new JLabel();
		lblDeposito.setBounds(40, 150, 400, 20);
		add(lblDeposito);
		
		txtValor = new JTextField();
		txtValor.setBounds(40, 175, 400, 30);
		add(txtValor);
		
		btnDepositar = new JButton("Realizar depósito");
		btnDepositar.setBounds(40, 225, 125, 40);
		add(btnDepositar);
		
		btnSacar = new JButton("Sacar");
		btnSacar.setBounds(180, 225, 125, 40);
		add(btnSacar);
	
		btnExtrato = new JButton("Extrato");
		btnExtrato.setBounds(320, 225, 125, 40);
		add(btnExtrato);
		
        txaMensagem = new JTextArea("");
        txaMensagem.setEditable(false);
        txaMensagem.setBounds(40, 285, 400, 150);
        add(txaMensagem);
		
		btnDepositar.addActionListener(e -> {
			
			String texto = txtValor.getText().replace(",",".");
			if (texto.isEmpty()) {
				JOptionPane.showMessageDialog(this,
						"Informe um valor!");
				return;
			}
			try {
				double deposito = Double.parseDouble(
						txtValor.getText().replace(",","."));
				
			if(deposito <= 0) {
					JOptionPane.showMessageDialog(this,
							"Informe um valor válido");
				}
				else {
					saldo += deposito;
					lblSaldo.setText ("R$" + String.format("%.2f", saldo));
					txtValor.setText("");
					
					// quando o deposito é feito, adiciona ao histórico do extrato
				    extrato += "Depósito: + R$ " + String.format("%.2f", deposito) + "\n";
				    txaMensagem.setText("Depósito realizado com sucesso!");
				    
				    txtValor.setText("");

				}
			}
			
			catch (NumberFormatException erro) {
				JOptionPane.showMessageDialog(this, 
						"Digite valores numéricos válidos!");
				
			}
		});
		
		
btnSacar.addActionListener(e -> {
			
			String texto = txtValor.getText().replace(",",".");
			if (texto.isEmpty()) {
				JOptionPane.showMessageDialog(this,
						"Informe um valor!");
				return;
			}
			try {
				double saque = Double.parseDouble(
						txtValor.getText().replace(",","."));
				
			if(saque <= 0) {
					JOptionPane.showMessageDialog(this,
							"Informe um valor válido");
				}
			else if (saque > saldo) {
				JOptionPane.showMessageDialog(this, 
						"O valor digitado é maior que o saldo disponível.");
			}
			
			else if (saque > 1000) {
				JOptionPane.showMessageDialog(this, 
						"Não foi possível realizar a operação: Valor digitado maior que 1000");
			}
			
			else if(saque % 10 != 0) {
				JOptionPane.showMessageDialog(this, 
						"O valor não é múltiplo de 10");
			}
				else {
					saldo -= saque;
					lblSaldo.setText ("R$" + String.format("%.2f", saldo));
					txtValor.setText("");
					
					// Cálculo das notas (RN05 e RN07)
				    int valorInteiro = (int) saque; //o valor inteiro do saque
				    int notas100 = valorInteiro / 100; //pega o valor do saque e divide por 100 para definir a quantidade de notas de 100
				    int resto = valorInteiro % 100;

				    int notas50 = resto / 50;
				    resto = resto % 50;

				    int notas10 = resto / 10;

				    // Monta o texto mostrando as notas entregues
				    String mensagemSaque = "Saque de R$ " + String.format("%.2f", saque) + " realizado!\nNotas entregues:\n";
				    if (notas100 > 0) mensagemSaque += "- " + notas100 + " nota(s) de R$ 100\n";
				    if (notas50 > 0)  mensagemSaque += "- " + notas50 + " nota(s) de R$ 50\n";
				    
				 // quando o saque é feito, adiciona ao histórico do extrato
				    extrato += "Saque: - R$ " + String.format("%.2f", saque) + "\n";
				    txaMensagem.setText("Saque realizado com sucesso!");
				    
				    txtValor.setText("");

				}
			}
			
			catch (NumberFormatException erro) {
				JOptionPane.showMessageDialog(this, 
						"Digite valores numéricos válidos!");
				
			}
		});

	btnExtrato.addActionListener(e -> {
    if (extrato.isEmpty()) {
        txaMensagem.setText("Nenhuma movimentação realizada ainda.");
    } else {
        txaMensagem.setText("EXTRATO BANCÁRIO\n" + extrato + "\nSaldo atual: R$ " + String.format("%.2f", saldo));
    }
});
//teste
		
	}

}
	

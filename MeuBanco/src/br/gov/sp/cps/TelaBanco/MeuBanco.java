package br.gov.sp.cps.TelaBanco;

import javax.swing.*;

public class MeuBanco extends JFrame {

	private static final long serialVersionUID = 1L;
	JLabel lblBanco, lblSaldo, lblSaldoTitulo, lblDeposito;
	JTextField txtDeposito;
	JButton btnDepositar;
	JTextArea txaMensagem;
	double saldo = 500.00;
	
	public MeuBanco() {
		setTitle("Banco");
		setSize(500, 520);
		setLayout(null);
		setLocationRelativeTo(null);
		
		lblBanco = new JLabel("Meu Banco");
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
		
		txtDeposito = new JTextField();
		txtDeposito.setBounds(40, 175, 400, 30);
		add(txtDeposito);
		
		btnDepositar = new JButton("Realizar depósito");
		btnDepositar.setBounds(40, 225, 400, 40);
		add(btnDepositar);
		
		
        txaMensagem = new JTextArea("");
        txaMensagem.setBounds(40, 285, 400, 150);
        add(txaMensagem);
		
		btnDepositar.addActionListener(e -> {
			
			String texto = txtDeposito.getText().replace(",",".");
			if (texto.isEmpty()) {
				JOptionPane.showMessageDialog(this,
						"Informe um valor!");
				return;
			}
			try {
				double deposito = Double.parseDouble(
						txtDeposito.getText().replace(",","."));
				
			if(deposito <= 0) {
					JOptionPane.showMessageDialog(this,
							"Informe um valor válido");
				}
				else {
					saldo += deposito;
					lblSaldo.setText ("R$" + String.format("%.2f", saldo));
					txtDeposito.setText("");
				}
			}
			
			catch (NumberFormatException erro) {
				JOptionPane.showMessageDialog(this, 
						"Digite valores numéricos válidos!");
				
			}
		});
		
	}

}
	

package view;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.BorderLayout.WEST;
import static java.awt.BorderLayout.NORTH;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import controll.ControleJogo;
import controll.ControleJogoImpl;
import controll.Observador;
/**
*
* @author aparicio da silva
*/

public class Tela extends JFrame implements Observador {

	private static final long serialVersionUID = 1L;
	
	class JogoTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		@Override
		public int getColumnCount() {
			return controle.getDimencao();
		}

		@Override
		public int getRowCount() {
			return controle.getDimencao();
		}

		@Override
		public Object getValueAt(int row, int col) {
			try {
				return controle.getPeca(row, col);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.toString());
				return null;
			}
		}
		
	}
	
	class JogoRenderer extends DefaultTableCellRenderer {
		
	
		private static final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {

			setIcon((ImageIcon) value);
			
			return this;
		}

	}

	private ControleJogo controle;
	private JTable tabuleiro;
	
	public Tela() throws Exception {
		this.controle = new ControleJogoImpl();
		this.controle.inicializar();
		this.controle.addObservador(this);
	
		
		setTitle("Jogo Aparicio");
		setSize(305, 370);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initComponents();
		pack();
	
	}

	private void initComponents() {

		// criar o tabuleiro e seus componentes
		tabuleiro = new JTable();
		tabuleiro.setModel(new JogoTableModel());
		for (int x=0;x<tabuleiro.getColumnModel().getColumnCount();x++) {
			tabuleiro.getColumnModel().getColumn(x).setWidth(62);
			tabuleiro.getColumnModel().getColumn(x).setMinWidth(62);
			tabuleiro.getColumnModel().getColumn(x).setMaxWidth(62);
		}
		tabuleiro.setRowHeight(62);
		tabuleiro.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabuleiro.setShowGrid(false);//bordas da tabela 
		tabuleiro.setIntercellSpacing(new Dimension(0, 0));		
		tabuleiro.setDefaultRenderer(Object.class, new JogoRenderer());
		
		
		tabuleiro.addMouseListener(new MouseAdapter(){		
			
			@Override
			public void mouseClicked(MouseEvent e) {
			controle.click((int)tabuleiro.rowAtPoint(e.getPoint()),(int)tabuleiro.columnAtPoint(e.getPoint()));
			System.out.println(tabuleiro.rowAtPoint(e.getPoint()) +"/"+tabuleiro.columnAtPoint(e.getPoint()));
			}
		});

		
		add(tabuleiro, CENTER);
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());		
	
		
		// criar os botoes de radio
		JPanel jrGrupo = new JPanel();

		ButtonGroup bgTipoHeroi = new ButtonGroup();
		
		JRadioButton jrTabuleiro1 = new JRadioButton("Hnefatafl");

		jrTabuleiro1.setActionCommand("11");
		jrGrupo.add(jrTabuleiro1);
		bgTipoHeroi.add(jrTabuleiro1);		
		JRadioButton jrTabuleiro2 = new JRadioButton("Tablut");
		jrGrupo.add(jrTabuleiro2);
		jrTabuleiro2.setActionCommand("9");
		bgTipoHeroi.add(jrTabuleiro2);
		
		JRadioButton jrTabuleiro3 = new JRadioButton("Brandubh");
		jrTabuleiro3.setSelected(true);
		jrGrupo.add(jrTabuleiro3);
		jrTabuleiro3.setActionCommand("7");
		bgTipoHeroi.add(jrTabuleiro3);
		
		ActionListener radioAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					//controle.setDimencao(Integer.parseInt(event.getActionCommand()));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		};
		jrTabuleiro1.addActionListener(radioAction);
		jrTabuleiro2.addActionListener(radioAction);
		radioAction.actionPerformed(new ActionEvent(jrTabuleiro1, ActionEvent.ACTION_PERFORMED, jrTabuleiro1.getActionCommand()));
		
		jp.add(jrGrupo, WEST);
		
		
		// criar botao
		JPanel jpCriar = new JPanel();
		JButton jbVoltar = new JButton("Voltar");		
		JButton jbNovoJogo = new JButton("Novo Jogo");
		jbVoltar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					controle.voltarJogada();

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		});
		jbNovoJogo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				try {	
					controle.iniciarJogo();
					jbNovoJogo.setEnabled(false);
					jrTabuleiro1.setEnabled(false);
					jrTabuleiro2.setEnabled(false);
					jrTabuleiro3.setEnabled(false);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		});
		jpCriar.add(jbVoltar);
		jpCriar.add(jbNovoJogo);
		jp.add(jpCriar, CENTER);
		
		JLabel jogador = new JLabel();
		add(jogador, NORTH);

		add(jp, SOUTH);
		jogador.setText("Proximo Jogador :"+ controle.getJogador());
		
		
	}

	public static void main(String[] args) {
		try {
			Tela d = new Tela();
			d.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}

	}

	@Override
	public void mudouTabuleiro() {
		tabuleiro.repaint();
	}

	@Override
	public void fimDeJogo(String msgErro) {
		JOptionPane.showMessageDialog(null, msgErro);
		System.exit(0);
	}


}

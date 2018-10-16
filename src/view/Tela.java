package view;import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.BorderLayout.WEST;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import control.ControleJogo;
import control.ControleJogoImpl;
import control.Observador;
/**
*
* @author aparicio da silva
*/

public class Tela extends JFrame implements Observador {

	private static final long serialVersionUID = 1L;
	
	class HeroiTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		@Override
		public int getColumnCount() {
			return 7;
		}

		@Override
		public int getRowCount() {
			return 7;
		}

		@Override
		public Object getValueAt(int row, int col) {
			try {
				return controle.getPeca(col, row);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.toString());
				return null;
			}
		}
		
	}
	
	class HeroiRenderer extends DefaultTableCellRenderer {
	
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
	
		
		setTitle("Titulo\u00F3i");
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
		tabuleiro.setModel(new HeroiTableModel());
		for (int x=0;x<tabuleiro.getColumnModel().getColumnCount();x++) {
			tabuleiro.getColumnModel().getColumn(x).setWidth(80);
			tabuleiro.getColumnModel().getColumn(x).setMinWidth(80);
			tabuleiro.getColumnModel().getColumn(x).setMaxWidth(80);
		}
		tabuleiro.setRowHeight(82);
		tabuleiro.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabuleiro.setShowGrid(false);
		tabuleiro.setIntercellSpacing(new Dimension(0, 0));
		tabuleiro.setDefaultRenderer(Object.class, new HeroiRenderer());
		tabuleiro.addMouseListener(new MouseAdapter(){	
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int lin = tabuleiro.rowAtPoint(e.getPoint());
				int col = tabuleiro.columnAtPoint(e.getPoint());
				clicar(col,lin);
				
			//controle.clic(e);
			System.out.println(lin +"/"+col);
			}
		});
		
		tabuleiro.addKeyListener(new KeyAdapter(){
			
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					controle.pressTecla( e.getKeyCode() );
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.toString());
				}
			}
			
		});
		
		
		add(tabuleiro, CENTER);

		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		
		// criar os botoes de radio
		JPanel jrGrupo = new JPanel();

		ButtonGroup bgTipoHeroi = new ButtonGroup();
		
		JRadioButton jrMontanha = new JRadioButton("Montanha");
		jrMontanha.setSelected(true);
		jrMontanha.setActionCommand("control.MovimentoHeroiMontanha");
		jrGrupo.add(jrMontanha);
		bgTipoHeroi.add(jrMontanha);
		
		JRadioButton jrAgua = new JRadioButton("\u00C1gua");
		jrGrupo.add(jrAgua);
		jrAgua.setActionCommand("control.MovimentoHeroiAgua");
		bgTipoHeroi.add(jrAgua);
		
		ActionListener radioAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					controle.setTipoHeroi(event.getActionCommand());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		};
		jrMontanha.addActionListener(radioAction);
		jrAgua.addActionListener(radioAction);
		radioAction.actionPerformed(new ActionEvent(jrMontanha, ActionEvent.ACTION_PERFORMED, jrMontanha.getActionCommand()));
		
		jp.add(jrGrupo, WEST);
		
		// botao criar
		JPanel jpCriar = new JPanel();
		JButton jbCriar = new JButton("Criar");
		jbCriar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					controle.criarHeroi();
					controle.run();
					//jbCriar.setEnabled(false);
					//jrMontanha.setEnabled(false);
					//jrAgua.setEnabled(false);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		});
		
		jpCriar.add(jbCriar);
		jp.add(jpCriar, CENTER);
		
		add(jp, SOUTH);
		
		
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
	public void clicar(int linha , int coluna) {
		try {
		
		controle.criarHeroi(linha, coluna);
	} catch (Exception e) {
		JOptionPane.showMessageDialog(null, e.toString());
	}}

}

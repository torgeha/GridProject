import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;


public class MainFrame extends JFrame {
	
	private GridModel gridModel;
	private GridPanel gridPanel;
	private JPanel radioButtonPanel;
	private JPanel buttonPanel;
	
	
	public MainFrame() {
		super("TESTGrid");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		
		constructGridPanel();
		constructRadioButtonPanel();
		constructButtonPanel();
		
		setVisible(true);
		pack();
		
		testMethod();
		
	}
	
	private void constructRadioButtonPanel() {
		radioButtonPanel = new JPanel(new GridBagLayout());
		GridBagConstraints radioPanelGbc = new GridBagConstraints();
		
		JRadioButton wall = new JRadioButton("Wall");
		radioPanelGbc.anchor = GridBagConstraints.LINE_START;
		radioButtonPanel.add(wall, radioPanelGbc);
		
		JRadioButton startPoint = new JRadioButton("Start");
		radioPanelGbc.gridy = 1;
		radioButtonPanel.add(startPoint, radioPanelGbc);
		
		JRadioButton endPoint = new JRadioButton("endPoint");
		radioPanelGbc.gridy = 2;
		radioButtonPanel.add(endPoint, radioPanelGbc);
		
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(wall);
		radioGroup.add(startPoint);
		radioGroup.add(endPoint);
		
		radioPanelGbc.gridy = 0;
		radioPanelGbc.anchor = GridBagConstraints.CENTER;
		add(radioButtonPanel, radioPanelGbc);
		
	}
	
	private void constructButtonPanel() {
		buttonPanel = new JPanel(new GridBagLayout());
		GridBagConstraints panelGBC = new GridBagConstraints();
		panelGBC.gridx = 1;
		
		JButton startButton = new JButton("Start");
		buttonPanel.add(startButton);
		
		JButton stopButton = new JButton("Stop");
		buttonPanel.add(stopButton);
		
		JButton clearButton = new JButton("Clear");
		buttonPanel.add(clearButton);
		
		
		add(buttonPanel, panelGBC);
	}
	
	private void constructGridPanel() {
		GridBagConstraints gpc = new GridBagConstraints();
		gpc.gridx = 0;
		gpc.gridy = 0;
		gpc.gridwidth = 10;
		gpc.gridheight = 10;
		gpc.insets = new Insets(5, 5, 5, 5);
		gpc.anchor = GridBagConstraints.FIRST_LINE_START;
		
		gridModel = new GridModel();
		this.gridPanel = new GridPanel(gridModel);
		gridModel.setGridPanel(gridPanel);
		gridPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

		add(gridPanel, gpc);
		
		
		
		
	}
	
	public void testMethod() {
		gridModel.setCell(3, 5, Cell.WALL);
	}
	
	public static void main(String[] args) {
		new MainFrame();
		
	}

}

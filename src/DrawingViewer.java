import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.event.MouseInputAdapter;

public class DrawingViewer extends JFrame {
	
	private JFrame controlFrame;
	private JButton drawButton, fillButton, undoButton; 
	private JRadioButton lineBox, recBox, ellipseBox;
	private JLabel drawLabel, fillLabel;
	private DrawingComponent component;
	public Color border;
	public Color fill;
	
	public DrawingViewer () {
		setupThisFrame();		
		setupControlFrame();
		setupButtons();
		showFrames();	
	}
	
	private void setupThisFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Drawing Program");
		setLocation(0,0);
		setSize(600,450);
		
		component = new DrawingComponent();
		add(component);
		
	}

	private void setupControlFrame() {
		controlFrame = new JFrame();
		controlFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		controlFrame.setTitle("Controls");
		controlFrame.setLocation(600,0);
		controlFrame.setSize(200,450);
	}
	private void setupButtons(){
		drawButton = new JButton("draw");
		fillButton = new JButton("fill");
		undoButton = new JButton("undo");
		lineBox = new JRadioButton("line");
		lineBox.setActionCommand("line");
		recBox = new JRadioButton("rectangle");
		recBox.setActionCommand("rectangle");
		ellipseBox = new JRadioButton("ellipse");
		ellipseBox.setActionCommand("ellipse");
		
		ButtonGroup group = new ButtonGroup();
		group.add(lineBox);
		group.add(recBox);
		group.add(ellipseBox);
		
		ActionListener drawListener = new drawButtonListener();
		drawButton.addActionListener(drawListener);
		
		ActionListener fillListener = new fillButtonListener();
		fillButton.addActionListener(fillListener);
		
		ActionListener undoListener = new undoButtonListener();
		undoButton.addActionListener(undoListener);
		
		ActionListener ShapeListener = new shapeCheckListener();
		lineBox.addActionListener(ShapeListener);
		recBox.addActionListener(ShapeListener);
		ellipseBox.addActionListener(ShapeListener);
		
		drawLabel = new JLabel();
		drawLabel.setOpaque(true);
		drawLabel.setFont(new Font("Consolas", Font.PLAIN,12));
		drawLabel.setForeground(Color.BLACK);
		drawLabel.setBackground(Color.BLACK);
		drawLabel.setText("         ");
		
		fillLabel = new JLabel();
		fillLabel.setOpaque(true);
		fillLabel.setFont(new Font("Consolas", Font.PLAIN,12));
		fillLabel.setForeground(Color.BLACK);
		fillLabel.setBackground(null);
		fillLabel.setText(" nothing ");
		
		JPanel panel = new JPanel();
		panel.add(drawButton);
		panel.add(fillButton);
		panel.add(undoButton);
		panel.add(lineBox);
		panel.add(recBox);
		panel.add(ellipseBox);
		panel.add(drawLabel);
		panel.add(fillLabel);
		
		controlFrame.add(panel);
	}

	private void showFrames() {
		
		setVisible(true);
		controlFrame.setVisible(true);
		
	}
	
	class drawButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Draw");
			border = JColorChooser.showDialog(drawLabel, "Choose a color", Color.BLACK);
			drawLabel.setBackground(border);
			if(border != null){
				drawLabel.setText("         ");
			}
			else{
				drawLabel.setText(" nothing ");
			}
			component.setDraw(border);
			
		}
		
	}
	class fillButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Fill");
			fill = JColorChooser.showDialog(fillLabel, "Choose a color", null);
			fillLabel.setBackground(fill);
			if(fill != null){
				fillLabel.setText("         ");
			}
			else{
				fillLabel.setText(" nothing ");
			}
			component.setFill(fill);
		}
		
	}
	class undoButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Undo");
		}
		
	}
	class shapeCheckListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == lineBox){
				System.out.println("Line Chosen");
				component.setLine();
			}
			else if (e.getSource() == recBox){
				System.out.println("Rectangle Chosen");
				component.setRec();
			}
			else if (e.getSource() == ellipseBox){
				System.out.println("Ellipse Chosen");
				component.setEllipse();
			}
		}
	}	
}

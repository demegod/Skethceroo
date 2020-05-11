import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;


/* Dimitrios Vlahos */
public class DrawingComponent extends JComponent {
	
	private int mouseX;
	private int mouseY;
	private int mouseX2;
	private int mouseY2; 
	public Color borderColor;
	public Color fillColor;
	public Shape shape;
	public Line2D line;
	public Rectangle rec;
	public Ellipse2D ellipse;
	public CreateShapes storedShapes;
	
	public DrawingComponent() {
		setupMouse();
		storedShapes = new CreateShapes();
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		
		Graphics2D g2 = (Graphics2D) g;
//		
//		if (shape != null){
//			if (shape.equals(rec)){
//			rec = new Rectangle(Math.min(mouseX, mouseX2), Math.min(mouseY, mouseY2) , Math.abs(mouseX - mouseX2), Math.abs(mouseY - mouseY2));
//			}
//		}
		
		shape = new Rectangle(Math.min(mouseX, mouseX2), Math.min(mouseY, mouseY2) , Math.abs(mouseX - mouseX2), Math.abs(mouseY - mouseY2));
//		shape = new Line2D.Double(mouseX, mouseY, mouseX2, mouseY2);
//		shape = new Ellipse2D.Double(Math.min(mouseX, mouseX2), Math.min(mouseY, mouseY2), Math.abs(mouseX - mouseX2), Math.abs(mouseY - mouseY2));
		
		if (shape != null){
			if(fillColor != null){
				g2.setColor(fillColor);
				g2.fill(shape);
			}
			if (borderColor != null){
				g2.setColor(borderColor);
				g2.draw(shape);
			}
		}
	}
	public void setLine(){
		System.out.println("Shape is Line");
		shape = line;
	}
	public void setRec(){
		System.out.println("Shape is Rectangle");
		shape = rec;
	}
	public void setEllipse(){
		System.out.println("Shape is Ellipse");
		shape = ellipse;
	}
	public void setDraw(Color e){
		borderColor = e;
	}
	public void setFill(Color e){
		fillColor = e;
	}
	public void setupMouse(){
		mouseX = Integer.MIN_VALUE;
		mouseY = Integer.MIN_VALUE;
		
		MouseListener listener = new MyListener();
		addMouseListener(listener);
		
		MouseMotionListener motionListener = new MotionListener();
		addMouseMotionListener(motionListener);
	}
	class MyListener extends MouseInputAdapter{
		@Override
		public void mousePressed(MouseEvent e) {
			System.out.println("XXX_Mouse Pressed");
			mouseX = e.getX();
			mouseY = e.getY();
		}
		@Override
		public void mouseReleased(MouseEvent e){
			System.out.println("XXX_Mouse Released");
			mouseX2 = e.getX();
			mouseY2 = e.getY();
			repaint();
		}
	}
	class MotionListener extends MouseMotionAdapter{
		@Override
		public void mouseDragged(MouseEvent e){
			System.out.println("XXX_Mouse Dragged");
			mouseX2 = e.getX();
			mouseY2 = e.getY();
			repaint();
		}
	}
	
}

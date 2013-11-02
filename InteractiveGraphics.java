import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.GLLightingFunc;
import javax.media.opengl.fixedfunc.GLMatrixFunc;
import javax.media.opengl.glu.GLU;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;

/**
 * By: Alexandra Tsampikakis, aletsa-8 Date: 20120205 Description: Version: 1.0
 */
public class InteractiveGraphics extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;

	float rotateT = 0.0f;
	GLU glu = new GLU();
	GLCanvas canvas = new GLCanvas();
	Frame frame = new Frame("A nice room!");
	JFrame jFrame = new JFrame("Admin");
	FPSAnimator animator;
	final Renderer renderer = new Renderer();
	
	float[] vectorAmbient = new float[4];
	float[] vectorSpecular = new float[4];
	float[] vectorDiffuse = new float[4];
	float[] vectorColorAmbient = new float[4];
	float[] vectorColorSpecular = new float[4];
	float[] vectorColorDiffuse = new float[4];
	
	final int TEAPOT = 0;
	final int CUBE = 1;
	final int SPHERE = 2;
	final int MARK = 4;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new InteractiveGraphics();
	}

	/**
	 * Constructor
	 */
	InteractiveGraphics() {
		GLCapabilities capabilities = new GLCapabilities(null);
		canvas = new GLCanvas(capabilities);
		canvas.addKeyListener(this);
		canvas.addGLEventListener(renderer);
		canvas.addMouseListener(renderer);
		canvas.addMouseMotionListener(renderer);
		frame.add(canvas);
		frame.setSize(700, 600);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});

		createAdminWindow();

		animator = new FPSAnimator(canvas, 30);
		frame.setVisible(true);
		animator.start();
		canvas.requestFocus();
	}

	/**
	 * Creats the administrate window, choose lightning and materials.
	 */
	public void createAdminWindow() {

		// The big window.
		JDesktopPane y = new JDesktopPane();
		setTitle("Administrate");
		setBounds(701, 0, 600, 400);
		setVisible(true);
		setContentPane(y);
		y.setBackground(new Color(255, 204, 211)); // Light pink.

		// Lsbels components
		JLabel label1 = new JLabel("Ambient");
		JLabel label2 = new JLabel("Specular");
		JLabel label3 = new JLabel("Diffuse");
		JLabel label4 = new JLabel("Ambient");
		JLabel label5 = new JLabel("Specular");
		JLabel label6 = new JLabel("Diffuse");

		jText1 = new JTextField();
		jText2 = new JTextField();
		jText3 = new JTextField();
		jText4 = new JTextField();
		jText5 = new JTextField();
		jText6 = new JTextField();
		jText7 = new JTextField();
		jText8 = new JTextField();
		jText9 = new JTextField();
		jText10 = new JTextField();
		jText11 = new JTextField();
		jText12 = new JTextField();
		jText13 = new JTextField();
		jText14 = new JTextField();
		jText15 = new JTextField();
		jText16 = new JTextField();
		jText17 = new JTextField();
		jText18 = new JTextField();
		jText19 = new JTextField();
		jText20 = new JTextField();
		jText21 = new JTextField();
		jText22 = new JTextField();
		jText23 = new JTextField();
		jText24 = new JTextField();

		jText1.setText("0.2");
		jText2.setText("0.2");
		jText3.setText("0.2");
		jText4.setText("1.0");
		jText5.setText("0.8");
		jText6.setText("0.8");
		jText7.setText("0.8");
		jText8.setText("1.0");
		jText9.setText("1.0");
		jText10.setText("1.0");
		jText11.setText("1.0");
		jText12.setText("1.0");
		
		// Color - Red is default.
		jText13.setText("1.0");
		jText14.setText("0.0");
		jText15.setText("0.0");
		jText16.setText("1.0");
		jText17.setText("1.0");
		jText18.setText("0.0");
		jText19.setText("0.0");
		jText20.setText("1.0");
		jText21.setText("1.0");
		jText22.setText("0.0");
		jText23.setText("0.0");
		jText24.setText("1.0");

		// A box where lightning is set.
		JInternalFrame lightning = new JInternalFrame("Lightning source", false, false);
		lightning.setVisible(true);
		lightning.setBounds(10, 10, 500, 120);
		y.add(lightning);
		JPanel c = (JPanel) lightning.getContentPane();
		GridLayout cGrid = new GridLayout(3, 5);
		c.setVisible(true);
		c.setLayout(cGrid);
		c.add(label1);     c.add(jText1);     c.add(jText2);     c.add(jText3);     c.add(jText4);
		c.add(label2);     c.add(jText5);     c.add(jText6);     c.add(jText7);     c.add(jText8);
		c.add(label3);     c.add(jText9);     c.add(jText10);    c.add(jText11);    c.add(jText12);

		// Light Ambient
		String a = jText1.getText();
		float aa = Float.valueOf(a.trim()).floatValue();
		vectorAmbient[0] = aa;
		String b = jText2.getText();
		float bb = Float.valueOf(b.trim()).floatValue();
		vectorAmbient[1] = bb;
		String l = jText3.getText();
		float ll = Float.valueOf(l.trim()).floatValue();
		vectorAmbient[2] = ll;
		String m = jText4.getText();
		float mm = Float.valueOf(m.trim()).floatValue();
		vectorAmbient[3] = mm;

		// Light Specular
		String f = jText5.getText();
		float ff = Float.valueOf(f.trim()).floatValue();
		vectorSpecular[0] = ff;
		String g = jText6.getText();
		float gg = Float.valueOf(g.trim()).floatValue();
		vectorSpecular[1] = gg;
		String h = jText7.getText();
		float hh = Float.valueOf(h.trim()).floatValue();
		vectorSpecular[2] = hh;
		String i = jText8.getText();
		float ii = Float.valueOf(i.trim()).floatValue();
		vectorSpecular[3] = ii;

		// Light Diffuse
		String j = jText9.getText();
		float jj = Float.valueOf(j.trim()).floatValue();
		vectorDiffuse[0] = jj;
		String k = jText10.getText();
		float kk = Float.valueOf(k.trim()).floatValue();
		vectorDiffuse[1] = kk;
		String n = jText11.getText();
		float nn = Float.valueOf(n.trim()).floatValue();
		vectorDiffuse[2] = nn;
		String o = jText12.getText();
		float oo = Float.valueOf(o.trim()).floatValue();
		vectorDiffuse[3] = oo;

		// A box where material is set.
		JInternalFrame material = new JInternalFrame("Materials", false, false);
		material.setVisible(true);
		material.setBounds(10, 150, 500, 120);
		y.add(material);
		JPanel d = (JPanel) material.getContentPane();
		GridLayout dGrid = new GridLayout(3, 5);
		d.setVisible(true);
		d.setLayout(dGrid);
		d.add(label4);     d.add(jText13);    d.add(jText14);    d.add(jText15);    d.add(jText16);
		d.add(label5);     d.add(jText17);    d.add(jText18);    d.add(jText19);    d.add(jText20);
		d.add(label6);     d.add(jText21);    d.add(jText22);    d.add(jText23);    d.add(jText24);
		
		// Color Ambient:
		String p = jText13.getText();
		float pp = Float.valueOf(p.trim()).floatValue();
		vectorColorAmbient[0] = pp;
		String q = jText14.getText();
		float qq = Float.valueOf(q.trim()).floatValue();
		vectorColorAmbient[1] = qq;
		String r = jText15.getText();
		float rr = Float.valueOf(r.trim()).floatValue();
		vectorColorAmbient[2] = rr;
		String s = jText16.getText();
		float ss = Float.valueOf(s.trim()).floatValue();
		vectorColorAmbient[3] = ss;
		
		// Color Specular:
		String t = jText17.getText();
		float tt = Float.valueOf(t.trim()).floatValue();
		vectorColorSpecular[0] = tt;
		String u = jText18.getText();
		float uu = Float.valueOf(u.trim()).floatValue();
		vectorColorSpecular[1] = uu;
		String v = jText19.getText();
		float vv = Float.valueOf(v.trim()).floatValue();
		vectorColorSpecular[2] = vv;
		String w = jText20.getText();
		float ww = Float.valueOf(w.trim()).floatValue();
		vectorColorSpecular[3] = ww;
		
		// Color Diffuse:
		String xx = jText21.getText();
		float xxx = Float.valueOf(xx.trim()).floatValue();
		vectorColorDiffuse[0] = xxx;
		String yy = jText22.getText();
		float yyy = Float.valueOf(yy.trim()).floatValue();
		vectorColorDiffuse[1] = yyy;
		String zz = jText23.getText();
		float zzz = Float.valueOf(zz.trim()).floatValue();
		vectorColorDiffuse[2] = zzz;
		String xy = jText24.getText();
		float xxyy = Float.valueOf(xy.trim()).floatValue();
		vectorColorDiffuse[3] = xxyy;
		

		// A box with the OK-button.
		JInternalFrame okay = new JInternalFrame("Save", false, false);
		okay.setVisible(true);
		okay.setBounds(10, 290, 100, 60);
		y.add(okay);
		JPanel ok = (JPanel) okay.getContentPane();
		GridLayout okGrid = new GridLayout(1, 1);
		ok.setVisible(true);
		ok.setLayout(okGrid);
		
		/**
		 * Is used for the JFrame when clicking the OK-button.
		 * 
		 * @author Alexandra Tsampikakis
		 * 
		 */
		class ClickListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == okButton) {
					if(renderer.currentShape != null) {
						// Light Ambient
						String a = jText1.getText();
						float aa = Float.valueOf(a.trim()).floatValue();
						vectorAmbient[0] = aa;
						String b = jText2.getText();
						float bb = Float.valueOf(b.trim()).floatValue();
						vectorAmbient[1] = bb;
						String l = jText3.getText();
						float ll = Float.valueOf(l.trim()).floatValue();
						vectorAmbient[2] = ll;
						String m = jText4.getText();
						float mm = Float.valueOf(m.trim()).floatValue();
						vectorAmbient[3] = mm;
	
						// Light Specular
						String f = jText5.getText();
						float ff = Float.valueOf(f.trim()).floatValue();
						vectorSpecular[0] = ff;
						String g = jText6.getText();
						float gg = Float.valueOf(g.trim()).floatValue();
						vectorSpecular[1] = gg;
						String h = jText7.getText();
						float hh = Float.valueOf(h.trim()).floatValue();
						vectorSpecular[2] = hh;
						String i = jText8.getText();
						float ii = Float.valueOf(i.trim()).floatValue();
						vectorSpecular[3] = ii;
	
						// Light Diffuse
						String j = jText9.getText();
						float jj = Float.valueOf(j.trim()).floatValue();
						vectorDiffuse[0] = jj;
						String k = jText10.getText();
						float kk = Float.valueOf(k.trim()).floatValue();
						vectorDiffuse[1] = kk;
						String n = jText11.getText();
						float nn = Float.valueOf(n.trim()).floatValue();
						vectorDiffuse[2] = nn;
						String o = jText12.getText();
						float oo = Float.valueOf(o.trim()).floatValue();
						vectorDiffuse[3] = oo;
						
						// Color Ambient:
						String p = jText13.getText();
						float pp = Float.valueOf(p.trim()).floatValue();
						renderer.currentShape.vectorColorAmbient[0] = pp;
						String q = jText14.getText();
						float qq = Float.valueOf(q.trim()).floatValue();
						renderer.currentShape.vectorColorAmbient[1] = qq;
						String r = jText15.getText();
						float rr = Float.valueOf(r.trim()).floatValue();
						renderer.currentShape.vectorColorAmbient[2] = rr;
						String s = jText16.getText();
						float ss = Float.valueOf(s.trim()).floatValue();
						renderer.currentShape.vectorColorAmbient[3] = ss;
						
						// Color Specular:
						String t = jText17.getText();
						float tt = Float.valueOf(t.trim()).floatValue();
						renderer.currentShape.vectorColorSpecular[0] = tt;
						String u = jText18.getText();
						float uu = Float.valueOf(u.trim()).floatValue();
						renderer.currentShape.vectorColorSpecular[1] = uu;
						String v = jText19.getText();
						float vv = Float.valueOf(v.trim()).floatValue();
						renderer.currentShape.vectorColorSpecular[2] = vv;
						String w = jText20.getText();
						float ww = Float.valueOf(w.trim()).floatValue();
						renderer.currentShape.vectorColorSpecular[3] = ww;
						
						// Color Diffuse:
						String xx = jText21.getText();
						float xxx = Float.valueOf(xx.trim()).floatValue();
						renderer.currentShape.vectorColorDiffuse[0] = xxx;
						String yy = jText22.getText();
						float yyy = Float.valueOf(yy.trim()).floatValue();
						renderer.currentShape.vectorColorDiffuse[1] = yyy;
						String zz = jText23.getText();
						float zzz = Float.valueOf(zz.trim()).floatValue();
						renderer.currentShape.vectorColorDiffuse[2] = zzz;
						String xy = jText24.getText();
						float xxyy = Float.valueOf(xy.trim()).floatValue();
						renderer.currentShape.vectorColorDiffuse[3] = xxyy;
						
						renderer.getVectors(vectorAmbient, vectorSpecular, vectorDiffuse);
					}
				}
			}
		} // End of ClickListener.

		
		ClickListener cl = new ClickListener();
		okButton = new JButton("OK");
		okButton.addActionListener(cl);
		ok.add(okButton);
		
		renderer.getVectors(vectorAmbient, vectorSpecular, vectorDiffuse);
	} // End of InteractiveGraphics

	// ---------------------------------------------------------------------------------------------

	JButton okButton;
	JTextField 	jText1, jText2, jText3, jText4, jText5, jText6, jText7, jText8,
				jText9, jText10, jText11, jText12, jText13, jText14, jText15, jText16,
				jText17, jText18, jText19, jText20, jText21, jText22, jText23, jText24;

	
	// ---------------------------------------------------------------------------------------------

	/**
	 * 
	 * @author Alexandra Tsampikakis
	 * 
	 */
	static class Renderer implements GLEventListener, MouseListener, MouseMotionListener {
		GLUT glut = new GLUT();
		Vector<Shapes> shapes = new Vector<Shapes>();
		int whatShapeToDraw;
		int plats;
		float rotateT;
		float radie;
		double xcoord;
		double ycoord;
		Shapes s = null;
		Shapes currentShape;
		Shapes mark;
		boolean createNewObject, markAnObject, moveObject, ifResize;
		float[] vectorAmbient = new float[4];
		float[] vectorSpecular = new float[4];
		float[] vectorDiffuse = new float[4];

		/**
		 * What shape the user wants to draw is forwarded to this class.
		 * 
		 * @param shapeToDraw
		 */
		private void setShapeToDraw(int shapeToDraw) {
			whatShapeToDraw = shapeToDraw;
		}
		
		/**
		 * To get user input from admin window.
		 * @param ambient
		 * @param diffuse
		 * @param specular
		 * @param color
		 */
		
		public void getVectors(float[] ambient, float[] diffuse, float[] specular) {
			vectorAmbient = ambient;
			vectorSpecular = specular;
			vectorDiffuse = diffuse;
		}

		/**
		 * Prints all the shapes and its parameters in the vector.
		 */
		public void printShapes() {
			String shape = "";
			for (int i = 0; i < shapes.size(); i++) {
				if (whatShapeToDraw == 0) {
					shape = "(Teapot)";
				} else if (whatShapeToDraw == 1) {
					shape = "(Cube)";
				} else if (whatShapeToDraw == 2) {
					shape = "(Sphere)";
				}
				System.out.println("My shape is: "+ shapes.elementAt(i).whatShapeToDraw + " " + shape);
				System.out.println("X: " + shapes.elementAt(i).x);
				System.out.println("Y: " + shapes.elementAt(i).y);
				System.out.println("Radie: " + shapes.elementAt(i).radie);
				System.out.println(shapes.elementAt(i).vectorColorAmbient[1]);
			}
		}

		/**
		 * Check if the user wants to rotate an object.
		 */
		private void ifRotate() {
			if (currentShape != null) {
				for (int i = 0; i < shapes.size(); i++) {
					Shapes a = shapes.elementAt(i);
					if (a == currentShape) {
						s.ifRotateOject(currentShape);
					}
				}
			}
		}

		/**
		 * Check if the user wants to mark an object.
		 * 
		 * @param markObject
		 */
		private void getIfMarkedObject(boolean markObject) {
			markAnObject = markObject;
			s.getIfMarkedObjectTrue(markAnObject);
			if (moveObject && markAnObject) {
				moveObjectTo();
			}
		}

		/**
		 * Check if the user wants to create a new object.
		 * 
		 * @param newObject
		 */
		private void getIfCreateNewObject(boolean newObject) {
			createNewObject = newObject;
		}

		public void ifMoveObject(boolean ifMoveObject) {
			moveObject = ifMoveObject;
		}

		public void ifResizeObject() { // att göra
			if (currentShape != null) {
				for (int i = 0; i < shapes.size(); i++) {
					Shapes a = shapes.elementAt(i);
					if (a == currentShape) {
						
					}
				}
			}
		}

		@Override
		public void display(GLAutoDrawable drawable) {
			/*
			if (currentShape != null) {
				for (int i = 0; i < shapes.size(); i++) {
					Shapes a = shapes.elementAt(i);
					if (a == currentShape) {
						setColor(drawable, s);
					}
				}
			}*/
			final GL2 gl = drawable.getGL().getGL2();
	    	gl.glClear(GL.GL_COLOR_BUFFER_BIT);
	        gl.glClear(GL.GL_DEPTH_BUFFER_BIT);
			
			
			// Saves all the shapes in a vector.
			for (Shapes s : shapes) {
				setColor(drawable, s);
				s.drawShapes(drawable);
			}

			// Draws the quad when marking an object.
			if (mark != null) {
				mark.drawShapes(drawable);
			}
		}
		
		/**
		 * Set color to current marked shape.
		 * @param drawable
		 */
		public void setColor(GLAutoDrawable drawable, Shapes s) {			
			final GL2 gl = drawable.getGL().getGL2();/*
			gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);*/

			// Prepare light parameters.
			float SHINE_ALL_DIRECTIONS = 1;
			float[] lightPos = { 1.0f, 1.0f, 0.0f, SHINE_ALL_DIRECTIONS };
			//float[] lightPos = { -30, 0, -500, SHINE_ALL_DIRECTIONS };
			//float[] vectorAmbient = { 0.2f, 0.2f, 0.2f, 1f };
			//float[] vectorSpecular = { 0.8f, 0.8f, 0.8f, 1f };
			//float[] vectorDiffuse = {1.0f, 1.0f, 1.0f, 1.0f};
			//final float[] colorRed = { 1.0f, 0.0f, 0.0f, 1.0f }; //vectorColor
			// Set light parameters.
			gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, lightPos, 0);
			gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, vectorAmbient, 0);
			gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, vectorSpecular, 0);
			gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, vectorDiffuse, 0);

			// Enable lighting in GL.
			gl.glEnable(GL2.GL_LIGHT0);
			gl.glEnable(GL2.GL_LIGHTING);

			// Set material properties.
			gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, s.getAmbient(), 0);
			gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, s.getSpecular(), 0);
			gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, s.getDiffuse(), 0);
			gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, 0.5f);
		}
		
		@Override
		public void dispose(GLAutoDrawable drawable) {
		}

		@Override
		public void init(GLAutoDrawable drawable) {
			GL2 gl = drawable.getGL().getGL2();
			gl.glShadeModel(GLLightingFunc.GL_SMOOTH);
			gl.glEnable(GL2.GL_DEPTH_TEST);
		}

		@Override
		public void reshape(GLAutoDrawable drawable, int x, int y, int width, int heigth) {
			final GL2 gl = drawable.getGL().getGL2();
			gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
			gl.glLoadIdentity();

			// Cant do like this if using picking and selecting.
			gl.glOrthof(0, width, heigth, 0, -500, 500);
			gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
			gl.glLoadIdentity();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			xcoord = e.getX();
			ycoord = e.getY();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			double x = e.getX();
			double y = e.getY();

			if (createNewObject) {
				createObject(x, y);
				createNewObject = false;
			}
			if (markAnObject) {
				getWhatObject(xcoord, ycoord);
				createMark();
				ifRotate();
				markAnObject = false;
			}
		}

		double xObject;
		double yObject;
		/**
		 * Mark the object within the closest area the user clicked with the mouse.
		 * 
		 * @param xcoord
		 * @param ycoord
		 */
		public void getWhatObject(double xcoord, double ycoord) {
			double[] checkedShapes = new double[shapes.size()];
			for (int i = 0; i < shapes.size(); i++) {
				xObject = xcoord - shapes.elementAt(i).x;
				yObject = ycoord - shapes.elementAt(i).y;
				double length = Math.sqrt(xObject * xObject + yObject * yObject);
				checkedShapes[i] = length;
			}
			double min = checkedShapes[0];
			plats = 0;
			for (int i = 1; i < checkedShapes.length; i++) {
				if (checkedShapes[i] < min) {
					min = checkedShapes[i];
					plats = i;
				}
			}
			currentShape = shapes.elementAt(plats);
		}

		/**
		 * Deletes the object that is marked.
		 */
		private void deleteObject() { // Ta bort det valda objectet i vektorn.
			shapes.remove(currentShape);
			mark = null;
		}

		/**
		 * Deletes all object that is in the vector shapes.
		 */
		private void deleteAllObjects() {
			shapes.removeAllElements();
			mark = null;
		}

		/**
		 * Moves the object when dragging the mouse.
		 */
		public void moveObjectTo() {
			if (currentShape != null) {
				for (int i = 0; i < shapes.size(); i++) {
					Shapes a = shapes.elementAt(i);
					if (a == currentShape) {
						currentShape.x = movedX;
						currentShape.y = movedY;
						mark.x = currentShape.x;
						mark.y = currentShape.y;
					}
				}
			}
		}
		
		
		/**
		 * Creates the marker when user wants to mark an object.
		 */
		public void createMark() {
			double x = currentShape.x;
			double y = currentShape.y;
			radie = currentShape.radie;
			mark = new Shapes(x, y, radie, 4);
			moveObject = false;
		}
		
		
		/**
		 * Creates an object.
		 * 
		 * @param x
		 * @param y
		 */
		public void createObject(double x, double y) {
			double dx = xcoord - x;
			double dy = ycoord - y;
			radie = (float) Math.sqrt(dx * dx + dy * dy);
			s = new Shapes(xcoord, ycoord, radie, whatShapeToDraw);
			shapes.add(s);
		}
		

		double movedX;
		double movedY;
		@Override
		public void mouseDragged(MouseEvent e) {
			if (moveObject) {
				movedX = e.getX();
				movedY = e.getY();
				moveObjectTo();
				currentShape.x = movedX;
				currentShape.y = movedY;
			}
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
		}

	} // End of Renderer.

	// ---------------------------------------------------------------------------------------------

	/**
	 * Class: Shapes, contains the information about the three shapes that we can draw.
	 * @author Alexandra Tsampikakis
	 * 
	 */
	static class Shapes {

		GLUT glut = new GLUT();
		double x;
		double y;
		float radie;
		float rotateT;
		int whatShapeToDraw;
		boolean markAnObject;
		Shapes currentShape;
		float[] vectorColorAmbient = {1.0f, 0.0f, 0.0f, 1.0f};
		float[] vectorColorSpecular = {1.0f, 0.0f, 0.0f, 1.0f};
		float[] vectorColorDiffuse = {1.0f, 0.0f, 0.0f, 1.0f};
		
		/**
		 * Constructor.
		 * 
		 * @param x
		 * @param y
		 * @param radie
		 */
		Shapes(double x, double y, float radie, int whatShape) {
			this.x = x;
			this.y = y;
			this.radie = radie;
			whatShapeToDraw = whatShape;
		}
		
		public void setColors(float[] ambient, float[] specular, float[] diffuse) {
			vectorColorAmbient = ambient;
			vectorColorSpecular = specular;
			vectorColorDiffuse = diffuse;
		}
		
		public float[] getAmbient() {	
			return vectorColorAmbient;
		}
		
		public float[] getDiffuse() {	
			return vectorColorDiffuse;
		}
		
		public float[] getSpecular() {	
			return vectorColorSpecular;
		}
		
		/**
		 * Check if the user wants to rotate an object.
		 * 
		 * @param currentShapenow
		 */
		public void ifRotateOject(Shapes currentShapenow) {
			currentShape = currentShapenow;
			currentShape.rotateT += 0.2f;
		}

		
		/**
		 * Check if the user wants to mark an object.
		 * 
		 * @param markObject
		 */
		private void getIfMarkedObjectTrue(boolean markObject) {
			markAnObject = markObject;
		}

		
		/**
		 * Check what object the user wants to draw and draws it.
		 * 
		 * @param drawable
		 */
		public void drawShapes(GLAutoDrawable drawable) {
			if (whatShapeToDraw == 0) {
				drawTeapot(drawable);
			} else if (whatShapeToDraw == 1) {
				drawCube(drawable);
			} else if (whatShapeToDraw == 2) {
				drawSphere(drawable);
			} else if (whatShapeToDraw == 4) {
				drawSquareRoundCurrentShape(drawable, x, y, radie);
			}
		}
		

		/**
		 * Draws a sphere.
		 * 
		 * @param drawable
		 */
		public void drawSphere(GLAutoDrawable drawable) {
			final GL2 gl = drawable.getGL().getGL2();
			gl.glPushMatrix();
			gl.glTranslated(x, y, 0);
			gl.glRotatef(rotateT, 1.0f, 0.0f, 0.0f);
			gl.glRotatef(rotateT, 0.0f, 1.0f, 0.0f);
			gl.glRotatef(rotateT, 0.0f, 0.0f, 1.0f);
			glut.glutSolidSphere(radie, 50, 50);
			gl.glPopMatrix();
		}

		
		/**
		 * draws a cube.
		 * 
		 * @param drawable
		 */
		public void drawCube(GLAutoDrawable drawable) {
			final GL2 gl = drawable.getGL().getGL2();
			gl.glPushMatrix();
			gl.glTranslated(x, y, 0);
			gl.glRotatef(rotateT, 1.0f, 0.0f, 0.0f);
			gl.glRotatef(rotateT, 0.0f, 1.0f, 0.0f);
			gl.glRotatef(rotateT, 0.0f, 0.0f, 1.0f);
			glut.glutSolidCube(radie);
			gl.glPopMatrix();
		}
		

		/**
		 * Draws a teapot.
		 * 
		 * @param drawable
		 */
		public void drawTeapot(GLAutoDrawable drawable) {
			final GL2 gl = drawable.getGL().getGL2();
			gl.glPushMatrix();
			gl.glTranslated(x, y, 0);
			gl.glRotatef(rotateT, 1.0f, 0.0f, 0.0f);
			gl.glRotatef(rotateT, 0.0f, 1.0f, 0.0f);
			gl.glRotatef(rotateT, 0.0f, 0.0f, 1.0f);
			glut.glutSolidTeapot(radie); // fungerar, men uppochner?!
			gl.glPopMatrix();
		}
		

		/**
		 * Draws a quad around the marked object.
		 * 
		 * @param x
		 * @param y
		 * @param radie
		 */
		public void drawSquareRoundCurrentShape(GLAutoDrawable drawable, double x, double y, float radie) {
			final GL2 gl = drawable.getGL().getGL2();
			gl.glBegin(GL2.GL_LINE_LOOP);
			gl.glColor3f(0, 0, 1);
			gl.glVertex2f((float) x - radie, (float) y + radie); // Top Left
			gl.glVertex2f((float) x + radie, (float) y + radie); // Top Right
			gl.glVertex2f((float) x + radie, (float) y - radie); // Bottom Right
			gl.glVertex2f((float) x - radie, (float) y - radie); // Bottom Left
			gl.glEnd();
		}
	} // End of Shapes.

	// ---------------------------------------------------------------------------------------------

	// Class InteractiveGraphics again:

	boolean createNewObject = false, markAnObject = false, moveObject = false;

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { // Exit program
			exit();
		}
		if (e.getKeyCode() == KeyEvent.VK_DELETE) { // Deletes all objects in
			renderer.deleteAllObjects();
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) { // Create a new object.
			createNewObject = true;
			renderer.getIfCreateNewObject(createNewObject);
		}
		if (e.getKeyCode() == KeyEvent.VK_0) { // Draw a teapot.
			renderer.setShapeToDraw(TEAPOT);
		}
		if (e.getKeyCode() == KeyEvent.VK_1) { // Draw a cube.
			renderer.setShapeToDraw(CUBE);
		}
		if (e.getKeyCode() == KeyEvent.VK_2) { // Draw a sphere.
			renderer.setShapeToDraw(SPHERE);
		}
		if (e.getKeyCode() == KeyEvent.VK_3) { // Moves the marked object.
			moveObject = true;
			renderer.ifMoveObject(moveObject);
		}
		if (e.getKeyCode() == KeyEvent.VK_4) { // Deletes the marked object.
			renderer.deleteObject();
		}
		if (e.getKeyCode() == KeyEvent.VK_5) { // Rotates the marked object.
			if (markAnObject) {
				renderer.ifRotate();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_6) { // Indicates that we want to mark
			markAnObject = true;
			renderer.getIfMarkedObject(markAnObject);
		}
		if (e.getKeyCode() == KeyEvent.VK_7) { // Print out the vector with
			renderer.printShapes();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * Exit the program.
	 */
	private void exit() {
		animator.stop();
		frame.dispose();
		System.exit(0);
	}
}

package threeBody;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GraphicsConfiguration;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Logger;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.swing.Timer;

import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.Viewer;
import com.sun.j3d.utils.universe.ViewingPlatform;

public class DrawAnimation extends Applet implements ActionListener, KeyListener {
	private static final long serialVersionUID = 1L;
	private Button go;
	private BoundingSphere bounds;
	private TransformGroup all;
	private TransformGroup red;
	private TransformGroup green;
	private TransformGroup blue;
	private Transform3D redtrans = new Transform3D();
	private Transform3D greentrans = new Transform3D();
	private Transform3D bluetrans = new Transform3D();
	private Timer timer;
	static Motion m = new Motion();
	private static Logger log = Logger.getLogger(DrawAnimation.class.getName());
	//create BranchGroup
	public BranchGroup createSceneGraph() {
		//create root to hold everything
		BranchGroup root = new BranchGroup();
	    bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),100.0);
	    all =new TransformGroup();
	    //add background
	    Background bg = new Background(new Color3f(0,0,0));
	    bg.setApplicationBounds(bounds);
	    all.addChild(bg);
	    //add red body
	    Sphere redsphere = new Sphere(1);
	    Material redm = new Material(new Color3f(225,0,0), new Color3f(0, 0, 0), new Color3f(1,1,1), new Color3f(1,1,1), 64);
	    Appearance reda = new Appearance();
	    redm.setLightingEnable(true);
	    reda.setMaterial(redm);
	    redsphere.setAppearance(reda);
	    red = new TransformGroup(redtrans);
	    red.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	    red.addChild(redsphere);
	    all.addChild(red);
	    //add green body
	    Sphere greensphere = new Sphere(1);
	    Material greenm = new Material(new Color3f(0,225,0), new Color3f(0, 0, 0), new Color3f(1,1,1), new Color3f(1,1,1), 64);
	    Appearance greena = new Appearance();
	    greenm.setLightingEnable(true);
	    greena.setMaterial(greenm);
	    greensphere.setAppearance(greena);
	    green = new TransformGroup(greentrans);
	    green.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	    green.addChild(greensphere);
	    all.addChild(green);
	    //add blue body
	    Sphere bluesphere = new Sphere(1);
	    Material bluem = new Material(new Color3f(0,0,255), new Color3f(0, 0, 0), new Color3f(1,1,1), new Color3f(1,1,1), 64);
	    Appearance bluea = new Appearance();
	    bluem.setLightingEnable(true);
	    bluea.setMaterial(bluem);
	    bluesphere.setAppearance(bluea);
	    blue = new TransformGroup(bluetrans);
	    blue.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	    blue.addChild(bluesphere);
	    all.addChild(blue);
	    //add light
	    Color3f light1Color = new Color3f(1.0f, 0.0f, 0.2f);
	    Vector3f light1Direction = new Vector3f(14.0f, -7.0f, -12.0f);
	    DirectionalLight light1 = new DirectionalLight(light1Color,light1Direction);
	    light1.setInfluencingBounds(bounds);
	    all.addChild(light1);
	    //add ambient color
	    Color3f ambientColor = new Color3f(1.0f, 1.0f, 1.0f);
	    AmbientLight ambientLightNode = new AmbientLight(ambientColor);
	    ambientLightNode.setInfluencingBounds(bounds);
	    all.addChild(ambientLightNode);
	    //add everything to root
	    root.addChild(all);
	    return root;
	}
	
	//init everything
	public DrawAnimation() {
		log.info("App started");
		//init data
		m.init();
	    this.setLayout(new BorderLayout());
	    GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
	    Canvas3D canvas = new Canvas3D(config);
	    this.add("Center", canvas);
	    canvas.addKeyListener(this);
	    timer = new Timer(200, this);
	    Panel p = new Panel();
	    go = new Button("Start");
	    p.add(go);
	    this.add("North", p);
	    go.addActionListener(this);
	    go.addKeyListener(this);
	    //init view point & create BranchGroup
	    BranchGroup scene = createSceneGraph();
	    ViewingPlatform viewingPlatform = new ViewingPlatform();
	    viewingPlatform.getViewPlatform().setActivationRadius(300f);
	    TransformGroup viewTransform = viewingPlatform.getViewPlatformTransform();
	    Transform3D viewpoint = new Transform3D();
	    viewpoint.lookAt(new Point3d(0,0,150), new Point3d(0,0,0), new Vector3d(0,1,0));
	    viewpoint.invert();
	    viewTransform.setTransform(viewpoint);
	    Viewer viewer = new Viewer(canvas);
	    View view = viewer.getView();
	    view.setBackClipDistance(30);
	    SimpleUniverse universe = new SimpleUniverse(viewingPlatform, viewer);
	    OrbitBehavior orbit = new OrbitBehavior(canvas,OrbitBehavior.REVERSE_ALL);
	    orbit.setSchedulingBounds(bounds);
	    viewingPlatform.setViewPlatformBehavior(orbit);
	    universe.addBranchGraph(scene);
	    this.addKeyListener(this);
	}
	
	public void CreatePractice(int n,Color3f color){
		Sphere practices = new Sphere(0.1f);
		Material practicem = new Material(color, new Color3f(0, 0, 0), new Color3f(1,1,1), new Color3f(1,1,1), 64);
		Appearance practicea = new Appearance();
		practicem.setLightingEnable(true);
		practicea.setMaterial(practicem);
		practices.setAppearance(practicea);
		TransformGroup practice = new TransformGroup(greentrans);
		practice.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		practice.addChild(practices);
		all.addChild(practice);
		Transform3D practicetrans = new Transform3D();
		practicetrans.setTranslation(new Vector3d(m.x[n][0], m.x[n][1], m.x[n][2]));
		practice.setTransform(practicetrans);
	}
	
	public void keyPressed(KeyEvent e) {
	}
	
	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}
	  
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == go) {
			if (!timer.isRunning()) {
				timer.start();
			}
		}
		else{
			redtrans.setTranslation(new Vector3d(m.red.position[0], m.red.position[1], m.red.position[2]));
			red.setTransform(redtrans);
			greentrans.setTranslation(new Vector3d(m.green.position[0], m.green.position[1], m.green.position[2]));
			green.setTransform(greentrans);
			bluetrans.setTranslation(new Vector3d(m.blue.position[0], m.blue.position[1], m.blue.position[2]));
			blue.setTransform(bluetrans);
			try {
				timer.wait(10000000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			new Thread(m).start();
			
			
		}
	}
	
	public static void main(String[] args) {
	    DrawAnimation bb = new DrawAnimation();
	    @SuppressWarnings("unused")
		MainFrame mf = new MainFrame(bb, 1000, 650);
	}
}
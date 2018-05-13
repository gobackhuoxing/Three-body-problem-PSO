 /*
 * CSYE 6205
 * @author Wenbo Sun
 * NUID:001994516
 */
package threeBody;
import java.applet.Applet;
import java.awt.Button;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
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
	private Button start;	//start button
	private Button pause; 	//pause button
	private Button resume; 	//resume button
	private Button stop; 	//stop button
	
	
	// build the 3D Animation
	private BoundingSphere leftbounds;
	private BoundingSphere rightbounds;
	private TransformGroup leftall;
	private TransformGroup rightall;
	private Canvas3D leftcanvas;
	private Canvas3D rightcanvas;
	// three groups of particles
	private TransformGroup red;
	private TransformGroup green;
	private TransformGroup blue;
	private Transform3D redtrans = new Transform3D();
	private Transform3D greentrans = new Transform3D();
	private Transform3D bluetrans = new Transform3D();
	//thread
	private Timer timer;
	static Motion m = new Motion();
	private static Logger log = Logger.getLogger(DrawAnimation.class.getName());
	//movement group for red particle
	TransformGroup redparticle0 = new TransformGroup();
	TransformGroup redparticle1 = new TransformGroup();
	TransformGroup redparticle2 = new TransformGroup();
	TransformGroup redparticle3 = new TransformGroup();
	TransformGroup redparticle4 = new TransformGroup();
	TransformGroup redparticle5 = new TransformGroup();
	TransformGroup redparticle6 = new TransformGroup();
	TransformGroup redparticle7 = new TransformGroup();
	TransformGroup redparticle8 = new TransformGroup();
	TransformGroup redparticle9 = new TransformGroup();
	TransformGroup redparticle10 = new TransformGroup();
	TransformGroup redparticle11 = new TransformGroup();
	TransformGroup redparticle12 = new TransformGroup();
	TransformGroup redparticle13 = new TransformGroup();
	TransformGroup redparticle14 = new TransformGroup();
	//movement group for green particle
	TransformGroup greenparticle0 = new TransformGroup();
	TransformGroup greenparticle1 = new TransformGroup();
	TransformGroup greenparticle2 = new TransformGroup();
	TransformGroup greenparticle3 = new TransformGroup();
	TransformGroup greenparticle4 = new TransformGroup();
	TransformGroup greenparticle5 = new TransformGroup();
	TransformGroup greenparticle6 = new TransformGroup();
	TransformGroup greenparticle7 = new TransformGroup();
	TransformGroup greenparticle8 = new TransformGroup();
	TransformGroup greenparticle9 = new TransformGroup();
	TransformGroup greenparticle10 = new TransformGroup();
	TransformGroup greenparticle11 = new TransformGroup();
	TransformGroup greenparticle12 = new TransformGroup();
	TransformGroup greenparticle13 = new TransformGroup();
	TransformGroup greenparticle14 = new TransformGroup();
	//movement group for blue particle
	TransformGroup blueparticle0 = new TransformGroup();
	TransformGroup blueparticle1 = new TransformGroup();
	TransformGroup blueparticle2 = new TransformGroup();
	TransformGroup blueparticle3 = new TransformGroup();
	TransformGroup blueparticle4 = new TransformGroup();
	TransformGroup blueparticle5 = new TransformGroup();
	TransformGroup blueparticle6 = new TransformGroup();
	TransformGroup blueparticle7 = new TransformGroup();
	TransformGroup blueparticle8 = new TransformGroup();
	TransformGroup blueparticle9 = new TransformGroup();
	TransformGroup blueparticle10 = new TransformGroup();
	TransformGroup blueparticle11 = new TransformGroup();
	TransformGroup blueparticle12 = new TransformGroup();
	TransformGroup blueparticle13 = new TransformGroup();
	TransformGroup blueparticle14 = new TransformGroup();
	//control the movement for red pariticle
	Transform3D redparticletrans0 = new Transform3D();
	Transform3D redparticletrans1 = new Transform3D();
	Transform3D redparticletrans2 = new Transform3D();
	Transform3D redparticletrans3 = new Transform3D();
	Transform3D redparticletrans4 = new Transform3D();
	Transform3D redparticletrans5 = new Transform3D();
	Transform3D redparticletrans6 = new Transform3D();
	Transform3D redparticletrans7 = new Transform3D();
	Transform3D redparticletrans8 = new Transform3D();
	Transform3D redparticletrans9 = new Transform3D();
	Transform3D redparticletrans10 = new Transform3D();
	Transform3D redparticletrans11 = new Transform3D();
	Transform3D redparticletrans12 = new Transform3D();
	Transform3D redparticletrans13 = new Transform3D();
	Transform3D redparticletrans14 = new Transform3D();
	//control the movement for green pariticle
	Transform3D greenparticletrans0 = new Transform3D();
	Transform3D greenparticletrans1 = new Transform3D();
	Transform3D greenparticletrans2 = new Transform3D();
	Transform3D greenparticletrans3 = new Transform3D();
	Transform3D greenparticletrans4 = new Transform3D();
	Transform3D greenparticletrans5 = new Transform3D();
	Transform3D greenparticletrans6 = new Transform3D();
	Transform3D greenparticletrans7 = new Transform3D();
	Transform3D greenparticletrans8 = new Transform3D();
	Transform3D greenparticletrans9 = new Transform3D();
	Transform3D greenparticletrans10 = new Transform3D();
	Transform3D greenparticletrans11 = new Transform3D();
	Transform3D greenparticletrans12 = new Transform3D();
	Transform3D greenparticletrans13 = new Transform3D();
	Transform3D greenparticletrans14 = new Transform3D();
	//control the movement for blue pariticle
	Transform3D blueparticletrans0 = new Transform3D();
	Transform3D blueparticletrans1 = new Transform3D();
	Transform3D blueparticletrans2 = new Transform3D();
	Transform3D blueparticletrans3 = new Transform3D();
	Transform3D blueparticletrans4 = new Transform3D();
	Transform3D blueparticletrans5 = new Transform3D();
	Transform3D blueparticletrans6 = new Transform3D();
	Transform3D blueparticletrans7 = new Transform3D();
	Transform3D blueparticletrans8 = new Transform3D();
	Transform3D blueparticletrans9 = new Transform3D();
	Transform3D blueparticletrans10 = new Transform3D();
	Transform3D blueparticletrans11 = new Transform3D();
	Transform3D blueparticletrans12 = new Transform3D();
	Transform3D blueparticletrans13 = new Transform3D();
	Transform3D blueparticletrans14 = new Transform3D();

	//init everything
	public DrawAnimation() {
		log.info("App started");
		//init data
		m.init();
		timer = new Timer(10, this);
		//create panel
	    Panel left=new Panel();
	    Panel right=new Panel();
	    Panel control = new Panel();
	    GridBagLayout gbl = new GridBagLayout();
	    GridBagConstraints gbc = new GridBagConstraints();
	    this.setLayout(gbl);
	    gbc.weightx = 0.0;
	    gbc.gridwidth = 2;
	    gbc.ipady = 30;
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    this.add(createControlPanel(control),gbc);
	    gbc.gridwidth = 1;
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    this.add(createLeftPanel(left),gbc);
	    left.addKeyListener(this);
	    gbc.gridwidth = 1;
	    gbc.gridx = 1;
	    gbc.gridy = 1;
	    this.add(createRightPanel(right),gbc);
	}	
	
	//create BranchGroup for left
	public BranchGroup createLeftSceneGraph() {
		//create root to hold everything
		BranchGroup root = new BranchGroup();
		leftbounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),100.0);
		leftall =new TransformGroup();
	    //add background
	    Background bg = new Background(new Color3f(0,0,0));
	    bg.setApplicationBounds(leftbounds);
	    leftall.addChild(bg);
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
	    leftall.addChild(red);
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
	    leftall.addChild(green);
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
	    leftall.addChild(blue);
	    //add light
	    Color3f light1Color = new Color3f(1.0f, 0.0f, 0.2f);
	    Vector3f light1Direction = new Vector3f(14.0f, -7.0f, -12.0f);
	    DirectionalLight light1 = new DirectionalLight(light1Color,light1Direction);
	    light1.setInfluencingBounds(leftbounds);
	    leftall.addChild(light1);
	    //add ambient color
	    Color3f ambientColor = new Color3f(1.0f, 1.0f, 1.0f);
	    AmbientLight ambientLightNode = new AmbientLight(ambientColor);
	    ambientLightNode.setInfluencingBounds(leftbounds);
	    leftall.addChild(ambientLightNode);
	    //add everything to root
	    root.addChild(leftall);
	    return root;
	}
	
	//create BranchGroup for right
	public BranchGroup createRightSceneGraph(){
		BranchGroup root = new BranchGroup();
		rightbounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),100.0);
		rightall =new TransformGroup();
		//add background
	    Background bg = new Background(new Color3f(0,0,0));
	    bg.setApplicationBounds(leftbounds);
	    rightall.addChild(bg);
	    //add light
	    Color3f light1Color = new Color3f(1.0f, 0.0f, 0.2f);
	    Vector3f light1Direction = new Vector3f(14.0f, -7.0f, -12.0f);
	    DirectionalLight light1 = new DirectionalLight(light1Color,light1Direction);
	    light1.setInfluencingBounds(leftbounds);
	    rightall.addChild(light1);
	    //add ambient color
	    Color3f ambientColor = new Color3f(1.0f, 1.0f, 1.0f);
	    AmbientLight ambientLightNode = new AmbientLight(ambientColor);
	    ambientLightNode.setInfluencingBounds(leftbounds);
	    rightall.addChild(ambientLightNode);
	    //add 15 particle for red
		redparticle0.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		redparticle0.addChild(CreatePractice(new Color3f(255,0,0)));
		rightall.addChild(redparticle0);
		redparticle1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		redparticle1.addChild(CreatePractice(new Color3f(255,0,0)));
		rightall.addChild(redparticle1);
		redparticle2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		redparticle2.addChild(CreatePractice(new Color3f(255,0,0)));
		rightall.addChild(redparticle2);
		redparticle3.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		redparticle3.addChild(CreatePractice(new Color3f(255,0,0)));
		rightall.addChild(redparticle3);
		redparticle4.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		redparticle4.addChild(CreatePractice(new Color3f(255,0,0)));
		rightall.addChild(redparticle4);
		redparticle5.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		redparticle5.addChild(CreatePractice(new Color3f(255,0,0)));
		rightall.addChild(redparticle5);
		redparticle6.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		redparticle6.addChild(CreatePractice(new Color3f(255,0,0)));
		rightall.addChild(redparticle6);
		redparticle7.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		redparticle7.addChild(CreatePractice(new Color3f(255,0,0)));
		rightall.addChild(redparticle7);
		redparticle8.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		redparticle8.addChild(CreatePractice(new Color3f(255,0,0)));
		rightall.addChild(redparticle8);
		redparticle9.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		redparticle9.addChild(CreatePractice(new Color3f(255,0,0)));
		rightall.addChild(redparticle9);
		redparticle10.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		redparticle10.addChild(CreatePractice(new Color3f(255,0,0)));
		rightall.addChild(redparticle10);
		redparticle11.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		redparticle11.addChild(CreatePractice(new Color3f(255,0,0)));
		rightall.addChild(redparticle11);
		redparticle12.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		redparticle12.addChild(CreatePractice(new Color3f(255,0,0)));
		rightall.addChild(redparticle12);
		redparticle13.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		redparticle13.addChild(CreatePractice(new Color3f(255,0,0)));
		rightall.addChild(redparticle13);
		redparticle14.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		redparticle14.addChild(CreatePractice(new Color3f(255,0,0)));
		rightall.addChild(redparticle14);
		//add 15 particle for green
		greenparticle0.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		greenparticle0.addChild(CreatePractice(new Color3f(0,255,0)));
		rightall.addChild(greenparticle0);
		greenparticle1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		greenparticle1.addChild(CreatePractice(new Color3f(0,255,0)));
		rightall.addChild(greenparticle1);
		greenparticle2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		greenparticle2.addChild(CreatePractice(new Color3f(0,255,0)));
		rightall.addChild(greenparticle2);
		greenparticle3.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		greenparticle3.addChild(CreatePractice(new Color3f(0,255,0)));
		rightall.addChild(greenparticle3);
		greenparticle4.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		greenparticle4.addChild(CreatePractice(new Color3f(0,255,0)));
		rightall.addChild(greenparticle4);
		greenparticle5.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		greenparticle5.addChild(CreatePractice(new Color3f(0,255,0)));
		rightall.addChild(greenparticle5);
		greenparticle6.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		greenparticle6.addChild(CreatePractice(new Color3f(0,255,0)));
		rightall.addChild(greenparticle6);
		greenparticle7.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		greenparticle7.addChild(CreatePractice(new Color3f(0,255,0)));
		rightall.addChild(greenparticle7);
		greenparticle8.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		greenparticle8.addChild(CreatePractice(new Color3f(0,255,0)));
		rightall.addChild(greenparticle8);
		greenparticle9.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		greenparticle9.addChild(CreatePractice(new Color3f(0,255,0)));
		rightall.addChild(greenparticle9);
		greenparticle10.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		greenparticle10.addChild(CreatePractice(new Color3f(0,255,0)));
		rightall.addChild(greenparticle10);
		greenparticle11.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		greenparticle11.addChild(CreatePractice(new Color3f(0,255,0)));
		rightall.addChild(greenparticle11);
		greenparticle12.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		greenparticle12.addChild(CreatePractice(new Color3f(0,255,0)));
		rightall.addChild(greenparticle12);
		greenparticle13.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		greenparticle13.addChild(CreatePractice(new Color3f(0,255,0)));
		rightall.addChild(greenparticle13);
		greenparticle14.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		greenparticle14.addChild(CreatePractice(new Color3f(0,255,0)));
		rightall.addChild(greenparticle14);
		//add 15 particle for blue
		blueparticle0.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		blueparticle0.addChild(CreatePractice(new Color3f(0,0,255)));
		rightall.addChild(blueparticle0);
		blueparticle1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		blueparticle1.addChild(CreatePractice(new Color3f(0,0,255)));
		rightall.addChild(blueparticle1);
		blueparticle2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		blueparticle2.addChild(CreatePractice(new Color3f(0,0,255)));
		rightall.addChild(blueparticle2);
		blueparticle3.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		blueparticle3.addChild(CreatePractice(new Color3f(0,0,255)));
		rightall.addChild(blueparticle3);
		blueparticle4.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		blueparticle4.addChild(CreatePractice(new Color3f(0,0,255)));
		rightall.addChild(blueparticle4);
		blueparticle5.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		blueparticle5.addChild(CreatePractice(new Color3f(0,0,255)));
		rightall.addChild(blueparticle5);
		blueparticle6.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		blueparticle6.addChild(CreatePractice(new Color3f(0,0,255)));
		rightall.addChild(blueparticle6);
		blueparticle7.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		blueparticle7.addChild(CreatePractice(new Color3f(0,0,255)));
		rightall.addChild(blueparticle7);
		blueparticle8.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		blueparticle8.addChild(CreatePractice(new Color3f(0,0,255)));
		rightall.addChild(blueparticle8);
		blueparticle9.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		blueparticle9.addChild(CreatePractice(new Color3f(0,0,255)));
		rightall.addChild(blueparticle9);
		blueparticle10.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		blueparticle10.addChild(CreatePractice(new Color3f(0,0,255)));
		rightall.addChild(blueparticle10);
		blueparticle11.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		blueparticle11.addChild(CreatePractice(new Color3f(0,0,255)));
		rightall.addChild(blueparticle11);
		blueparticle12.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		blueparticle12.addChild(CreatePractice(new Color3f(0,0,255)));
		rightall.addChild(blueparticle12);
		blueparticle13.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		blueparticle13.addChild(CreatePractice(new Color3f(0,0,255)));
		rightall.addChild(blueparticle13);
		blueparticle14.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		blueparticle14.addChild(CreatePractice(new Color3f(0,0,255)));
		rightall.addChild(blueparticle14);
	    //add everything to root
	    root.addChild(rightall);
		return root;
	}
	
	//create panel for left
	private Panel createLeftPanel(Panel p) {
	    GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
	    leftcanvas = new Canvas3D(config);
	    leftcanvas.setSize(600, 550);
	    p.add(leftcanvas);
	    //init view point & create BranchGroup
	    BranchGroup scene = createLeftSceneGraph();
	    ViewingPlatform viewingPlatform = new ViewingPlatform();
	    viewingPlatform.getViewPlatform().setActivationRadius(300f);
	    TransformGroup viewTransform = viewingPlatform.getViewPlatformTransform();
	    Transform3D viewpoint = new Transform3D();
	    viewpoint.lookAt(new Point3d(0,0,50), new Point3d(0,0,0), new Vector3d(0,1,0));
	    viewpoint.invert();
	    viewTransform.setTransform(viewpoint);
	    Viewer viewer = new Viewer(leftcanvas);
	    View view = viewer.getView();
	    view.setBackClipDistance(30);
	    SimpleUniverse universe = new SimpleUniverse(viewingPlatform, viewer);
	    OrbitBehavior orbit = new OrbitBehavior(leftcanvas,OrbitBehavior.REVERSE_ALL);
	    orbit.setSchedulingBounds(leftbounds);
	    viewingPlatform.setViewPlatformBehavior(orbit);
	    universe.addBranchGraph(scene);
	    return p;
	  }
	
	//create panel for right
	private Panel createRightPanel(Panel p) {
	    GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
	    rightcanvas = new Canvas3D(config);
	    rightcanvas.setSize(600, 550);
	    p.add(rightcanvas);
	    //init view point & create BranchGroup
	    BranchGroup scene = createRightSceneGraph();
	    ViewingPlatform viewingPlatform = new ViewingPlatform();
	    viewingPlatform.getViewPlatform().setActivationRadius(300f);
	    TransformGroup viewTransform = viewingPlatform.getViewPlatformTransform();
	    Transform3D viewpoint = new Transform3D();
	    viewpoint.lookAt(new Point3d(0,0,20), new Point3d(0,0,0), new Vector3d(0,1,0));
	    viewpoint.invert();
	    viewTransform.setTransform(viewpoint);
	    Viewer viewer = new Viewer(rightcanvas);
	    View view = viewer.getView();
	    view.setBackClipDistance(30);
	    SimpleUniverse pso = new SimpleUniverse(viewingPlatform, viewer);
	    OrbitBehavior orbit = new OrbitBehavior(rightcanvas,OrbitBehavior.REVERSE_ALL);
	    orbit.setSchedulingBounds(rightbounds);
	    viewingPlatform.setViewPlatformBehavior(orbit);
	    pso.addBranchGraph(scene);
	    return p;
	}
	
	//create control panel and all button
	private Panel createControlPanel(Panel p) {
	    start = new Button("Start");
	    pause = new Button("Pause"); 	
		resume = new Button("Resume");	
		stop = new Button("Stop");
		//get information for red
		TextField redpx = new TextField(3);
		TextField redpy = new TextField(3);
		TextField redpz = new TextField(3);
		TextField redvx = new TextField(3);
		TextField redvy = new TextField(3);
		TextField redvz = new TextField(3);
		TextField redm = new TextField(3);
		//get information for green
		TextField greenpx = new TextField(3);
		TextField greenpy = new TextField(3);
		TextField greenpz = new TextField(3);
		TextField greenvx = new TextField(3);
		TextField greenvy = new TextField(3);
		TextField greenvz = new TextField(3);
		TextField greenm = new TextField(3);
		//get information for blue
		TextField bluepx = new TextField(3);
		TextField bluepy = new TextField(3);
		TextField bluepz = new TextField(3);
		TextField bluevx = new TextField(3);
		TextField bluevy = new TextField(3);
		TextField bluevz = new TextField(3);
		TextField bluem = new TextField(3);

	    p.setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridwidth = 1;
	    gbc.gridx = 4;
	    gbc.gridy = 0;
	    p.add(start,gbc);
	    gbc.gridx = 5;
	    p.add(pause,gbc);
	    gbc.gridx = 11;
	    p.add(resume,gbc);
	    gbc.gridx = 16;
	    p.add(stop,gbc);
	    gbc.gridwidth = 4;
	    gbc.gridx = 1;
	    gbc.gridy = 1;
	    p.add(new Label("Please input the initial Position, Velocity, and Mass                             "),gbc);
	    gbc.gridx = 1;
	    gbc.gridy = 2;
	    p.add(new Label("Default Position:      Red(2,0,0)      Green(0,2,0)      Blue(0,0,2)           "),gbc);
	    gbc.gridx = 1;
	    gbc.gridy = 3;
	    p.add(new Label("Velocity:      Red(0,0.1,0)      Green(0,0,0.1)      Blue(0.1,0,0)   Mass 1"),gbc);
	    gbc.gridwidth = 1;
	    gbc.gridx = 5;
	    gbc.gridy = 1;
	    p.add(new Label("Red"),gbc);
	    gbc.gridx = 6;
	    p.add(new Label("Position:"),gbc);
	    gbc.gridx = 7;
	    p.add(redpx,gbc);
	    gbc.gridx = 8;
	    p.add(new Label("X"),gbc);
	    gbc.gridx = 9;
	    p.add(redpy,gbc);
	    gbc.gridx = 10;
	    p.add(new Label("Y"),gbc);
	    gbc.gridx = 11;
	    p.add(redpz,gbc);
	    gbc.gridx = 12;
	    p.add(new Label("Z"),gbc);
	    gbc.gridx = 13;
	    p.add(new Label("Velocity:"),gbc);
	    gbc.gridx = 14;
	    p.add(redvx,gbc);
	    gbc.gridx = 15;
	    p.add(new Label("X"),gbc);
	    gbc.gridx = 16;
	    p.add(redvy,gbc);
	    gbc.gridx = 17;
	    p.add(new Label("Y"),gbc);
	    gbc.gridx = 18;
	    p.add(redvz,gbc);
	    gbc.gridx = 19;
	    p.add(new Label("Z"),gbc);
	    gbc.gridx = 20;
	    p.add(new Label("Mass"),gbc);
	    gbc.gridx = 21;
	    p.add(redm,gbc);
	    gbc.gridx = 5;
	    gbc.gridy = 2;
		p.add(new Label("Green"),gbc);
	    gbc.gridx = 6;
	    p.add(new Label("Position:"),gbc);
	    gbc.gridx = 7;
	    p.add(greenpx,gbc);
	    gbc.gridx = 8;
	    p.add(new Label("X"),gbc);
	    gbc.gridx = 9;
	    p.add(greenpy,gbc);
	    gbc.gridx = 10;
	    p.add(new Label("Y"),gbc);
	    gbc.gridx = 11;
	    p.add(greenpz,gbc);
	    gbc.gridx = 12;
	    p.add(new Label("Z"),gbc);
	    gbc.gridx = 13;
	    p.add(new Label("Velocity:"),gbc);
	    gbc.gridx = 14;
	    p.add(greenvx,gbc);
	    gbc.gridx = 15;
	    p.add(new Label("X"),gbc);
	    gbc.gridx = 16;
	    p.add(greenvy,gbc);
	    gbc.gridx = 17;
	    p.add(new Label("Y"),gbc);
	    gbc.gridx = 18;
	    p.add(greenvz,gbc);
	    gbc.gridx = 19;
	    p.add(new Label("Z"),gbc);
	    gbc.gridx = 20;
	    p.add(new Label("Mass"),gbc);
	    gbc.gridx = 21;
	    p.add(greenm,gbc);
	    gbc.gridx = 5;
	    gbc.gridy = 3;
		p.add(new Label("Blue"),gbc);
	    gbc.gridx = 6;
	    p.add(new Label("Position:"),gbc);
	    gbc.gridx = 7;
	    p.add(bluepx,gbc);
	    gbc.gridx = 8;
	    p.add(new Label("X"),gbc);
	    gbc.gridx = 9;
	    p.add(bluepy,gbc);
	    gbc.gridx = 10;
	    p.add(new Label("Y"),gbc);
	    gbc.gridx = 11;
	    p.add(bluepz,gbc);
	    gbc.gridx = 12;
	    p.add(new Label("Z"),gbc);
	    gbc.gridx = 13;
	    p.add(new Label("Velocity:"),gbc);
	    gbc.gridx = 14;
	    p.add(bluevx,gbc);
	    gbc.gridx = 15;
	    p.add(new Label("X"),gbc);
	    gbc.gridx = 16;
	    p.add(bluevy,gbc);
	    gbc.gridx = 17;
	    p.add(new Label("Y"),gbc);
	    gbc.gridx = 18;
	    p.add(bluevz,gbc);
	    gbc.gridx = 19;
	    p.add(new Label("Z"),gbc);
	    gbc.gridx = 20;
	    p.add(new Label("Mass"),gbc);
	    gbc.gridx = 21;
	    p.add(bluem,gbc);
	    start.addActionListener(this);
	    start.addKeyListener(this);
	    return p;
	}
	
	//create a sphere
	public Sphere CreatePractice(Color3f color){
		Sphere practices = new Sphere(0.1f);
		Material practicem = new Material(color,new Color3f(0, 0, 0), new Color3f(1,1,1), new Color3f(1,1,1), 64);
		Appearance practicea = new Appearance();
		practicem.setLightingEnable(true);
		practicea.setMaterial(practicem);
		practices.setAppearance(practicea);
		return practices;
	}
	
	public void keyPressed(KeyEvent e) {
	}
	
	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}
	  
	//start thread
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == start) {
			if (!timer.isRunning()) {
				timer.start();
			}
		}
		else{
			//move the three body
			redtrans.setTranslation(new Vector3d(m.red.position[0], m.red.position[1], m.red.position[2]));
			red.setTransform(redtrans);
			greentrans.setTranslation(new Vector3d(m.green.position[0], m.green.position[1], m.green.position[2]));
			green.setTransform(greentrans);
			bluetrans.setTranslation(new Vector3d(m.blue.position[0], m.blue.position[1], m.blue.position[2]));
			blue.setTransform(bluetrans);
			
			m.tempred = m.red;
			m.tempgreen = m.green;
			m.tempblue = m.blue;
			m.initPsored(m.red, m.green, m.blue);
			m.initPsogreen(m.green, m.red, m.blue);
			m.initPsoblue(m.blue, m.green, m.red);
			//move particles
			for(int i=0;i<m.g;i++){
				new Thread(m).start();
				//red
				redparticletrans0.setTranslation(new Vector3d(m.redx[0][0], m.redx[0][1], m.redx[0][2]));
				redparticle0.setTransform(redparticletrans0);
				redparticletrans1.setTranslation(new Vector3d(m.redx[1][0], m.redx[1][1], m.redx[1][2]));
				redparticle1.setTransform(redparticletrans1);
				redparticletrans2.setTranslation(new Vector3d(m.redx[2][0], m.redx[2][1], m.redx[2][2]));
				redparticle2.setTransform(redparticletrans2);
				redparticletrans3.setTranslation(new Vector3d(m.redx[3][0], m.redx[3][1], m.redx[3][2]));
				redparticle3.setTransform(redparticletrans3);
				redparticletrans4.setTranslation(new Vector3d(m.redx[4][0], m.redx[4][1], m.redx[4][2]));
				redparticle4.setTransform(redparticletrans4);
				redparticletrans5.setTranslation(new Vector3d(m.redx[5][0], m.redx[5][1], m.redx[5][2]));
				redparticle5.setTransform(redparticletrans5);
				redparticletrans6.setTranslation(new Vector3d(m.redx[6][0], m.redx[6][1], m.redx[6][2]));
				redparticle6.setTransform(redparticletrans6);
				redparticletrans7.setTranslation(new Vector3d(m.redx[7][0], m.redx[7][1], m.redx[7][2]));
				redparticle7.setTransform(redparticletrans7);
				redparticletrans8.setTranslation(new Vector3d(m.redx[8][0], m.redx[8][1], m.redx[8][2]));
				redparticle8.setTransform(redparticletrans8);
				redparticletrans9.setTranslation(new Vector3d(m.redx[9][0], m.redx[9][1], m.redx[9][2]));
				redparticle9.setTransform(redparticletrans9);
				redparticletrans10.setTranslation(new Vector3d(m.redx[10][0], m.redx[10][1], m.redx[10][2]));
				redparticle10.setTransform(redparticletrans10);
				redparticletrans11.setTranslation(new Vector3d(m.redx[11][0], m.redx[11][1], m.redx[11][2]));
				redparticle11.setTransform(redparticletrans11);
				redparticletrans12.setTranslation(new Vector3d(m.redx[12][0], m.redx[12][1], m.redx[12][2]));
				redparticle12.setTransform(redparticletrans12);
				redparticletrans13.setTranslation(new Vector3d(m.redx[13][0], m.redx[13][1], m.redx[13][2]));
				redparticle13.setTransform(redparticletrans13);
				redparticletrans14.setTranslation(new Vector3d(m.redx[14][0], m.redx[14][1], m.redx[14][2]));
				redparticle14.setTransform(redparticletrans14);
				//green
				greenparticletrans0.setTranslation(new Vector3d(m.greenx[0][0], m.greenx[0][1], m.greenx[0][2]));
				greenparticle0.setTransform(greenparticletrans0);
				greenparticletrans1.setTranslation(new Vector3d(m.greenx[1][0], m.greenx[1][1], m.greenx[1][2]));
				greenparticle1.setTransform(greenparticletrans1);
				greenparticletrans2.setTranslation(new Vector3d(m.greenx[2][0], m.greenx[2][1], m.greenx[2][2]));
				greenparticle2.setTransform(greenparticletrans2);
				greenparticletrans3.setTranslation(new Vector3d(m.greenx[3][0], m.greenx[3][1], m.greenx[3][2]));
				greenparticle3.setTransform(greenparticletrans3);
				greenparticletrans4.setTranslation(new Vector3d(m.greenx[4][0], m.greenx[4][1], m.greenx[4][2]));
				greenparticle4.setTransform(greenparticletrans4);
				greenparticletrans5.setTranslation(new Vector3d(m.greenx[5][0], m.greenx[5][1], m.greenx[5][2]));
				greenparticle5.setTransform(greenparticletrans5);
				greenparticletrans6.setTranslation(new Vector3d(m.greenx[6][0], m.greenx[6][1], m.greenx[6][2]));
				greenparticle6.setTransform(greenparticletrans6);
				greenparticletrans7.setTranslation(new Vector3d(m.greenx[7][0], m.greenx[7][1], m.greenx[7][2]));
				greenparticle7.setTransform(greenparticletrans7);
				greenparticletrans8.setTranslation(new Vector3d(m.greenx[8][0], m.greenx[8][1], m.greenx[8][2]));
				greenparticle8.setTransform(greenparticletrans8);
				greenparticletrans9.setTranslation(new Vector3d(m.greenx[9][0], m.greenx[9][1], m.greenx[9][2]));
				greenparticle9.setTransform(greenparticletrans9);
				greenparticletrans10.setTranslation(new Vector3d(m.greenx[10][0], m.greenx[10][1], m.greenx[10][2]));
				greenparticle10.setTransform(greenparticletrans10);
				greenparticletrans11.setTranslation(new Vector3d(m.greenx[11][0], m.greenx[11][1], m.greenx[11][2]));
				greenparticle11.setTransform(greenparticletrans11);
				greenparticletrans12.setTranslation(new Vector3d(m.greenx[12][0], m.greenx[12][1], m.greenx[12][2]));
				greenparticle12.setTransform(greenparticletrans12);
				greenparticletrans13.setTranslation(new Vector3d(m.greenx[13][0], m.greenx[13][1], m.greenx[13][2]));
				greenparticle13.setTransform(greenparticletrans13);
				greenparticletrans14.setTranslation(new Vector3d(m.greenx[14][0], m.greenx[14][1], m.greenx[14][2]));
				greenparticle14.setTransform(greenparticletrans14);
				//blue
				blueparticletrans0.setTranslation(new Vector3d(m.bluex[0][0], m.bluex[0][1], m.bluex[0][2]));
				blueparticle0.setTransform(blueparticletrans0);
				blueparticletrans1.setTranslation(new Vector3d(m.bluex[1][0], m.bluex[1][1], m.bluex[1][2]));
				blueparticle1.setTransform(blueparticletrans1);
				blueparticletrans2.setTranslation(new Vector3d(m.bluex[2][0], m.bluex[2][1], m.bluex[2][2]));
				blueparticle2.setTransform(blueparticletrans2);
				blueparticletrans3.setTranslation(new Vector3d(m.bluex[3][0], m.bluex[3][1], m.bluex[3][2]));
				blueparticle3.setTransform(blueparticletrans3);
				blueparticletrans4.setTranslation(new Vector3d(m.bluex[4][0], m.bluex[4][1], m.bluex[4][2]));
				blueparticle4.setTransform(blueparticletrans4);
				blueparticletrans5.setTranslation(new Vector3d(m.bluex[5][0], m.bluex[5][1], m.bluex[5][2]));
				blueparticle5.setTransform(blueparticletrans5);
				blueparticletrans6.setTranslation(new Vector3d(m.bluex[6][0], m.bluex[6][1], m.bluex[6][2]));
				blueparticle6.setTransform(blueparticletrans6);
				blueparticletrans7.setTranslation(new Vector3d(m.bluex[7][0], m.bluex[7][1], m.bluex[7][2]));
				blueparticle7.setTransform(blueparticletrans7);
				blueparticletrans8.setTranslation(new Vector3d(m.bluex[8][0], m.bluex[8][1], m.bluex[8][2]));
				blueparticle8.setTransform(blueparticletrans8);
				blueparticletrans9.setTranslation(new Vector3d(m.bluex[9][0], m.bluex[9][1], m.bluex[9][2]));
				blueparticle9.setTransform(blueparticletrans9);
				blueparticletrans10.setTranslation(new Vector3d(m.bluex[10][0], m.bluex[10][1], m.bluex[10][2]));
				blueparticle10.setTransform(blueparticletrans10);
				blueparticletrans11.setTranslation(new Vector3d(m.bluex[11][0], m.bluex[11][1], m.bluex[11][2]));
				blueparticle11.setTransform(blueparticletrans11);
				blueparticletrans12.setTranslation(new Vector3d(m.bluex[12][0], m.bluex[12][1], m.bluex[12][2]));
				blueparticle12.setTransform(blueparticletrans12);
				blueparticletrans13.setTranslation(new Vector3d(m.bluex[13][0], m.bluex[13][1], m.bluex[13][2]));
				blueparticle13.setTransform(blueparticletrans13);
				blueparticletrans14.setTranslation(new Vector3d(m.bluex[14][0], m.bluex[14][1], m.bluex[14][2]));
				blueparticle14.setTransform(blueparticletrans14);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			//updata position
			m.red.setPosition(m.redgbest);
			m.green.setPosition(m.greengbest);
			m.blue.setPosition(m.bluegbest);
			//update velocity
			m.red.setVelocity(m.getVelocity(m.tempred,m.red));
			m.green.setVelocity(m.getVelocity(m.tempgreen,m.green));
			m.blue.setVelocity(m.getVelocity(m.tempblue,m.blue));
		}
	}
	
	public static void main(String[] args) {
	    DrawAnimation bb = new DrawAnimation();
	    //create the window          size of the window
		MainFrame mf = new MainFrame(bb, 1200, 650);
	    mf.setTitle("PSO on Three-Body Problem - Wenbo Sun 001994516");
	}
}
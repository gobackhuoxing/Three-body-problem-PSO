package threeBody;
import java.util.TimerTask;
//class to caculate the motion
public class Motion extends TimerTask{
	int n=15; 				//15 agents for each body, totaly 45 agents;
	int g =200;				//PSO loop times
	int scope = 1;          //PSO scope
	double w = 0.729; 		//inertia weight
    double c1 = 1.49445; 	//cognitive/local weight
    double c2 = 1.49445; 	//social/global weight
    double[][] v = new double[n][3];
    double[] vmax = new double[]{scope*0.01,scope*0.01,scope*0.01};
	double[] vmin = new double[]{-scope*0.01,-scope*0.01,-scope*0.01};
	
	double[][] redpbest = new double[n][];		//best for one priticle
    double[] redgbest;							//best for all priticles
    double[][] redx = new double[n][3];
	double[] redapproximatePosition;
	double[] redxmax;
	double[] redxmin;
	int redcount = 0; 			//control the PSO loop
	
	double[][] greenpbest = new double[n][];		//best for one priticle
    double[] greengbest;							//best for all priticles
    double[][] greenx = new double[n][3];
	double[] greenapproximatePosition;
	double[] greenxmax;
	double[] greenxmin;
	int greencount = 0; 			//control the PSO loop
	
	double[][] bluepbest = new double[n][];		//best for one priticle
    double[] bluegbest;							//best for all priticles
    double[][] bluex = new double[n][3];
	double[] blueapproximatePosition;
	double[] bluexmax;
	double[] bluexmin;
	int bluecount = 0; 			//control the PSO loop
    
	double t = 0.001;		//second(s)
	double[] redposition; 	//meter(m)
	double[] redvelocity; 	//meter/second(m/s)
	double redmass;			//kiloton(kt)
	double[] greenposition;
	double[] greenvelocity;
	double greenmass;
	double[] blueposition;
	double[] bluevelocity;
	double bluemass;
	Body red;
	Body green;
	Body blue;
	Body tempred;
	Body tempgreen;
	Body tempblue;
	
	//init body
	public void init(){
		redposition = new double[] {2,0,0};
		redvelocity = new double[] {0,0.1,0};
		redmass = 1;
		greenposition = new double[] {0,2,0};
		greenvelocity = new double[] {0,0,0.1};
		greenmass = 1;
		blueposition = new double[] {0,0,2};
		bluevelocity = new double[] {0.1,0,0};
		bluemass = 1;
		
		red = new Body(redposition,redvelocity,redmass);
		green = new Body(greenposition,greenvelocity,greenmass);
		blue = new Body(blueposition,bluevelocity,bluemass); 
	}
	
	//get the distance between two point
	public double getDistance(double[] p1,double[] p2){
		double distance = Math.pow(Math.pow(p1[0]-p2[0], 2)
				+Math.pow(p1[1]-p2[1], 2)
				+Math.pow(p1[2]-p1[2], 2),1.0/2);
		return distance;
	}
	
	//get the acceleration for body tar
	public double[] getAcceleration(Body tar,Body b){
		// adjust the G to make the motivition more Observable
		double G = 6.67*Math.pow(10,-2);
		double distance = getDistance(tar.position,b.position);
		double acceleration = G*b.mass*Math.pow(10, 6)/Math.pow(distance, 2);
		double accelerations[] = new double[]{acceleration*(b.position[0]-tar.position[0])/distance,
				acceleration*(b.position[1]-tar.position[1])/distance,
				acceleration*(b.position[2]-tar.position[2])/distance};
		return accelerations;
	}
	
	//get velocity for next viewpoint(next 0.1 ms)
	public double[] getVelocity(Body tar,Body b1, Body b2){
		double acc[] = new double[]{getAcceleration(tar,b1)[0]+getAcceleration(tar,b2)[0],
				getAcceleration(tar,b1)[1]+getAcceleration(tar,b2)[1],
				getAcceleration(tar,b1)[2]+getAcceleration(tar,b2)[2]};
		
		double velocity[] = new double[]{ tar.velocity[0]+acc[0]*t,
				tar.velocity[1]+acc[1]*t,
				tar.velocity[2]+acc[2]*t};
		return velocity;
	}
	
	//get the approximate position for next viewpoint (next 0.1 ms)
	public double[] getPosition(Body tar,Body b1, Body b2){
		double acc[] = new double[]{getAcceleration(tar,b1)[0]+getAcceleration(tar,b2)[0],
				getAcceleration(tar,b1)[1]+getAcceleration(tar,b2)[1],
				getAcceleration(tar,b1)[2]+getAcceleration(tar,b2)[2]};
		
		double position[]=new double[]{tar.position[0]+tar.velocity[0]*t+acc[0]*Math.pow(t, 2)/2,
				tar.position[1]+tar.velocity[1]*t+acc[1]*Math.pow(t, 2)/2,
				tar.position[2]+tar.velocity[2]*t+acc[2]*Math.pow(t, 2)/2};
		return position;
	}
	
	//init PSO for red
	public void initPsored(Body tar,Body b1,Body b2){
		redapproximatePosition= getPosition(tar,b1,b2);
		
		//init redgbest with a very bad position
		redgbest = new double[]{-redapproximatePosition[0],-redapproximatePosition[2],-redapproximatePosition[2]};
		//range of redx
		redxmax = new double[]{tar.position[0]+scope,tar.position[1]+scope,tar.position[2]+scope};
		redxmin = new double[]{tar.position[0]-scope,tar.position[1]-scope,tar.position[2]-scope};
		//range of v
		
		//init the redx y, redpbest and redgbest
		for(int i = 0;i<n;i++){
			redx[i] = new double[] {scope*2*Math.random()+ redxmin[0],
								 scope*2*Math.random()+ redxmin[1],
								 scope*2*Math.random()+ redxmin[2]};
				
			v[i] = new double[] {scope*2*0.01*Math.random()+vmin[0],
								 scope*2*0.01*Math.random()+vmin[1],
								 scope*2*0.01*Math.random()+vmin[2]};
			redpbest[i]=redx[i];
			if(getDistance(redgbest,redapproximatePosition)>getDistance(redpbest[i],redapproximatePosition)) redgbest = redpbest[i];
		}
	}
	
	//main pso for red
	public void psored(Body tar,Body b1,Body b2){
		int i = redcount;
		v[i] = new double[] {w*v[i][0]+c1*Math.random()*(redpbest[i][0]-redx[i][0])+c2*Math.random()*(redgbest[0]-redx[i][0]),
				w*v[i][1]+c1*Math.random()*(redpbest[i][1]-redx[i][1])+c2*Math.random()*(redgbest[1]-redx[i][1]),
				w*v[i][2]+c1*Math.random()*(redpbest[i][2]-redx[i][2])+c2*Math.random()*(redgbest[2]-redx[i][2])};
		//keep the v in range
		if(v[i][0]>vmax[0]) v[i][0]=vmax[0];
		else if(v[i][0]<vmin[0]) v[i][0]=vmin[0];
		if(v[i][1]>vmax[1]) v[i][1]=vmax[1];
		else if(v[i][1]<vmin[1]) v[i][1]=vmin[1];
		if(v[i][2]>vmax[2]) v[i][2]=vmax[2];
		else if(v[i][2]<vmin[2]) v[i][2]=vmin[2];
		//use v to get the new position
		redx[i][0]+=v[i][0];
		redx[i][1]+=v[i][1];
		redx[i][2]+=v[i][2];
		//keep the redx in range
		if(redx[i][0]>redxmax[0]) v[i][0]=redxmax[0];
		else if(redx[i][0]<redxmin[0]) v[i][0]=redxmin[0];
		if(redx[i][1]>redxmax[1]) v[i][1]=redxmax[1];
		else if(redx[i][1]<redxmin[1]) v[i][1]=redxmin[1];
		if(redx[i][2]>redxmax[2]) v[i][2]=redxmax[2];
		else if(redx[i][2]<redxmin[2]) v[i][2]=redxmin[2];
		//update redpbest and redgbest
		if(getDistance(redpbest[i],redapproximatePosition)>getDistance(redx[i],redapproximatePosition)) redpbest[i]=redx[i];
		if(getDistance(redgbest,redapproximatePosition)>getDistance(redpbest[i],redapproximatePosition)) redgbest = redpbest[i];
		
	}
	
	//init PSO for green
	public void initPsogreen(Body tar,Body b1,Body b2){
		greenapproximatePosition= getPosition(tar,b1,b2);
		
		//init greengbest with a very bad position
		greengbest = new double[]{-greenapproximatePosition[0],-greenapproximatePosition[2],-greenapproximatePosition[2]};
		//range of greenx
		greenxmax = new double[]{tar.position[0]+scope,tar.position[1]+scope,tar.position[2]+scope};
		greenxmin = new double[]{tar.position[0]-scope,tar.position[1]-scope,tar.position[2]-scope};
		//range of v
		
		//init the greenx y, greenpbest and greengbest
		for(int i = 0;i<n;i++){
			greenx[i] = new double[] {scope*2*Math.random()+ greenxmin[0],
								 scope*2*Math.random()+ greenxmin[1],
								 scope*2*Math.random()+ greenxmin[2]};
				
			v[i] = new double[] {scope*2*0.01*Math.random()+vmin[0],
								 scope*2*0.01*Math.random()+vmin[1],
								 scope*2*0.01*Math.random()+vmin[2]};
			greenpbest[i]=greenx[i];
			if(getDistance(greengbest,greenapproximatePosition)>getDistance(greenpbest[i],greenapproximatePosition)) greengbest = greenpbest[i];
		}
	}
	
	//main pso for green
	public void psogreen(Body tar,Body b1,Body b2){
		int i = greencount;
		v[i] = new double[] {w*v[i][0]+c1*Math.random()*(greenpbest[i][0]-greenx[i][0])+c2*Math.random()*(greengbest[0]-greenx[i][0]),
				w*v[i][1]+c1*Math.random()*(greenpbest[i][1]-greenx[i][1])+c2*Math.random()*(greengbest[1]-greenx[i][1]),
				w*v[i][2]+c1*Math.random()*(greenpbest[i][2]-greenx[i][2])+c2*Math.random()*(greengbest[2]-greenx[i][2])};
		//keep the v in range
		if(v[i][0]>vmax[0]) v[i][0]=vmax[0];
		else if(v[i][0]<vmin[0]) v[i][0]=vmin[0];
		if(v[i][1]>vmax[1]) v[i][1]=vmax[1];
		else if(v[i][1]<vmin[1]) v[i][1]=vmin[1];
		if(v[i][2]>vmax[2]) v[i][2]=vmax[2];
		else if(v[i][2]<vmin[2]) v[i][2]=vmin[2];
		//use v to get the new position
		greenx[i][0]+=v[i][0];
		greenx[i][1]+=v[i][1];
		greenx[i][2]+=v[i][2];
		//keep the greenx in range
		if(greenx[i][0]>greenxmax[0]) v[i][0]=greenxmax[0];
		else if(greenx[i][0]<greenxmin[0]) v[i][0]=greenxmin[0];
		if(greenx[i][1]>greenxmax[1]) v[i][1]=greenxmax[1];
		else if(greenx[i][1]<greenxmin[1]) v[i][1]=greenxmin[1];
		if(greenx[i][2]>greenxmax[2]) v[i][2]=greenxmax[2];
		else if(greenx[i][2]<greenxmin[2]) v[i][2]=greenxmin[2];
		//update greenpbest and greengbest
		if(getDistance(greenpbest[i],greenapproximatePosition)>getDistance(greenx[i],greenapproximatePosition)) greenpbest[i]=greenx[i];
		if(getDistance(greengbest,greenapproximatePosition)>getDistance(greenpbest[i],greenapproximatePosition)) greengbest = greenpbest[i];
		
	}
	
	//init PSO for blue
	public void initPsoblue(Body tar,Body b1,Body b2){
		blueapproximatePosition= getPosition(tar,b1,b2);
		
		//init bluegbest with a very bad position
		bluegbest = new double[]{-blueapproximatePosition[0],-blueapproximatePosition[2],-blueapproximatePosition[2]};
		//range of bluex
		bluexmax = new double[]{tar.position[0]+scope,tar.position[1]+scope,tar.position[2]+scope};
		bluexmin = new double[]{tar.position[0]-scope,tar.position[1]-scope,tar.position[2]-scope};
		//range of v
		
		//init the bluex y, bluepbest and bluegbest
		for(int i = 0;i<n;i++){
			bluex[i] = new double[] {scope*2*Math.random()+ bluexmin[0],
								 scope*2*Math.random()+ bluexmin[1],
								 scope*2*Math.random()+ bluexmin[2]};
				
			v[i] = new double[] {scope*2*0.01*Math.random()+vmin[0],
								 scope*2*0.01*Math.random()+vmin[1],
								 scope*2*0.01*Math.random()+vmin[2]};
			bluepbest[i]=bluex[i];
			if(getDistance(bluegbest,blueapproximatePosition)>getDistance(bluepbest[i],blueapproximatePosition)) bluegbest = bluepbest[i];
		}
	}
	
	//main pso for blue
	public void psoblue(Body tar,Body b1,Body b2){
		int i = bluecount;
		v[i] = new double[] {w*v[i][0]+c1*Math.random()*(bluepbest[i][0]-bluex[i][0])+c2*Math.random()*(bluegbest[0]-bluex[i][0]),
				w*v[i][1]+c1*Math.random()*(bluepbest[i][1]-bluex[i][1])+c2*Math.random()*(bluegbest[1]-bluex[i][1]),
				w*v[i][2]+c1*Math.random()*(bluepbest[i][2]-bluex[i][2])+c2*Math.random()*(bluegbest[2]-bluex[i][2])};
		//keep the v in range
		if(v[i][0]>vmax[0]) v[i][0]=vmax[0];
		else if(v[i][0]<vmin[0]) v[i][0]=vmin[0];
		if(v[i][1]>vmax[1]) v[i][1]=vmax[1];
		else if(v[i][1]<vmin[1]) v[i][1]=vmin[1];
		if(v[i][2]>vmax[2]) v[i][2]=vmax[2];
		else if(v[i][2]<vmin[2]) v[i][2]=vmin[2];
		//use v to get the new position
		bluex[i][0]+=v[i][0];
		bluex[i][1]+=v[i][1];
		bluex[i][2]+=v[i][2];
		//keep the bluex in range
		if(bluex[i][0]>bluexmax[0]) v[i][0]=bluexmax[0];
		else if(bluex[i][0]<bluexmin[0]) v[i][0]=bluexmin[0];
		if(bluex[i][1]>bluexmax[1]) v[i][1]=bluexmax[1];
		else if(bluex[i][1]<bluexmin[1]) v[i][1]=bluexmin[1];
		if(bluex[i][2]>bluexmax[2]) v[i][2]=bluexmax[2];
		else if(bluex[i][2]<bluexmin[2]) v[i][2]=bluexmin[2];
		//update bluepbest and bluegbest
		if(getDistance(bluepbest[i],blueapproximatePosition)>getDistance(bluex[i],blueapproximatePosition)) bluepbest[i]=bluex[i];
		if(getDistance(bluegbest,blueapproximatePosition)>getDistance(bluepbest[i],blueapproximatePosition)) bluegbest = bluepbest[i];
		
	}	
	
	// test data
	public void test(){
		init();
		System.out.println(red.toString());
		System.out.println(green.toString());
		System.out.println(blue.toString());
		
		for(int i =0;i<100;i++){
			System.out.println(i);
			tempred = red;
			tempgreen = green;
			tempblue = blue;
			initPsored(tempred,tempgreen,tempblue);
			initPsogreen(tempgreen,tempred,tempblue);
			initPsoblue(tempblue,tempred,tempgreen);
			for(int j =0;j<n;j++){
				for(int k=0;k<g;k++){
					//red
					psored(tempred,tempgreen,tempblue);
				
					//green
					psogreen(tempgreen,tempred,tempblue);
				
					//blue
					psoblue(tempblue,tempred,tempgreen);
				}
				redcount++;
				greencount++;
				bluecount++;
			}
			redcount=0;
			greencount=0;
			bluecount=0;
			red.setPosition(redgbest);
			red.setVelocity(getVelocity(tempred,tempgreen,tempblue));
			green.setPosition(greengbest);
			green.setVelocity(getVelocity(tempgreen,tempred,tempblue));
			blue.setPosition(bluegbest);
			blue.setVelocity(getVelocity(tempblue,tempgreen,tempred));
			System.out.println(red.toString());
			System.out.println(green.toString());
			System.out.println(blue.toString());
		}
	}
	
	public void run() {
		synchronized(DrawAnimation.m){
			psored(tempred,tempgreen,tempblue);
			psogreen(tempgreen,tempred,tempblue);
			psoblue(tempblue,tempred,tempgreen);
		}
	}
	
	public static void main(String[] args) {
		Motion m = new Motion();
		m.test();
	}
}

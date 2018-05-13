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
    double[][] pbest = new double[n][];		//best for one priticle
    double[] gbest;			//best for all priticles
    double[][] x = new double[n][3];
	double[][] v = new double[n][3];
    int count = 0; 			//control the PSO loop
    
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
	Body temp1;
	Body temp2;
	Body temp3;
	
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
	
	//main PSO algorithm
	public double[] pso(Body tar,Body b1,Body b2){
		int i = count;
		double[] approximatePosition= getPosition(tar,b1,b2);
		
		//init gbest with a very bad position
		gbest = new double[]{-approximatePosition[0],-approximatePosition[2],-approximatePosition[2]};
		//range of x
		double[] xmax = new double[]{tar.position[0]+scope,tar.position[1]+scope,tar.position[2]+scope};
		double[] xmin = new double[]{tar.position[0]-scope,tar.position[1]-scope,tar.position[2]-scope};
		//range of v
		double[] vmax = new double[]{scope*0.01,scope*0.01,scope*0.01};
		double[] vmin = new double[]{-scope*0.01,-scope*0.01,-scope*0.01};
		//init the x y, pbest and gbest
		
			x[i] = new double[] {scope*2*Math.random()+ xmin[0],
								 scope*2*Math.random()+ xmin[1],
								 scope*2*Math.random()+ xmin[2]};
			
			v[i] = new double[] {scope*2*0.01*Math.random()+vmin[0],
					scope*2*0.01*Math.random()+vmin[1],
					scope*2*0.01*Math.random()+vmin[2]};
			pbest[i]=x[i];
			if(getDistance(gbest,approximatePosition)>getDistance(pbest[i],approximatePosition)) gbest = pbest[i];
		
		//main PSO
		for(int j = 0; j<g;j++){
			
				v[i] = new double[] {w*v[i][0]+c1*Math.random()*(pbest[i][0]-x[i][0])+c2*Math.random()*(gbest[0]-x[i][0]),
						w*v[i][1]+c1*Math.random()*(pbest[i][1]-x[i][1])+c2*Math.random()*(gbest[1]-x[i][1]),
						w*v[i][2]+c1*Math.random()*(pbest[i][2]-x[i][2])+c2*Math.random()*(gbest[2]-x[i][2])};
				//keep the v in range
				if(v[i][0]>vmax[0]) v[i][0]=vmax[0];
				else if(v[i][0]<vmin[0]) v[i][0]=vmin[0];
				if(v[i][1]>vmax[1]) v[i][1]=vmax[1];
				else if(v[i][1]<vmin[1]) v[i][1]=vmin[1];
				if(v[i][2]>vmax[2]) v[i][2]=vmax[2];
				else if(v[i][2]<vmin[2]) v[i][2]=vmin[2];
				//use v to get the new position
				x[i][0]+=v[i][0];
				x[i][1]+=v[i][1];
				x[i][2]+=v[i][2];
				//keep the x in range
				if(x[i][0]>xmax[0]) v[i][0]=xmax[0];
				else if(x[i][0]<xmin[0]) v[i][0]=xmin[0];
				if(x[i][1]>xmax[1]) v[i][1]=xmax[1];
				else if(x[i][1]<xmin[1]) v[i][1]=xmin[1];
				if(x[i][2]>xmax[2]) v[i][2]=xmax[2];
				else if(x[i][2]<xmin[2]) v[i][2]=xmin[2];
				//update pbest and gbest
				if(getDistance(pbest[i],approximatePosition)>getDistance(x[i],approximatePosition)) pbest[i]=x[i];
				if(getDistance(gbest,approximatePosition)>getDistance(pbest[i],approximatePosition)) gbest = pbest[i];
			
		}
		return gbest;
	}
	
	//updata position and velocity
	public void update(Body b1,Body b2,Body b3){
		//save the body to temp
		Body temp1 = b1;
		Body temp2 = b2;
		Body temp3 = b3;
		
		b1.setPosition(pso(temp1,temp2,temp3));
		b1.setVelocity(getVelocity(temp1,temp2,temp3));
		
		b2.setPosition(pso(temp2,temp1,temp3));
		b2.setVelocity(getVelocity(temp2,temp1,temp3));
		
		b3.setPosition(pso(temp3,temp2,temp1));
		b3.setVelocity(getVelocity(temp3,temp2,temp1));
	}
	
	// test data
	public void showAll(){
		init();
		System.out.println(red.toString());
		System.out.println(green.toString());
		System.out.println(blue.toString());
		
		for(int i =0;i<100;i++){
			System.out.println(i);
			update(red,green,blue);
			
			System.out.println(red.toString());
			System.out.println(green.toString());
			System.out.println(blue.toString());
		}
	}

	public void run() {
		if(count==0){
			temp1 = red;
			temp2 = green;
			temp3 = blue;
		}
		pso(temp1,temp2,temp3);
		pso(temp2,temp1,temp3);
		pso(temp3,temp2,temp1);
	}
}

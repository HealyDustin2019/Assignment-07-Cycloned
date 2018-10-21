import java.lang.Math;
import java.util.Scanner;
public class Main{
  public static void main(String[] args){
    String[] a = userInteraction();
    String[] b = userInteraction();
    double x = coordToDouble(a[0]);
    double y = coordToDouble(a[1]);
    double z = coordToDouble(b[0]);
    double w = coordToDouble(b[1]);
    calcDist(x,y,z,w);
  }
  public static String[] userInteraction(){
    System.out.println("Enter Longitude in the following format: #*#\'#\"D\nExamples:\n12*36\'45\"E	-118*48\'21\"W	26*19\'13\"E\nEnter here:");
    Scanner s = new Scanner(System.in);
    String c1 = s.nextLine();
    System.out.print("Enter Latitude in the same fashion: \n");
    String c2 = s.nextLine();
    System.out.println("\n");
    String[] coord = new String[2];
    coord[0] = c1;
    coord[1] = c2;
    return coord;
  }
  public static double coordToDouble(String c1){
    int[] z = new int[3];
    z[0] = 0;
    int y = 0;
    String[] w = new String[3];
    for(int x = 0; x<c1.length();x++){
      if(c1.charAt(x) == '\'' || c1.charAt(x) == '\"' || c1.charAt(x) == '*'){     
        z[y] = x;
        y++;
      }
    }
    w[0] = c1.substring(0,z[0]); 
    w[1] = c1.substring(z[0]+1,z[1]); 
    w[2] = c1.substring(z[1]+1,z[2]);
    int dega = Integer.parseInt(w[0]);
    int mina = Integer.parseInt(w[1]);
    int seca = Integer.parseInt(w[2]);
    if (c1.charAt(c1.length()-1) == 'S' || c1.charAt(c1.length()-1) == 'W')
      dega *= -1;
    if(dega < 0){
      mina *= -1;
      seca *= -1;
    }
    double ans = dega + (mina/60.0) + (seca/(3600.0));
    return ans;
  }
  public static void calcDist(double x, double y, double z, double w){
    double r = 6371;
    x = x/180*Math.PI;
    y = y/180*Math.PI;
    z = z/180*Math.PI;
    w = w/180*Math.PI;
    double dx = x-z;
    double dy = y-w;
    double c = Math.sin(dy/2)*Math.sin(dy/2);
    double d = Math.sin(dx/2)*Math.sin(dx/2);
	double e = c + (Math.cos(y)*Math.cos(w)*d);
    double f = 2 * Math.atan2(Math.sqrt(e),Math.sqrt(1-e));
    double g = r * f;
    System.out.printf("The distance between the two points is ~%.2f kilometers.",g); 
  }
}

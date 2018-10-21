import java.lang.Math;
import java.util.Scanner;
public class Main{
  public static void main(String[] args){
    double distance = 0;
    for(int j = 0;j < 12;j++)
        distance += totalDist();
    System.out.printf("Total Distance Travelled: ~%.2f kilometers",distance);
  }
  public static double totalDist(){
    String[] a = userInteraction();
    String[] b = userInteraction();
    double x = coordToDouble(a[0]);
    double y = coordToDouble(a[1]);
    double z = coordToDouble(b[0]);
    double w = coordToDouble(b[1]);
    return calcDist(x,y,z,w);
  }
  public static String[] userInteraction(){
    System.out.println("Enter Longitude in DMS Format:");
    Scanner s = new Scanner(System.in);
    String c1 = s.nextLine();
    System.out.print("Enter Latitude in DMS Format: \n");
    String c2 = s.nextLine();
    System.out.print("\033[H\033[2J");
    String[] coord = new String[2];
    coord[0] = c1;
    coord[1] = c2;
    return coord;
  }
  public static double coordToDouble(String c1){
    int[] z = new int[3];
    z[0] = 0;
    z[1] = 0;
    z[2] = 0;
    int y = 0;
    String[] w = new String[3];
    for(int x = 0; x<c1.length();x++){
      if(c1.charAt(x) == '\'' || c1.charAt(x) == '\"' || c1.charAt(x) == '*'){     
        z[y] = x;
        y++;
      }
    }
    w[0] = c1.substring(0,z[0]);
    int dega = Integer.parseInt(w[0]);
    int mina = 0;
    int seca = 0;
    if(z[1] != 0){
        w[1] = c1.substring(z[0]+1,z[1]);
        mina = Integer.parseInt(w[1]);
        if(z[2] != 0){
            w[2] = c1.substring(z[1]+1,z[2]);
            seca = Integer.parseInt(w[2]);
        }
    }
    
    
    
    if (c1.charAt(c1.length()-1) == 'S' || c1.charAt(c1.length()-1) == 'W')
      dega *= -1;
    if(dega < 0){
      mina *= -1;
      seca *= -1;
    }
    double ans = dega + (mina/60.0) + (seca/(3600.0));
    return ans;
  }
  public static double calcDist(double x, double y, double z, double w){
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
    System.out.printf("The distance between the two points is ~%.2f kilometers.\n",g);
    return g;
  }
}
/*Results of tracking distance travelled by Hurricane Gloria over a 13 day period:
Day 1: 554.40 kilometers
Day 2: 468.60 kilometers
Day 3: 331.65 kilometers
Day 4: 845.79 kilometers
Day 5: 1170.41 kilometers
Day 6: 1054.44 kilometers
Day 7: 940.67 kilometers
Day 8: 598.93 kilometers
Day 9: 530.30 kilometers
Day 10: 241.15 kilometers
Day 11: 222.39 kilometers
Day 12: 333.58 kilometers
Total kilometers travelled: 7,292.31*/

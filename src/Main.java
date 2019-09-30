import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

public class Main {


	 public static class Take extends JFrame   {
		public static int[] data= new int[400];


		// = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,1,1,1,1,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};


		int x , y;

		public Take(String pop) {

			super (pop);
			this.setSize(400,400);
			this.setVisible(true);

            JPanel mainPanel = new JPanel();
            mainPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);

                    System.out.println(e.getX() +"  "+ e.getY());

                   x = e.getX()/20;
                   y= e.getY()/20;


                   /* if(data[(x)*20+y]==0)
					data[(x)*20+y]=1;
                    else*/
						data[(x)*20+y]=1;


				//	System.out.println(x +"  "+ y);

					NaiveBayesClassifier classifier = new NaiveBayesClassifier(Main.EXAMPLES,
							Main.FEATURES, Main.CLASSES, Main.X, Main.y);

					try {
						Main.read_data();
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					classifier.train();

					System.out.println("Accuracy = " + measure_accuracy(classifier));

					for (int i = 0; i <data.length ; i++) {


						System.out.print(data[i]);

					}


					System.out.println("Predicted digit: "
							+ classifier.classify( data  ));





                }
            });

            this.add(mainPanel);


			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		}


		public void paint(Graphics g){



		    g.setColor(Color.BLUE);



			{



				for (int j = 0; j <data.length ; j++) {

					if(data[j]==1)
					g.fillRect(j/20*20, (j%20)*20, 20, 20);

				}

			}

            repaint();



           // input();


        }


        /*public int[] input (){

		    int[] X = new int[400];
		    int k=0;

            for (int i = 0; i < 20; i++)
                for (int j = 0; j < 20; j++)
                    X[k++] = data[i][j];

		    return X;
        }*/


		/*public Take(String d) {
			super("Digit Display");
			for (int i = 0; i < 20; i++)
				for (int j = 0; j < 20; j++)
					data[i][j] = d[i * 20 + j];
			setSize(400, 400);
			setVisible(true);
		}

		public void paint(Graphics g) {
			for (int i = 0; i < 20; i++)
				for (int j = 0; j < 20; j++) {
					if (data[i][j] == 1)
						g.fillRect(i * 20, j * 20, 20, 20);
				}
		}*/





	}
	final static int EXAMPLES = 5000;
	final static int FEATURES = 400;
	final static int CLASSES = 10;

	public static int X[][] = new int[EXAMPLES][FEATURES];
	public static int y[] = new int[EXAMPLES];

	public static void read_data() throws Exception {
		BufferedReader featureReader = new BufferedReader(new FileReader(
				"data//X.csv"));
		BufferedReader outputReader = new BufferedReader(new FileReader(
				"data//Y.csv"));

		String featureStr = null;
		String outputStr = null;

		int examplesCount = 0;
		while (true) {
			featureStr = featureReader.readLine();
			outputStr = outputReader.readLine();
			if (featureStr == null)
				break;

			int featureCount = 0;

			String[] features = featureStr.split(",");
			for (String f : features) {
				X[examplesCount][featureCount] = Integer.parseInt(f);
				featureCount++;
			}

			y[examplesCount] = Integer.parseInt(outputStr);
			examplesCount++;

		}

		featureReader.close();
		outputReader.close();
	}

	public static void main(String[] args) throws Exception {

		 Take ti = new Take("Draw here");
	/*	read_data();

	//	Random random = new Random();
	//	int randomExampleNumber = random.nextInt(EXAMPLES);

	//	new DigitDisplay(X[randomExampleNumber]);

		NaiveBayesClassifier classifier = new NaiveBayesClassifier(EXAMPLES,
				FEATURES, CLASSES, X, y);

		classifier.train();

		System.out.println("Accuracy = " + measure_accuracy(classifier));

		System.out.println("Predicted digit: "
				+ classifier.classify( Take.data  ));*/

		//System.out.println("\nActual digit: " + y[randomExampleNumber]);*/
	}

	static double measure_accuracy(NaiveBayesClassifier classifier) {

		int RUNS = 100;
		double count = 0;

		for (int i = 0; i < RUNS; ++i) {

			int ex = new Random().nextInt(EXAMPLES);

			if (classifier.classify(X[ex]) == y[ex])
				count++;
		}

		return count / RUNS;
	}
}

@SuppressWarnings("serial")
class DigitDisplay extends JFrame {
	int[][] data = new int[20][20];

	public DigitDisplay(int[] d) {
		super("Digit Display");
		for (int i = 0; i < 20; i++)
			for (int j = 0; j < 20; j++)
				data[i][j] = d[i * 20 + j];
		setSize(400, 400);
		setVisible(true);
	}

	public void paint(Graphics g) {
		for (int i = 0; i < 20; i++)
			for (int j = 0; j < 20; j++) {
				if (data[i][j] == 1)
					g.fillRect(i * 20, j * 20, 20, 20);
			}
	}
}

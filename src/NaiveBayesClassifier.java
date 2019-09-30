import java.util.Scanner;

public class NaiveBayesClassifier {
	int exampleCount;
	int featureCount;
	int classCount;
	int[][] X; // Features of the training examples
	int[] Y; // Classifications of the training examples

	double[] D;
	double[][] P;

	public NaiveBayesClassifier(int exampleCount, int featureCount, int classCount, int[][] f, int[] y) {
		super();
		this.exampleCount = exampleCount;
		this.featureCount = featureCount;
		this.classCount = classCount;
		X = f;
		Y = y;

		D = new double[classCount];
		P = new double[featureCount][classCount];

	}

	public void train() {
		// fill D

		for(int i = 0; i < classCount; ++i)
		{
			int c = 0;
			for (int j = 0; j <exampleCount; ++j){
				// fill D[i]
				// count of i in Y / ExampleCount
				if (Y[j] == i)
					c++;
			}

			D[i] =(double) c / exampleCount;

		}
		// fill P

		for(int i = 0; i < featureCount; ++i)
			for(int j = 0; j < classCount; ++j)
			{
				double count = 0;
				// fill P[i][j]
				for(int k = 0; k < exampleCount; ++k)
				{
					if (X[k][i] == 1 && Y[k] == j)
						count++;



				}

				double p1 =(double) count/exampleCount ;
				double p2 = D[j];
				P[i][j] = p1 / p2;

			}
	}

	public  int classify(int[] features) {


		//double[] probX = new double[classCount];


		double maxp=0;
		double px;
		int maxd=0;

		for (int i = 0; i <classCount ; i++) {

			//	maxp=D[i];

			//maxd=i;

			px =D[i];


			for (int j = 0; j <featureCount ; j++) {



				if(features[j]==1) {
					px *= P[j][i];


				}
				else{

					px *= (1-P[j][i]);

				}


			}

			System.out.println("px="+px+"i="+i);
			if(px>maxp){
				maxp=px;
				maxd=i;
			}

		}

		return maxd;
	}
}

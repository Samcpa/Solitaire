public class Puissance4
{

	public void matrice(int nbLigne)
	{
		char [][] puiss4= new char[nbLigne][];
		for(int i = 0; i < puiss4.length; i++)
		{
			puiss4[i] = new char[puiss4.length];
		}
		
		for (int i = 0; i < puiss4.length; i++) 
		{
			for (int j = 0; j < puiss4.length; j++) 
			{
				puiss4[i][j] = 'X';
				System.out.print(puiss4[i][j]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args)
    {
		Puissance4 m = new Puissance4();
		m.matrice(4);
	}
	
}
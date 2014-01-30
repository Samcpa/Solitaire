package Plateau;

public class Solitaire {
	
	public static void main(String[] args)
    {
		Plateau m = new Plateau();
		m.setDimensions(5);
		m.debutPartie();
		m.deplacement();
	}
}
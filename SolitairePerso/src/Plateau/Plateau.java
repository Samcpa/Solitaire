package Plateau;

import java.util.Scanner;

public class Plateau extends Pion {
	/*
	 * [i] = lignes/ordonnee [j] = colonnes/abscisse
	 */

	private int dimensions;
	int[][] plateau;
	private Pion bille = new Pion();
	private int nbPions;
	private int orDepart;
	private int absDepart;
	private int ordArriv;
	private int absArriv;
	private String pion;

	// RETOURNE LES DIMENSIONS DU TABLIER
	public int getDimensions()
	{
		return dimensions;
	}

	// DEFINIT LES DIMENSIONS DU TABLIER
	public void setDimensions(int dimension)
	{
		dimensions = dimension;
	}

	// RETOURNE ORDONNEE DE DEPART DU PION
	public int getOrDepart()
	{
		return orDepart;
	}

	// DETERMINE L'ORDONNEE DE DEPART DU PION
	public void setOrDepart(int orDepart)
	{
		this.orDepart = orDepart;
	}

	// RETOURNE L'ABSCISSE DE DEPART DU PION
	public int getAbsDepart()
	{
		return absDepart;
	}

	// DETERMINE L'ABSCISSE DE DEPART DU PION
	public void setAbsDepart(int absDepart)
	{
		this.absDepart = absDepart;
	}

	// RETOURNE ORDONNEE D'ARRIVEE DU PION
	public int getOrArriv()
	{
		return ordArriv;
	}

	// DETERMINE L'ORDONNEE D'ARRIVEE DU PION
	public void setOrArriv(int orArriv)
	{
		this.ordArriv = orArriv;
	}

	// RETOURNE L'ABSCISSE D'ARRIVEE DU PION
	public int getAbsArriv()
	{
		return absArriv;
	}

	// DETERMINE L'ABSCISSE D'ARRIVEE DU PION
	public void setAbsArriv(int absArriv)
	{
		this.absArriv = absArriv;
	}

	public int getPlateau(int x, int y)
	{
		return plateau[x][y];
	}

	// DETERMINE L'ABSCISSE D'ARRIVEE DU PION
	public void setPlateau(int x, int y, int valeur)
	{
		plateau[x][y] = valeur;
	}

	
	// RETOURNE LE NOMBE DE PIONS PRESENTS SUR LE TABLIER
	public int getNbPions()
	{
		return nbPions;
	}

	// DETERMINE LE NOMBRE DE PIONS SUR LE TABLIER
	public void setNbPions(int nbPions)
	{
		this.nbPions = nbPions;
	}
	
	public String getPion()
	{
		return pion;
	}

	// DEFINIT LES CARACTERES A AFFICHER
	public void setPion(String pion)
	{
		this.pion = pion;
	}

	// VERIFIFE QUE LA CASE CIBLE EST LIBRE ET QUE LA CASE INTERMEDIAIRE EST OCCUPEE
	private boolean deplacePossible (int y, int x, int yinter, int xinter)
	{
		if ((plateau[y][x] != 1 && plateau[yinter][xinter] == 1))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//
	private boolean ecart ()
	{
		if ((getAbsArriv() - getAbsDepart() == 2 || getAbsArriv() - getAbsDepart() == -2) || (getOrArriv() - getOrDepart() == 2 || getOrArriv() - getOrDepart() == -2))
		{
			return true ;
		}
		else
		{
			return false;
		}
	}
	
	// CREATION DU TABLIER (MATRICE)
	private void Tablier()
	{
		plateau = new int [getDimensions()][getDimensions()];
		setPion("X ");
		for (int i = 0; i < plateau.length; i++)
		{
			plateau[i] = new int[plateau.length];
		}
	}

	// AFFICHE LE TABLIER
	private void affiche()
	{
		for (int i = getDimensions() - 1; i >= 0; i--)
		{
			for (int j = 0; j < getDimensions(); j++)
			{
				if(plateau[i][j] == 1)
				{
					System.out.print(getPion());
				}
				else
				{
					System.out.print("  ");
				}
			}
			System.out.println();
		}
	}

	// DISPOSITION DE PIONS AU DEBUT DE PARTIE
	public void debutPartie()
	{
		Tablier();

		for (int i = getDimensions() - 1; i >= 0; i--)
		{
			for (int j = 0; j < getDimensions(); j++)
			{
				bille.setVal(1);
				plateau[i][j] = bille.getVal();

				int milieuLigne = getDimensions() / 2;
				milieuLigne = getDimensions() - milieuLigne;

				int milieuColonne = getDimensions() / 2;
				milieuColonne = getDimensions() - milieuColonne;

				plateau[milieuLigne - 1][milieuColonne - 1] = 0;

				if(plateau[i][j] == 1)
				{
					System.out.print(getPion());
				}
				else
				{
					System.out.print("  ");
				}
			}
			System.out.println();
		}

		int pions = (getDimensions() * getDimensions()) - 1;
		setNbPions(pions);
		System.out.println("\n" + pions + " pions \n");
	}

	// DETERMINE LE TYPE DE DEPLACEMENET SELON LES COORDONNEES SAISIES
	private void typeDeplacement() 
	{
		// SAISIE COORDONNEES PAR L'UTILISATEUR
		Scanner sc = new Scanner(System.in);

		try
		{
			System.out.println("Ordonnée de départ de votre pion");
			setOrDepart(sc.nextInt());

			System.out.println("Abscisse de départ de votre pion");
			setAbsDepart(sc.nextInt());

			System.out.println("Ordonnée d'arrivée de votre pion");
			setOrArriv(sc.nextInt());

			System.out.println("Abscisse d'arrivée de votre pion");
			setAbsArriv(sc.nextInt());
		}

		catch (Exception e)
		{
			System.out.println("\nVous devez saisir un chiffre!\n");
		}
	}

	// EFFECTUE LE DEPLACEMENT VOULU PAR L'UTILISATEUR SELON LES REGLES DU JEU
	public void deplacement()
	{
		while (getNbPions() > 1)
		{
			try
			{
				typeDeplacement();
				
				// RECUPERE LE TYPE DE DEPLACEMENT
				// VERIFIE QUE LA CASE EST LIBRE
				// VERIFIE QUE LE DEPLACEMENT NE DEPASSE PAS 2 CASES

				int ordInter = (ordArriv + orDepart) / 2;
				int absInter = (absArriv + absDepart) / 2;
				
				if ((deplacePossible(getOrArriv(), getAbsArriv(), ordInter, absInter) == true) 
						&& (ecart() == true))
				{
					// LA CASE CIBLE DEVIENT OCCUPEE
					bille.setVal(1);
					setPlateau(ordArriv, absArriv, bille.getVal());

					// LE PION MANGE DISPARAIT ET LA CASE DE DEPART EST VIDE
					bille.setVal(0);
					setPlateau(orDepart, absDepart, bille.getVal());
					setPlateau(ordInter, absInter, bille.getVal());
					
					// MISE A JOUR DU NOMBRE DE PIONS RESTANTS
					setNbPions(getNbPions() - 1);
				}
				
				else 
				{
					System.out.println("\nDéplacement impossible\nRejouez\n");
				}

				affiche();

				// MISE A JOUR DU NOMBRE DE PIONS
				System.out.println("\n" + getNbPions() + " pions \n");
				deplacement();
			}

			catch (java.lang.ArrayIndexOutOfBoundsException e)
			{
				System.out.println("\nVous sortez du tablier!\n Rejouez\n");
				affiche();
			}
		}
		System.out.println("\nPartie terminée\n");
	}
}
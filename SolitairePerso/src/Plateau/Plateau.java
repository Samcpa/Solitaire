package Plateau;

import java.util.Scanner;

public class Plateau extends Pion {
	/*
	 * [i] = lignes/ordonnee [j] = colonnes/abscisse
	 */

	private int dimensions;
	String[][] plateau;
	private Pion bille = new Pion();
	private int nbPions;
	private int orDepart;
	private int absDepart;
	private int ordArriv;
	private int absArriv;
	private int typeDeplacement;

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

	// RETOURNE LE TYPE DE DEPLACEMENT
	public int getTypeDeplacement()
	{
		return typeDeplacement;
	}

	// DETERMINE LE TYPE DE DEPLACEMENT
	public void setTypeDeplacement(int typeDeplacement)
	{
		this.typeDeplacement = typeDeplacement;
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

	// CREATION DU TABLIER (MATRICE)
	public void Tablier()
	{
		plateau = new String[getDimensions()][getDimensions()];

		for (int i = 0; i < plateau.length; i++)
		{
			plateau[i] = new String[plateau.length];
		}
	}

	// AFFICHE LE TABLIER
	public void affiche()
	{
		for (int i = getDimensions() - 1; i >= 0; i--)
		{
			for (int j = 0; j < getDimensions(); j++)
			{
				System.out.print(plateau[i][j]);
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
				bille.setVal("X ");
				plateau[i][j] = bille.getVal();

				int milieuLigne = getDimensions() / 2;
				milieuLigne = getDimensions() - milieuLigne;

				int milieuColonne = getDimensions() / 2;
				milieuColonne = getDimensions() - milieuColonne;

				plateau[milieuLigne - 1][milieuColonne - 1] = "  ";

				System.out.print(plateau[i][j]);
			}
			System.out.println();
		}

		int pions = (getDimensions() * getDimensions()) - 1;
		setNbPions(pions);
		System.out.println("\n" + pions + " pions \n");
	}

	// DETERMINE LE TYPE DE DEPLACEMENET SELON LES COORDONNEES SAISIES
	@SuppressWarnings("resource")
	public int typeDeplacement() 
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

		if (getOrDepart() == getOrArriv() && getAbsArriv() > getAbsDepart())
		{
			// Déplacement horizontal vers la droite.
			setTypeDeplacement(1);
		}

		if (getOrDepart() == getOrArriv() && getAbsArriv() < getAbsDepart())
		{
			// Déplacement horizontal vers la gauche.
			setTypeDeplacement(2);
		}

		if (getOrDepart() > getOrArriv() && getAbsArriv() == getAbsDepart())
		{
			// Déplacement vertical vers le bas.
			setTypeDeplacement(3);
		}

		if (getOrDepart() < getOrArriv() && getAbsArriv() == getAbsDepart())
		{
			// Déplacement vertical vers le haut.
			setTypeDeplacement(4);
		}
		return getTypeDeplacement();
	}

	// EFFECTUE LE DEPLACEMENT VOULU PAR L'UTILISATEUR SELON LES REGLES DU JEU
	public void deplacement()
	{
		while (getNbPions() > 1)
		{
			try
			{
				typeDeplacement();
				
				int ordInter = (ordArriv + orDepart) / 2;
				int absInter = (absArriv + absDepart) / 2;

				// RECUPERE LE TYPE DE DEPLACEMENT
				// VERIFIE QUE LA CASE EST LIBRE
				// VERIFIE QUE LE DEPLACEMENT NE DEPASSE PAS 2 CASES

				if ((!plateau[ordArriv][absArriv].equals("X ") && plateau[ordInter][absInter].equals("X "))
						&& (getTypeDeplacement() == 1)
						&& (getAbsArriv() - getAbsDepart() == 2))
				{
					// LA CASE CIBLE DEVIENT OCCUPEE
					bille.setVal("X ");
					plateau[ordArriv][absArriv] = bille.getVal();

					// LE PION MANGE DISPARAIT ET LA CASE DE DEPART EST VIDE
					bille.setVal("  ");
					plateau[orDepart][absDepart] = bille.getVal();
					plateau[ordInter][absInter] = bille.getVal();
					// MISE A JOUR DU NOMBRE DE PIONS RESTANTS
					setNbPions(getNbPions() - 1);
				}

				if ((!plateau[ordArriv][absArriv].equals("X ") && plateau[ordInter][absInter].equals("X "))
						&& (getTypeDeplacement() == 2)
						&& (getAbsDepart() - getAbsArriv() == 2))
				{
					// LA CASE CIBLE DEVIENT OCCUPEE
					bille.setVal("X ");
					plateau[ordArriv][absArriv] = bille.getVal();

					// LE PION MANGE DISPARAIT ET LA CASE DE DEPART EST VIDE
					bille.setVal("  ");
					plateau[orDepart][absDepart] = bille.getVal();
					plateau[ordInter][absInter] = bille.getVal();

					// MISE A JOUR DU NOMBRE DE PIONS RESTANTS
					setNbPions(getNbPions() - 1);
				}

				if ((!plateau[ordArriv][absArriv].equals("X ") && plateau[ordInter][absInter].equals("X "))
						&& (getTypeDeplacement() == 3)
						&& (getOrDepart() - getOrArriv() == 2))
				{
					// LA CASE CIBLE DEVIENT OCCUPEE
					bille.setVal("X ");
					plateau[ordArriv][absArriv] = bille.getVal();

					// LE PION MANGE DISPARAIT ET LA CASE DE DEPART EST VIDE
					bille.setVal("  ");
					plateau[orDepart][absDepart] = bille.getVal();
					plateau[ordInter][absInter] = bille.getVal();

					// MISE A JOUR DU NOMBRE DE PIONS RESTANTS
					setNbPions(getNbPions() - 1);
				}

				if ((!plateau[ordArriv][absArriv].equals("X ") && plateau[ordInter][absInter].equals("X "))
						&& (getTypeDeplacement() == 4)
						&& (getOrArriv() - getOrDepart() == 2))
				{
					// LA CASE CIBLE DEVIENT OCCUPEE
					bille.setVal("X ");
					plateau[ordArriv][absArriv] = bille.getVal();

					// LE PION MANGE DISPARAIT ET LA CASE DE DEPART EST VIDE
					bille.setVal("  ");
					plateau[orDepart][absDepart] = bille.getVal();
					plateau[ordInter][absInter] = bille.getVal();

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
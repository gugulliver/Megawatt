package megawatt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.Set;

public class Carte {
	//graph des couts de transport
	private int[][] graphactuel;
	

	private ArrayList<Ville> listeDesVillesactuel;



	public Carte(int taille) {
		listeDesVillesactuel = crergraphpartext(taille);
	}
	
	public Carte(int[][] carte, Ville[] villes) {
		crerliaisongraphparFrancois(carte, villes);
		listeDesVillesactuel = crervillesactuel(villes);
	}

	private ArrayList<Ville> crervillesactuel(Ville[] villes){
		ArrayList<Ville> res =	new ArrayList<Ville>();
		for(Ville v: villes){
			res.add(v);
		}
		return res;
	}
	
	
	public int[][] getGraphactuel() {
		return graphactuel;
	}

	public ArrayList<Ville> getListeDesVillesactuel() {
		return listeDesVillesactuel;
	}
	

	//debut generation
	
	public ArrayList<Ville> crergraphpartext(int taille) {
		int[][] graph = new int[taille][taille];
		ArrayList<Ville> listeDesVilles = new ArrayList<Ville>();
		int numeroLigneLu = 0;
		String[] listI;
		String ligneNonDecoupe;
		try {
			BufferedReader f = new BufferedReader(new FileReader("graphVilleA.txt"));
			ligneNonDecoupe = f.readLine();
			while (ligneNonDecoupe != null) {

				if (ligneNonDecoupe != null) {
					listI = ligneNonDecoupe.split("	");

					int[] listI2;
					String nom1 = listI[0];
					for (int v = 1; v < listI.length; v++) {
						int a = Integer.parseInt(listI[v]);
						//System.out.println(" ligne "+numeroLigneLu+" colonne "+(v - 1)+a);
						graph[numeroLigneLu][v - 1] = a;
					}

					Ville villecourante = new Ville(nom1);
					listeDesVilles.add(villecourante);
					ligneNonDecoupe = f.readLine();

				}
				numeroLigneLu++;
			}
			
			f.close();
		}

		catch (Exception e) {
			System.out.println("une operation sur le fichier graphDeVille.txt a leve l'exception " + e);

		}
		
		//print2D(graph);
		
		crerliaisongraphpartext(graph, listeDesVilles);

		this.graphactuel = graph;

		return listeDesVilles;
	}

	public static void print2D(int mat[][])
    {
        // Loop through all rows
        for (int[] row : mat)
 
            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
    }
		
	public static void crerliaisongraphpartext(int[][] graph, ArrayList<Ville> listeDesVilles) {
		// test
		int taille = graph.length;
		if (taille == listeDesVilles.size()) {

			for (int l = 0; l < taille; l++) {
				for (int c = 0; c < taille; c++) {
					if (graph[l][c] >= 0) {
						//System.out.println(" le cout de "+c+" vers "+l+" est "+graph[l][c]);
						listeDesVilles.get(l).ajouterVilleAdjacente(listeDesVilles.get(c), graph[l][c]);
					}
				}
			}

		}
	}
	
	public static void crerliaisongraphparFrancois(int[][] graph, Ville[] listeDesVilles) {
		// test
		int taille = graph.length;
		if (taille == listeDesVilles.length) {

			for (int l = 0; l < taille; l++) {
				for (int c = 0; c <taille-l; c++) {
					if (graph[l][l+c] >= 0) {
						//System.out.println(" le cout de "+c+" vers "+l+" est "+graph[l][c]);
						listeDesVilles[l].ajouterVilleAdjacente(listeDesVilles[l+c], graph[l][l+c]);
					}
				}
			}

		}
	}


	//fin generation
	
	public boolean acheterVille(Ville v, Joueur j, int etape) {
		if(!(j.getVilles().contains(v))){
			List<Ville> listvillesjoueur = j.getVilles();
			int coutemplacement = getCoutAchat(v, etape);
			int coutchemin = plusCourtChemin(listvillesjoueur, v);
			if((coutemplacement > -1)&&(coutchemin>-1)){
				int cout = coutchemin+coutemplacement;
				if (j.getArgent() > cout) {
					j.depenser(cout);
					j.addVilles(v);
					acheterVille(j, v);
					System.out.println(" joueur "+j.getId()+" achete "+v.getNom()+" au prix de "+cout);
					return true;
				}
			}
		}
		return false;
	}

	public int getCoutAchat(Ville v, int etape) {
		int i = 0;
		while (!(v.getEspace()[i].equals(-1))) {
			i++;
		}
		if(i<etape){
		return (i + 2) * 5;
		} else
		return -1;
	}

	public void acheterVille(Joueur j, Ville v){
		int i = 0;
		while (!(v.getEspace()[i].equals(-1))) {
			i++;
		}
		if(i<3){
		v.getEspace()[i] = j.getId();
		}
	}

	public boolean estOccupeT(Ville v) {
		for( int i=0; i<3; i++){
			if (v.getEspace()[i].equals(-1)){
				return true;
			}
		}
		return false;
	}

	
	// Dijkstra
	
	private int plusCourtChemin(List<Ville> listVille, Ville ville) {
		
		if(listVille.isEmpty()){
			return 0;
		}
		
		HashMap<Ville, Integer> listVilleparcouru = new HashMap<Ville, Integer>() ;
		HashMap<Ville, Integer> adjacentes = initMapVille(listVille);

		while( !(adjacentes.isEmpty())){
			HashMap<Ville, Integer> adjacentes2 = boucle( adjacentes,  listVilleparcouru);
			adjacentes = adjacentes2;
			if(listVilleparcouru.containsKey(ville)){
				return listVilleparcouru.get(ville);
			}
		}
		
		return -1;
	}

	
	private HashMap<Ville, Integer> boucle(HashMap<Ville, Integer> adj, HashMap<Ville, Integer> listVilleparcouru){
			
		int val = 1000;
		HashMap<Ville, Integer> adj2 = new HashMap<Ville, Integer>();
		
			for (Entry<Ville, Integer> entry : adj.entrySet()) {
				Ville nomdepart = entry.getKey();
				Integer coutdepart = entry.getValue();
				for (Entry<Ville, Integer> entry2 : nomdepart.getAdjacent().entrySet()) {
					Ville nomarrive = entry2.getKey();
					Integer coutarrive = entry2.getValue();
					val = coutdepart + coutarrive;
					System.out.print(nomarrive + " " + val);
					if (listVilleparcouru.containsKey(nomarrive)) {
						if (listVilleparcouru.get(nomarrive) > val) {
							listVilleparcouru.replace(nomarrive, val);
							adj2.put(nomarrive, val);
						}
					} else {
						listVilleparcouru.put(nomarrive, val);
						adj2.put(nomarrive, val);
					}
				}
			}
	
	return adj2;
}
	


	private HashMap<Ville, Integer> initMapVille(List<Ville> listVille) {
		HashMap<Ville, Integer> li = new HashMap<Ville, Integer>();
		for (Ville v : listVille) {
			li.put(v, 0);
		}
		return li;

	}


}

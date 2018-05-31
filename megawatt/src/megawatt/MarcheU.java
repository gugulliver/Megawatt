package megawatt;

import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;

public class MarcheU {
	private ArrayList<Usine> piocheBlanche;
	private ArrayList<Usine> piocheSombre;
	private ArrayList<Usine> pioche;
	private ArrayList<Usine> usinesDispo;

	public MarcheU(){

		//initialisation de pioche
		Usine usine1  =new Usine(new int[]{2,0,0,0}, 3,1,0);
		Usine usine2  =new Usine(new int[]{2,0,0,0}, 4,1,0);
		Usine usine3  =new Usine(new int[]{0,2,0,0}, 5,1,0);
		Usine usine4  =new Usine(new int[]{0,0,1,0}, 6,1,0);
		Usine usine5  =new Usine(new int[]{1,0,0,0}, 7,1,0);
		Usine usine6  =new Usine(new int[]{0,3,3,0}, 8,2,0);
		Usine usine7  =new Usine(new int[]{3,0,0,0}, 9,2,0);
		Usine usine8  =new Usine(new int[]{0,0,2,0},10,2,0);
		Usine usine9  =new Usine(new int[]{0,0,0,0},11,1,0);
		Usine usine10 =new Usine(new int[]{2,0,0,0},12,2,0);
		Usine usine11 =new Usine(new int[]{0,0,0,1},13,2,0);
		Usine usine12 =new Usine(new int[]{0,1,0,0},14,2,0);
		Usine usine13 =new Usine(new int[]{2,0,0,0},15,2,0);
		Usine usine14 =new Usine(new int[]{0,2,0,0},16,3,0);
		Usine usine15 =new Usine(new int[]{0,0,0,0},17,2,0);
		Usine usine16 =new Usine(new int[]{0,0,2,0},18,3,0);
		Usine usine17 =new Usine(new int[]{0,1,0,0},19,3,0);
		Usine usine18 =new Usine(new int[]{3,0,0,0},20,4,0);
		Usine usine19 =new Usine(new int[]{0,0,0,1},21,3,0);
		Usine usine20 =new Usine(new int[]{0,3,3,0},22,5,0);
		Usine usine21 =new Usine(new int[]{0,0,2,0},23,4,0);
		Usine usine22 =new Usine(new int[]{0,0,0,0},24,3,0);
		Usine usine23 =new Usine(new int[]{2,0,0,0},25,5,0);
		Usine usine24 =new Usine(new int[]{0,1,0,0},26,4,0);
		Usine usine25 =new Usine(new int[]{1,0,0,0},27,4,0);
		Usine usine26 =new Usine(new int[]{0,0,0,0},28,3,0);
		Usine usine27 =new Usine(new int[]{2,0,0,0},29,5,0);
		Usine usine28 =new Usine(new int[]{0,0,2,0},30,5,0);
		Usine usine29 =new Usine(new int[]{0,0,0,0},31,4,0);
		Usine usine30 =new Usine(new int[]{0,0,0,2},32,5,0);
		Usine usine31 =new Usine(new int[]{3,0,0,0},33,6,0);
		Usine usine32 =new Usine(new int[]{0,3,0,0},34,6,0);
		Usine usine33 =new Usine(new int[]{0,2,2,0},35,5,0);
		Usine usine34 =new Usine(new int[]{0,0,0,0},36,5,0);
		Usine usine35 =new Usine(new int[]{0,0,0,2},37,6,0);
		Usine usine36 =new Usine(new int[]{0,0,3,0},38,6,0);
		Usine usine37 =new Usine(new int[]{0,2,0,0},39,6,0);
		Usine usine38 =new Usine(new int[]{2,0,0,0},40,6,0);
		Usine usine39 =new Usine(new int[]{0,0,2,0},42,6,0);
		Usine usine40 =new Usine(new int[]{0,0,0,0},44,6,0);
		Usine usine41 =new Usine(new int[]{0,2,0,0},46,7,0);
		Usine usine42 =new Usine(new int[]{0,0,0,2},50,7,0);
		Usine etape3  =new Usine(new int[]{0,0,0,0},100,0,0);
		this.pioche=new ArrayList<Usine>();
		//this.pioche.add(usine1);
		//this.pioche.add(usine2);
		//this.pioche.add(usine3);
		//this.pioche.add(usine4);
		this.pioche.add(usine5);
		this.pioche.add(usine6);
		this.pioche.add(usine7);
		this.pioche.add(usine8);
		this.pioche.add(usine9);
		this.pioche.add(usine10);
		this.pioche.add(usine11);
		this.pioche.add(usine12);
		this.pioche.add(usine13);
		this.pioche.add(usine14);
		this.pioche.add(usine15);
		this.pioche.add(usine16);
		this.pioche.add(usine17);
		this.pioche.add(usine18);
		this.pioche.add(usine19);
		this.pioche.add(usine20);
		this.pioche.add(usine21);
		this.pioche.add(usine22);
		this.pioche.add(usine23);
		this.pioche.add(usine24);
		this.pioche.add(usine25);
		this.pioche.add(usine26);
		this.pioche.add(usine27);
		this.pioche.add(usine28);
		this.pioche.add(usine29);
		this.pioche.add(usine30);
		this.pioche.add(usine31);
		this.pioche.add(usine32);
		this.pioche.add(usine33);
		this.pioche.add(usine34);
		this.pioche.add(usine35);
		this.pioche.add(usine36);
		this.pioche.add(usine37);
		this.pioche.add(usine38);
		this.pioche.add(usine39);
		this.pioche.add(usine40);
		this.pioche.add(usine41);
		this.pioche.add(usine42);
		this.pioche.add(etape3);
		
		this.usinesDispo = new ArrayList<Usine>();
		this.usinesDispo.add(usine1);
		this.usinesDispo.add(usine2);
		this.usinesDispo.add(usine3);
		this.usinesDispo.add(usine4);
		
	}
	
	//problème : return une array list
	//			Lorsqu'on choisi l'usine, on est obligé d'enchérie
	//			Soit on saute le tours du joueur après nous, soit on est obligé de réencherir
	public Joueur[] lancerEnchere(Joueur[] listejoueur, int etape) {
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		for(int i =0;i< listejoueur.length;i++){
			joueurs.add(listejoueur[i]);
		}
		ArrayList<Joueur> joueurfin = (ArrayList<Joueur>) joueurs.clone(); // liste de joueur a retourner et
												// donc update
		ArrayList<Joueur> joueurencours = (ArrayList<Joueur>) joueurs.clone(); // liste secondaire pour
													// permettre de savoir qui a
													// une usine ou n'en veut
													// pas
		int prix = 0;
		while (!joueurencours.isEmpty()) { // début des enchere avec choix de
											// l'usine

			Usine usineencours = this.choixUsine(joueurencours.get(0)); // le
																			// premier
																			// joueur
																			// choisie
																			// une
																			// usine
			if (usineencours == null) { // si il ne choisi pas d'usine, ils sort
										// des enchères
				joueurencours.remove(0);
			} else { // sinon on continue normalement
				prix = usineencours.getPrixInitial(); // on créer le prix des usines
				System.out.println("début des enchères, le prix de départ est " + prix); // previent les joueurs
				ArrayList<Joueur> enjeu = (ArrayList<Joueur>) joueurencours.clone(); // création d'une seconde liste pour savoir qui est en jeu
				boolean premiertour = true;		//pour l'achat de cette usine
				boolean passer = false;
		

				
				while (enjeu.size() >1) {// tant qu'il ne reste pas une personne en jeu pour l'achat de lusine
						for (int i = (premiertour?1:0); i < enjeu.size(); i++) {// on parcours la liste
							premiertour=false;
							System.out.println("c'est au joueur " + enjeu.get(i).getId() + " d'enchérir, tu as "+enjeu.get(i).getArgent());
							System.out.println("le prix est a " + prix + " combien veux tu rajouter?");
							System.out.println("Attention, si le prix de l'usine et la mise que tu rajoute dépassent ton argent, tu passeras automatiquement");
							System.out.println("si tu ne veux pas de cette usine,tape 0");
							Scanner sc = new Scanner(System.in);
							int nb = sc.nextInt(); // pour demander un int
							if (nb == 0 || prix + nb > enjeu.get(i).getArgent()) {
								enjeu.remove(i);
								i--;
								if(enjeu.size() ==1){
									break;
								}
								}else {
								prix = nb + prix; // on met a jour le prix jusqu'a ce qu'il n'en reste qu'un
							}
							//}while(passer);
					}
				
					

				}
				System.out.println("felicitation, le joueur "+enjeu.get(0).getId()+" remporte l'usine !");
				System.out.println("te voila dépouillé de "+prix+" !!");
				for(int i =0; i<joueurfin.size();i++){
					if(joueurfin.get(i).getId() == enjeu.get(0).getId()){ // on cherche le joueur dans la liste final
						//System.out.println(joueurfin.get(i).getUsines().size());
						int argentfin = joueurfin.get(i).getArgent()-prix; // on lui retire le prix de l'usine
						joueurfin.get(i).setArgent(argentfin); // on met a jours son argent
						joueurfin.get(i).ajouterUsine(usineencours);// on lui ajoute l'usine
						this.enleverUsine(usineencours);
						this.actualiserMarcheEnchere();
						joueurencours.remove(joueurfin.get(i)); // on l'enleve de l'enchère generale
						//ajouter enlever usine et atualiser marché
					}
				}
			}
		}
		Joueur[] joueurfinal = new Joueur[joueurfin.size()];
		for(int i = 0; i<joueurfin.size();i++){
			joueurfinal[i] = joueurfin.get(i);
		}
		return joueurfinal;
	}

	/**
	 * tire une carte de la pioche et on trie le marché dans l'ordre croissant.
	 * 
	 * @param etape
	 */
	public void actualiserMarcheEnchere() {
		int place = this.usinesDispo.size();
		for(int i = 0; i<this.usinesDispo.size();i++){
			if(this.usinesDispo.get(i).getPrixInitial() > this.pioche.get(0).getPrixInitial() ){
				place = i;
				break;
			}
		}
		this.usinesDispo.add(place, this.pioche.get(0));
		this.pioche.remove(0);
	}

	public void actualiserMarcheEtape(int etape) {

	}

	/**
	 * initialise les trois listes de pioche
	 */
	public void initialiserPoche() {

	}

	public ArrayList<Usine> getUsinesAchetables(int etape) {
		return this.usinesDispo;
	}

	/**
	 * Affiche les usines du marché et demande à joueur de choisir parmi les
	 * usines achetable
	 * 
	 * @param joueur
	 * @param usines
	 * @return
	 */
	public Usine choixUsine(Joueur joueur) {
		Usine usinechoisie = null;
		System.out.println("c'est au joueur " + joueur.getId() + " de jouer");
		System.out.println("Choisissez une usine a mettre en enchere parmis les suivante :");
		System.out.println("Usine 1 : " + this.usinesDispo.get(0).toString());
		System.out.println("Usine 2 : " + this.usinesDispo.get(1).toString());
		System.out.println("Usine 3 : " + this.usinesDispo.get(2).toString());
		System.out.println("Usine 4 : " + this.usinesDispo.get(3).toString());
		System.out.println("Tapez 1,2,3 ou 4 pour choisir une Usine ou 0 pour passer");

		boolean choix = true;
		while (choix) {
			Scanner sc = new Scanner(System.in);
			int nb = sc.nextInt(); // pour demander un int
			if (nb < 0 || nb > 4) {
				System.out.println("faites attentions s'il vous plait, retaper un chiffre entre 0 et 4");
				nb = sc.nextInt(); // pour demander un int
			} else {
				if (nb != 0) {
					usinechoisie = this.usinesDispo.get(nb - 1);
				}
				choix = false;
			}

		}
		return usinechoisie;

	}

	/**
	 * enlève une usine du marché et update en tirant une usine de la pioche
	 * 
	 * @param usine
	 */
	public void enleverUsine(Usine usine) {
		this.usinesDispo.remove(usine);
	}
	
	/**
	 * La fonction va retirer une seule fois de la partie la plus petite centrale
	 * du marché et la remplacer par une nouvelle tirée dans la pioche
	 */
	//ajouté par françois
	public void transition_etape1_etape2(){
		//on retire la plus petite usine du marché qui est censée être en dernière position de usines_dispo
		this.usinesDispo.remove(this.usinesDispo.size()-1);
		//on tire une nouvelle carte qu'on actualise dans le marché
		this.actualiserMarcheEnchere();
	}
	
	/*
	 * 
	 */
	//ajouté par françois
	public  void transition_etape2_etape3(){
		//On retire du marché la carte "étape 3" qui est considérée comme la plus forte ainsi que la centrale la plus faible
		//la place respective de ces cartes est la première et dernière position de la liste usines_dispo
		this.usinesDispo.remove(0);
		this.usinesDispo.remove(this.usinesDispo.size()-1);
		
		//on note qu'on ne tire pas de nouvelles cartes. Le marché des usines est dorénavant 6.
	}
	
}

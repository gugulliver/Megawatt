package megawatt;
import java.math.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Partie {
	private Joueur[] joueurs;
	private int[][] carte;// matrice des villes avec leurs interconnexions. 
	private Ville[] villes;// INITIALISATION DES VILLES DU JEU.
	private MarcheU marcheU;
	private Ressources ressources;//ressources du marché.
	private int nbreTours;//nombres de tours du jeu
	
	private Carte cartedeG; //Guillaume 25/05/2018
	
	
	public Partie(Joueur[] joueurs, Ville[] villes, MarcheU marcheU, Ressources ressources) {
		super();
		this.joueurs = joueurs;
		this.carte = new int[][]{
			{ 0, 0,10,17,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1}, //MEXICO CITY SOUTH
			{ 0, 0,10,17,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1}, //MEXICO CITY NORTH
			{10,10, 0,16,23,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1}, //GUADALAJARA
			{17,17,16, 0,14,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,10,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1}, //MONTERREY
			{-1,-1,23,14, 0, 8,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,16,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1}, //CHIHUAHUA
			{-1,-1,-1,-1, 8, 0, 9,22,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,18,-1,19,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1}, //JUAREZ
			{-1,-1,-1,-1,-1, 9, 0,-1,25,-1,-1,18,-1,12,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,18,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1}, //ALBUQUERQUE
			{-1,-1,-1,-1,-1,-1,-1, 0, 3,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2}, //SAN DIEGO
			{-1,-1,-1,-1,-1,-1,-1,-1, 0,12,-1, 8,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2}, //LOS ANGELES
			{-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,20,16,22,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2}, //SAN FRANCISCO
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,-1,22,-1, 5,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2}, //PORTLAND
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,14,25,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2}, //LAS VEGAS
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,14,-1,-1,-1,-1,29,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2}, //SALT LAKE CITY
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,-1,-1,-1,-1,24,26,23,-1,-1,-1,-1,19,17,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2}, //DENVER
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 4,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,3}, //SEATTLE
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,16,-1,28,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,3}, //VANCOUVER
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 5,14,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,3}, //CALGARY
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,15,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,3}, //EDMONTON
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,11,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,3}, //REGINA
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,13,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,3}, //WINNIPEG
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,10,-1,-1,-1,14,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,3}, //MINNEAPOLIS
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 3,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,4}, //MILWAUKEE
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 6, 9,16,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,10,-1,10,-1,-1,-1,-1,-1,-1,-1,-1,4}, //CHICAGO
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 8,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 9,-1, 7,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,4}, //INDIANAPOLIS
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 8,-1, 9,-1,-1,-1,-1,-1,-1,-1,-1, 9,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,4}, //SAINT LOUIS
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,10,14,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,4}, //KANSAS CITY
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,15,-1,-1, 7,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,4}, //OKLAHOMA CITY
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,-1,-1,15,14,12,-1,-1,-1, 7,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,4}, //MEMPHIS
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 6, 9,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,5}, //SAN ANTONIO
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 8,12,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,5}, //HOUSTON
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,16,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,5}, //DALLAS/FORT WORTH
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,16,20,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,5}, //NEW ORLEANS
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,10,-1, 8, 8,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,5}, //ATLANTA
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,12,12,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,5}, //JACKSONVILLE
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,5}, //MIAMI
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,12,12,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,6}, //CHARLOTTE
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,22,15,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,6}, //NASHVILLE
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,12, 7,-1,-1, 5,-1,-1,-1,-1,-1,-1,6}, //WASHINGTON
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 6, 7,-1,-1,-1,-1,-1,-1,-1,-1,6}, //COLUMBUS
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,-1,11, 9,11,11,-1,-1,-1,-1,6}, //PITTSBURGH
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 8,-1,-1,-1,-1,-1,-1,-1,6}, //DETROIT
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,-1,14,14, 8,-1,-1,-1,6}, //TORONTO
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 3, 3,-1,-1,-1,-1,7}, //PHILADELPHIA
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 0,13, 7,12,-1,7}, //NEW YORK SOUTH
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,13, 7,12,-1,7}, //NEW YORK NORTH
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,-1, 3,-1,7}, //OTTAWA
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 9,-1,7}, //BOSTON
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 5,7}, //MONTREAL
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,7}  //QUEBEC
		}; // ATTENTION:matrice (pas encore) symétrique
		symetrique(this.carte);//on symétrise la carte
		this.villes = villes;
		this.marcheU = marcheU;
		this.ressources = ressources;
		this.nbreTours=0;
		this.cartedeG= new Carte(carte, villes);
	}
	public void symetrique(int[][] symetrique){
		for(int i=0;i<49;i++){
			for(int j=i+1;j<49;j++){
				symetrique[j][i]=symetrique[i][j];
			}
		}
	}
	/**
	 * permet d'actualiser l'ordre des joueurs à chaque tour en modifiant this.joueur.
	 */
	public void ordreDesJoueurs(){
		for(int i=0;i<this.joueurs.length;i++){
			for(int j=i+1;j<this.joueurs.length;j++){
				if(this.joueurs[i].getNbrVilles()<this.joueurs[j].getNbrVilles()){
					Joueur temp=this.joueurs[i];
					this.joueurs[i]=this.joueurs[j];
					this.joueurs[j]=temp;
				}else if(this.joueurs[i].getNbrVilles()==this.joueurs[j].getNbrVilles()){
					if(this.joueurs[i].valeurMaxUsines()<this.joueurs[j].valeurMaxUsines()){
						Joueur temp=this.joueurs[i];
						this.joueurs[i]=this.joueurs[j];
						this.joueurs[j]=temp;
					}
				}
			}
		}
	}
	public void regenerationDesRessources(int[] reapprovisionnement){
		
	}
	public void regenarationDuMarche(){
		//régénère le marché en modifiant this.Marché
		
	}
	/**
	 * demande au joueur j quelles villes il voudra acheter 
	 * remarque: il doit acheter les villes une par une pour le 
	 * respect des régles du jeu (et pour la santé mentale de Guillaume !!!!!!!)
	 * actualisent les villes qui ont été acheté et 
	 * possédée par le joueur, son argent.
	 * @param j
	 * @param etape
	 */
public void acheterVilles(Joueur j,int etape){
		System.out.println("achat des villes pour le joueur "+j.getId());
		Scanner sc=new Scanner(System.in);
		System.out.println("les villes sont :"+this.villesString()+"/n quand vous voullez vous arretez rentrez -1");
		int nombre=sc.nextInt (); 
		while(nombre != -1){
			Ville v = this.villes[nombre];
			System.out.println(nombre+" "+v.getNom());
			boolean b = cartedeG.acheterVille(v, j, etape);
			
			if(!b){
				System.out.println("il ne vous est pas possible d'acheter cette ville");
				//error, ville pas achetable dans ce cas
			}
			nombre=sc.nextInt (); 
		}
	}
		public String villesString(){
		String re = "";
		for(int i=0; i<this.villes.length; i++){
			re+= " "+villes[i].getNom()+": "+i+" ";
		}
		return re;
	}
	
	
	public void etape1(){
		ArrayList<Integer> villes_alim_par_joueur=new ArrayList<Integer>(); //cet array ne servira que si le jeu est fini, pour déterminer le gagnant
		Joueur gagnant=null;
		
		while(this.conditionEtape1()){
			this.joueurs=this.marcheU.lancerEnchere(this.joueurs,1);
			for(int i=this.joueurs.length-1;i>=0;i--){
				this.ressources.acheterRessources(this.joueurs[i]);
			}
			for(int i=this.joueurs.length-1;i>=0;i--){
				this.acheterVilles(this.joueurs[i],1);
			}
			for(int i=0;i<this.joueurs.length;i++){
				int nbreVillesAlimentees=this.joueurs[this.joueurs.length-i-1].choisirUsines();
				if(!conditionVictoire()) villes_alim_par_joueur.add(0, nbreVillesAlimentees);; //rajouté
				this.joueurs[this.joueurs.length-i-1].remunererJoueur(nbreVillesAlimentees);
			}
			this.ressources.reapprovisionnement(this.joueurs.length,1);
			if(!conditionVictoire()){
				gagnant=vainqueur(villes_alim_par_joueur);  //rajouté
			}
			this.marcheU.actualiserMarcheEnchere();
			this.ordreDesJoueurs(); //modification, cette ligne était au début
			nbreTours++;
		}
		if(!conditionVictoire()){
			if(gagnant!=null) System.out.println(gagnant.toString());
		}else{
		this.marcheU.transition_etape1_etape2();
		System.out.println("\n\n\ns**********************************************************************************\n"
				+ "/*****************************ETAPE 2*********************************************/*\n"
				+ "********************************************************************************\n\n\n");
		this.etape2();
		}
	}
	
	//complété par françois
	public void etape2(){
		ArrayList<Integer> villes_alim_par_joueur=new ArrayList<Integer>(); //cet array ne servira que si le jeu est fini, pour déterminer le gagnant
		Joueur gagnant=null;
		
		while(this.conditionEtape2()&&(conditionVictoire())){
			this.joueurs=this.marcheU.lancerEnchere(this.joueurs,2);
			for(int i=this.joueurs.length-1;i>=0;i--){
				this.ressources.acheterRessources(this.joueurs[i]);
			}
			for(int i=this.joueurs.length-1;i>=0;i--){
				this.acheterVilles(this.joueurs[i],2);
			}
			for(int i=this.joueurs.length-1;i>=0;i--){
				int nbreVillesAlimentees=this.joueurs[i].choisirUsines();
				if(!conditionVictoire()) villes_alim_par_joueur.add(nbreVillesAlimentees); //rajouté
				this.joueurs[i].remunererJoueur(nbreVillesAlimentees);
			}
			this.ressources.reapprovisionnement(this.joueurs.length,2);
			if(!conditionVictoire()){
				gagnant=vainqueur(villes_alim_par_joueur);  //rajouté
			}
			this.marcheU.actualiserMarcheEnchere();
			this.ordreDesJoueurs(); //modification, cette ligne était au début
			nbreTours++;
		}
		
		if(!conditionVictoire()){
			if(gagnant!=null) System.out.println(gagnant.toString());
		}else{
		this.marcheU.transition_etape2_etape3();
		System.out.println("\n\n\ns**********************************************************************************\n"
				+ "/*****************************ETAPE 3*********************************************/*\n"
				+ "********************************************************************************\n\n\n");
		this.etape3();
		}
	}
	
	//complété par françois
	public void etape3(){
		ArrayList<Integer> villes_alim_par_joueur=new ArrayList<Integer>(); //cet array ne servira que si le jeu est fini, pour déterminer le gagnant
		Joueur gagnant=null;
		while(conditionVictoire()){
			this.joueurs=this.marcheU.lancerEnchere(this.joueurs,3);
			for(int i=this.joueurs.length-1;i>=0;i--){
				this.ressources.acheterRessources(this.joueurs[i]);
			}
			for(int i=this.joueurs.length-1;i>=0;i--){
				System.out.println("\n\n/*************** Joueur "+this.joueurs[i].getId()+" **************/\n");
				this.acheterVilles(this.joueurs[i],3);
			}
			for(int i=this.joueurs.length-1;i>=0;i--){
				int nbreVillesAlimentees=this.joueurs[i].choisirUsines();
				if(!conditionVictoire()) villes_alim_par_joueur.add(nbreVillesAlimentees); //rajouté
				this.joueurs[i].remunererJoueur(nbreVillesAlimentees);
			}
			this.ressources.reapprovisionnement(this.joueurs.length,3);
			if(!conditionVictoire()){
				gagnant=vainqueur(villes_alim_par_joueur);  //rajouté
			}
			this.marcheU.actualiserMarcheEnchere();
			this.ordreDesJoueurs(); //modification, cette ligne était au début
			nbreTours++;
		}
		
		if(gagnant!=null) System.out.println(gagnant.toString());
	}
	public boolean conditionEtape1(){
		boolean etape = true;
		for(int i=0;i<this.joueurs.length-1;i++){
			if(this.joueurs[i].getVilles().size()>=7){
				etape=false;
			}
		}
		return etape;
	}
	/**
	 * Retourne faux dès que la carte "Etape 3" est dans le marché des usines
	 * @return
	 */
	//complété par françois
	public boolean conditionEtape2(){
		//si l'étape 3 est dans le marché, elle est considérée comme l'usine la plus forte donc est en position 0
		return this.marcheU.getUsinesAchetables(2).get(0).getPrixInitial()!=100;
	}
	
	/**
	 * Retourne faux dès qu'un jour atteint le nombre de villes nécessaires pour finir le jeu
	 * @return
	 */
	//complété par françois
	public boolean conditionVictoire(){
		int[] villes_connectees_max={0,0,18,17,17,15,14};  //nb de villes qu'un joueur doit atteindre en fonction de i joueurs
		
		int max=0; //maximum de villes possédée par un des joueurs
		for(int i=0;i<this.joueurs.length;i++){
			if(max<this.joueurs[i].getNbrVilles()) max=this.joueurs[i].getNbrVilles();
		}
		
		return max<villes_connectees_max[this.joueurs.length];
	}
	
	/**
	 * Retourne le joueur grand gagnant de la partie
	 * @param villes
	 * @return
	 */
	//complété par françois
	public Joueur vainqueur(ArrayList<Integer> villes){
		Joueur gagnant;
		
		//on détermine d'abord le nombre maximal de villes qui ont été alimentées
		int max=0;
		for(int i=0;i<villes.size();i++){
			if(max<villes.get(i)) max=villes.get(i);
		}
		
		//on regarde quels joueurs possèdent ce maximum
		ArrayList<Integer> index=new ArrayList<Integer>();
		for(int i=0;i<villes.size();i++){
			if(villes.get(i)==max) index.add(i);
		}
		
		//si la taille d'index vaut 1, on retourne le vainqueur,sinon, on doit voir qui à le plus d'argent
		while(index.size()>1){
			System.out.println(index.toString());
			if(this.joueurs[index.get(0)].getArgent()>this.joueurs[index.get(1)].getArgent()) index.remove(1);
			else index.remove(0);
		}
		gagnant=this.joueurs[index.get(0)];
		
		
		return gagnant;
	}
	
}

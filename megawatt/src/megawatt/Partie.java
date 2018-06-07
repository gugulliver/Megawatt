package megawatt;
import java.math.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Partie {
	private Joueur[] joueurs;
	private MarcheU marcheU;
	private Ressources ressources;//ressources du marché.
	private int nbreTours;//nombres de tours du jeu	
	private Carte cartedeG; //Guillaume 25/05/2018
	
	
	public Partie(Joueur[] joueurs, MarcheU marcheU, Ressources ressources) {
		super();
		this.joueurs = joueurs;
		this.marcheU = marcheU;
		this.ressources = ressources;
		this.nbreTours=0;
		this.cartedeG= new Carte(50);
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
			Ville v = this.cartedeG.getListeDesVillesactuel().get(nombre);
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
		for(int i=0; i<this.cartedeG.getListeDesVillesactuel().size(); i++){
			re+= " "+this.cartedeG.getListeDesVillesactuel().get(i).getNom()+": "+i+" ";
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

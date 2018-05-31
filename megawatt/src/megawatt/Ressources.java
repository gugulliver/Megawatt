package megawatt;

import java.util.Arrays;
import java.util.Observable;
import java.util.Scanner;

public class Ressources extends Observable{

	
	//RAJOUTÉ PAR MOI
	//_______________________________________________________________________________________________________________________________________________
	//_______________________________________________________________________________________________________________________________________________
	//_______________________________________________________________________________________________________________________________________________
	//_______________________________________________________________________________________________________________________________________________
	//_______________________________________________________________________________________________________________________________________________
	public static final Integer CHANGEMENT_MARCHE_RESSOURCES=0;
	
	
	private int[] etat_actuel; //etat_actuel=[qte_charbon,qte_gaz,qte_petrole,qte_uranium];
	private int[] qte_max;  //quantité maximal qu'une ressource peut avoir sur le marché
	
	public Ressources(){
		this.etat_actuel=new int[]{23,18,14,2};
		this.qte_max=new int[]{27,24,20,12};
	}
	
	public void ajouter_soustraire_ressources(int[] qte){
		int somme;
		for(int i=0;i<this.etat_actuel.length;i++){
			somme=this.etat_actuel[i]+qte[i];
			this.etat_actuel[i]=somme>this.qte_max[i]? this.qte_max[i] : (somme<0?0:somme);
		}
		
		this.setChanged();
		this.notifyObservers(CHANGEMENT_MARCHE_RESSOURCES);
	}

	public void set_ressources(int[] etat_nouveau){
		for(int i=0;i<this.etat_actuel.length;i++){
			this.etat_actuel[i]=etat_nouveau[i];
		}
		this.setChanged();
		this.notifyObservers(CHANGEMENT_MARCHE_RESSOURCES);
	}
	
	public int[] getEtat_actuel() {
		return etat_actuel;
	}

	/**
	 * Les 4 fonctions ci-dessous permettent de renvoyer le prix de la ressource la moins chère selon
	 * la quantité actuelle de la ressource disponible sur le marché
	 * @param index
	 * @return
	 */
	public int prixCharbonindex(int index){
		index--; //parce que l'utilisateur souhaitera une quantite n qui est à la position n-1 dans le tableau
		if(index>22) return 1;
		if(index<=22&&index>18)return 2;
		if(index<=18&&index>14)return 3;
		if(index<=14&&index>11)return 4;
		if(index<=11&&index>8)return 5;
		if(index<=8&&index>5)return 6;
		if(index<=5&&index>3)return 7;
		if(index<=3&&index>1)return 8;
		if(index<=1)return 9;
		return 0;
	}
	public int prixGazindex(int index){
		index--; //parce que l'utilisateur souhaitera une quantite n qui est à la position n-1 dans le tableau
		if(index>20) return 1;
		if(index<=20&&index>17)return 2;
		if(index<=17&&index>14)return 3;
		if(index<=14&&index>11)return 4;
		if(index<=11&&index>8)return 5;
		if(index<=8&&index>5)return 6;
		if(index<=5&&index>2)return 7;
		if(index<=2)return 8;
		return 0;
	}
	public int prixPetroleindex(int index){
		index--; //parce que l'utilisateur souhaitera une quantite n qui est à la position n-1 dans le tableau
		if(index>17) return 1;
		if(index<=17&&index>15)return 2;
		if(index<=15&&index>13)return 3;
		if(index<=13&&index>11)return 4;
		if(index<=11&&index>9)return 5;
		if(index<=9&&index>7)return 6;
		if(index<=7&&index>5)return 7;
		if(index<=5&&index>3)return 8;
		if(index<=3)return 9;
		return 0;
	}
	public int prixUraniumindex(int index){
		index--; //parce que l'utilisateur souhaitera une quantite n qui est à la position n-1 dans le tableau
		if(index==11) return 1;
		if(index==10)return 2;
		if(index==9)return 3;
		if(index==8)return 4;
		if(index==7)return 5;
		if(index==6)return 6;
		if(index<=5&&index>3)return 7;
		if(index<=3&&index>1)return 8;
		if(index<=1)return 9;
		return 0;
	}
	
	/**
	 * Les 4 fonctions ci-dessous permettent de renvoyer le prix à payer pour obtenir la quantité de ressource désirée
	 * @return
	 */
	public int prixCharbon(int qte_voulue){
		int stock=this.etat_actuel[0];
		int prix=0;
		while(stock>0&&qte_voulue>0){
			prix+=prixCharbonindex(stock);
			stock--;
			qte_voulue--;
		}
		return prix;
	}
	public int prixGaz(int qte_voulue){
		int stock=this.etat_actuel[1];
		int prix=0;
		while(stock>0&&qte_voulue>0){
			prix+=prixGazindex(stock);
			stock--;
			qte_voulue--;
		}
		return prix;
	}
	public int prixPetrole(int qte_voulue){
		int stock=this.etat_actuel[2];
		int prix=0;
		while(stock>0&&qte_voulue>0){
			prix+=prixPetroleindex(stock);
			stock--;
			qte_voulue--;
		}
		return prix;
	}
	public int prixUranium(int qte_voulue){
		int stock=this.etat_actuel[3];
		int prix=0;
		while(stock>0&&qte_voulue>0){
			prix+=prixUraniumindex(stock);
			stock--;
			qte_voulue--;
		}
		return prix;
	}
	
	
	/**
	 * Les 4 fonctions du dessous réalisent la même chose que les 4 fonctions du dessus
	 * Néanmoins, les fonctions du dessous modifient aussi l'état actuel du marché
	 * @param qte_voulue
	 * @return
	 */
	public int prixCharbonDefinitif(int qte_voulue){
		int prix=0;
		while(this.etat_actuel[0]>0&&qte_voulue>0){
			prix+=prixCharbonindex(this.etat_actuel[0]);
			this.etat_actuel[0]--;
			qte_voulue--;
		}
		this.setChanged();
		this.notifyObservers(CHANGEMENT_MARCHE_RESSOURCES);
		return prix;
	}
	public int prixGazDefinitif(int qte_voulue){
		int prix=0;
		while(this.etat_actuel[1]>0&&qte_voulue>0){
			prix+=prixGazindex(this.etat_actuel[1]);
			this.etat_actuel[1]--;
			qte_voulue--;
		}
		this.setChanged();
		this.notifyObservers(CHANGEMENT_MARCHE_RESSOURCES);
		return prix;
	}
	public int prixPetroleDefinitif(int qte_voulue){
		int prix=0;
		while(this.etat_actuel[2]>0&&qte_voulue>0){
			prix+=prixPetroleindex(this.etat_actuel[2]);
			this.etat_actuel[2]--;
			qte_voulue--;
		}
		this.setChanged();
		this.notifyObservers(CHANGEMENT_MARCHE_RESSOURCES);
		return prix;
	}
	public int prixUraniumDefinitif(int qte_voulue){
		int prix=0;
		while(this.etat_actuel[3]>0&&qte_voulue>0){
			prix+=prixUraniumindex(this.etat_actuel[3]);
			this.etat_actuel[3]--;
			qte_voulue--;
		}
		this.setChanged();
		this.notifyObservers(CHANGEMENT_MARCHE_RESSOURCES);
		return prix;
	}
	
	
	/**
	 * Les deux fonctions ci-dessous permettent de synthétiser le fonctionnement des 8 fonctions écrites au-dessus
	 * @return
	 */
	public int prix(int type,int qte){
		if(type==0) return prixCharbon(qte);
		if(type==1) return prixGaz(qte);
		if(type==2) return prixPetrole(qte);
		if(type==3) return prixUranium(qte);
		return -1;
	}
	public int prixDefinitif(int type,int qte){
		if(type==0) return prixCharbonDefinitif(qte);
		if(type==1) return prixGazDefinitif(qte);
		if(type==2) return prixPetroleDefinitif(qte);
		if(type==3) return prixUraniumDefinitif(qte);
		return -1;
	}
	
	public boolean achat_impossible(int qte,int type,Joueur joueur){
		if(qte<0||qte>this.etat_actuel[type]){
			System.out.println("ÉCHEC: Le marché ne peut pas répondre à cette demande.");
			return true;
		}
		if(type==0){
			if(joueur.getArgent()<prixCharbon(qte)){
				System.out.println("ÉCHEC: Vous n'avez pas l'argent nécessaire pour vous procurer cette quantité.");
				return true;
			}else return false;
		}
		if(type==1){
			if(joueur.getArgent()<prixGaz(qte)){
				System.out.println("ÉCHEC: Vous n'avez pas l'argent nécessaire pour vous procurer cette quantité.");
				return true;
			}else return false;
		}
		if(type==2){
			if(joueur.getArgent()<prixPetrole(qte)){
				System.out.println("ÉCHEC: Vous n'avez pas l'argent nécessaire pour vous procurer cette quantité.");
				return true;
			}else return false;
		}
		if(type==3){
			if(joueur.getArgent()<prixUranium(qte)){
				System.out.println("ÉCHEC: Vous n'avez pas l'argent nécessaire pour vous procurer cette quantité.");
				return true;
			}else return false;
		}
		return true;
	}
	
	
	public void setEtat_actuel(int[] etat_actuel) {
		this.etat_actuel = etat_actuel;
	}

	public int[] getQte_max() {
		return qte_max;
	}

	public void setQte_max(int[] qte_max) {
		this.qte_max = qte_max;
	}
	
	
	
	
	public String toStringEtatActuel() {
		return "achat_ressources [etat_actuel=" + Arrays.toString(etat_actuel) + "]";
	}
	//_______________________________________________________________________________________________________________________________________________
	//_______________________________________________________________________________________________________________________________________________
	//_______________________________________________________________________________________________________________________________________________
	//_______________________________________________________________________________________________________________________________________________
	//_______________________________________________________________________________________________________________________________________________
	
	
	/**
	 * reapprovisionne le marché à chaque fin de 
	 * tour en fonction du nombre de joueur
	 * @param nbreJoueurs
	 * @param etape
	 */
	public void reapprovisionnement(int nbreJoueurs, int etape){
		int[][][] paquet={{{2,2,3,2},{5,2,1,1},{2,4,3,2}},  	//étapes 1,2,3 pour 2 joueurs
				  {{2,2,3,2},{5,2,1,1},{2,4,3,2}},		//étapes 1,2,3 pour 3 joueurs
				  {{3,3,3,2},{6,3,2,1},{3,5,4,2}},		//étapes 1,2,3 pour 4 joueurs
				  {{4,3,4,3},{7,3,2,2},{4,6,5,3}},		//étapes 1,2,3 pour 5 joueurs
				  {{5,4,5,3},{8,4,3,2},{5,7,6,4}}};		//étapes 1,2,3 pour 6 joueurs
		int[] reaprovisionner=paquet[nbreJoueurs][etape];
		for(int i=0;i<4;i++){
			this.etat_actuel[i]=this.etat_actuel[i]+reaprovisionner[i]<=this.qte_max[i]?this.etat_actuel[i]+reaprovisionner[i]:this.qte_max[i];
		}
	}
	/** 
	 * demande au joueur les ressources qu'il veut acheter.
	 * vérifie si ça demande est réalisable.
	 * actualise l'argent du joueur, ses ressources et le marché de 
	 * ressources.
	 * @param joueur
	 */
	public void acheterRessources(Joueur joueur){
		int[] qte=new int[4]; //quantité choisies par le joueur
		String[] type={"charbon","gaz","pétrole","uranium"};
		int valider_achat=0;
		Scanner sc=new Scanner(System.in);
		for(int i=0;i<4;i++){
			do{
			System.out.println("\n\n/*************** Joueur "+joueur.getId()+" **************/\n");
			System.out.println("Voici l'état actuel du marché:");
			for(int j=0;j<4;j++) System.out.print(type[j]+": "+this.etat_actuel[j]+"  ");
			System.out.println();
			System.out.println("Voici vos ressources:");
			for(int j=0;j<4;j++) System.out.print(type[j]+": "+joueur.getRessources()[j]+"  "); System.out.println();
			System.out.println("Votre argent: "+joueur.getArgent());
			System.out.println("Vos usines: ");
			joueur.montrerUsines();
			System.out.println();
			System.out.println("Choisissez la quantité de "+type[i]+" que vous voulez acheter (tapez 0 si vous ne voulez pas acheter) :");
			qte[i]=sc.nextInt();
			if(qte[i]==0) break;
			if(!achat_impossible(qte[i],i,joueur)){
				System.out.println("Cela vous coutera: "+prix(i,qte[i])+" électrons. Tapez 1 pour valider votre achat, 0 pour l'annuler:");
				valider_achat=sc.nextInt();
			}
			}while(achat_impossible(qte[i],i,joueur)||valider_achat!=1);
			
			joueur.getRessources()[i]+=qte[i];
			joueur.setArgent(joueur.getArgent()-prixDefinitif(i,qte[i]));
		}
		
		
		this.setChanged();
		this.notifyObservers(CHANGEMENT_MARCHE_RESSOURCES);
	}
	
	
	public void acheterRessourcesIA(){
		
	}
}

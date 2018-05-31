package megawatt;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Joueur {
	private int id;
	private int argent;
	protected int nbrVilles;
	protected List<Usine> usines;
	private List<Ville> villes;
	protected int[] ressources = {0, 0, 0, 0};
	private Color couleur;
	
	public Joueur(int id, int argent, int nbrVilles, List<Usine> usines, List<Ville> villes, int[] ressources, Color couleur) {
		super();
		this.id = id;
		this.argent = argent;
		this.nbrVilles = nbrVilles;
		this.usines = usines;
		this.villes = villes;
		this.ressources = ressources;
		this.couleur=couleur;
	}
	
		public Joueur(int id, Color couleur) {
		this.id = id;
		this.argent = 50;
		this.usines = new ArrayList<Usine>();
		this.villes =new ArrayList<Ville>();
		this.couleur=couleur;
	}
	
	/**
	 * demande aux joueurs de selectionner les usines à activer( 
	 * et demande les ressources necessaires en fonction des usines)
	 * vérifie si les usines choisient sont activables et retourne le 
	 * nombres de villes alimentées. 
	 * @return
	 */
	//modifié par françois
	public int choisirUsines(){
		Scanner sc=new Scanner(System.in);
		int choix=0;
		boolean validation_choix=false;
		List<Integer> usines_choisies=new ArrayList<Integer>();
		
		
		//PHASE DE CHOIX DES USINES
		do{
			System.out.println("\n\n/*************** Joueur "+this.id+" **************/\n");
			this.montrerUsines();
			this.montrerRessources();
			this.montrerArgent();
			this.montrerNbVilles();
			System.out.println("\nSélectionnez les usines que vous voulez utiliser (Taper par exemple 2 pour sélectionner l'usine 2)");
			System.out.println("Tapez 0 lorsque vous avez validé votre sélection");
			choix=sc.nextInt();
			if(choix==0) validation_choix=true;
			if(choix!=0){
				if(!usine_non_selectionnable(choix)) usines_choisies.add(choix-1);
				else System.out.println("\nATTENTION: Vous ne pouvez pas utiliser l'usine choisie ou bien votre saisie n'est pas valide\n");
			}
		}while(!validation_choix);
		
		//PHASE POUR DETERMINER LE NOMBRE DE VILLES ALIMENTÉES AINSI QUE LES RESSOURCES CONSOMMÉES PAR LE JOUEUR
		int somme=0; //somme des villes alimentables par les usines choisies
		int type;
		for(int i=0;i<usines_choisies.size();i++){
			Usine usine=this.usines.get(usines_choisies.get(i));
			somme+=usine.getVilleAlimentable();
			if(usine.usine_hybride()){
				do{
					System.out.println("Ressources Usine Hybride: "+Arrays.toString(usine.getRessources()));
					System.out.println("Tapez la ressource que vous voulez utilisez pour cette usine:\nCharbon=0\nGaz=1\nPétrole=2\nUranium=3");
					type=sc.nextInt();
				}while(type<0||type>4||this.ressources[type]<usine.getRessources()[type]);
				this.ressources[type]-=usine.getRessources()[type];
			}else{
				for(int j=0;j<4;j++) this.ressources[j]-=usine.getRessources()[j];
			}
		}
		
		//sc.close();
		return somme>this.nbrVilles?this.nbrVilles:somme; //faut se rappeler que le joueur ne peut pas alimenter plus de ville qu'il n'en possède
	}
	
	//fonction rajoutée par françois pour savoir si l'usine sélectionnée peut être utilisée
	public boolean usine_non_selectionnable(int choix){
		boolean choix_invalide=true;
		boolean usine_alimentable=false;
		
		for(int i=0;i<this.usines.size();i++){
			if(choix-1==i) choix_invalide=false;
		}
		if(choix_invalide) return true;
		else{
			for(int i=0;i<4;i++){
				//le joueur posséde les ressources pour alimenter l'usine ou bien l'usine est écologique
				if(this.usines.get(choix-1).getRessources()[i]<=this.ressources[i] &&this.usines.get(choix-1).getRessources()[i]>0
						||this.usines.get(choix-1).usine_ecologique()) usine_alimentable=true; 
			}
		}
		return (choix_invalide||!usine_alimentable);
	}
	
	
	
	/**
	 * actualise l'argent du joueur en fonction des villes alimentées
	 */
	public void remunererJoueur(int nbreVillesAlimentees){
		int[] remuneration={10,22,33,44,54,64,73,
							82,90,98,105,112,118,124,
							129,134,138,142,145,148,150};  //argent gagné en fonction des villes alimentées (correspond à l'indice du tableau)
		this.argent+=remuneration[nbreVillesAlimentees];
		System.out.println("\nVous avez gagné "+remuneration[nbreVillesAlimentees]+" électrons\n");
		
		this.montrerUsines();
		this.montrerRessources();
		this.montrerArgent();
		this.montrerNbVilles();
	}
	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArgent() {
		return argent;
	}

	public void setArgent(int argent) {
		this.argent = argent;
	}

	public int getNbrVilles() {
		return nbrVilles;
	}

	public void setNbrVilles(int nbrVilles) {
		this.nbrVilles = nbrVilles;
	}

	public List<Usine> getUsines() {
		return usines;
	}

	public void setUsines(List<Usine> usines) {
		this.usines = usines;
	}

	public List<Ville> getVilles() {
		return villes;
	}

	public void setVilles(List<Ville> villes) {
		this.villes = villes;
	}
	///////Guillaume
	public void addVilles(Ville v) {
		this.villes.add(v);
		this.nbrVilles++;
	}
	///////Guillaume
	public void depenser(int cout) {
		System.out.println(cout);
		this.argent -= cout;
	}

	public int[] getRessources() {
		return ressources;
	}

	public void setRessources(int[] ressources) {
		this.ressources = ressources;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + argent;
		result = prime * result + id;
		result = prime * result + nbrVilles;
		result = prime * result + Arrays.hashCode(ressources);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Joueur other = (Joueur) obj;
		if (argent != other.argent)
			return false;
		if (id != other.id)
			return false;
		if (nbrVilles != other.nbrVilles)
			return false;
		if (!Arrays.equals(ressources, other.ressources))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Joueur [id=" + id + ", argent=" + argent + ", nbrVilles=" + nbrVilles + ", ressources="
				+ Arrays.toString(ressources) + "]";
	}
	
	/**
	 * ajoute une usine au joueur tout en vérifiant ses ressources et
	 * son nombre d'usines.
	 * @param usine
	 */
	public void ajouterUsine(Usine usine){
		if(this.usines.size()==3){
			this.montrerUsines();
			System.out.println("Trop d'usines: Choisir l'index de l'usine à supprimer:");
			Scanner sc=new Scanner(System.in);
			int choixUsine=sc.nextInt();
			this.usines.remove(choixUsine);
			this.usines.add(usine);
		}
		else{
			this.usines.add(usine);
		}
	}
	
	
	//fonction rajoutée par françois
	/**
	 * permet de connaitre quelle est la valeur maximale des usines du joueur
	 */
	public int valeurMaxUsines(){
		int max=0;
		for(int i=0;i<this.usines.size();i++){
			if(max<this.usines.get(i).getPrixInitial()) max=this.usines.get(i).getPrixInitial();
		}
		return max;
	}
	
	
	//fonction rajoutée par moi pour montrer les usines du joueur
	public void montrerUsines(){
		System.out.println("Voici vos usines:");
		for(int i=0;i<this.usines.size();i++){
			System.out.print("Usine "+(i+1)+": Ressources="+Arrays.toString(this.usines.get(i).getRessources())+" | Villes_alimentables: "+this.usines.get(i).getVilleAlimentable());
			System.out.println();
		}
	}
	//fonction rajoutée par moi pour montrer les ressources du joueur
	public void montrerRessources(){
		System.out.println("Voici vos ressources:");
		String[] type={"charbon","gaz","petrole","uranium"};
		for(int i=0;i<4;i++) System.out.print(type[i]+": "+this.ressources[i]+"  ");
		System.out.println();
	}
	//fonction rajoutée par moi pour montrer l'argent du joueur
	public void montrerArgent(){
		System.out.println("Votre argent: "+this.argent+" électrons");
	}
	//fonction rajoutée par moi pour montrer le nombre de villes du joueur
	public void montrerNbVilles(){
		System.out.println("Votre nombre de villes: "+this.nbrVilles);
	}
	
	
}

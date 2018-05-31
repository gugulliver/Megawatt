package megawatt;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		
		Joueur[] joueurs=new Joueur[3];
		for(int i=0;i<3;i++){
			Joueur j=new Joueur(i+1, Color.black);
			joueurs[i]=j;
		}
		
		ArrayList<Usine> usines=new ArrayList<Usine>();
		for(int i=0;i<30;i++){
			Usine temp=new Usine(new int[]{1,0,0,0},i,1,1);
			usines.add(temp);
		}
		MarcheU marche=new MarcheU();
		Ressources rs=new Ressources();
		
		String[] v=new String[]{"MEXICO CITY SOUTH","MEXICO CITY NORTH","GUADALAJARA","MONTERREY","CHIHUAHUA","JUAREZ","ALBUQUERQUE", //zone 1 (violette)
	 			 "SAN DIEGO","LOS ANGELES","SAN FRANCISCO","PORTLAND","LAS VEGAS","SALT LAKE CITY","DENVER",		   //zone 2 (bleue)	
	 			 "SEATTLE","VANCOUVER","CALGARY","EDMONTON","REGINA","WINNIPEG","MINNEAPOLIS",						   //zone 3 (jaune)	
	 			 "MILWAUKEE","CHICAGO","INDIANAPOLIS","SAINT LOUIS","KANSAS CITY","OKLAHOMA CITY","MEMPHIS",		   //zone 4 (verte)
	 			 "SAN ANTONIO","HOUSTON","DALLAS/FORT WORTH","NEW ORLEANS","ATLANTA","JACKSONVILLE","MIAMI",		   //zone 5 (orange)
	 			 "CHARLOTTE","NASHVILLE","WASHINGTON","COLUMBUS","PITTSBURGH","DETROIT","TORONTO",					   //zone 6 (rouge)
	 			 "PHILADELPHIA","NEW YORK SOUTH","NEW YORK NORTH","OTTAWA","BOSTON","MONTREAL","QUEBEC"};			   //zone 7 (cyan)
		
		Ville[] villes=new Ville[v.length];
		for(int i=0;i<villes.length;i++){
			Ville vi=new Ville(v[i]);
			villes[i]=vi;
		}
		Partie p=new Partie(joueurs, villes, marche,  rs);
		p.etape1();
		//System.out.println("\n\n\n**********************************************************************************\n"
		//		+ "/*****************************ETAPE 2*********************************************/*\n"
		//		+ "********************************************************************************\n\n\n");
		//p.etape2();
		//System.out.println("\n\n\ns**********************************************************************************\n"
		//		+ "/*****************************ETAPE 3*********************************************/*\n"
		//		+ "********************************************************************************\n\n\n");
		//p.etape3();
		

	}
		 
		 
	

}

package model;

import java.util.ArrayList;
import java.util.Comparator;

public class MedalOrder implements Comparator<Country>{

	ArrayList<Country> countries = new ArrayList<>();

	public void addCountry(String name, int mg, int ms, int mb, int fg, int fs, int fb) {
		countries.add(new Country(name, mg, ms, mb, fg, fs, fb));
	}
	
	public String printOrders() {
		String msg = orderByMale();
		msg += orderByFemale();
		msg += orderByMedals();
		return msg;
	}

	private String orderByMale(){
		boolean inversion = true;
		for (int i = 0; i < countries.size() && inversion; i++) {
 			inversion = false;
 	    	for (int j = 1; j < countries.size() - i; j++) {
 	    		if (compare(countries.get(j), countries.get(j-1)) > 0) {
 	    			Country temp = countries.get(j);
 	    			countries.set(j, countries.get(j-1));
 	    			countries.set(j-1, temp);
 	    			inversion = true;
 	    		}
 	    	}
 	    }
		return printByMale();
	}
	
	private String orderByFemale(){
		boolean inversion = true;
		for (int i = 0; i < countries.size() && inversion; i++) {
 			inversion = false;
 	    	for (int j = 1; j < countries.size() - i; j++) {
 	    		if (countries.get(j).compareTo(countries.get(j-1)) < 0) {
 	    			Country temp = countries.get(j);
 	    			countries.set(j, countries.get(j-1));
 	    			countries.set(j-1, temp);
 	    			inversion = true;
 	    		}
 	    	}
 	    }
		return printByFemale();
	}
	
	private String orderByMedals(){
		boolean inversion = true;
		for (int i = 0; i < countries.size() && inversion; i++) {
 			inversion = false;
 	    	for (int j = 1; j < countries.size() - i; j++) {
 	    		if (combined(countries.get(j), countries.get(j-1)) > 0) {
 	    			Country temp = countries.get(j);
 	    			countries.set(j, countries.get(j-1));
 	    			countries.set(j-1, temp);
 	    			inversion = true;
 	    		}
 	    	}
 	    }
		return printByMedals();
	}
	
	private int combined(Country c0, Country c1) {
		int output = (c0.getMg()+c0.getFg())-(c1.getMg()+c1.getFg());
		if(output == 0)
			output = (c0.getMs()+c0.getFs())-(c1.getMs()+c1.getFs());
		if(output == 0)
			output = (c0.getMb()+c0.getFb())-(c1.getMb()+c1.getFb());
		if(output == 0)
			output = c0.getName().compareTo(c1.getName());
		return output;
	}
	
	private String printByMale() {
		String msg = "Masculino\n";
		for(int i=0; i<countries.size(); i++) {
			msg += countries.get(i).getName()+" "+countries.get(i).getMg()+" "+countries.get(i).getMs()+" "+countries.get(i).getMb()+"\n";
		}
		msg += "----------\n";
		return msg;
	}
	
	private String printByFemale() {
		String msg = "Femenino\n";
		for(int i=0; i<countries.size(); i++) {
			msg += countries.get(i).getName()+" "+countries.get(i).getFg()+" "+countries.get(i).getFs()+" "+countries.get(i).getFb()+"\n";
		}
		msg += "----------\n";
		return msg;
	}
	
	private String printByMedals() {
		String msg = "Combinado";
		for(int i=0; i<countries.size(); i++) {
			msg += "\n"+countries.get(i).getName()+" ";
			msg += (countries.get(i).getMg()+countries.get(i).getFg())+" ";
			msg += (countries.get(i).getMs()+countries.get(i).getFs())+" ";
			msg += (countries.get(i).getMb()+countries.get(i).getFb());
		}
		return msg;
	}

	@Override
	public int compare(Country c0, Country c1) {
		int output = c0.getMg()-c1.getMg();
		if(output == 0)
			output = c0.getMs()-c1.getMs();
		if(output == 0)
			output = c0.getMb()-c1.getMb();
		if(output == 0)
			output = c0.getName().compareTo(c1.getName());
		return output;
	}
}

package model;

public class Country implements Comparable<Country>{
	
	private String name;
	private int mg, ms, mb, fg, fs, fb;
	
	public Country(String name, int mg, int ms, int mb, int fg, int fs, int fb) {
		this.name = name;
		this.mg = mg;
		this.ms = ms;
		this.mb = mb;
		this.fg = fg;
		this.fs = fs;
		this.fb = fb;
	}

	public String getName() {
		return name;
	}

	public int getMg() {
		return mg;
	}

	public int getMs() {
		return ms;
	}

	public int getMb() {
		return mb;
	}

	public int getFg() {
		return fg;
	}

	public int getFs() {
		return fs;
	}

	public int getFb() {
		return fb;
	}

	@Override
	public int compareTo(Country c0) {
		int output = fg - c0.getFg();
		if(output == 0)
			output = fs - c0.getFs();
		if(output == 0)
			output = fb - c0.getFb();
		if(output == 0)
			output = name.compareTo(c0.getName());
		return output;
	}
}

import java.awt.Color;

public class Palette {
	private Color[] colors;
	private String name;

	public Palette(Color[] c, String n) {
		// TODO Auto-generated constructor stub
		colors = c;
		name = n;
	}

	public static void main(String[] args) {
		// Test-driven development

	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Palette) {
			Palette p = (Palette) obj;
			if (this.colors.length != p.colors.length) {
				return false;
			}
			if (!this.name.equals(p.name)) {
				return false;
			}
			for (int i = 0; i < p.colors.length; i++) {
				if (!p.colors[i].equals(this.colors[i])) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

}

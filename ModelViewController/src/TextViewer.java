
public class TextViewer implements View {

	private DrawingModel model;

	@Override
	public void update(DrawingModel m) {
		this.model = m;
	}

	/**
	 * Used to display metadata about shapes in the console
	 */
	protected void display() {
		// draw the shapes
		if (model != null) {
			System.out.println("ᘳ⪩ ͟ل͜⪨ᘰ 乁(■ᨎ■)ㄏ ヽ(`◞ `)ﾉ");
			for (Shape s : model.getShapes()) {
				System.out.println(s);
			}
		}
	}

}

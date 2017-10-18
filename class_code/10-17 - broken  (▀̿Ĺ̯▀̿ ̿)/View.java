/**
 *  A view is able to receive data from the model
 * @author codes
 *
 */
public interface View {
	/**
	 * Called to send to the view the data of the model
	 */
	void update(PolygonModel model);

}

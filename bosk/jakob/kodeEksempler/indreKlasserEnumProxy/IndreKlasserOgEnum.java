package bosk.jakob.kodeEksempler.indreKlasserEnumProxy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;


public class IndreKlasserOgEnum {

	Model model = new Model();
	View view  = new View();
	public static final double MOVE_LENGTH = 0.3;

	private void addMoveGUIButtonListeners(){

		//Tilføjer en ActionListener til en														 
		//GUI knap i en anden klasse
		view.addUpListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0){
				Rectangle2D.Double move = newBounds(model.getBounds(), MOVE_LENGTH, Direction.NORTH);
				model.updateBounds(move);
				repaint();
			}});
	}

	public class Model{
		
		Rectangle2D.Double m;
		
		public Model(){	
			m = new Rectangle2D.Double(100, 100, 100, 100);
		}
		
		//Returns the actual bounds - not a copy! Should make a deep copy of "m", else user can edit 
		//the object when he should not. 
		public Rectangle2D.Double getBounds(){
			return m;	
		}
		
		//Should make a deep copy of move and set m to the hard copy - else user can edit m 
		//even if he should not. 
		public void updateBounds(Rectangle2D.Double move){
			m = move;
		} 

	}
	
	public class View{
		public View(){
		}
		public void addUpListener(ActionListener al){
		}
	}
	
	private static Rectangle2D.Double newBounds(Rectangle2D.Double old, double length, Direction direction){
		switch(direction){
		case WEST:
			double xw = old.x + (-1 * old.width * length);
			return new Rectangle2D.Double(xw, old.y, old.width, old.height);
			
		case EAST:
			double xe = old.x + (1 * old.width * length);
			return new Rectangle2D.Double(xe, old.y, old.width, old.height);
			
		case NORTH:
			double yn = old.y + (1 * old.height * length);
			return new Rectangle2D.Double(old.x, yn, old.width, old.height);
			
		case SOUTH:
			double ys = old.y + (-1 * old.height * length);
			return new Rectangle2D.Double(old.x, ys, old.width, old.height);
			
		case IN:
			return new Rectangle2D.Double(
					old.x + length * old.width, //x is increased by the factor in proportion to the width
					old.y + length * old.height, //y is increased by the factor in proportion to the height
					old.width - (length * old.width * 2), //width is decreased by the factor
					old.height - (length * old.height * 2) //height is decreased by the factor
			);
			
		case OUT:
			return new Rectangle2D.Double(old.x - old.width * length, //x is decreased by the factor in proportion to the width
					old.y - old.height * length, //y is decreased by the factor in proportion to the height
					old.width + old.width * length * 2, //width is increased by the factor
					old.height + old.height * length * 2//height is increased by the factor
			);
		}
		
		return old;
	
	}
	
	private void repaint(){		
	}
}

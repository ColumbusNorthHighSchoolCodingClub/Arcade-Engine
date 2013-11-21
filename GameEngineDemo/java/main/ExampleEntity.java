package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Random;

import src.AnimPanel;
import src.Entity;

public class ExampleEntity extends Entity {

	private static Random rand = new Random();
	
	private Color myColor, myOutline;
	
	public ExampleEntity(AnimPanel panel, int x, int y, int width, int height) {
		super(panel, x, y, rand.nextInt(100) + 2, rand.nextInt(100) + 2);
		
		myColor = new Color(255, 20, 255, 150);
		myOutline = new Color(myColor.getRed(), myColor.getGreen(), myColor.getBlue(), 255);
		int xVel = rand.nextInt(20) - 10, yVel = rand.nextInt(20) - 10;
		
		while(xVel == 0 && yVel == 0) {
			xVel = rand.nextInt(20) - 10;
			yVel = rand.nextInt(20) - 10;
		}
		
		this.setVelocity(xVel, yVel);
	}

	@Override
	public void draw(Graphics g) {
		
		g.setColor(myColor);
		g.fillRect(coord.x, coord.y, width, height);

		g.setColor(myOutline);
		for(int coord = 0, coord2; coord < bounds.npoints; coord++) {

			if(coord == bounds.npoints - 1) coord2 = 0;
			else coord2 = coord + 1;
			
			g.drawLine(bounds.xpoints[coord], bounds.ypoints[coord], 
					   bounds.xpoints[coord2], bounds.ypoints[coord2]);
		}
	}
	
	@Override
	public void moveWithVelocity(ArrayList<Entity> entities) {
		
		if(this.vel.x == 0 && this.vel.y == 0)
			return;
	
		if(this.getCollidedEnts(entities, vel.x, vel.y).size() == 0) {
			
			bounds.translate(vel.x, vel.y);
			
			coord.x += vel.x;
			coord.y += vel.y;
		}
		else {
			
			boolean invLoc = true;
			
			double prop = .5, loop = 0;
			
			while(invLoc) {
									
				if(this.getCollidedEnts(entities, vel.x, vel.y, prop).size() == 0) {
					
					if(loop >= 15) invLoc = false;
					else prop += prop/((double) 2);
				}
				else {
					prop -= prop/((double) 2);
				}
				
				loop++;
			}
			
			int newX = (int) (((double) vel.x) * prop),
				newY = (int) (((double) vel.y) * prop);;
			
			bounds.translate(newX, newY);
			
			coord.x += newX;
			coord.y += newY;
			
			
			int xVel = rand.nextInt(20) - 10, yVel = rand.nextInt(20) - 10;
			
			while(xVel == 0 && yVel == 0) {
				xVel = rand.nextInt(20) - 10;
				yVel = rand.nextInt(20) - 10;
			}
			
			this.setVelocity(xVel, yVel);
		}
}
	
	
	@Override
	public void think() {
		
	}
}

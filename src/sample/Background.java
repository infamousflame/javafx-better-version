package sample;

import java.awt.Image;

public class Background {
		int x, y, height, width, offset;

		public Background( int x, int y, int height, int width, int offset){
			this.x = x;
			this.y = y;
			this.height = height;
			this.width = width;
			this.offset = offset;
		}
		
		public int getX(){
			return x;
		}
		public int getY(){
			return y;
		}
		public int getHeight(){
			return height;
		}
		public int getWidth(){
			return width;
		}
		public int getTrueX(){
			return x + offset;
		}
		public void setX(int x){
			this.x = x;
		}
		public void setY(int y){
			this.y = y;
		}
		public void setHeight(int height){
			this.height = height;
		}
		public void setWidth(int width){
			this.width = width;
		}
	}


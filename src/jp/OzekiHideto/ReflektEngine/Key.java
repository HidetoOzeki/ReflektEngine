package jp.OzekiHideto.ReflektEngine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Key extends KeyAdapter{
	public Key(){
		
	}
	public class Keys{
		public boolean pressed;
		public Keys(){
			
		}
		public void toggle(boolean pressed){
			this.pressed = pressed;
		}
	}
	public Keys up = new Keys();
	public Keys down = new Keys();
	public Keys left = new Keys();
	public Keys right = new Keys();
	public Keys space = new Keys();
	public void toggleKeys(int kc,boolean pressed){
		if(kc==KeyEvent.VK_W)up.toggle(pressed);
		if(kc==KeyEvent.VK_S)down.toggle(pressed);
		if(kc==KeyEvent.VK_D)right.toggle(pressed);
		if(kc==KeyEvent.VK_A)left.toggle(pressed);
		if(kc==KeyEvent.VK_SPACE)space.toggle(pressed);
	}
	@Override
	public void keyPressed(KeyEvent k) {
		toggleKeys(k.getKeyCode(),true);
	}
	@Override
	public void keyReleased(KeyEvent k) {
		toggleKeys(k.getKeyCode(),false);
	}
	
}

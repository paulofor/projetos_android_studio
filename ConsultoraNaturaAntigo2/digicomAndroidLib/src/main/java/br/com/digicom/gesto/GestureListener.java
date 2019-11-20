package br.com.digicom.gesto;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

public class GestureListener extends SimpleOnGestureListener {

	 public static String currentGestureDetected;
   
     @Override
     public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
    	 boolean result = false;
         try {
            if (distanceX <= - 30) {
            	onSwipeRight();
            	result = true;
            }
         } catch (Exception exception) {
             exception.printStackTrace();
         }
         return result;


     }
    
     @Override
     public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
    	 boolean result = false;
         try {
             float diffY = e2.getY() - e1.getY();
             float diffX = e2.getX() - e1.getX();
             if (Math.abs(diffX) > Math.abs(diffY)) {
                 if (Math.abs(diffX) > 100 && Math.abs(velocityX) > 100) {
                     if (diffX > 0) {
                         onSwipeRight();
                     } else {
                         onSwipeLeft();
                     }
                 }
             } else {
                 if (Math.abs(diffY) > 100 && Math.abs(velocityY) > 100) {
                     if (diffY > 0) {
                         onSwipeBottom();
                     } else {
                         onSwipeTop();
                     }
                 }
             }
         } catch (Exception exception) {
             exception.printStackTrace();
         }
         return result;

     }
	private void onSwipeTop() {
		// TODO Auto-generated method stub
		
	}
	private void onSwipeBottom() {
		// TODO Auto-generated method stub
		
	}
	private void onSwipeLeft() {
		// TODO Auto-generated method stub
		
	}
	private void onSwipeRight() {
		// TODO Auto-generated method stub
		currentGestureDetected = "direita";
		
	}
}

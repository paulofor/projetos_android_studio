package br.com.digicom.video;


import android.content.Context;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.AttributeSet;
import android.widget.VideoView;
import br.com.digicom.log.DCLog;




public class DigicomVideoView extends VideoView {

	int mVideoWidth = 0;
	int mVideoHeight = 0;
	
	//long tempoParada = 0;
	private DigicomVideoListener mListener;
	
	public static final String TAG = "VideoTag";
	
	
	
    public DigicomVideoView(Context context) {
        super(context);
    }
    
    public DigicomVideoView(Context context, AttributeSet attrs) {
    	super(context, attrs);
    }
    
    public void setListener(DigicomVideoListener listener) {
        mListener = listener;
        this.setOnCompletionListener(listener);
    }
    
    @Override
    public void pause() {
        super.pause();
        if (mListener != null) {
            mListener.onPause(this.getCurrentPosition());
        }
        //DCLog.d(TAG, this, "Posicao:" + tempoParada);
    }
    /*
    public DigicomVideoView(Context context, AttributeSet attrs) {
    	super(context, attrs);
    }
    */
    
    
   
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    	//setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    	System.out.println("widthMeasureSpec:" + widthMeasureSpec);
    	System.out.println("heightMeasureSpec:" + heightMeasureSpec);
    	System.out.println("getTop():" + getTop());
    	System.out.println("getLeft():" + getLeft());
    	System.out.println("getHeight():" + getHeight());
    	System.out.println("getWidth():" + getWidth());
        //super.onMeasure(widthMeasureSpec,heightMeasureSpec);
       	System.out.println("getTop():" + getTop());
    	System.out.println("getLeft():" + getLeft());
    	System.out.println("getHeight():" + getHeight());
    	System.out.println("getWidth():" + getWidth());
        //setMeasuredDimension(widthMeasureSpec, widthMeasureSpec);
        //int width = getDefaultSize(mVideoWidth, widthMeasureSpec);
        //int height = getDefaultSize(mVideoHeight, heightMeasureSpec);
    	
    	int width = getDefaultSize(mVideoWidth, widthMeasureSpec);
	    int height = getDefaultSize(mVideoHeight, heightMeasureSpec);
        if (mVideoWidth > 0 && mVideoHeight > 0) {
        if ( mVideoWidth * height  > width * mVideoHeight ) {
        	//Log.i("@@@", "image too tall, correcting");
        	height = width * mVideoHeight / mVideoWidth;
    	} else if ( mVideoWidth * height  < width * mVideoHeight ) {
    		//Log.i("@@@", "image too wide, correcting");
    	    width = height * mVideoWidth / mVideoHeight;
    		} else {
    		//Log.i("@@@", "aspect ratio is correct: " +
    	    //width+"/"+height+"="+
    	    //mVideoWidth+"/"+mVideoHeight);
    		}
    	}
        //Log.i("@@@@@@@@@@", "setting size: " + width + 'x' + height);
    	setMeasuredDimension(width, height);
    }
    
    public void reproduzVideo(String arquivo, int posIni) {
		DCLog.d(DigicomVideoView.TAG, this, ": Arquivo:" + arquivo);
		try {
			setVideoPath(arquivo);
			seekTo(posIni);
			start();
			requestFocus();
		} catch (Exception e) {
			DCLog.e(DigicomVideoView.TAG, this, e);
		}
	}
    
    @Override
    public void stopPlayback() {
        super.stopPlayback();
       
        DCLog.d(TAG, this, "Stop:");
    }
    
    public static interface DigicomVideoListener extends OnCompletionListener{
        void onPause(int posicao);
    }

}
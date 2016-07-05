package com.xitiz.camera;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by xitiz on 6/27/16.
 */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mHolder;
    private Camera mCamera;
    public static final String TAG = "Clicks";

    public CameraPreview(Context context, Camera camera){
        super(context);
        mCamera = camera;
        mCamera.setDisplayOrientation(90);

        mHolder =  getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        try{
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
//            mCamera.startFaceDetection();
        } catch (IOException e){
            Log.d(TAG,"Error setting camera Preview" + e.getMessage());
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
//        mCamera.stopFaceDetection();
        mCamera.stopPreview();
        mCamera.release();
        mCamera = null;

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h){
        refreshCamera();
    }

    public void refreshCamera(){
        if(mHolder.getSurface() == null){
            return;
        }

        try{
            //mCamera.stopFaceDetection();
            mCamera.stopPreview();
        } catch (Exception e){

        }

        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();
//            mCamera.startFaceDetection();
        } catch (Exception e){
            Log.d(TAG, "Error starting camera preview: " + e.getMessage());
        }
    }


}

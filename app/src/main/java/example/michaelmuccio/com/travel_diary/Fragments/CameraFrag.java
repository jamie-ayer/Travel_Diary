package example.michaelmuccio.com.travel_diary.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import example.michaelmuccio.com.travel_diary.Activities.CameraActivity;
import example.michaelmuccio.com.travel_diary.R;

//public class CameraFrag extends CameraBaseFrag {
//    // Native camera.
//    private Camera mCamera;
//
//    // View to display the camera output.
//    private CameraPreview mPreview;
//
//    // Reference to the containing view.
//    private View mCameraView;
//
//    /**
//     * Default empty constructor.
//     */
//    public CameraFrag(){
//        super();
//    }
//
//    /**
//     * Static factory method
//     * @param sectionNumber
//     * @return
//     */
//    public static CameraFrag newInstance(int sectionNumber) {
//        CameraFrag fragment = new CameraFrag();
//        Bundle args = new Bundle();
//        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    /**
//     * OnCreateView fragment override
//     * @param inflater
//     * @param container
//     * @param savedInstanceState
//     * @return
//     */
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.camera_frag, container, false);
//
//        // Create our Preview view and set it as the content of our activity.
//        boolean opened = safeCameraOpenInView(view);
//
//        if(opened == false){
//            Log.d("CameraGuide","Error, Camera failed to open");
//            return view;
//        }
//
//        // Trap the capture button.
//        ImageButton captureButton = (ImageButton) view.findViewById(R.id.button_capture);
//        captureButton.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // get an image from the camera
//                        mCamera.takePicture(null, null, mPicture);
//                    }
//                }
//        );
//
//        return view;
//    }
//
//    /**
//     * Recommended "safe" way to open the camera.
//     * @param view
//     * @return
//     */
//    private boolean safeCameraOpenInView(View view) {
//        boolean qOpened = false;
//        releaseCameraAndPreview();
//        mCamera = getCameraInstance();
//        mCameraView = view;
//        qOpened = (mCamera != null);
//
//        if(qOpened == true){
//            mPreview = new CameraPreview(getActivity().getBaseContext(), mCamera,view);
//            FrameLayout preview = (FrameLayout) view.findViewById(R.id.camera_preview);
//            preview.addView(mPreview);
//            mPreview.startCameraPreview();
//        }
//        return qOpened;
//    }
//
//    /**
//     * Safe method for getting a camera instance.
//     * @return
//     */
//    public static Camera getCameraInstance(){
//        Camera c = null;
//        try {
//            c = Camera.open(); // attempt to get a Camera instance
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        return c; // returns null if camera is unavailable
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        releaseCameraAndPreview();
//    }
//
//    /**
//     * Clear any existing preview / camera.
//     */
//    private void releaseCameraAndPreview() {
//
//        if (mCamera != null) {
//            mCamera.stopPreview();
//            mCamera.release();
//            mCamera = null;
//        }
//        if(mPreview != null){
//            mPreview.destroyDrawingCache();
//            mPreview.mCamera = null;
//        }
//    }
//
//    /**
//     * Surface on which the camera projects it's capture results. This is derived both from Google's docs and the
//     * excellent StackOverflow answer provided below.
//     *
//     * Reference / Credit: http://stackoverflow.com/questions/7942378/android-camera-will-not-work-startpreview-fails
//     */
//    class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
//
//        // SurfaceHolder
//        private SurfaceHolder mHolder;
//
//        // Our Camera.
//        private Camera mCamera;
//
//        // Parent Context.
//        private Context mContext;
//
//        // Camera Sizing (For rotation, orientation changes)
//        private Camera.Size mPreviewSize;
//
//        // List of supported preview sizes
//        private List<Camera.Size> mSupportedPreviewSizes;
//
//        // Flash modes supported by this camera
//        private List<String> mSupportedFlashModes;
//
//        // View holding this camera.
//        private View mCameraView;
//
//        public CameraPreview(Context context, Camera camera, View cameraView) {
//            super(context);
//
//            // Capture the context
//            mCameraView = cameraView;
//            mContext = context;
//            setCamera(camera);
//
//            // Install a SurfaceHolder.Callback so we get notified when the
//            // underlying surface is created and destroyed.
//            mHolder = getHolder();
//            mHolder.addCallback(this);
//            mHolder.setKeepScreenOn(true);
//            // deprecated setting, but required on Android versions prior to 3.0
//            mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
//        }
//
//        /**
//         * Begin the preview of the camera input.
//         */
//        public void startCameraPreview()
//        {
//            try{
//                mCamera.setPreviewDisplay(mHolder);
//                mCamera.startPreview();
//            }
//            catch(Exception e){
//                e.printStackTrace();
//            }
//        }
//
//        /**
//         * Extract supported preview and flash modes from the camera.
//         * @param camera
//         */
//        private void setCamera(Camera camera)
//        {
//            // Source: http://stackoverflow.com/questions/7942378/android-camera-will-not-work-startpreview-fails
//            mCamera = camera;
//            mSupportedPreviewSizes = mCamera.getParameters().getSupportedPreviewSizes();
//            mSupportedFlashModes = mCamera.getParameters().getSupportedFlashModes();
//
//            // Set the camera to Auto Flash mode.
//            if (mSupportedFlashModes != null && mSupportedFlashModes.contains(Camera.Parameters.FLASH_MODE_AUTO)){
//                Camera.Parameters parameters = mCamera.getParameters();
//                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
//                mCamera.setParameters(parameters);
//            }
//
//            requestLayout();
//        }
//
//        /**
//         * The Surface has been created, now tell the camera where to draw the preview.
//         * @param holder
//         */
//        public void surfaceCreated(SurfaceHolder holder) {
//            try {
//                mCamera.setPreviewDisplay(holder);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        /**
//         * Dispose of the camera preview.
//         * @param holder
//         */
//        public void surfaceDestroyed(SurfaceHolder holder) {
//            if (mCamera != null){
//                mCamera.stopPreview();
//            }
//        }
//
//        /**
//         * React to surface changed events
//         * @param holder
//         * @param format
//         * @param w
//         * @param h
//         */
//        public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
//            // If your preview can change or rotate, take care of those events here.
//            // Make sure to stop the preview before resizing or reformatting it.
//
//            if (mHolder.getSurface() == null){
//                // preview surface does not exist
//                return;
//            }
//
//            // stop preview before making changes
//            try {
//                Camera.Parameters parameters = mCamera.getParameters();
//
//                // Set the auto-focus mode to "continuous"
//                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
//
//                // Preview size must exist.
//                if(mPreviewSize != null) {
//                    Camera.Size previewSize = mPreviewSize;
//                    parameters.setPreviewSize(previewSize.width, previewSize.height);
//                }
//
//                mCamera.setParameters(parameters);
//                mCamera.startPreview();
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//
//        /**
//         * Calculate the measurements of the layout
//         * @param widthMeasureSpec
//         * @param heightMeasureSpec
//         */
//        @Override
//        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
//        {
//            // Source: http://stackoverflow.com/questions/7942378/android-camera-will-not-work-startpreview-fails
//            final int width = resolveSize(getSuggestedMinimumWidth(), widthMeasureSpec);
//            final int height = resolveSize(getSuggestedMinimumHeight(), heightMeasureSpec);
//            setMeasuredDimension(width, height);
//
//            if (mSupportedPreviewSizes != null){
//                mPreviewSize = getOptimalPreviewSize(mSupportedPreviewSizes, width, height);
//            }
//        }
//
//        /**
//         * Update the layout based on rotation and orientation changes.
//         * @param changed
//         * @param left
//         * @param top
//         * @param right
//         * @param bottom
//         */
//        @Override
//        protected void onLayout(boolean changed, int left, int top, int right, int bottom)
//        {
//            // Source: http://stackoverflow.com/questions/7942378/android-camera-will-not-work-startpreview-fails
//            if (changed) {
//                final int width = right - left;
//                final int height = bottom - top;
//
//                int previewWidth = width;
//                int previewHeight = height;
//
//                if (mPreviewSize != null){
//                    Display display = ((WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
//
//                    switch (display.getRotation())
//                    {
//                        case Surface.ROTATION_0:
//                            previewWidth = mPreviewSize.height;
//                            previewHeight = mPreviewSize.width;
//                            mCamera.setDisplayOrientation(90);
//                            break;
//                        case Surface.ROTATION_90:
//                            previewWidth = mPreviewSize.width;
//                            previewHeight = mPreviewSize.height;
//                            break;
//                        case Surface.ROTATION_180:
//                            previewWidth = mPreviewSize.height;
//                            previewHeight = mPreviewSize.width;
//                            break;
//                        case Surface.ROTATION_270:
//                            previewWidth = mPreviewSize.width;
//                            previewHeight = mPreviewSize.height;
//                            mCamera.setDisplayOrientation(180);
//                            break;
//                    }
//                }
//
//                final int scaledChildHeight = previewHeight * width / previewWidth;
//                mCameraView.layout(0, height - scaledChildHeight, width, height);
//            }
//        }
//
//        /**
//         *
//         * @param sizes
//         * @param width
//         * @param height
//         * @return
//         */
//        private Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int width, int height)
//        {
//            // Source: http://stackoverflow.com/questions/7942378/android-camera-will-not-work-startpreview-fails
//            Camera.Size optimalSize = null;
//
//            final double ASPECT_TOLERANCE = 0.1;
//            double targetRatio = (double) height / width;
//
//            // Try to find a size match which suits the whole screen minus the menu on the left.
//            for (Camera.Size size : sizes){
//
//                if (size.height != width) continue;
//                double ratio = (double) size.width / size.height;
//                if (ratio <= targetRatio + ASPECT_TOLERANCE && ratio >= targetRatio - ASPECT_TOLERANCE){
//                    optimalSize = size;
//                }
//            }
//
//            // If we cannot find the one that matches the aspect ratio, ignore the requirement.
//            if (optimalSize == null) {
//                // TODO : Backup in case we don't get a size.
//            }
//
//            return optimalSize;
//        }
//    }
//
//    /**
//     * Picture Callback for handling a picture capture and saving it out to a file.
//     */
//    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {
//
//        @Override
//        public void onPictureTaken(byte[] data, Camera camera) {
//
//            File pictureFile = getOutputMediaFile();
//            if (pictureFile == null){
//                Toast.makeText(getActivity(), "Image retrieval failed.", Toast.LENGTH_SHORT)
//                        .show();
//                return;
//            }
//
//            try {
//                FileOutputStream fos = new FileOutputStream(pictureFile);
//                fos.write(data);
//                fos.close();
//
//                // Restart the camera preview.
//                safeCameraOpenInView(mCameraView);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    };
//
//    /**
//     * Used to return the camera File output.
//     * @return
//     */
//    private File getOutputMediaFile(){
//
//        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES), "UltimateCameraGuideApp");
//
//        if (! mediaStorageDir.exists()){
//            if (! mediaStorageDir.mkdirs()){
//                Log.d("Camera Guide", "Required media storage does not exist");
//                return null;
//            }
//        }
//
//        // Create a media file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        File mediaFile;
//        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
//                "IMG_"+ timeStamp + ".jpg");
//
//        DialogHelper.showDialog( "Success!","Your picture has been saved!",getActivity());
//
//        return mediaFile;
//    }
//}

package org.dpk.d2dfc.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;


import androidx.core.app.ActivityCompat;

import org.dpk.d2dfc.D2DFC_HANDLER;
import org.dpk.d2dfc.data.constants.ApplicationConstants;
import org.dpk.d2dfc.data.db.DataBaseHelper;
import org.dpk.d2dfc.data_models.dao.FamilyInfoTable;
import org.dpk.d2dfc.data_models.dao.ITable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chandradasdipok on 5/31/2016.
 */
public class FileHandler {

    private static final int STORAGE_PERMISSION_CODE = 23;
    D2DFC_HANDLER d2DFC_handler;

    public FileHandler(D2DFC_HANDLER d2DFC_handler) {
        this.d2DFC_handler = d2DFC_handler;
    }

    public List<Uri> getReportFilesURIs(){
        List<Uri> reportURIs = new ArrayList<Uri>();
        File appFolder = new File(ApplicationConstants.externalStorageFolder, ApplicationConstants.appFolder);
        File [] reportFiles = appFolder.listFiles();
        for (File file: reportFiles){
            reportURIs.add(Uri.fromFile(file));
        }
        return reportURIs;
    }
    public static String getPath(Context context, Uri uri) throws URISyntaxException {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = { "_data" };
            Cursor cursor = null;

            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                // Eat it
            }
        }
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }
    public static void readFile(){

    }
    public  boolean createCSVFileFromTableData(ITable iTable){
        List<ITable> iTables = d2DFC_handler.selectRows(iTable);
        File newFolder = new File(ApplicationConstants.externalStorageFolder, ApplicationConstants.appFolder);
        if (!newFolder.exists()){
            newFolder.mkdir();
        }
        if (newFolder.exists()){
            Log.d("MKDR", newFolder.getAbsolutePath());
        }
        File file = new File(newFolder, iTable.tableName()+".csv");
        Log.d("File",file.getAbsolutePath());
        ApplicationConstants.filePath= file.getAbsolutePath();
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (ITable itable:iTables){
                Log.d("CSV-DATA",iTable.tableName()+":"+itable.toString()+":"+file.getName());
                fileWriter.write(itable.toString()+"\n");
            }
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }


}
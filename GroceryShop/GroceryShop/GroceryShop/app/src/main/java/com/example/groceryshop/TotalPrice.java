package com.example.groceryshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TotalPrice extends AppCompatActivity {
    TextView Home_adress, u;
    TextView totalprice;
    TextView cart_subtotal;
    TextView vat;
    Button paymentMethod;

    int price=0,Vat=0;
    String username;
    ImageView pdfbtn;
    RelativeLayout rl;

    Bitmap receiptsimple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_price);

        Home_adress = findViewById(R.id.ADRESS);
        username = getIntent().getStringExtra("username");
        pdfbtn = findViewById(R.id.Pdfbtn);
        u = findViewById(R.id.username);
        u.setText(username);


        totalprice=findViewById(R.id.totalPrice);
        cart_subtotal=findViewById(R.id.cart_sub_total);
        vat=findViewById(R.id.vat);
        paymentMethod=findViewById(R.id.paymentMethod);
        username = getIntent().getStringExtra("username");
        String s1 = getIntent().getStringExtra("value");
        //price will come from cart
        cart_subtotal.setText(s1);
        price=Integer.parseInt(cart_subtotal.getText().toString());
        if(price<=100)
            Vat=0;
        else {
            Vat=10*(price/100);
            vat.setText(Integer.toString(Vat));
        }
        int t=Vat+price+50;
        totalprice.setText(Integer.toString(t));



        paymentMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(TotalPrice.this,PaymentMethod.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });



        pdfbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inflate the layout to a view
                View receiptView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.receipt, null);

                // Capture the layout view as a Bitmap
                Bitmap receiptBitmap = getBitmapFromView(receiptView);

                // Save the bitmap as an image in the MediaStore
                String jpegFileName = "temp_image.jpg"; // Provide the desired filename here
                saveBitmapToMediaStore(receiptBitmap, jpegFileName);

                // Retrieve the saved image's content URI from MediaStore
                Uri imageUri = FileProvider.getUriForFile(TotalPrice.this, getApplicationContext().getPackageName() + ".provider", new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), jpegFileName));

                // Create a PDF from the image in the MediaStore
                createPdfFromBitmap(receiptBitmap, "receipt-pdf.pdf");

                Intent intent1 = new Intent(TotalPrice.this, PaymentSuccessful.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });


        DatabaseReference userInfoRef = FirebaseDatabase.getInstance().getReference("Users").child(username);

        // Check if the username node exists
        userInfoRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Check if the "home address" child exists under the username
                    if (dataSnapshot.hasChild("Home Address")) {
                        // Read the value of "home address"
                        String homeAddress = dataSnapshot.child("Home Address").getValue(String.class);

                        // Set the value in a text field (replace this with your UI logic)
                        // For now, printing the value to the console
                        //Home_adress.setText(homeAddress);
                        Home_adress.setText("");
                    } else {
                        Home_adress.setText("");
                        //Toast.makeText(getApplicationContext(), "No 'home address' child under username: " + username, Toast.LENGTH_SHORT).show();

                    }
                } else {
                    //Toast.makeText(getApplicationContext(), "Username not found: " + username, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private Bitmap getBitmapFromView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    // Method to save a Bitmap as an image
    private void saveBitmapToMediaStore(Bitmap bitmap, String fileName) {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DISPLAY_NAME, fileName);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");

        ContentResolver resolver = getContentResolver();
        Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        try {
            OutputStream outputStream = resolver.openOutputStream(imageUri);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            if (outputStream != null) {
                outputStream.close();
            }
            Toast.makeText(getApplicationContext(), "Image saved to MediaStore", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/*
    private void createPdfFromBitmap(Bitmap bitmap, String fileName) {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File pdfFile = new File(path, fileName); // Final PDF file

        try {
            File jpegFile = new File(path, "temp_image.jpg"); // Temporarily save as JPEG

            // Save the bitmap as a JPEG image
            saveBitmapToMediaStore(bitmap, jpegFile.getAbsolutePath());

            // Convert the File to a content URI using FileProvider
            Uri imageUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", jpegFile);

            // Convert the image URI to a byte array
            byte[] imageBytes = getByteArrayFromUri(imageUri);

            // Now, convert the image content to a PDF
            PdfWriter pdfWriter = new PdfWriter(pdfFile);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument, PageSize.A4);

            // Create an iText Image object using the byte array
            com.itextpdf.layout.element.Image pdfImage = new com.itextpdf.layout.element.Image(ImageDataFactory.create(imageBytes));

            // Add the image to the PDF
            document.add(pdfImage);

            // Close the document
            document.close();

            // Show a toast message
            Toast.makeText(getApplicationContext(), "PDF created successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error creating PDF: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }*/
    private void createPdfFromBitmap(Bitmap bitmap, String fileName) {
        File path = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS); // Use appropriate directory
        File pdfFile = new File(path, fileName);

        try {
            PdfWriter pdfWriter = new PdfWriter(pdfFile);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);

            // Convert the bitmap to a byte array
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            // Create an iText Image object using the byte array
            ImageData imageData = ImageDataFactory.create(byteArray);
            com.itextpdf.layout.element.Image pdfImage = new com.itextpdf.layout.element.Image(imageData);

            // Add the image to the PDF
            document.add(pdfImage);

            // Close the document
            document.close();

            // Show a toast message
            Toast.makeText(getApplicationContext(), "PDF created successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error creating PDF: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }



    private byte[] getByteArrayFromUri(Uri uri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                byteArrayOutputStream.write(buffer, 0, length);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
package com.example.amarhisab;

import static androidx.appcompat.app.AlertDialog.Builder;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

@SuppressWarnings({"unused", "Convert2Lambda", "PointlessBooleanExpression", "WrapperTypeMayBePrimitive"})
public class HomeActivity extends AppCompatActivity {
    EditText name, bill, pay, due,date;
    Button insert, update, delete, view;
    DBHelper1 DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        name = (EditText) findViewById(R.id.name);
        bill = (EditText) findViewById(R.id.bill);
        pay =(EditText) findViewById(R.id.pay);
        due =(EditText) findViewById(R.id.due);
        date =(EditText) findViewById(R.id.date);
        insert =(Button) findViewById(R.id.btnInsert);
        update =(Button) findViewById(R.id.btnUpdate);
        delete =(Button) findViewById(R.id.btnDelete);
        view =(Button) findViewById(R.id.btnView);
        DB = new DBHelper1(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String billTXT = bill.getText().toString();
                String payTXT = pay.getText().toString();
                String dueTXT = due.getText().toString();
                String dateTXT = date.getText().toString();

                Boolean checkinsertdata = DB.insertdata(nameTXT, billTXT, payTXT,dueTXT,dateTXT);
                if(checkinsertdata==true)
                    Toast.makeText(HomeActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(HomeActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String billTXT = bill.getText().toString();
                String payTXT = pay.getText().toString();
                String dueTXT = due.getText().toString();
                String dateTXT = date.getText().toString();

                Boolean checkupdatedata = DB.updatedata(nameTXT, billTXT, payTXT,dueTXT,dateTXT);
                if(checkupdatedata==true) {
                    Toast.makeText(HomeActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(HomeActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }        });
        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                Boolean checkudeletedata = DB.deletedata(nameTXT);
                if(checkudeletedata==true)
                    Toast.makeText(HomeActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(HomeActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(HomeActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer;
                buffer = new StringBuffer();
                while(res.moveToNext()){
                    final StringBuffer append = buffer.append("Name :").append(res.getString(0)).append("\n\n");
                    buffer.append("Bill :").append(res.getString(1)).append("\n\n");
                    buffer.append("Pay :").append(res.getString(2)).append("\n\n");
                    buffer.append("Due :").append(res.getString(3)).append("\n\n");
                    buffer.append("Date :").append(res.getString(4)).append("\n\n");
                    buffer.append("--------------------------------------").append("\n\n");
                }

                Builder builder = new Builder(HomeActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Customer Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });
    }
}
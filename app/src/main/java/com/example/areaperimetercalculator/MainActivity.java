package com.example.areaperimetercalculator;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
{
    //rectangle and square
    //area = length and width
    //perimeter = length and width

    //circle
    //area = radius
    //circumference = radius

    //triangle
    //area = base and height
    //perimeter = side a, side b, side c (Pythagorean)

    //For square/rectangle container
    EditText et_j_length;
    EditText et_j_width;
    ConstraintLayout cont_j_squareRectView;
    Spinner sp_j_shapes;
    TextView tv_j_areaPerimeter;
    ArrayAdapter <String> spinnerAdapter;

    //For circle container
    EditText et_j_radius;
    TextView tv_j_areaPerimeterCircle;
    ConstraintLayout cont_j_circleView;

    //For triangle container
    ConstraintLayout cont_j_triangleView;
    EditText et_j_sideA;
    EditText et_j_sideB;
    EditText et_j_sideC;
    TextView tv_j_areaPerimeterTriangle;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Square and Rectangle
        et_j_length = findViewById(R.id.et_v_length);
        et_j_width = findViewById(R.id.et_v_width);
        cont_j_squareRectView = findViewById(R.id.cont_v_squareRectangle);
        sp_j_shapes = findViewById(R.id.sp_v_shapes);
        tv_j_areaPerimeter = findViewById(R.id.tv_v_computedValues);

        //Circle
        et_j_radius = findViewById(R.id.et_v_radius);
        tv_j_areaPerimeterCircle = findViewById(R.id.tv_v_computedAreaPerimeterCircle);
        cont_j_circleView = findViewById(R.id.cont_v_circle);

        //Triangle
        cont_j_triangleView = findViewById(R.id.cont_v_triangle);
        et_j_sideA = findViewById(R.id.et_v_sideA);
        et_j_sideB = findViewById(R.id.et_v_sideB);
        et_j_sideC = findViewById(R.id.et_v_sideC);
        tv_j_areaPerimeterTriangle = findViewById(R.id.tv_v_computedValuesTriangle);

        //Because we are making a simple drop down menu (spinner) that will only contain
        //strings as options. We can use a string array with the built-in array adapter
        //to populate the spinner

        //We will use this to populate our spinner
        String[] shapes = new String[]{"Square", "Rectangle", "Circle", "Triangle"};

        //adapter is what links the java code with the xml for the spinner
        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,shapes);

        sp_j_shapes.setAdapter(spinnerAdapter);

        setupSpinnerChangeListener();
        textChangeListenerSquareRect();
        textChangeListenerCircle();
        textChangeListenerTriangle();

    }
    //--------------------
    //Functions
    //--------------------
    public void setupSpinnerChangeListener()
    {
        sp_j_shapes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                //0-3 indexes
                if(position == 0)
                {
                    //square
                    cont_j_squareRectView.setVisibility(View.VISIBLE);
                    //not showing for circle and triangle
                    hideConstraintView(cont_j_circleView);
                    hideConstraintView(cont_j_triangleView);
                }
                else if(position == 1)
                {
                    //rectangle
                    cont_j_squareRectView.setVisibility(View.VISIBLE);
                    //not showing for circle and triangle
                    hideConstraintView(cont_j_circleView);
                    hideConstraintView(cont_j_triangleView);
                }
                else if(position == 2)
                {
                    //circle
                    cont_j_circleView.setVisibility(View.VISIBLE);
                    //not showing for square and rectangle and triangle
                    hideConstraintView(cont_j_squareRectView);
                    hideConstraintView(cont_j_triangleView);
                }
                else if(position == 3)
                {
                    //triangle
                    cont_j_triangleView.setVisibility(View.VISIBLE);
                    //not showing for square and rectangle
                    //hide constraint view is a function we made below
                    hideConstraintView(cont_j_squareRectView);
                    hideConstraintView(cont_j_circleView);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }

    public void hideConstraintView(ConstraintLayout cl)
    {
        cl.setVisibility(View.INVISIBLE);
    }

    public void textChangeListenerSquareRect()
    {
        //Width
        et_j_width.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable s)
            {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                setAreaAndPerimeterSquareRect(et_j_length.getText().toString(), et_j_width.getText().toString());
            }
        });

        //Length
        et_j_length.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable s)
            {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                //This will update the GUI as the user inputs numbers for L & W
                setAreaAndPerimeterSquareRect(et_j_length.getText().toString(), et_j_width.getText().toString());
            }
        });
    }

    public void textChangeListenerCircle()
    {
        et_j_radius.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable s)
            {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                setAreaPerimeterCircle(et_j_radius.getText().toString());
            }
        });
    }

    public void textChangeListenerTriangle()
    {
        //Side A
        et_j_sideA.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                setAreaPerimeterTriangle(et_j_sideA.getText().toString(), et_j_sideB.getText().toString(), et_j_sideC.getText().toString());
            }
        });

        //Side B
        et_j_sideB.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable s)
            {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                setAreaPerimeterTriangle(et_j_sideA.getText().toString(), et_j_sideB.getText().toString(), et_j_sideC.getText().toString());
            }
        });

        //Side C
        et_j_sideC.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable s)
            {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                setAreaPerimeterTriangle(et_j_sideA.getText().toString(), et_j_sideB.getText().toString(), et_j_sideC.getText().toString());
            }
        });
    }

    public void setAreaAndPerimeterSquareRect(String lengthS, String widthS)
    {
        if(lengthS.isEmpty() || widthS.isEmpty())
        {
            return;
        }

        //convert the string to a double so we can do math
        double length = Double.parseDouble(lengthS);
        double width = Double.parseDouble(widthS);

        double area = length * width;

        double perimeter = length + length + width + width;

        tv_j_areaPerimeter.setText("Area = " + area + "\n Perimeter = " + perimeter);
    }

    public void setAreaPerimeterCircle(String radiusS)
    {
        if(radiusS.isEmpty())
        {
            return;
        }

        double radius = Double.parseDouble(radiusS);
        double pi = Math.PI;

        double area = pi * Math.pow(radius, 2);

        double perimeter = 2 * pi * radius;

        tv_j_areaPerimeterCircle.setText("Area = " + area + "\n Perimeter = " + perimeter);
    }

    public void setAreaPerimeterTriangle(String aS, String bS, String cS)
    {
        if(aS.isEmpty() || bS.isEmpty() || cS.isEmpty())
        {
            return;
        }

        double sideA = Double.parseDouble(aS);
        double sideB = Double.parseDouble(bS);
        double sideC = Double.parseDouble(cS);

        //Perimeter
        double perimeter = sideA + sideB + sideC;
        double s = perimeter / 2.0;

        //Area
        double area = Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
        tv_j_areaPerimeterTriangle.setText("Area = " + area + "\n Perimeter = " + perimeter);
    }
}


